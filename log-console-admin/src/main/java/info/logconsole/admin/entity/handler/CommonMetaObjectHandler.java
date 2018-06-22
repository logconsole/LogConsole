package info.logconsole.admin.entity.handler;

import java.time.LocalDateTime;

import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;

/**
 * @author xiahongjian
 * @time 2018-06-22 07:04:50
 */
public class CommonMetaObjectHandler extends MetaObjectHandler {

    private static final String FIELD_NAME_CREATE_TIME = "createTime";
    private static final String FIELD_NAME_UPDATE_TIME = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName(FIELD_NAME_CREATE_TIME, metaObject);
        if (createTime == null)
            setFieldValByName(FIELD_NAME_CREATE_TIME, LocalDateTime.now(), metaObject);
        Object updateTime = getFieldValByName(FIELD_NAME_CREATE_TIME, metaObject);
        if (updateTime == null)
            setFieldValByName(FIELD_NAME_UPDATE_TIME, LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName(FIELD_NAME_UPDATE_TIME, LocalDateTime.now(), metaObject);
    }

}
