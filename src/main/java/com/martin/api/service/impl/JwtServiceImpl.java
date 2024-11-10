package com.martin.api.service.impl;

import com.martin.api.persistence.model.User;
import com.martin.api.service.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements IJwtService {

  @Value("${jwt.secret.key}")
  private String JWT_SECRET;

  @Value("${jwt.time.expiration}")
  private Long JWT_EXPIRATION;

  @Override
  public String generateToken(User user) {
    Date issueAt = new Date(System.currentTimeMillis());
    Date expiration = new Date(issueAt.getTime() + JWT_EXPIRATION);
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", user.getId());
    return Jwts.builder()
        .claims(claims)
        .subject(user.getUsername())
        .issuedAt(issueAt)
        .expiration(expiration)
        .signWith(generateKey())
        .compact();
  }

  @Override
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  @Override
  public boolean isTokenValid(String token, User user) {
    final String username = extractUsername(token);
    return username.equals(user.getUsername()) && !isTokenExpired(token);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
    final Claims claims = Jwts.parser()
        .verifyWith(generateKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
    return claimResolver.apply(claims);
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private SecretKey generateKey() {
    final byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
