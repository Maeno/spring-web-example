package org.maeno.example.security;

import org.maeno.example.security.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AppAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

    private final AppUserDetailsService appUserDetailsService;

    @Autowired
    public AppAuthenticationProvider(final AppUserDetailsService appUserDetailsService) {
        this.appUserDetailsService = appUserDetailsService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        final String searchKey = authentication.getName();
        final String password = authentication.getCredentials().toString();

        if (StringUtils.isEmpty(searchKey) || StringUtils.isEmpty(password)) {
            throw new AuthenticationCredentialsNotFoundException("username or password must not be null and empty.");
        }

        UserDetails userDetails;
        try {
            userDetails = appUserDetailsService.loadUserByKey(searchKey, password);
        } catch (UsernameNotFoundException e) {
            throw new AuthenticationCredentialsNotFoundException("incorrect username or e-mail or password.", e);
        }
        return userDetails;
    }
}
