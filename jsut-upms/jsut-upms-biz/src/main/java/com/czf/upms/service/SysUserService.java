package com.czf.upms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czf.dto.SysUserSearchDTO;
import com.czf.entity.SysUser;

/**
 * @Author: Scott
 * @Date: 2020/12/1 20:44
 */
public interface SysUserService extends IService<SysUser> {

    IPage<SysUser> getUserPage(Page page, SysUserSearchDTO userDTO);
}
