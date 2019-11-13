package com.api.security;

import com.api.exceptions.ApiUnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

/**
 * Filtre Jersey executé tout le temps en PreMatching avant meme que la request soit bindée
 * sur un endpoint afin d'extraire le user du token et de pouvoir faire l'injection de dépendance
 */
@Provider
@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class PreMatchingCurrentUserFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {

        // Authentification avec la validation du token JWT (chiffrement, durée de validité, issuer/audience)
        // récupération du login et codeCompte dans le token JWT
        // Ajout du user dans les SecurityContext afin de pouvoir le récupérer dans les end points de l'API
        try {


            Jws<Claims> jws = new AuthorizationValidator(false).validate(requestContext);

            AppSecurityContext appSecurityContext = new AppSecurityContextFactory(
                    requestContext.getSecurityContext(),
                    (String) jws.getBody().get("login"),
                    (String) jws.getBody().get("compte")
            ).build();

            requestContext.setSecurityContext(appSecurityContext);

        }
        catch (ApiUnauthorizedException ignored) {
        }

    }

}
