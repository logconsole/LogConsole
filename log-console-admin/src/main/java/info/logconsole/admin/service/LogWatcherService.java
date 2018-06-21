package info.logconsole.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import info.logconsole.admin.entity.LogWatcher;

/**
 * <p>
 * 日志监控器 服务类
 * </p>
 *
 * @author hongjian.xia
 * @since 2018-06-21
 */
public interface LogWatcherService extends IService<LogWatcher> {
    public List<LogWatcher> findAllEnableWatchers(boolean fetchNotifers);
}
