package com.example.hotel.util;

import com.example.hotel.enums.RoleEnum;
import com.example.hotel.enums.UserTypeEnum;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  private final static String SECRET_KEY = "2024_CS5721"; // private key
  private final Map<String, Date> tokenBlacklist = new ConcurrentHashMap<>(); // black_list

  private final static int USER_EXPIRE = 600000;
  private final static int ADMINISTRATOR_EXPIRE = 3600000;
  // Create JWT Token
  public static String generateToken(String userId, byte userType) {
    int expireTime = 0;
    if (UserTypeEnum.USER.getCode().equals(userType)){
      expireTime = USER_EXPIRE;
    }else{
      expireTime = ADMINISTRATOR_EXPIRE;
    }
    return Jwts.builder()
        .setSubject(userId)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expireTime))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
  }

  // Validate JWT Token
  public Claims validateToken(String token) {
    if (isTokenBlacklisted(token)) {
      throw new JwtException("Token is blacklisted");
    }
    return Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();
  }

  // put Token into blacklist
  public void blacklistToken(String token) {
    Date expiration = validateToken(token).getExpiration();
    tokenBlacklist.put(token, expiration);
  }

  // check Token in blacklist
  public boolean isTokenBlacklisted(String token) {
    return tokenBlacklist.containsKey(token) && tokenBlacklist.get(token).after(new Date());
  }

  // Periodically clear expired blacklist tokens
  public void cleanupBlacklist() {
    tokenBlacklist.entrySet().removeIf(entry -> entry.getValue().before(new Date()));
  }

  public static String getUserIdFromToken(String token) {
    try {
      Claims claims = Jwts.parser()
          .setSigningKey(SECRET_KEY)
          .parseClaimsJws(token)
          .getBody();
      return claims.getSubject(); // get subject from token(userId)
    } catch (SignatureException e) {
      // token 签名不匹配，可能被篡改
      return null;
    } catch (Exception e) {
      // 处理其他异常
      return null;
    }
  }
}

