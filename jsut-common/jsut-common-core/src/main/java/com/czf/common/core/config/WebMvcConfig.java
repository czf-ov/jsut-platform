package com.czf.common.core.config;

import cn.hutool.core.date.DatePattern;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

/**
 * @Author: Scott
 * @Date: 2020/12/5 11:41
 */
@Configuration
@ConditionalOnWebApplication(type = SERVLET)
public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * 对前端传来的日期进行格式化
     * the { @Link JacksonConfiguration } 处理返回日期类型
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar dateTimeFormatterRegistrar = new DateTimeFormatterRegistrar();
        dateTimeFormatterRegistrar.setDateFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN));
        dateTimeFormatterRegistrar.setTimeFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN));
        dateTimeFormatterRegistrar.setDateTimeFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN));
        dateTimeFormatterRegistrar.registerFormatters(registry);
    }
}
