package com.czf.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czf.dto.UserDTO;
import com.czf.entity.User;
import com.czf.upms.mapper.UserMapper;
import com.czf.upms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Scott
 * @Date: 2020/12/1 20:50
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<User> getUserPage(Page page, UserDTO userDTO) {
        return userMapper.selectUserPage(page, userDTO);
    }


}
