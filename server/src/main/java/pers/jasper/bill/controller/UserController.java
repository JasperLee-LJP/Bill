package pers.jasper.bill.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Api(tags = "用户管理")
public class UserController {
    @GetMapping("user")
    @ApiOperation("获取用户信息")
    private String getUserInfo() {
        return "Jasper Lee";
    }
}
