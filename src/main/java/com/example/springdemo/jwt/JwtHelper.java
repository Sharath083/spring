package com.example.springdemo.jwt;

import com.example.springdemo.exception.CommonException;
import com.fasterxml.jackson.core.JsonFactory;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.checkerframework.checker.units.qual.UnknownUnits;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtHelper {
    Gson gson = new Gson();

    public static final long JWT_TOKEN_VALIDITY = 1000 ;
    private String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

    public JwtRequest getUsernameFromToken(String token) {
        System.out.println(token+"in getUsernameFromToken");
        return gson.fromJson(getClaimFromToken(token, Claims::getSubject),JwtRequest.class);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {

        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public UUID getUserId(){
        var token=(String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return UUID.fromString(getClaimFromToken(token, Claims::getId));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(String data,String id) {
        System.out.println(data+"in generateToken");
        Map<String, Object> claims = new HashMap<>();
        JwtRequest request = new JwtRequest();
        request.setUsername(data);
        request.setUserId(id);
        return doGenerateToken(claims, request);
    }

    private String doGenerateToken(Map<String, Object> claims, JwtRequest subject) {
        System.out.println("------------------> in do generate token");
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(gson.toJson(subject))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token).getUsername();
        if(isTokenExpired(token)){
            throw new CommonException("0001","Session has expired,please login again");
        }
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }



}
