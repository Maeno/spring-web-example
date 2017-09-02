package org.maeno.example.security;

import org.maeno.example.domain.Account;
import org.maeno.example.repository.AccountMapper;
import org.maeno.example.security.dto.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AppAuthenticationProvider implements AuthenticationProvider{

    private AccountMapper accountMapper;

    @Autowired
    public AppAuthenticationProvider(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String searchKey = authentication.getName();
        final String password = authentication.getCredentials().toString();

        if (StringUtils.isEmpty(searchKey) || StringUtils.isEmpty(password)) {
            throw new AuthenticationCredentialsNotFoundException("username or password must not be null and empty.");
        }

        Account account = accountMapper.auth(searchKey, password);
        if (StringUtils.isEmpty(account)) {
            throw new AuthenticationCredentialsNotFoundException("incorrect username or password.");
        }

        return new UsernamePasswordAuthenticationToken(new LoginUser(account), password, authentication.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
