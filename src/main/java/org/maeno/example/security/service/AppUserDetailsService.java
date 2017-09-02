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

    @Override
    public UserDetails loadUserByUsername(String searchKey) throws UsernameNotFoundException {
        final Account account = accountMapper.loadAccount(searchKey);

        if (StringUtils.isEmpty(account)) {
            throw new UsernameNotFoundException("could not find the account.");
        }
        return new LoginUser(account);
    }
}
