package com.czf.upms.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czf.common.core.utils.Res;
import com.czf.dto.UserDTO;
import com.czf.entity.User;
import com.czf.upms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/page")
    public Res<IPage<User>> getUserPage(Page page, UserDTO userDTO) {
        return Res.success(userService.getUserPage(page, userDTO));
    }

}
