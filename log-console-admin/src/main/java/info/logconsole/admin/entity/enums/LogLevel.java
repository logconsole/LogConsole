package info.logconsole.admin.entity.enums;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IEnum;

/** 
 * @author xiahongjian
 * @time   2018-06-20 18:44:35
 */
public enum LogLevel implements IEnum {
    ERROR(0),
    WARN(1),
    INFO(2),
    DEBUG(3),
    TRACE(4),
    ALL(5);
    
    int value;

    LogLevel(int value) {
        this.value = value;
    }
    
    @Override
    public Serializable getValue() {
        return value;
    }

    public static LogLevel of(String level) {
        for (LogLevel logLevel : values()) {
            if (logLevel.name().equalsIgnoreCase(level))
                return logLevel;
        }
        return INFO;
    }
}
