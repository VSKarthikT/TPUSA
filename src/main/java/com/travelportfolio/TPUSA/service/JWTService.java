package com.travelportfolio.TPUSA.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTService {

  private String secretKey = "";

  // KEY generator in constructor
  public JWTService() {
    try {
      KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
      SecretKey sk = keyGen.generateKey();
      secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());

    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Error in key generation", e);
    }
  }

  public String generateToken(String email, String role) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("role", role);
    return Jwts.builder()
        .claims()
        .add(claims)
        .subject(email)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
        .and()
        .signWith(getkey())
        .compact();
  }

  private SecretKey getkey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String extractUserName(String token) {
    // extract the username from jwt token
    return extractClaim(token, Claims::getSubject);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
    final Claims claims = extractAllClaims(token);
    return claimResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
        .verifyWith(getkey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    // System.out.println("In jwt service token is " + token);
    final String userName = extractUserName(token);
    // System.out.println("username in jwt validate token is " + userName);
    // System.out.println("userdetails in jwt validate token is " +
    // userDetails.getUsername());
    // System.out.println("token expired val" + isTokenExpired(token) +
    // userName.equals(userDetails.getUsername()));
    return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

}
