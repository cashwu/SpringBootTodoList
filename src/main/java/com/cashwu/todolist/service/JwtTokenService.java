package com.cashwu.todolist.service;

import io.jsonwebtoken.*;
import jakarta.security.auth.message.AuthException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author cash
 */
@Service
public class JwtTokenService implements Serializable {

    private static final long EXPIRATION_TIME = 30 * 60 * 1000;

    private static final String SECRET = "cash1234";

    public String generateToken() {

        return Jwts.builder()
                .setSubject("cash")
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public void validateToken(String token) throws AuthException {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);
        } catch (MalformedJwtException e) {
            throw new AuthException("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            throw new AuthException("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            throw new AuthException("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            throw new AuthException("JWT token compact of handler are invalid");
        }
    }
}
