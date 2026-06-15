
package com.ps05.analyticsbackend.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private static final String SECRET =
            "analyticsdashboardsecretkeyanalyticsdashboardsecretkey";

    private static final Key key =
            new SecretKeySpec(
                    SECRET.getBytes(),
                    SignatureAlgorithm.HS256.getJcaName());

    public static String generateToken(String username) {

        return Jwts.builder()

                .setSubject(username)

                .setIssuedAt(new Date())

                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60))

                .signWith(key)

                .compact();
    }
}