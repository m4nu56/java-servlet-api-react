package com.api.exceptions;

import java.io.Serializable;

public enum TypeApiErrorCode implements Serializable {

	GENERIC_APP_ERROR_CODE(5001),
	NETWORK_ERROR_CODE(5002),
	MISSING_HTTP_HEADERS(5003),
	BAD_HTTP_HEADERS(5004),

	MISSING_PARAMETER(4000),
	PARAMETER_FORMAT_INVALID(4001),
	INVALID_TOKEN(4002),
	BAD_PERMISSION(4003),

	DATABASE_INSERT(3004),
	OBJECT_NOT_FOUND(3003),
	DATABASE_DELETE(3002),
	BUSINESS_ERROR(3001),
	;

	private int errorCode;

	TypeApiErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
