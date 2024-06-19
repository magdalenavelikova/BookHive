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
    public static final long JWT_TOKEN_VALIDITY = 30 * 24 * 60 * 60;
    private Key key;

    @PostConstruct
    public void initKey() {
        this.key = Keys.hmacShaKeyFor(jwtSigningKey.getBytes());
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(token).getBody();
    }

    public Date getExpirationDate(String token) {
        return getClaims(token).getExpiration();
    }

    public String generate(String userId, String username, String role, String tokenType) {
        Map<String, String> claims = Map.of("id", userId, "username", username, "role", role);
        long expMillis = "ACCESS".equalsIgnoreCase(tokenType)
                ? JWT_TOKEN_VALIDITY
                : JWT_TOKEN_VALIDITY * 2;

        final Date now = new Date();
        final Date exp = new Date(now.getTime() + expMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(claims.get("id"))
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key)
                .compact();
    }

    private boolean isExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }


}
