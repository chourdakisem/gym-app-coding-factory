package gr.aueb.cf.gymapp.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${access.secret.key}")
    private String accessSecret;

    @Value("${refresh.secret.key}")
    private String refreshSecret;

    public String generateAccessToken(UserDetails userDetails) {
        String token = Jwts
                .builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .signWith(signInKey(accessSecret))
                .compact();
        return token;
    }

    public String generateRefreshToken(UserDetails userDetails) {
        String token = Jwts
                .builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(signInKey(refreshSecret))
                .compact();
        return token;
    }

    private SecretKey signInKey(String secret) {
        byte[] keyBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String secret, String token) {
        Claims claims = Jwts
                .parser()
                .verifyWith(signInKey(secret))
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims;
    }

    private <T> T extractClaim(String secret, String token, Function<Claims, T> resolver) {
        var claims = extractAllClaims(secret,token);
        return resolver.apply(claims);
    }

    public String extractUsername(String secret, String token) {
        String username = extractClaim(secret, token, (claims) -> claims.getSubject());
        return username;
    }

    private Date extractExpiration(String secret, String token) {
        Date expiration = extractClaim(secret, token, (claims) -> claims.getExpiration());
        return expiration;
    }

    public boolean isValid(String secret, String token, UserDetails userDetails) {
        String username = extractUsername(secret, token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(secret, token);
    }

    private boolean isTokenExpired(String secret, String token) {
        Date expiration = extractExpiration(secret, token);
        return expiration.before(new Date());
    }
}
