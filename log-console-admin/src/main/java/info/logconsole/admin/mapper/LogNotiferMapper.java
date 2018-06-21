package info.logconsole.admin.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import info.logconsole.admin.entity.LogNotifer;

/**
 * <p>
 * 日志监控通知器 Mapper 接口
 * </p>
 *
 * @author hongjian.xia
 * @since 2018-06-21
 */
public interface LogNotiferMapper extends BaseMapper<LogNotifer> {
    public List<LogNotifer> selectByWatcherId(Integer watcherId, boolean onlyEnable);
}
