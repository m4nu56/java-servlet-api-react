package com.api.security;

import com.api.exceptions.ApiUnauthorizedException;
import com.api.exceptions.ErrorMessage;
import com.api.exceptions.TypeApiErrorCode;
import com.api.utils.JWTUtils;
import com.api.utils.PlatformConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.api.utils.JWTUtils.AUTHENTICATION_SCHEME;

class AuthorizationValidator {

    private boolean shouldAbortIfAuthFail;

    AuthorizationValidator(boolean shouldAbortIfAuthFail) {
        this.shouldAbortIfAuthFail = shouldAbortIfAuthFail;
    }

    /**
     * Vérifie que les Headers de la request HTTP contiennent bien le HttpHeaders.AUTHORIZATION
     * et que ce dernier est un token JWT valide.
     * la request est abortWith(ErrorMessage) si une erreur est remontée
     *
     * @throws ApiUnauthorizedException
     */
    Jws<Claims> validate(ContainerRequestContext requestContext) throws ApiUnauthorizedException {

        // Get the Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Validate the Authorization header
        if (!new JWTUtils()
                .isTokenBasedAuthentication(authorizationHeader)) {
            if (shouldAbortIfAuthFail) {
                abortWithUnauthorized(requestContext, TypeApiErrorCode.MISSING_HTTP_HEADERS, null);
            }
            throw new ApiUnauthorizedException(TypeApiErrorCode.MISSING_HTTP_HEADERS, "Missing header Authorization");
        }

        // Extract the token from the Authorization header
        String token = authorizationHeader
                .substring(AUTHENTICATION_SCHEME.length()).trim();

        try {

            Jws<Claims> jws = new JWTUtils(PlatformConstants.INSTANCE.getJwtConfig())
                    .parseJWT(token, PlatformConstants.ISSUER, PlatformConstants.AUDIENCE);

            String login = (String) jws.getBody().get("login");
            String compte = (String) jws.getBody().get("compte");

            if (StringUtils.isBlank(login) || StringUtils.isBlank(compte)) {
                throw new ApiUnauthorizedException(TypeApiErrorCode.INVALID_TOKEN, "Login ou Compte manquant");
            }

            return jws;

        }
        catch (JwtException | ApiUnauthorizedException e) {
            if (shouldAbortIfAuthFail) {
                abortWithUnauthorized(requestContext, TypeApiErrorCode.INVALID_TOKEN, e);
            }
            throw new ApiUnauthorizedException(TypeApiErrorCode.INVALID_TOKEN, e.getMessage());
        }

    }

    /**
     * Si erreur d'authentification on abort la request avec une ApiExceptionAuthentication wrappé dans un ErrorMessage en json.
     */
    private void abortWithUnauthorized(ContainerRequestContext requestContext, TypeApiErrorCode typeApiErrorCode, Exception e) {
        ErrorMessage errorMessage = new ErrorMessage(new ApiUnauthorizedException(typeApiErrorCode, e != null ? e.getMessage() : null));
        requestContext.abortWith(
                Response.status(errorMessage.getStatus())
                        .entity(errorMessage)
                        .type(MediaType.APPLICATION_JSON)
                        .build()
        );
    }

}
