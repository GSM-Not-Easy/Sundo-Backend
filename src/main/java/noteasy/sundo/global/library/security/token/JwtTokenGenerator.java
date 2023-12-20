package noteasy.sundo.global.library.security.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.auth.dto.TokenDto;
import noteasy.sundo.global.library.security.principle.AuthDetailsService;
import noteasy.sundo.queryfactory.user.Authority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Component
@RequiredArgsConstructor
public class JwtTokenGenerator {

    private final AuthDetailsService authDetailsService;
    private final JwtProperties jwtProperties;

    public TokenDto.Response generate(Long userId, Authority authority) {
        String accessToken = generateAccessToken(userId, jwtProperties.getAccessSecret(), authority);
        String refreshToken = generateRefreshToken(userId, jwtProperties.getRefreshSecret(), authority);
        LocalDateTime accessExp = getAccessExp();
        LocalDateTime refreshExp = getRefreshExp();
        return new TokenDto.Response(accessToken, refreshToken, accessExp, refreshExp);
    }

    private String generateAccessToken(Long userId, String accessSecret, Authority authority) {
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(accessSecret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .setSubject(userId.toString())
                .claim("type", JwtProperties.Properties.accessType)
                .claim(JwtProperties.Properties.roleType, authority)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getAccessExp()))
                .compact();
    }

    private String generateRefreshToken(Long userId, String refreshSecret, Authority authority) {
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(refreshSecret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .setSubject(userId.toString())
                .claim("type", JwtProperties.Properties.refreshType)
                .claim(JwtProperties.Properties.roleType, authority)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getRefreshExp()))
                .compact();
    }

    private LocalDateTime getAccessExp() {
        return LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp());
    }

    private LocalDateTime getRefreshExp() {
        return LocalDateTime.now().plusSeconds(jwtProperties.getRefreshExp());
    }
}