package uz.pdp.appshortlink.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.appshortlink.config.AppProperties;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final AppProperties appProperties;

    public String generateToken(String username) {
        Integer expireDays = appProperties.getJwt().getExpireDays();
        Date expireDate = new Date(System.currentTimeMillis() + expireDays * 24 * 60 * 60 * 1000);
        return Jwts.builder()
                .expiration(expireDate)
                .subject(username)
                .issuedAt(new Date())
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        String secretKey = appProperties.getJwt().getSecretKey();
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String getSubject(String token) {
        return ((Claims)
                Jwts.parser()
                        .verifyWith(getKey())
                        .build()
                        .parse(token)
                        .getPayload()
        ).getSubject();
    }
}
