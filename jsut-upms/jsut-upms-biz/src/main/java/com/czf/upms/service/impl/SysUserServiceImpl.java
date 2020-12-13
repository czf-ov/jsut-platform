package com.czf.upms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czf.dto.SysUserSearchDTO;
import com.czf.entity.SysUser;
import com.czf.upms.mapper.SysUserMapper;
import com.czf.upms.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Scott
 * @Date: 2020/12/1 20:50
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public IPage<SysUser> getUserPage(Page page, SysUserSearchDTO userDTO) {
        return userMapper.selectUserPage(page, userDTO);
    }


}
