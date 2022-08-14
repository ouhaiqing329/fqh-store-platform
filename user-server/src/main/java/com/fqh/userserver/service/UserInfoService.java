package com.fqh.userserver.service;

import com.fqh.userserver.entity.User;
import com.fqh.utils.response.BaseResponseResult;

/**
 * 用户信息服务
 *
 * @author fqh
 * @date 2022/08/14
 */
public interface UserInfoService {

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return {@link BaseResponseResult}<{@link User}>
     */
    BaseResponseResult<User> getUserInfo(String username);
}
