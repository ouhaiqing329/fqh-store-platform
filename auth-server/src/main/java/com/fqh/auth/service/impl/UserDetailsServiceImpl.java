package com.fqh.auth.service.impl;

import com.fqh.auth.api.UserFeignClient;
import com.fqh.utils.response.BaseResponseResult;
import com.fqh.utils.response.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new User(userInfo.getUsername(),userInfo.getPassword(),authorities);
    }
}
