package com.example.teemo_backend.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtil {


    public static String getUseremail(String token,String secretKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody().get("email" , String.class);
    }


    public static boolean isExpired(String token,String secretKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody().getExpiration().before(new Date());
    }

    public static String create(String email , String key , long expireTimeToMs){

        Claims claims = Jwts.claims();

        claims.put("email",email);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expireTimeToMs))
                .signWith(SignatureAlgorithm.ES256,key)
                .compact();



    }


}
