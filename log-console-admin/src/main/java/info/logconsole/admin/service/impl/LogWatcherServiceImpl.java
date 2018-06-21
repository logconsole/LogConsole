package info.logconsole.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import info.logconsole.admin.entity.LogWatcher;
import info.logconsole.admin.mapper.LogWatcherMapper;
import info.logconsole.admin.service.LogNotiferService;
import info.logconsole.admin.service.LogWatcherService;

/**
 * <p>
 * 日志监控器 服务实现类
 * </p>
 *
 * @author hongjian.xia
 * @since 2018-06-21
 */
@Service
public class LogWatcherServiceImpl extends ServiceImpl<LogWatcherMapper, LogWatcher> implements LogWatcherService {

    @Autowired
    private LogWatcherMapper logWatcherMapper;
    
    @Autowired
    private LogNotiferService logNotiferService;
    
    @Override
    public List<LogWatcher> findAllEnableWatchers(boolean fetchNotifers) {
        List<LogWatcher> watchers = logWatcherMapper.selectList(new EntityWrapper<LogWatcher>().where("enable=1"));
        if (!fetchNotifers)
            return watchers;
        
        watchers.forEach(w -> {
            w.setNotifers(logNotiferService.findNotiferByWatcherId(w.getId(), true));
        });
        return watchers;
    }

}
