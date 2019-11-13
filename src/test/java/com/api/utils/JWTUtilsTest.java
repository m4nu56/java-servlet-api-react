package com.api.utils;

import org.junit.jupiter.api.Test;

class JWTUtilsTest {

	@Test
	void generateSecretKey() {
		System.out.println(new JWTUtils(PlatformConstants.INSTANCE.getJwtConfig()).generateSecretKey(PlatformConstants.INSTANCE.getJwtConfig().getSignatureAlgorithm()));
	}
}
