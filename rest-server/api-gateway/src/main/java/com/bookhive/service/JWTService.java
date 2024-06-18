package com.bookhive.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String jwtSigningKey;
//    @Value("${jwt.expiration}")
//    private String expiration;
    public static final long JWT_TOKEN_VALIDITY = 7 * 24 * 60 * 60;
    private Key key;


    @PostConstruct
    public void initKey() {
        this.key = Keys.hmacShaKeyFor(jwtSigningKey.getBytes());
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public boolean isExpired(String token) {
        try {
            return getClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }




}
