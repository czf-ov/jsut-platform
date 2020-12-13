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
@TableName("sys_role")
public class SysRole extends Model<SysRole> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long roleId;

    private String roleName;

    private String roleCode;

    private String roleDesc;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    private Integer delFlag;

}
