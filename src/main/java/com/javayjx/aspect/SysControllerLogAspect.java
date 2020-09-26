package com.javayjx.aspect;



import com.javayjx.annotation.SysLog;
import com.javayjx.entity.base.User;
import com.javayjx.entity.ser.AutoLog;
import com.javayjx.service.ser.AutoLogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class SysControllerLogAspect {


    @Autowired
    private AutoLogService autoLogService;

    @Pointcut("@annotation(com.javayjx.annotation.SysLog)")
    public void controllerAspect() {
    }



    @Around("controllerAspect()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = null;
        proceed = joinPoint.proceed();
        //获取注解内容
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog syslog = method.getAnnotation(SysLog.class);

        AutoLog autoLog = new AutoLog();
        autoLog.setDescription(syslog.descrption());
        autoLog.setModelType(syslog.modelType());
        autoLog.setOperationType(syslog.operateType());

        //获取session中的用户
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if ("0:0:0:0:0:0:0:1".equals(request.getRemoteAddr())) {
            autoLog.setIp("127.0.0.1");
        } else {
            autoLog.setIp(request.getRemoteAddr());
        }
        User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
        autoLog.setUsername(currentUser.getName());

        autoLogService.insert(autoLog);
        return proceed;
    }
}
