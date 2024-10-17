package com.arutha.utill;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Helper class for JWT.
 */
@Component
public class JwtHelper {
    private static final long EXPIRATION_TIME_LIMIT = 24 * 60 * 60 * 1000;

    SecretKey key = Jwts.SIG.HS256.key().build();

    /**
     * Generates JWT Token.
     *
     * @param user - user details
     * @return JWT Token
     */
    public String generateToken(UserDetails user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + EXPIRATION_TIME_LIMIT))
                .signWith(key)
                .compact();
    }

    public String fetchUserName(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }

    private Date fetchExpirationDate(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = fetchExpirationDate(token);
        return expiration.before(new Date());
    }

    public Boolean validateToken(String token, UserDetails user) {
        return user.getUsername().equals(fetchUserName(token))
                && !isTokenExpired(token);
    }
}
