package info.logconsole.admin.entity.enums;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IEnum;

/** 
 * @author xiahongjian
 * @time   2018-06-20 23:11:22
 */
public enum NotiferType implements IEnum {
    SHORT_MESSAGE(0),
    EMAIL(1);
    
    int value;
    
    NotiferType(int value) {
        this.value = value;
    }

    @Override
    public Serializable getValue() {
        return value;
    }
}
