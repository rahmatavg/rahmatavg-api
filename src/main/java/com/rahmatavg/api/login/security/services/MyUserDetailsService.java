package com.rahmatavg.api.login.security.services;

import com.rahmatavg.api.login.model.Login;
import com.rahmatavg.api.login.repository.LoginRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginRepositoryImp loginRepositoryImp;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Login login = loginRepositoryImp.findByEmail(s);
        if (login == null) {
            new UsernameNotFoundException("User Not Found with username: " + s);
            return new Login(null, "rahmatavgemailnotfoundapi", "rahmatavgpasswordnotfoundapi", null, null, login.getPassword());
        } else {
            return new Login(login.getId(), login.getEmail(), login.getName(), null, null, login.getPassword());
        }
    }
}
