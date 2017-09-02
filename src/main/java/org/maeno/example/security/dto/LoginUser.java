package org.maeno.example.security.dto;

import org.maeno.example.domain.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

public class LoginUser extends User {

    public LoginUser(Account account) {
        super(account.getUsername(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<GrantedAuthority>());
        this.password = account.getPassword();
        this.email = account.getEmail();
    }

    public String email;

    public String password;
}
