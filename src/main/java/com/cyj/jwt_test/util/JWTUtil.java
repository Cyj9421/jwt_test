package com.cyj.jwt_test.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;

import java.util.Map;

public class JWTUtil {
    /*
    生成token    header.payload.sing
     */
    private static final String SING="!QW#E%R";
    public static String getToken(Map<String,String> map){
        Calendar instance=Calendar.getInstance();
        instance.add(Calendar.SECOND,6);   //设置token失效时间为2个小时
        //创建Jwt Builder
        JWTCreator.Builder builder=JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token=builder
                .withExpiresAt(instance.getTime())          //签名时效
                .sign(Algorithm.HMAC256(SING));             //令牌
        return token;
}
    /*
    验证token  合法性
    */
    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }
    /*
    获取token信息方法
     */
    public static DecodedJWT getTokenInfo(String token){
        DecodedJWT verify=JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        return verify;
    }
}
