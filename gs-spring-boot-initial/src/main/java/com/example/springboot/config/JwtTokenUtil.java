package com.example.springboot.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;
	
	private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
//	@Value("${jwt.secret}")
//	private String secret;
	
	SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	// récupère nom d'utilisateur du jéton jwt
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	// récupère date d'expiration du jéton jwt
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	// on a besoin du clé sécret pour pouvoir récupèrer des infos
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}
	
	// vérifie si le jéton a expiré
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	// génère jéton
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}
	
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
					.signWith(key).compact();
	}
	
	// valider jéton
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
