package com.backend.travel.utils;


import cn.hutool.core.codec.Base64;
import com.backend.travel.common.CommonConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;

public class JWTUtils {

    /**
     * 创建token
     *
     * @param id
     * @param uname
     * @param role
     * @return
     */
    public static String createToken(Long id, String uname, String role) {
        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")//请求头
                .setHeaderParam("alg", "HS256")
                .setSubject("medical-user")// 分类
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + CommonConstant.expireTime))
                .claim("id", id)
                .claim("uname", uname)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, CommonConstant.APP_SECRET)
                .compact();
        return JwtToken;
    }

    /**
     * 获取token
     *
     * @param id
     * @param userAccount
     * @param role
     * @return
     */
    public static String getJwtToken(Long id, String userAccount, String role) {
        return createToken(id, userAccount, role);
    }

    /**
     * 生成加密的key
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] decode = Base64.decode(CommonConstant.APP_SECRET);
        SecretKeySpec keySpec = new SecretKeySpec(decode, 0, decode.length, "AES");
        return keySpec;
    }

    /**
     * 解析jwt的字符串
     *
     * @param jsonWebToken
     * @return
     */
    public static Claims parseJWT(String jsonWebToken) {
        SecretKey secretKey = generalKey();
        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jsonWebToken)
                .getBody();
    }


    /**
     * 判断token是否有效
     *
     * @param token
     * @return
     */
    public static boolean checkToken(String token) {
        if (!StringUtils.hasLength(token)) return false;
        try {
            Jwts.parser().setSigningKey(CommonConstant.APP_SECRET).parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean checkToken(HttpServletRequest request) {
        try {
            String token = request.getHeader("token");
            if (!StringUtils.hasLength(token)) return false;
            Jwts.parser().setSigningKey(CommonConstant.APP_SECRET).parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Claims getClaims(String token) {
        if (!StringUtils.hasLength(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(CommonConstant.APP_SECRET).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        return body;
    }

    // 根据token获取用户id
    public static Integer getAccountIdByJwtToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasLength(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(CommonConstant.APP_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (Integer) claims.get("id");
    }


}
