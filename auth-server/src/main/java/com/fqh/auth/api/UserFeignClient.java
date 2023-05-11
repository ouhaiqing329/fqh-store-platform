package com.fqh.auth.api;

import com.fqh.utils.response.BaseResponseResult;
import com.fqh.utils.response.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Feign客户端api
 *
 * @author fqh
 * @date 2022/08/14
 */
@FeignClient(name = "user-server",
            url = "${server.config.user-server.domain}"
)
public interface UserFeignClient {

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return {@link UserInfo}
     */
    @GetMapping("${server.config.user-server.getUserInfo}")
    BaseResponseResult<UserInfo> getUserInfo(String username);

}
