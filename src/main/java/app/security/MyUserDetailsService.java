package app.security;

import app.dao.UserDao;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service()
public class MyUserDetailsService implements UserDetailsService {

    //get user from the database, via Hibernate
    @Autowired
    private UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User fromDao = userDao.get(s);
        System.out.println("in user details service");
        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
                fromDao.getName(),
                fromDao.getPassword(),
                true, true, true, true, new ArrayList<GrantedAuthority>() {{
            add(new SimpleGrantedAuthority("ROLE_USER"));
        }}
        );

        System.out.println(user);
        return user;
    }
}