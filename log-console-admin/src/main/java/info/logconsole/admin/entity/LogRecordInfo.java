package info.logconsole.admin.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 日志信息
 * </p>
 *
 * @author hongjian.xia
 * @since 2018-04-09
 */
@TableName("log_record_info")
public class LogRecordInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志唯一标识 主键id
     */
    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;
    /**
     * 源系统唯一标识id
     */
    @TableField("system_id")
    private String systemId;
    /**
     * 日志打印时间
     */
    @TableField("log_time")
    private LocalDateTime logTime;
    /**
     * 日志级别
     */
    @TableField("log_level")
    private String logLevel;
    /**
     * 日志输出类
     */
    private String clazz;
    /**
     * 日志内容
     */
    @TableField("log_content")
    private String logContent;
    /**
     * 创建时间
     */
    @TableField("create_date")
    private String createDate;


    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "LogRecordInfo{" +
        ", logId=" + logId +
        ", systemId=" + systemId +
        ", logTime=" + logTime +
        ", logLevel=" + logLevel +
        ", clazz=" + clazz +
        ", logContent=" + logContent +
        ", createDate=" + createDate +
        "}";
    }
}
