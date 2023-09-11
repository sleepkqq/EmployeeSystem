package com.sleepkqq.employeesystem.controller;

import com.sleepkqq.employeesystem.model.Developer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {
    private static final String SECRET_KEY = "developer_key"; // Замените на ваш секретный ключ
    private static final long EXPIRATION_TIME = 86400000; // 24 часа в миллисекундах (или настройте по вашим требованиям)

    public static String generateToken(String email) {
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
