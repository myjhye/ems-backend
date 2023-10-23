package net.javaguides.ems.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private Long jwtExpirationMillis; // 수정: 밀리초로 저장

    // jwt token 생성
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationMillis); // 수정: 밀리초로 계산
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate) // 수정: 만료 날짜 설정
                .signWith(key())
                .compact();
        return token;
    }

    private Key key() {

        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }



    // jwt token에서 username(유저이름) 가져오기
    public String getUsername(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();


        String username = claims.getSubject();

        return username;
    }




    // validate jwt token
    public boolean validateToken(String token) {

        Jwts.parser()
                .setSigningKey(key())
                .build()
                .parse(token);

        return true;
    }


}
