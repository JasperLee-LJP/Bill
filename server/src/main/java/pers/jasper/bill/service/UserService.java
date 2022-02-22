package pers.jasper.bill.service;

import pers.jasper.bill.dto.UserLoginDto;
import pers.jasper.bill.po.User;

public interface UserService {
    public User register(UserLoginDto userLoginDto);
    public String login(UserLoginDto userLoginDto);
    public User getUserByUsername(String username);
    public User getUserById(Integer id);
//    User getUserInfoByUserNameAndPassword(User user);
}
