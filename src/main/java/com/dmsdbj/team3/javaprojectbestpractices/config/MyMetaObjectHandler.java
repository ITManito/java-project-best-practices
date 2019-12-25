package com.dmsdbj.team3.javaprojectbestpractices.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/** 自动填充字段配置类
 * @author : angel-rmm
 * @Date 2019/12/17  8:37 下午
 * @Package com.dmsdbj.team3.javaprojectbestpractices.config
 * @Version v1.0
 * @Copyright
 * @Email 18232466045@163.com
 * @Contract https://github.com/ITManito
 */
@Configuration
public class MyMetaObjectHandler implements MetaObjectHandler {
    //    插入填充
    @Override
    public void insertFill(MetaObject metaObject) {
//获取创建/更新时间字段
        Object createFiledType = getFieldValByName("createTime", metaObject);
        Object updateFiledType = getFieldValByName("updateTime", metaObject);
//判空，重新获取值
        if (createFiledType == null) {
            setFieldValByName("createTime", new Date(), metaObject);
        }
        if (updateFiledType == null) {

            setFieldValByName("updateTime", new Date(), metaObject);
        }

    }

    //    更新填充
    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateFieldType = getFieldValByName("updateTime", metaObject);
        if (updateFieldType == null) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }
    }
}