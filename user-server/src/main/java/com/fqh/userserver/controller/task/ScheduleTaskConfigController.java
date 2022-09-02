package com.fqh.userserver.controller.task;

import com.fqh.userserver.entity.ScheduleTaskConfig;
import com.fqh.userserver.service.ScheduleTaskConfigService;
import com.fqh.utils.response.BaseResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ouhaiqing
 * @date 2022/9/1 9:43
 */
@RestController
@RequestMapping("/system/schedule/task")
@Api(value = "定时任务相关",tags = "定时任务相关")
public class ScheduleTaskConfigController {

    @Autowired
    private ScheduleTaskConfigService scheduleTaskConfigService;

    @PostMapping("/add")
    @ApiOperation(value = "新增定时任务")
    public BaseResponseResult<Void> add(@RequestBody ScheduleTaskConfig scheduleTaskConfig){
        scheduleTaskConfigService.save(scheduleTaskConfig);
        return BaseResponseResult.success();
    }
    @GetMapping("/run")
    @ApiOperation(value = "根据Id执行定时任务")
    public BaseResponseResult<Void> run(@RequestParam Long id){
        scheduleTaskConfigService.run(id);
        return BaseResponseResult.success();
    }

}
