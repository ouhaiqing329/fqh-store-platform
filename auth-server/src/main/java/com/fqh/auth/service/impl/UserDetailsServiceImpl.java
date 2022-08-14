package com.fqh.auth.service.impl;

import com.fqh.auth.api.UserFeignClient;
import com.fqh.utils.response.BaseResponseResult;
import com.fqh.utils.response.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 用户详细信息服务impl
 *
 * @author fqh
 * @date 2022/08/14
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名获取用户信息
        BaseResponseResult<UserInfo> result = userFeignClient.getUserInfo(username);
        UserInfo userInfo = result.getData();
        if (Objects.isNull(userInfo)){
            throw new UsernameNotFoundException("账号或密码不正确！");
        }
        //用户角色权限
        List<GrantedAuthority> authorities = new ArrayList<>();
        //构建Security User
        return new User(userInfo.getUsername(), userInfo.getPassword(), authorities);
    }
}
