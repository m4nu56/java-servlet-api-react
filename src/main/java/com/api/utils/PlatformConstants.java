package com.api.utils;

import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Des constantes valorisées par la lecture du fichier .properties dans PropertiesUtils
 */
public enum PlatformConstants {
	INSTANCE;

	public static final String ISSUER = "ISSUER";
	public static final String AUDIENCE = "AUDIENCE";
	private JWTConfig jwtConfig = new JWTConfig(SignatureAlgorithm.HS256, "YcUVnqnixeZlFttHXjbxc4CDd7QtQFgTybn/iicLW6I=");

	public JWTConfig getJwtConfig() {
		return jwtConfig;
	}

	public void setJwtConfig(JWTConfig jwtConfig) {
		if (this.jwtConfig != null)
			throw new RuntimeException("Update denied");
		this.jwtConfig = jwtConfig;
	}
}
