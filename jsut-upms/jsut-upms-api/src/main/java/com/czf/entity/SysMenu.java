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
@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long menuId;

    private String name;

    private String permission;

    private String path;

    private Long parentId;

    private String icon;

    private String component;

    private Long sort;

    private Integer keepAlive;

    private Integer type;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    private Integer delFlag;

}
