package com.example.hotel.util;

import com.example.hotel.enums.UserTypeEnum;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JwtUtil {

  private static final String SECRET_KEY = "2024_CS5721"; // private key
  private static final byte[] SECRET_KEY_BYTES = Base64.getEncoder().encode(SECRET_KEY.getBytes());
  private final Map<String, Date> tokenBlacklist = new ConcurrentHashMap<>(); // black_list

  private static final int USER_EXPIRE = 600000;
  private static final int ADMINISTRATOR_EXPIRE = 3600000;

  // Create JWT Token
  public static String generateToken(String userId, byte userType) {
    int expireTime = userType == UserTypeEnum.USER.getCode() ? USER_EXPIRE : ADMINISTRATOR_EXPIRE;
    return Jwts.builder()
        .setSubject(userId)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expireTime))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY_BYTES)
        .compact();
  }

  // Validate JWT Token
  public Claims validateToken(String token) {
    if (isTokenBlacklisted(token)) {
      throw new JwtException("Token is blacklisted");
    }
    try {
      return Jwts.parser()
          .setSigningKey(SECRET_KEY_BYTES)
          .parseClaimsJws(token)
          .getBody();
    } catch (ExpiredJwtException e) {
      throw new JwtException("Token has expired", e);
    } catch (SignatureException e) {
      throw new JwtException("Invalid JWT signature", e);
    } catch (Exception e) {
      throw new JwtException("JWT parsing error", e);
    }
  }

  // Put token into blacklist
  public void blacklistToken(String token) {
    Date expiration = validateToken(token).getExpiration();
    tokenBlacklist.put(token, expiration);
  }

  // Check token in blacklist
  public boolean isTokenBlacklisted(String token) {
    return tokenBlacklist.containsKey(token) && tokenBlacklist.get(token).after(new Date());
  }

  // Periodically clear expired blacklist tokens
  @Scheduled(fixedRate = 3600000)
  public void cleanupBlacklist() {
    tokenBlacklist.entrySet().removeIf(entry -> entry.getValue().before(new Date()));
  }

  public String getUserIdFromToken(String token) {
    try {
      if (StringUtils.isBlank(token) || !token.startsWith("Bearer ")) {
        return StringUtils.EMPTY;
      }
      token = token.substring(7); // Remove "Bearer " prefix
      Claims claims = validateToken(token);
      return claims.getSubject(); // Get subject from token (userId)
    } catch (SignatureException e) {
      return StringUtils.EMPTY;
    } catch (Exception e) {
      return StringUtils.EMPTY;
    }
  }
}

