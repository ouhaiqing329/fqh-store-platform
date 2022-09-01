package com.fqh.userserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fqh.userserver.entity.User;
import com.fqh.userserver.mapper.UserMapper;
import com.fqh.userserver.request.UserInfoReq;
import com.fqh.userserver.service.UserInfoService;
import com.fqh.utils.BeanUtil;
import com.fqh.utils.response.BaseResponseResult;
import com.fqh.utils.response.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResponseResult<UserInfo> getUserInfo(String username) {
        User user = userMapper.selectOne(new QueryWrapper<>(new User()).lambda().eq(User::getUsername, username));
        UserInfo userInfo = new UserInfo();
        if (Objects.isNull(user)) {
            return BaseResponseResult.success(null);
        }
        BeanUtils.copyProperties(user, userInfo);
        return BaseResponseResult.success(userInfo);
    }

    @Override
    public boolean register(UserInfoReq request) {
        User user = BeanUtil.copyProperties(request, User::new);
        return userMapper.insert(user) > 0;
    }
}
