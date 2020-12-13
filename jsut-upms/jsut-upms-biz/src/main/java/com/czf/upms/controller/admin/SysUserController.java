package com.czf.upms.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czf.common.core.utils.Res;
import com.czf.dto.SysUserSearchDTO;
import com.czf.entity.SysUser;
import com.czf.upms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cheng
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/page")
    public Res<IPage<SysUser>> getUserPage(Page page, SysUserSearchDTO userDTO) {
        return Res.success(userService.getUserPage(page, userDTO));
    }

}
