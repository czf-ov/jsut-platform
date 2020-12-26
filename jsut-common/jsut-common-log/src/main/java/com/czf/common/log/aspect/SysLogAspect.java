package com.czf.common.log.aspect;

import com.czf.common.log.annoation.SysLog;
import com.czf.common.log.constant.LogTypeConstants;
import com.czf.common.log.util.SysLogUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

/**
 * @Author: Scott
 * @Date: 2020/12/17 21:37
 *
 * 定义SysLog切面
 */

@Aspect
@Slf4j
public class SysLogAspect {

    @Around("@annotation(sysLog)")
    @SneakyThrows
    public Object around(ProceedingJoinPoint point, SysLog sysLog){

        // 获取syslog 修改的类及其方法
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        log.debug("[类名]：{}，[方法]：{}", className, methodName);

        com.czf.entity.SysLog logInfo = SysLogUtil.getLogInfo(sysLog.value());

        Long startTime = System.currentTimeMillis();

        Object obj = null;
        try {
            obj = point.proceed();
        } catch (Exception e) {
            logInfo.setType(LogTypeConstants.ERROR);
            logInfo.setException(e.getMessage());
            throw e;
        } finally {
            Long endTime = System.currentTimeMillis();
            Long time = endTime - startTime;
            logInfo.setTime(time.toString());
            // todo 发布信息

        }
        return obj;
    }

}
