package pers.jasper.bill.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.jasper.bill.dto.UserLoginDto;
import pers.jasper.bill.po.User;
import pers.jasper.bill.service.UserService;

@RestController
@RequestMapping("api/user")
@Api(tags = "用户管理")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "{id}")
    @ApiOperation(value = "获取用户信息", produces = MediaType.APPLICATION_JSON_VALUE)
    private String getUserInfo(@PathVariable Integer id) {
        return "Jasper Lee " + id;
    }

    @PostMapping(value = "register")
    @ApiOperation("注册新用户")
    private ResponseEntity<User> register(@RequestBody UserLoginDto userDto) {
        User user = userService.register(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping(value = "login")
    @ApiOperation("用户登录")
    private String login(@RequestBody UserLoginDto user) {
        System.out.println("login: " + user);
        return "login";
    }
}
