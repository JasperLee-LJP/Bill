package pers.jasper.bill.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pers.jasper.bill.dto.UserLoginDto;
import pers.jasper.bill.exception.CustomException;
import pers.jasper.bill.exception.ErrorCode;
import pers.jasper.bill.po.User;
import pers.jasper.bill.mapper.UserMapper;
import pers.jasper.bill.service.UserService;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
}
