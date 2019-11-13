package com.api.exceptions;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe modèle pour les errors throwé par l'API
 */
@XmlRootElement
public class ErrorMessage {

	/**
	 * contains the same HTTP Status code returned by the server
	 */
	@XmlElement(name = "status")
	private
	int status;

	/**
	 * application specific error code
	 */
	@XmlElement(name = "code")
	private
	int code;

	/**
	 * message describing the error
	 */
	@XmlElement(name = "message")
	private
	String message;

	/**
	 * link point to page where the error message is documented
	 */
	@XmlElement(name = "link")
	private
	String link;

	/**
	 * extra information that might useful for developers
	 */
	@XmlElement(name = "developerMessage")
	private
	String developerMessage;


	public ErrorMessage(ApiException ex) {
		this.code = ex.getCode();
		this.status = ex.getStatus();
		this.message = ex.getMessage();
	}

	public ErrorMessage() {
	}

	public ErrorMessage(int status, int code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public int getStatus() {
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

	String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	String getDeveloperMessage() {
		return developerMessage;
	}

	void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "ErrorMessage{" +
			"status=" + status +
			", code=" + code +
			", message='" + message + '\'' +
			", developerMessage='" + developerMessage + '\'' +
			'}';
	}
}
