package pers.jasper.bill.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.jasper.bill.dto.UserLoginDto;
import pers.jasper.bill.exception.CustomException;
import pers.jasper.bill.exception.ErrorCode;
import pers.jasper.bill.po.User;
import pers.jasper.bill.security.SecurityUtils;
import pers.jasper.bill.security.jwt.JWTFilter;
import pers.jasper.bill.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("api")
@Api(tags = "用户管理")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "user/register")
    @ApiOperation("注册新用户")
    private ResponseEntity<User> register(@RequestBody UserLoginDto userDto) {
        User user = userService.register(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping(value = "user/login")
    @ApiOperation("用户登录")
    private ResponseEntity<JWTToken> login(@RequestBody UserLoginDto userDto) {
        String jwt = userService.login(userDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, jwt);

        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "user")
    @ApiOperation(value = "获取当前登录用户信息")
    private ResponseEntity<User> getUserInfo() {
        Optional<String> username = SecurityUtils.getCurrentUsername();
        if(username.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, ErrorCode.UNAUTHORIZED);
        }
        User user = userService.getUserByUsername(username.get());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "user/{id}")
    @ApiOperation(value = "根据用户ID获取用户信息")
    private ResponseEntity<User> getUserInfoById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String token;

        JWTToken(String token) {
            this.token = token;
        }

        @JsonProperty("token")
        String getToken() {
            return token;
        }

        void setToken(String token) {
            this.token = token;
        }
    }
}
