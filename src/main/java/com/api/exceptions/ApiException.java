package com.api.exceptions;

public class ApiException extends Exception {

	/**
	 * contains redundantly the HTTP status of the response sent back to the client in case of error, so that
	 * the developer does not have to look into the response headers. If null a default
	 */
	private Integer status;

	/**
	 * application specific error code
	 */
	private int code;

	/**
	 * link documenting the exception
	 */
	private String link;

	/**
	 * detailed error description for developers
	 */
	private String developerMessage;

	ApiException(int status, int code, String message, String developerMessage) {
		super(message);
		this.status = status;
		this.code = code;
		this.developerMessage = developerMessage;
	}

	public ApiException(ErrorMessage errorMessage) {
		super(errorMessage.getMessage());
		this.status = errorMessage.getStatus();
		this.code = errorMessage.getCode();
		this.developerMessage = errorMessage.getDeveloperMessage();
		this.link = errorMessage.getLink();
	}

	public ApiException() {
	}

	int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}


}
