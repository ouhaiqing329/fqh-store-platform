package com.fqh.userserver.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fqh.userserver.entity.User;
import com.fqh.userserver.mapper.UserMapper;
import com.fqh.utils.response.BaseResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResponseResult<User> getUserInfo(String username) {
        User user = userMapper.selectOne(new QueryWrapper<>(new User()).lambda().eq(User::getUsername, username));
        return BaseResponseResult.success(user);
    }
}
