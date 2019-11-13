package com.api.utils;

import io.jsonwebtoken.SignatureAlgorithm;

import java.io.Serializable;

class JWTConfig implements Serializable {

	private SignatureAlgorithm signatureAlgorithm;
	private String encodedKey;

	JWTConfig(SignatureAlgorithm signatureAlgorithm, String encodedKey) {
		this.signatureAlgorithm = signatureAlgorithm;
		this.encodedKey = encodedKey;
	}

	SignatureAlgorithm getSignatureAlgorithm() {
		return signatureAlgorithm;
	}

	String getEncodedKey() {
		return encodedKey;
	}

}
