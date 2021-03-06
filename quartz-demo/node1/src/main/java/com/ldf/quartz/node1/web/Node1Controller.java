package com.ldf.quartz.node1.web;

import com.ldf.quartz.core.param.JobDetailParam;
import com.ldf.quartz.core.param.SimpleTriggerParam;
import com.ldf.quartz.core.param.execute.ExecuteSimpleParam;
import com.ldf.quartz.node1.service.QuartzService;
import io.swagger.annotations.ApiOperation;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import java.text.ParseException;

/**
 * Created by ldf on 2018/6/15.
 */
@RestController
@RequestMapping("node1")
public class Node1Controller {

    @Autowired
    QuartzService quartzService;

    @ApiOperation(value = "执行simple任务")
    @RequestMapping(value = "executeSimpleJob", method = RequestMethod.POST)
    public void executeJob(@RequestBody ExecuteSimpleParam simpleParam) throws SchedulerException, ClassNotFoundException, ParseException {
        quartzService.simpleJobExecute(simpleParam.getJobDetailParam(), simpleParam.getTriggerParam());
    }

    @ApiOperation(value = "删除任务")
    @RequestMapping(value = "delJob", method = RequestMethod.DELETE)
    public boolean delJob(String jobName, String jobGroup) throws SchedulerException {
        return quartzService.delSchedule(jobName, jobGroup);
    }

    @ApiOperation(value = "关闭调度器")
    @RequestMapping(value = "shutdownSchedule", method = RequestMethod.POST)
    public void shutdownSchedule() throws SchedulerException {
        quartzService.shutdown();
    }

}
