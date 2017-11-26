package org.maeno.example.security.service;

import org.maeno.example.domain.Account;
import org.maeno.example.repository.AccountMapper;
import org.maeno.example.security.dto.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AppUserDetailsService implements UserDetailsService{

    private AccountMapper accountMapper;

    @Autowired
    public AppUserDetailsService(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public UserDetails loadUserByKey(String searchKey, final String password) throws UsernameNotFoundException {
        final Account account = accountMapper.auth(searchKey, password);

        if (StringUtils.isEmpty(account)) {
            throw new UsernameNotFoundException("could not find the account.");
        }
        return new LoginUser(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
