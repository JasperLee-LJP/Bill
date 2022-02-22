package pers.jasper.bill.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pers.jasper.bill.mapper.UserMapper;
import pers.jasper.bill.po.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component("userDetailsService")
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = mapper.getUserByUsername(username);
        if(users.size() == 0) {
            throw new UsernameNotFoundException("User " + username + " was not found in db");
        }
        User user = users.get(0);

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
        grantedAuthorities.add(grantedAuthority);

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                grantedAuthorities);
    }
}
