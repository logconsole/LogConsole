package info.logconsole.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import info.logconsole.admin.entity.LogNotifer;

/**
 * <p>
 * 日志监控通知器 服务类
 * </p>
 *
 * @author hongjian.xia
 * @since 2018-06-21
 */
public interface LogNotiferService extends IService<LogNotifer> {
    public List<LogNotifer> findNotiferByWatcherId(Integer watcherId, boolean onlyEnable);
}
