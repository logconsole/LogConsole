package info.logconsole.admin.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 日志监控通知器
 * </p>
 *
 * @author hongjian.xia
 * @since 2018-06-21
 */
@TableName("log_notifer")
public class LogNotifer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 通知名称
     */
    private String name;
    /**
     * 通知类型
     */
    private Integer type;
    /**
     * 通知的接收者，短信->手机号码，邮件->邮箱，多个使用’;’分隔
     */
    private String receiver;
    /**
     * 通知内容模板
     */
    private String template;
    /**
     * 如果type为邮件类型，此项为邮件的主题
     */
    private String subject;
    /**
     * 通知器开始工作时间
     */
    @TableField("start_time")
    private LocalDateTime startTime;
    /**
     * 通知器结束工作时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;
    /**
     * 通知器是否启用，默认值为0即不启用
     */
    private Boolean enable;
    /**
     * 此通知器两次触发的最小间隔时间
     */
    @TableField("min_interval")
    private Integer minInterval;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getMinInterval() {
        return minInterval;
    }

    public void setMinInterval(Integer minInterval) {
        this.minInterval = minInterval;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "LogNotifer{" +
        ", id=" + id +
        ", name=" + name +
        ", type=" + type +
        ", receiver=" + receiver +
        ", template=" + template +
        ", subject=" + subject +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", enable=" + enable +
        ", minInterval=" + minInterval +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
