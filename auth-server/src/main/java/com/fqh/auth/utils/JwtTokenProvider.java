package com.fqh.auth.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
@Slf4j
public class JwtTokenProvider {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expireTime}")
  private Integer expireTime;

  /**
   * 创建令牌
   *
   * @param username 用户名
   * @param params   参数
   * @return {@link String}
   */
  public String createToken(String username,Authentication authentication, Map<String, String> params) {
    // JWT头部分信息【Header】
    Map<String, Object> header = new HashMap<>();
    header.put("alg", "HS256");
    header.put("typ", "JWT");

    // 载核【Payload】
    Map<String, Object> payload = new HashMap<>();
    //签发者
    payload.put("iss", "auth-server");
    //用户
    payload.put("sub", "wxapp");
    payload.put("username",username);
    payload.put("Authentication",authentication);
    //参数
    payload.put("params", params);

    // 声明Token失效时间
    Calendar instance = Calendar.getInstance();
    instance.add(Calendar.SECOND, expireTime);// 300s

    // 生成Token
    return Jwts.builder()
            .setHeader(header)// 设置Header
            .setClaims(payload) // 设置载核
            .setExpiration(instance.getTime())// 设置生效时间
            .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(secret.getBytes(StandardCharsets.UTF_8))) // 签名,这里采用私钥进行签名,不要泄露了自己的私钥信息
            .compact(); // 压缩生成xxx.xxx.xxx
  }

  /**
   * 验证
   *
   * @param token 令牌
   * @return boolean
   */
  public boolean verify(String token){
    try {
      Claims claims = Jwts.parser()
              .setSigningKey(Base64.getEncoder().encode(secret.getBytes(StandardCharsets.UTF_8)))
              .parseClaimsJws(token)
              .getBody();
      Date date = new Date();
      if (claims != null && date.before(claims.getExpiration())){
        return true;
      }
    } catch (Exception e) {
      log.warn("JWT格式验证失败:{},exception:{}", token,e);
    }
    return false;
  }


  /**
   * 获得认证信息
   *
   * @param token 令牌
   * @return {@link Authentication}
   */
  public Authentication getAuthentication(String token){
    try {
      Claims claims = Jwts.parser()
              .setSigningKey(Base64.getEncoder().encode(secret.getBytes(StandardCharsets.UTF_8)))
              .parseClaimsJws(token)
              .getBody();
      Date date = new Date();
      if (claims != null && date.before(claims.getExpiration())){
        LinkedHashMap<String,Object> authentication = claims.get("Authentication", LinkedHashMap.class);
        return new UsernamePasswordAuthenticationToken(authentication.get("principal"),authentication.get("credentials"), (Collection<? extends GrantedAuthority>) authentication.get("authorities"));
      }
    } catch (Exception e) {
      log.warn("获取已认证信息失败:{}", token);
    }
    return null;
  }

}