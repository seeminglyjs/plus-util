package com.source.plusutil.user.jwt;

import com.source.plusutil.config.PropertiesConfig;
import com.source.plusutil.user.dto.NewTokenRequestDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final PropertiesConfig config;

    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(String userName){
        return generateToken(new HashMap<>(), userName);
    }

    public String generateToken(Map<String, Object> extraClaims, String userName) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) //만료 하루 지정
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(Map<String, Object> extraClaims, String userName, Date IssueDate, Date ExpirationDate) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userName)
                .setIssuedAt(IssueDate)
                .setExpiration(ExpirationDate) //만료 하루 지정
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(NewTokenRequestDto newTokenRequestDto) {
        return Jwts
                .builder()
                .setClaims(newTokenRequestDto.getPayLoad())
                .setSubject(newTokenRequestDto.getUserName())
                .setIssuedAt(newTokenRequestDto.getIssueDate())
                .setExpiration(newTokenRequestDto.getExpirationDate()) //만료 하루 지정
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValidate(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenValidate(String token, String userName){
        final String userNameByToken = extractUserName(token);
        return (userName.equals(userNameByToken) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey()) //sing key는 jwt를 디지털 암호화 한다.
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(config.getJwtSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
