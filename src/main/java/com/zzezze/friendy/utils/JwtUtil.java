package com.zzezze.friendy.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {
    private final Algorithm algorithm;

    public JwtUtil(String secret) {
        this.algorithm = Algorithm.HMAC256(secret);
    }

    public String encode(String username) {
        return JWT.create()
                .withClaim("username", username)
                .sign(algorithm);
    }

    public String decode(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT verify = verifier.verify(token);

        return verify.getClaim("username").asString();
    }
}
