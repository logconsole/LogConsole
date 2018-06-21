package info.logconsole.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 日志监控器和通知器的关联关系
 * </p>
 *
 * @author hongjian.xia
 * @since 2018-06-21
 */
@TableName("watcher_notifer_rel")
public class WatcherNotiferRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("log_watcher_id")
    private Integer logWatcherId;
    @TableField("log_notifer_id")
    private Integer logNotiferId;


    public Integer getLogWatcherId() {
        return logWatcherId;
    }

    public void setLogWatcherId(Integer logWatcherId) {
        this.logWatcherId = logWatcherId;
    }

    public Integer getLogNotiferId() {
        return logNotiferId;
    }

    public void setLogNotiferId(Integer logNotiferId) {
        this.logNotiferId = logNotiferId;
    }

    @Override
    public String toString() {
        return "WatcherNotiferRel{" +
        ", logWatcherId=" + logWatcherId +
        ", logNotiferId=" + logNotiferId +
        "}";
    }
}
