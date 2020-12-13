package com.czf.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: Scott
 * @Date: 2020/12/2 21:09
 */
@Data
public class SysUserSearchDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String phone;

    private Integer status;

    private LocalDateTime creatTime;

    private LocalDateTime endTime;

}
