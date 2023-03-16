package com.example.ottokeng.global.security.jwt;

import com.example.ottokeng.global.security.authentication.CustomUserDetailsService;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@RequiredArgsConstructor
@Component
@Getter
public class JwtTokenProvider {

    @Value("${jwt.token.secretKey}")
    private String secretKey;

    private final long TOKEN_VALIDATION_EXPIREDTIME = 1000L * 60 * 60 * 3;
    private final long REFRESHTOKEN_VALIDATION_EXPIREDTIME = TOKEN_VALIDATION_EXPIREDTIME * 8 * 90;

    private final CustomUserDetailsService customUserDetailsService;

    // 암호화된 키 값 가져오기
    private Key getSignInKey(String secretKey) {
        byte[] bytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String createAccessToken(String oauthId) {
        Claims claims = Jwts.claims().setSubject(oauthId);

        Date date = new Date();
        Date validity = new Date(date.getTime() + TOKEN_VALIDATION_EXPIREDTIME);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(validity)
                .signWith(getSignInKey(secretKey), SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshToken(String oauthId){
        Claims claims = Jwts.claims().setSubject(oauthId);

        Date date = new Date();
        Date validity = new Date(date.getTime() + REFRESHTOKEN_VALIDATION_EXPIREDTIME);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(validity)
                .signWith(getSignInKey(secretKey), SignatureAlgorithm.HS256)
                .compact();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(getOAuthId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getOAuthId(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException, IllegalArgumentException, MalformedJwtException, UnsupportedJwtException, PrematureJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else{
            return bearerToken;
        }
    }

    public String resolveRefreshToken(String refreshToken){
//        String refreshToken = request.getHeader("RefreshToken");
        if(refreshToken != null && refreshToken.startsWith("Bearer ")){
            return refreshToken.substring(7);
        } else {
            return refreshToken;
        }
    }

    public boolean isTokenExpired(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();

        return expiration.before(new Date());
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public Long getExpiration(String token) {
        Date expiration = Jwts.parserBuilder().setSigningKey(secretKey.getBytes())
                .build().parseClaimsJws(token).getBody().getExpiration();

        Long now = new Date().getTime();

        return (expiration.getTime() - now);
    }

}