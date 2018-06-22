package info.logconsole.admin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import info.logconsole.admin.entity.LogWatcher;
import info.logconsole.admin.service.LogWatcherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 日志监控器 前端控制器
 * </p>
 *
 * @author hongjian.xia
 * @since 2018-06-21
 */
@Api("logwatcher相关api")
@Controller
@RequestMapping("/admin/logWatcher")
public class LogWatcherController {

    @Autowired
    private LogWatcherService logWatcherService;
    
    @ApiOperation(value = "获取所有日志监控器", response = LogWatcher.class, responseContainer = "List")
    @ResponseBody
    @GetMapping("/alll")
    public List<LogWatcher> getAllWatchers() {
        return logWatcherService.findAllEnableWatchers(true);
    }
}

