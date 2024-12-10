package com.fqh.auth.service.access;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqh.auth.entity.AcUserRole;
import com.fqh.auth.service.access.AcUserRoleService;
import com.fqh.auth.mapper.AcUserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author ouhai
* @description 针对表【ac_user_role(用户角色表)】的数据库操作Service实现
* @createDate 2024-12-10 15:28:24
*/
@Service
public class AcUserRoleServiceImpl extends ServiceImpl<AcUserRoleMapper, AcUserRole>
    implements AcUserRoleService{

}




