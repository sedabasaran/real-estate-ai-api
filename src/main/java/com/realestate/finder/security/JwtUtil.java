package com.realestate.finder.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.realestate.finder.entity.User;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

	private final Key SIGNING_KEY;
	private final long EXPIRATION_TIME;

	public JwtUtil(@Value("${app.jwtSecret}") String secret, @Value("${app.jwtExpirationMs}") long expirationTime) {
		this.SIGNING_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
		this.EXPIRATION_TIME = expirationTime;
	}

	public String generateToken(String email) {
		return Jwts.builder().setSubject(email).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SIGNING_KEY, SignatureAlgorithm.HS256).compact();
	}

	public String extractEmail(String token) {
		return getClaims(token).getSubject();
	}

	public boolean isTokenValid(String token) {
		return !isTokenExpired(token);
	}

	public boolean validateToken(String token, User user) {
		final String email = extractEmail(token);
		return email.equals(user.getEmail()) && !isTokenExpired(token);
	}

	private io.jsonwebtoken.Claims getClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(SIGNING_KEY).build().parseClaimsJws(token).getBody();
	}

	private boolean isTokenExpired(String token) {
		return getClaims(token).getExpiration().before(new Date());
	}

}
