package com.example.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.util.ThreadLocalUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author： 17602
 * @Date： 2022/8/9 10:38
 * @Desc：
 **/
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "create_time", Date.class, new Date());
        this.strictInsertFill(metaObject, "create_by", String.class, ThreadLocalUser.loginThreadLocal.get().getAccount());
    }
    
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "update_time", Date.class, new Date());
        this.strictUpdateFill(metaObject, "update_by", String.class, ThreadLocalUser.loginThreadLocal.get().getAccount());
    }
}
