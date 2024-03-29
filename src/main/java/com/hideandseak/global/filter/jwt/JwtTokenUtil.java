package com.hideandseak.global.filter.jwt;

import com.hideandseak.global.exception.JwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Duration;
import java.util.Date;

public class JwtTokenUtil {

    private static final long expiredTimeMs = 1000L * 9999999 * 99999999;

    public static String createAccessToken(String userAccount, String key){
        Claims claims = Jwts.claims();
        claims.put("userAccount", userAccount);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Duration.ofDays(30).toMillis()))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
    public static String createRefreshToken(String userAccount, String key) {
        return Jwts.builder()
                .claim("userAccount", userAccount)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Duration.ofDays(30).toMillis()))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
    public static String generateAccessTokenFromRefreshToken(String refreshToken, String key){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key).build()
                    .parseClaimsJws(refreshToken)
                    .getBody();
            String userAccount = claims.get("userAccount", String.class);
            return createAccessToken(userAccount, key);
        } catch (ExpiredJwtException e){
            throw JwtException.JWT_IS_EXPIRED;
        } catch (io.jsonwebtoken.JwtException e){
            throw JwtException.JWT_NOT_FOUND_EXCEPTION;
        }
    }
}