package com.fqh.auth.utils;

import com.fqh.auth.handle.ServiceException;
import com.fqh.utils.response.BaseResponseResult;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
   * @return {@link String}
   */
  public String createToken(String username) {
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

    // 声明Token失效时间
    Calendar instance = Calendar.getInstance();
    // 300s
    instance.add(Calendar.HOUR, expireTime);

    //获取用户基本信息
//    BaseResponseResult<UserInfo> userInfo = userFeignClient.getUserInfo(username);
//    payload.put("userInfo",userInfo.getData());
    // 生成Token 生成xxx.xxx.xxx
    return Jwts.builder()
            .setHeader(header)// 设置Header
            .setClaims(payload) // 设置载核
            .setExpiration(instance.getTime())// 设置生效时间
            .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(secret.getBytes(StandardCharsets.UTF_8))) // 签名,这里采用私钥进行签名,不要泄露了自己的私钥信息
            .compact();
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
      log.warn("JWT格式验证失败:{},exception:", token,e);
      throw new ServiceException("JWT格式验证失败");
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
        //根据用户名获取用户权限
        String username = claims.get("username").toString();
        return new UsernamePasswordAuthenticationToken(username,null,null);
      }
    } catch (Exception e) {
      log.warn("获取已认证信息失败:{}", token);
      throw new ServiceException("获取已认证信息失败");
    }
    return null;
  }

  /**
   * 获取数据
   *
   * @param resp resp基础响应结果
   */
  private <T>T getData(BaseResponseResult<T> resp) {
    if (resp == null){
      throw new ServiceException("请求失败，响应为空！");
    }
    if (resp.getCode() == 0){
      return resp.getData();
    }

    return null;
  }

}