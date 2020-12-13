package com.czf.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czf.dto.SysUserSearchDTO;
import com.czf.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Scott
 * @Date: 2020/12/1 20:51
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    IPage<SysUser> selectUserPage(Page page, @Param("query") SysUserSearchDTO userDTO);
}
