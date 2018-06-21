package info.logconsole.admin.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 日志监控器
 * </p>
 *
 * @author hongjian.xia
 * @since 2018-06-21
 */
@TableName("log_watcher")
public class LogWatcher implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 监控器名称，非空唯一
     */
    private String name;
    /**
     * 监控应用名称（支持正则表达式），多个使用’;’分隔
     */
    @TableField("app_name")
    private String appName;
    /**
     * 监控主机（支持正则表达式），多个使用’;’分隔
     */
    private String host;
    /**
     * 监控日志级别，0->ERROR, 1->WARN, 2-INFO, 3->DEBUG, 4->TRACE, 5->ALL
     */
    @TableField("log_level")
    private Integer logLevel;
    /**
     * 需要监控的日志产生的类名（支持正则表达式），多个使用’;’分隔
     */
    private String clazz;
    
    private Boolean enable;
    
    /**
     * 此日志监控器关联的通知器
     */
    @TableField(exist = false)
    private List<LogNotifer> notifers;


    public List<LogNotifer> getNotifers() {
        return notifers;
    }

    public void setNotifers(List<LogNotifer> notifers) {
        this.notifers = notifers;
    }

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

    public Integer getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(Integer logLevel) {
        this.logLevel = logLevel;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
    

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "LogWatcher{" +
        ", id=" + id +
        ", name=" + name +
        ", appName=" + appName +
        ", host=" + host +
        ", logLevel=" + logLevel +
        ", clazz=" + clazz +
        ", enable=" + enable + 
        "}";
    }
}
