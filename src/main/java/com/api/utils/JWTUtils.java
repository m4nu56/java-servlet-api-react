package com.api.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;


public class JWTUtils {

	public static final String AUTHENTICATION_SCHEME = "Bearer";

	private JWTConfig jwtConfig;

	public JWTUtils(JWTConfig jwtConfig) {
		this.jwtConfig = jwtConfig;
	}

	public JWTUtils() {

	}

	/**
	 * On utilise ça juste une fois via un main pour générer la clé qui sera utiliser pour chiffrer les JWT
	 * On retourne la clé générée en Base64
	 */
	public String generateSecretKey(SignatureAlgorithm signatureAlgorithm) {
		// Create a new secretKey
		SecretKey secretKey = Keys.secretKeyFor(signatureAlgorithm); //or HS384 or HS512
		// get base64 encoded version of the key
		return Base64.getEncoder().encodeToString(secretKey.getEncoded());
	}

	/**
	 * On Base64 decode la clé de chiffrement puis on return une SecretKey à partir de cette clé et de l'algo de chiffrement
	 */
	public SecretKey decodeSecretKeyFromBase64EncodedKey(String base64EncodedKey, SignatureAlgorithm signatureAlgorithm) {
		// decode the base64 encoded string
		byte[] decodedKey = Base64.getDecoder().decode(base64EncodedKey);
		// rebuild key using SecretKeySpec
		return new SecretKeySpec(decodedKey, signatureAlgorithm.getJcaName());
	}


	/**
	 * Crée un token JWT avec:
	 * - expiration: new Date + expirationMillis
	 * - issuer: issuer
	 * - audience: audience
	 * - claims: mapClaims
	 */
	public String createJWT(long expirationMillis, String issuer, String audience, Map<String, String> mapClaims) {

		// On utilise la clé générée pour nos échanges
		SecretKey key = decodeSecretKeyFromBase64EncodedKey(jwtConfig.getEncodedKey(), jwtConfig.getSignatureAlgorithm());

		// Construction du JWT
		long timestamp = System.currentTimeMillis();

		JwtBuilder jwtBuilder = Jwts.builder()
			.setIssuedAt(new Date(timestamp))
			.setExpiration(new Date(timestamp + expirationMillis)) // on set la expiration date
			.setIssuer(issuer)
			.setAudience(audience);

		for (Map.Entry<String, String> entrySet : mapClaims.entrySet()) {
			jwtBuilder.claim(entrySet.getKey(), entrySet.getValue());
		}

		return jwtBuilder.signWith(key)
			.compact();

	}

	/**
	 * Parse le jwt chiffré en paramètre
	 * 1. Déchiffrement avec la clé de chiffrement et l'algo défini avec les attributs `encodedKey` et `signatureAlgorithm` de la classe JWTUtils
	 * 2. Contrôle que l'issuer, l'audience et la expirationDate sont valides
	 * <p>
	 * Retourne le Jws<Claims> si tout est valide
	 * Throw une JwtException (Runtime) si problème pour décoder le contenu ou si l'issuer ou l'audience ne correspond pas aux valeurs attendues.
	 */
	public Jws<Claims> parseJWT(String jwsString, String issuerExpected, String audienceExpected) {

		// On utilise la clé générée pour nos échanges entre le portail et les applis clients
		SecretKey key = decodeSecretKeyFromBase64EncodedKey(jwtConfig.getEncodedKey(), jwtConfig.getSignatureAlgorithm());

		return Jwts.parser()
			.requireIssuer(issuerExpected)
			.requireAudience(audienceExpected)
			.setSigningKey(key)
			.parseClaimsJws(jwsString);
	}

	/**
	 * Check if the Authorization header is valid
	 * It must not be null and must be prefixed with "Bearer" plus a whitespace
	 * The authentication scheme comparison must be case-insensitive
	 *
	 * @param authorizationHeader
	 * @return
	 */
	public boolean isTokenBasedAuthentication(String authorizationHeader) {
		return authorizationHeader != null && authorizationHeader.toLowerCase()
			.startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
	}

}
