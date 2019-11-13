package com.api.security;

import com.api.exceptions.ApiUnauthorizedException;
import com.api.utils.PlatformConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import utils.mocker.MockAuthorizationJWT;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;

import static com.api.utils.JWTUtils.AUTHENTICATION_SCHEME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthenticationFilterTest {

	@Test
	void onTestValidateHttpHeadersAuthorization_withoutAuthorizationHeader_shouldThrowApiUnauthorizedException() {
		ContainerRequestContext containerRequestContext = Mockito.mock(ContainerRequestContext.class);
		Mockito.doReturn(null).when(containerRequestContext).getHeaderString(HttpHeaders.AUTHORIZATION);
		assertThrows(
			ApiUnauthorizedException.class,
			() -> new AuthorizationValidator(false).validate(containerRequestContext)
		);
	}

	@Test
	void onTestValidateHttpHeadersAuthorization_withBadAuthScheme_shouldThrowApiUnauthorizedException() {
		ContainerRequestContext containerRequestContext = Mockito.mock(ContainerRequestContext.class);
		Mockito.doReturn("NotBearer ").when(containerRequestContext).getHeaderString(HttpHeaders.AUTHORIZATION);
		assertThrows(
			ApiUnauthorizedException.class,
			() -> new AuthorizationValidator(false).validate(containerRequestContext)
		);
	}

	@Test
	void onTestValidateHttpHeadersAuthorization_withValidJWT_shouldReturnsClaims() throws ApiUnauthorizedException {
		ContainerRequestContext containerRequestContext = Mockito.mock(ContainerRequestContext.class);

		Mockito.doReturn(MockAuthorizationJWT.mockAuthorizationJWT()).when(containerRequestContext).getHeaderString(HttpHeaders.AUTHORIZATION);
		Jws<Claims> claimsJws = new AuthorizationValidator(false).validate(containerRequestContext);
		assertEquals(PlatformConstants.AUDIENCE, claimsJws.getBody().getAudience());
		assertEquals(PlatformConstants.ISSUER, claimsJws.getBody().getIssuer());
		assertEquals("myLogin", claimsJws.getBody().get("login"));
		assertEquals("myAccount", claimsJws.getBody().get("compte"));
	}

	@Test
	void onTestValidateHttpHeadersAuthorization_withInvalidJWT_shouldThrowApiUnauthorizedException() {
		ContainerRequestContext containerRequestContext = Mockito.mock(ContainerRequestContext.class);
		Mockito.doReturn(AUTHENTICATION_SCHEME + " " + "bad_token").when(containerRequestContext).getHeaderString(HttpHeaders.AUTHORIZATION);
		assertThrows(
			ApiUnauthorizedException.class,
			() -> new AuthorizationValidator(false).validate(containerRequestContext)
		);
	}

	@Test
	void onTestValidateHttpHeadersAuthorization_withJWTMissingLogin_shouldThrowApiUnauthorizedException() {
		ContainerRequestContext containerRequestContext = Mockito.mock(ContainerRequestContext.class);
		Mockito.doReturn(MockAuthorizationJWT.mockAuthorizationJWT(null, null)).when(containerRequestContext).getHeaderString(HttpHeaders.AUTHORIZATION);
		assertThrows(
			ApiUnauthorizedException.class,
			() -> new AuthorizationValidator(false).validate(containerRequestContext)
		);
	}
}
