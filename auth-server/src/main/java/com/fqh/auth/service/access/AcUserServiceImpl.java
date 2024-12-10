package com.fqh.auth.service.access;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqh.auth.entity.AcUser;
import com.fqh.auth.mapper.AcUserMapper;
import org.springframework.stereotype.Service;

/**
 * @author ouhai
 * @description 针对表【ac_user(用户表)】的数据库操作Service实现
 * @createDate 2024-12-10 15:28:24
 */
@Service
public class AcUserServiceImpl extends ServiceImpl<AcUserMapper, AcUser>
        implements AcUserService {

}




