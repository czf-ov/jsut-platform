package com.czf.common.core.annoation;

import java.lang.annotation.*;

/**
 * @Author: Scott
 * @Date: 2020/12/5 18:21
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 描述
     */
    String value();
}
