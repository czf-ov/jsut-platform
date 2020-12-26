package com.czf.common.log.annoation;

import java.lang.annotation.*;

/**
 * @Author: Scott
 * @Date: 2020/12/17 21:33
 *
 * 系统日志
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface SysLog {

    String value();
}
