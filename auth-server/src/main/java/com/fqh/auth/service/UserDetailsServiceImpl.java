package com.fqh.auth.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fqh.auth.entity.UserInfoEntity;
import com.fqh.auth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.*;
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
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名获取用户信息
        UserInfoEntity userInfoEntity = userMapper.selectOne(Wrappers.<UserInfoEntity>lambdaQuery().eq(UserInfoEntity::getDeleteMark, 0).eq(UserInfoEntity::getUsername, username));
        if (Objects.isNull(userInfoEntity)){
            throw new UsernameNotFoundException("账号密码错误或账号不存在！");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new User(userInfoEntity.getUsername(), userInfoEntity.getPassword(), authorities);
    }
}
