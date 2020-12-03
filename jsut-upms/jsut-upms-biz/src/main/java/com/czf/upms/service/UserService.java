package com.czf.upms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czf.dto.UserDTO;
import com.czf.entity.User;

/**
 * @Author: Scott
 * @Date: 2020/12/1 20:44
 */
public interface UserService {

    IPage<User> getUserPage(Page page, UserDTO userDTO);
}
