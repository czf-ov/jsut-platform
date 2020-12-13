package com.czf.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author cheng
 */

@Data
@TableName("sys_log")
public class SysLog extends Model<SysLog> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private String type;

    private String title;

    private String serviceId;

    private String createBy;

    private LocalDateTime createTime;

    private String remoteAddr;

    private String userAgent;

    private String requestUri;

    private String method;

    private String params;

    private String time;

    private String delFlag;

    private String exception;

}
