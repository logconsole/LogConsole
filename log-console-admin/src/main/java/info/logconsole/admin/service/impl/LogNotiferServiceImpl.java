package info.logconsole.admin.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import info.logconsole.admin.entity.LogNotifer;
import info.logconsole.admin.mapper.LogNotiferMapper;
import info.logconsole.admin.service.LogNotiferService;

/**
 * <p>
 * 日志监控通知器 服务实现类
 * </p>
 *
 * @author hongjian.xia
 * @since 2018-06-21
 */
@Service
public class LogNotiferServiceImpl extends ServiceImpl<LogNotiferMapper, LogNotifer> implements LogNotiferService {
    
    @Autowired
    private LogNotiferMapper logNotiferMapper;

    @Override
    public List<LogNotifer> findNotiferByWatcherId(Integer watcherId, boolean onlyEnable) {
        if (watcherId == null)
            return Collections.emptyList();
        return logNotiferMapper.selectByWatcherId(watcherId, onlyEnable);
    }

}
