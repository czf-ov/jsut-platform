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
@TableName("sys_dept")
public class SysDept extends Model<SysDept> implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long deptId;

    private String name;

    private Long sort;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    private Integer delFlag;

    private Long parentId;

}
