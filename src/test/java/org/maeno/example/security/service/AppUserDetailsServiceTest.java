package org.maeno.example.security.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.maeno.example.config.MyBatisConfiguration;
import org.maeno.example.domain.Account;
import org.maeno.example.security.dto.LoginUser;
import org.maeno.example.test.DatabaseTestSupport;
import org.maeno.example.test.TestDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {
        TestDataSourceConfig.class,
        AppUserDetailsService.class,
        MyBatisConfiguration.class})
public class AppUserDetailsServiceTest extends DatabaseTestSupport {

    @Autowired
    private AppUserDetailsService sut;

    @Test
    public void testSearchKeyNull() throws Exception {
        try {
            sut.loadUserByUsername(null);
            fail();
        } catch (UsernameNotFoundException e) {
            assertThat(e.getMessage(), is("could not find the account."));
        }
    }

    @Test
    public void testSearchKey() throws Exception {
        UserDetails userDetails = sut.loadUserByUsername("SCOTT");
        assertThat(userDetails, is(notNullValue()));
        assertThat(userDetails, samePropertyValuesAs(
                new LoginUser(
                        new Account(0,
                                "SCOTT",
                                "SCOTT@example.com",
                                "TIGER")
        )));

        userDetails = sut.loadUserByUsername("bar@example.com");
        assertThat(userDetails, is(notNullValue()));
        assertThat(userDetails, samePropertyValuesAs(
                new LoginUser(
                        new Account(1,
                                "FOO",
                                "bar@example.com",
                                "BAR")
                )));
    }
}
