package pers.jasper.bill.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pers.jasper.bill.dto.UserLoginDto;
import pers.jasper.bill.exception.CustomException;
import pers.jasper.bill.exception.ErrorCode;
import pers.jasper.bill.po.User;
import pers.jasper.bill.mapper.UserMapper;
import pers.jasper.bill.security.jwt.JWTFilter;
import pers.jasper.bill.security.jwt.TokenProvider;
import pers.jasper.bill.service.UserService;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public User register(UserLoginDto userLoginDto){
        User user = new User();
        BeanUtils.copyProperties(userLoginDto, user);
        user.setCreateTime(new Date());
        user.setStatus(1);

        List<User> users = userMapper.getUserByUsername(user.getUsername());
        if(users.size() > 0) {
            throw new CustomException(HttpStatus.CONFLICT, ErrorCode.USER_EXIST);
        }

        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userMapper.addUser(user);
        user.setPassword(null);
        return user;
    }

    @Override
    public String login(UserLoginDto user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication, true);
        jwt = JWTFilter.TOKEN_PREFIX + jwt;

        return jwt;
    }

    @Override
    public User getUserByUsername(String username){
        List<User> users = userMapper.getUserByUsername(username);
        if(users.size() == 0) {
            throw new CustomException(HttpStatus.NOT_FOUND, ErrorCode.UNAUTHORIZED);
        }
        User user = users.get(0);
        user.setPassword(null);
        return user;
    }

    @Override
    public User getUserById(Integer id) {
        List<User> users = userMapper.getUserInfoById(id);
        if(users.size() == 0) {
            throw new CustomException(HttpStatus.NOT_FOUND, ErrorCode.UNAUTHORIZED);
        }
        User user = users.get(0);
        user.setPassword(null);
        return user;
    }
}
