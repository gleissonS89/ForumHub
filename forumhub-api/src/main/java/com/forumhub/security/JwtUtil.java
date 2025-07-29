package com.forumhub.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtUtil {

    private String secret = "segredoSuperSecreto";

    public String gerarToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String validarToken(String token) {
        try {
            return Jwst.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
