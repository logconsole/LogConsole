package info.logconsole.admin.service.impl;

import info.logconsole.admin.entity.LogRecordInfo;
import info.logconsole.admin.mapper.LogRecordInfoMapper;
import info.logconsole.admin.service.LogRecordInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志信息 服务实现类
 * </p>
 *
 * @author hongjian.xia
 * @since 2018-06-20
 */
@Service
public class LogRecordInfoServiceImpl extends ServiceImpl<LogRecordInfoMapper, LogRecordInfo> implements LogRecordInfoService {

}
