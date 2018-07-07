package info.logconsole.admin.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import info.logconsole.admin.entity.enums.LogLevel;

/**
 * <p>
 * 日志信息
 * </p>
 *
 * @author hongjian.xia
 * @since 2018-06-20
 */
@TableName("log_record_info")
public class LogRecordInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志唯一标识 主键id
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Integer id;
    /**
     * 源系统唯一标识id
     */
    @TableField("app_name")
    private String appName;
   /**
    * 应用所部署的服务器
    */
    private String host;
    /**
     * 日志打印时间
     */
    @TableField("log_time")
    private LocalDateTime logTime;
    /**
     * 日志级别
     */
    @TableField("log_level")
    private LogLevel logLevel;
    /**
     * 日志输出类
     */
    private String clazz;
    /**
     * 日志内容
     */
    private String content;
    /**
     * 创建时间
     */
    @TableField("create_date")
    private LocalDateTime createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createDate) {
        this.createTime = createDate;
    }

    @Override
    public String toString() {
        return "LogRecordInfo{" +
        ", id=" + id +
        ", appName=" + appName +
        ", host=" + host +
        ", logTime=" + logTime +
        ", logLevel=" + logLevel +
        ", clazz=" + clazz +
        ", content=" + content +
        ", createDate=" + createTime +
        "}";
    }
}
