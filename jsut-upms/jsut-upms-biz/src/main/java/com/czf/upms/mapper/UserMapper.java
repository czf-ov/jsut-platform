package com.czf.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czf.dto.UserDTO;
import com.czf.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Scott
 * @Date: 2020/12/1 20:51
 */
public interface UserMapper extends BaseMapper<User> {
    IPage<User> selectUserPage(Page page, @Param("query") UserDTO userDTO);
}
