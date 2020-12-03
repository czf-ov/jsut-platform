package com.czf.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Scott
 */
@Data
@TableName("sys_user")
public class User extends Model<User> implements Serializable {

  @TableId
  private Integer userId;

  private Integer userType;

  private String userName;

  private String password;

  private String phone;

  private String avatar;

  private Integer deptId;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;

  private Integer lockFlag;

  private Integer delFlag;

}
