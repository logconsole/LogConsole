package info.logconsole.admin.entity.enums;
/** 
 * @author xiahongjian
 * @time   2018-06-21 23:18:18
 */

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IEnum;

public enum OnOffEnum implements IEnum {
    OFF(0),
    ON(1);
    
    int value;
    
    private OnOffEnum(int value) {
        this.value = value;
    }

    @Override
    public Serializable getValue() {
        return value;
    }
    
}
