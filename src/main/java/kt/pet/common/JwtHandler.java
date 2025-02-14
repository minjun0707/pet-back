package kt.pet.common;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtHandler {
    private final String type = "Bearer ";

    public String createToken(String base64EncodedKey, String subject, long maxAgeSeconds) {
        Date now = new Date();
        SecretKey secretKey = getSecretKey(base64EncodedKey); // ✅ 시크릿 키 변환

        return type + Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + maxAgeSeconds * 1000L))
                .signWith(secretKey, Jwts.SIG.HS256) // ✅ 최신 버전 방식
                .compact();
    }

    public String extractSubject(String base64EncodedKey, String token) {
        return parse(base64EncodedKey, token).getPayload().getSubject(); // ✅ getBody() → getPayload()
    }

    public boolean validate(String base64EncodedKey, String token) {
        try {
            parse(base64EncodedKey, token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private Jws<Claims> parse(String base64EncodedKey, String token) {
        SecretKey secretKey = getSecretKey(base64EncodedKey); // ✅ SecretKey 변환
        return Jwts.parser() // ✅ `parserBuilder()` 제거
                .verifyWith(secretKey) // ✅ `setSigningKey()` → `verifyWith()`
                .build()
                .parseSignedClaims(untype(token)); // ✅ `parseClaimsJws()` → `parseSignedClaims()`
    }

    private String untype(String token) {
        return token.substring(type.length());
    }

    private SecretKey getSecretKey(String base64EncodedKey) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64EncodedKey)); // ✅ Base64 디코딩 후 SecretKey 변환
    }
}
