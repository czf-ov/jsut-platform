package com.czf.common.security.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Scott
 * @Date: 2020/12/26 20:24
 */
@Data
public class JsutUser implements Serializable {

    private Long identity;

    private Integer userType;


}
