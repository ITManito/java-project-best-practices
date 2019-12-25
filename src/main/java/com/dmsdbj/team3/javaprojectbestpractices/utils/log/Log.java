package com.dmsdbj.team3.javaprojectbestpractices.utils.log;

import java.lang.annotation.*;

/**
 * @author : angel-rmm
 * @Date 2019/12/3  4:46 下午
 * @Package com.dmsdbj.team3.javaprojectbestpractices.utils.log
 * @Version v1.0
 * @Copyright
 * @Email 18232466045@163.com
 * @Contract https://github.com/ITManito
 */
//where: 方法,类，接口，枚举上
@Target({ElementType.METHOD, ElementType.TYPE})
//when：运行时：注解保存在class文件，Jvm加载class文件后，依然存在
@Retention(RetentionPolicy.RUNTIME)
//注解是否将包含在JavaDoc中
@Documented
public @interface Log {
    //    日志描述信息
    String description() default "";

}