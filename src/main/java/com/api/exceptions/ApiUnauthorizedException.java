package com.api.exceptions;

import javax.ws.rs.core.Response;

/**
 * 401 Unauthorized
 * the HTTP status code for authentication errors
 * L'utilisateur n'est pas authentifié, à ne pas confondre avec des problèmes de permissions et l'erreur 403 Forbidden
 */
public class ApiUnauthorizedException extends ApiException {

	public ApiUnauthorizedException(TypeApiErrorCode typeApiErrorCode, String developerMessage) {
		super(
			Response.Status.UNAUTHORIZED.getStatusCode(),
			typeApiErrorCode.getErrorCode(),
			"invalid token",
			developerMessage
		);

	}

}
