package com.mycarrental.security.jwt;

import com.mycarrental.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger logger  = LoggerFactory.getLogger(JwtUtils.class);


    @Value("${backendapi.app.jwtSecret}") // aplication.yml dosyasindan aldik
    private String jwtSecret;

    @Value("${backendapi.app.jwtExpirationMs}")
    private long jwtExpirationMs;

//Burada token uretiyoruz
    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject("" + (userDetails.getId())) // id yi aldik
                .setIssuedAt(new Date()) // date ekledik
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) //expiration ekledik
                .signWith(SignatureAlgorithm.HS512, jwtSecret) //secret ekledik
                .compact();
    }

    public Long getIdFromJwtToken(String token){
        return Long.parseLong(Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject());

    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
        } catch (SecurityException e) {
            logger.error("Invalid JWT signature : {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token : {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token expired : {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token unsupported : {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty : {}", e.getMessage());
        }
        return false;
    }

}
