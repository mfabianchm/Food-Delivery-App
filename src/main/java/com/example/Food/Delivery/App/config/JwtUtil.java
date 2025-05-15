package com.example.Food.Delivery.App.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import static java.security.KeyRep.Type.SECRET;


/*This class is a utility/helper for working with JWTs (JSON Web Tokens).
JwtUtil (or JwtUtils) is a custom utility class that you create yourself in your
Spring Boot project. It is not provided by Spring Security or Spring Boot.*/
@Component
public class JwtUtil {


    //injects the value of the property jwt.secret from application.properties into the secretKeyEncoded variable.
    @Value("${jwt.secret}")
    private String secretKeyEncoded;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        byte[] decodedKey = Base64.getDecoder().decode(secretKeyEncoded);
        this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
    }


    /*UserDetails is not automatically injected into generateToken(...),
    you pass it manually after successful authentication.*/
    //This method Creates a signed JWT token containing user data
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1h
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    //This method Extracts the username from the JWT
    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }

    //This method Validates token by matching the username
    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername());
    }
}
