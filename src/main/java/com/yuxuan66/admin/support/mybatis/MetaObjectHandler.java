package com.yuxuan66.admin.support.mybatis;

import com.yuxuan66.admin.common.utils.Lang;
import com.yuxuan66.admin.common.utils.Stp;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Sir丶雨轩
 * @since 2022/8/11
 */
@Slf4j
@Component
public class MetaObjectHandler implements com.baomidou.mybatisplus.core.handlers.MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        this.strictInsertFill(metaObject, "createTime", Lang::getTime, Timestamp.class);
        this.strictInsertFill(metaObject, "createBy", () -> Objects.requireNonNull(Stp.get()).getNickName(), String.class);
        this.strictInsertFill(metaObject, "createId", Stp::getId, Long.class);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Lang::getTime, Timestamp.class);
        this.strictUpdateFill(metaObject, "updateBy", () -> Objects.requireNonNull(Stp.get()).getNickName(), String.class);
        this.strictUpdateFill(metaObject, "updateId",Stp::getId, Long.class);
    }
}
