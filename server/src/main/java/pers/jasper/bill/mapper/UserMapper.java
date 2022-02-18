package pers.jasper.bill.mapper;

import org.apache.ibatis.annotations.Mapper;
import pers.jasper.bill.po.User;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getUserByUsername(String username);
    int addUser(User user);
    User getUserInfoById(Integer id);
    User getUserInfoByUserNameAndPassword(User user);
}
