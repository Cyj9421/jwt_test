package com.cyj.jwt_test.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cyj.jwt_test.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class JWTinterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception{
        Map<String,Object> map=new HashMap<>();
        String requestURL=request.getRequestURI();
        System.out.println("requestURl="+requestURL);
        String token=request.getHeader("token");
        try {
            JWTUtil.verify(token);//验证令牌
            return true;    //放行请求
        }catch (SignatureVerificationException e){
            e.printStackTrace();
            map.put("code",501);
            map.put("msg","无效签名");
        }catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("code",502);
            map.put("msg","token失效");
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("code",503);
            map.put("msg","token算法不一致");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",504);
            map.put("msg","token无效");
        }
        String json=new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
