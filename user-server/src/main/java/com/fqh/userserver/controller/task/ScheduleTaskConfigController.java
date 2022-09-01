package com.fqh.userserver.controller.task;

import com.fqh.userserver.service.ScheduleTaskConfigService;
import com.fqh.utils.response.BaseResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ouhaiqing
 * @date 2022/9/1 9:43
 */
@RestController
@RequestMapping("/system/schedule/task")
public class ScheduleTaskConfigController {

    @Autowired
    private ScheduleTaskConfigService scheduleTaskConfigService;

    @GetMapping
    public BaseResponseResult<Void> run(@RequestParam Long id){
        scheduleTaskConfigService.run(id);
        return BaseResponseResult.success();
    }

}
