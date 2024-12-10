package com.fqh.auth.service.access;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqh.auth.entity.AcAppAccessLog;
import com.fqh.auth.mapper.AcAppAccessLogMapper;
import org.springframework.stereotype.Service;

/**
 * @author ouhai
 * @description 针对表【ac_app_access_log(用户操作记录表)】的数据库操作Service实现
 * @createDate 2024-12-10 15:28:23
 */
@Service
public class AcAppAccessLogServiceImpl extends ServiceImpl<AcAppAccessLogMapper, AcAppAccessLog>
        implements AcAppAccessLogService {

}




