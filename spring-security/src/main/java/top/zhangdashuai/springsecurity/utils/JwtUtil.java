package top.zhangdashuai.springsecurity.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author zhangdashuai
 */
public class JwtUtil {

    /**
     * 有效期
     */
    public static final Long JWT_TTL = 60 * 60 * 1000L;

    /**
     * 设置秘钥明文
     */
    public static final String JWT_KEY = "zhangdashuai";

    private static String createJwt() {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, JWT_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TTL))
                .compact();
    }

    public static Claims parseJwt(String jwt) {
        return Jwts.parser()
                .setSigningKey(JWT_KEY)
                .parseClaimsJws(jwt)
                .getBody();
    }
}