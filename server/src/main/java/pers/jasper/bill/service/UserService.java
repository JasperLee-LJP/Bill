package pers.jasper.bill.service;

import pers.jasper.bill.dto.UserLoginDto;
import pers.jasper.bill.po.User;

public interface UserService {
    public User register(UserLoginDto userLoginDto);
//    User getUserInfoById(Integer id);
//    User getUserInfoByUserNameAndPassword(User user);
}
