package com.dmsdbj.team3.javaprojectbestpractices.utils.log;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author : angel-rmm
 * @Date 2019/12/4  9:36 上午
 * @Package com.dmsdbj.team3.javaprojectbestpractices.utils.log
 * @Version v1.0
 * @Copyright
 * @Email 18232466045@163.com
 * @Contract https://github.com/ITManito
 */
@Aspect
@Component
//@Profile({"dev","test"})
@Slf4j
public class LogAspect {
//    todo ?
    ThreadLocal<String> tag = new ThreadLocal<String>();
    //    换行符

    /**
     * 限额限日志次的 trace id todo?
     */
    private static final String TRACE_ID = "TRACE_ID";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    //    1. 定义切点：以自定义注解@Log为切点
    @Pointcut("@annotation(com.dmsdbj.team3.javaprojectbestpractices.utils.log.Log)")

    public void log() {
        System.out.println("切面：定义切点");
    }

    //    2. 定义环绕点 around
    @Around("log()")
    public Object doAroud(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        定义开始时间（接口调用）
        long startTime = System.currentTimeMillis();
//        添加MDC IdWorker.getIdStr():生成唯一标识
        MDC.put(TRACE_ID, IdWorker.getIdStr());
        //       在切点方法 执行切点
        Object result = proceedingJoinPoint.proceed();
//        日志
//        打印参数-gson
        log.info("Response Args: {}", new Gson().toJson(result));
//        执行耗时
        log.info("Time-Consuming:{} ms", System.currentTimeMillis() - startTime);
//        移除MDC
        MDC.remove(TRACE_ID);
        return result;
    }

    //    3. before
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) throws ClassNotFoundException {
//        打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

//        获取@Log描述信息 ： 根据切点（具体哪个类的哪个方法）获取该方法上@log注解后的注释信息
        String methodDescription = getAspectLogDescription(joinPoint);

        // 打印请求相关参数
        log.info("-------------start---------------");
//        打印请求URL
        log.info("URL            : {}", request.getRequestURL().toString());
//        打印描述信息
        log.info("Description    : {}", methodDescription);
//        打印http method
        log.info("HTTP Method    : {}", request.getMethod());
//        打印调用controller的全路径和执行方法 todo ?
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
//        打印请求Ip
        log.info("IP             : {}", request.getRemoteAddr());
//        打印请求入参
        log.info("Request Args   : {}", new Gson().toJson(joinPoint.getArgs()));

    }

    //    4. after
    @After("log()")
    public void doAfter() {
        log.info("-------------End---------------" + LINE_SEPARATOR);
    }

    //    获取切面注解的描述  根据切点获得目标类名，切点方法名，根据目标类名获取该类的所有方法，循环所有方法和切点方法名比较，如果名同且长度同，则获取目标类中该切点方法的注解信息
    public String getAspectLogDescription(JoinPoint joinPoint) throws ClassNotFoundException {
//        目标名，方法名
        String targeName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
//        参数
        Object[] arguments = joinPoint.getArgs();
//        目标类，方法
        Class targertClass = Class.forName(targeName);
        Method[] methods = targertClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(Log.class).description());
                    break;
                }
            }
        }

        return description.toString();
    }
}