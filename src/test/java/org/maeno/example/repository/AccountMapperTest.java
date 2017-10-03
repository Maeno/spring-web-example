package org.maeno.example.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.maeno.example.config.MyBatisConfiguration;
import org.maeno.example.domain.Account;
import org.maeno.example.test.DatabaseTestSupport;
import org.maeno.example.test.TestDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {
        TestDataSourceConfig.class,
        MyBatisConfiguration.class})
public class AccountMapperTest extends DatabaseTestSupport{

    @Autowired
    private AccountMapper sut;

    @Test
    public void testLoadAccountSuccess() throws Exception {
        final Account account = sut.loadAccount("SCOTT");
        assertThat(account, is(notNullValue()));
        assertThat(account, is(samePropertyValuesAs(
                new Account(0,
                        "SCOTT",
                        "SCOTT@example.com",
                        "TIGER")
        )));
        final Account account1 = sut.loadAccount("bar@example.com");
        assertThat(account1, is(notNullValue()));
        assertThat(account1, is(samePropertyValuesAs(
                new Account(1,
                        "FOO",
                        "bar@example.com",
                        "BAR")
        )));
    }



    @Test
    public void testLoadAccountFailed() throws Exception {
        final Account account = sut.loadAccount("XXX");
        assertThat(account, is(nullValue()));
    }

    @Test
    public void testAuthSuccess() throws Exception {
        final Account account = sut.auth("SCOTT", "TIGER");
        assertThat(account, is(notNullValue()));
        assertThat(account, is(samePropertyValuesAs(
                new Account(0,
                        "SCOTT",
                        "SCOTT@example.com",
                        "TIGER")
        )));

        final Account account1 = sut.auth("bar@example.com", "BAR");
        assertThat(account1, is(notNullValue()));
        assertThat(account1, is(samePropertyValuesAs(
                new Account(1,
                        "FOO",
                        "bar@example.com",
                        "BAR")
        )));
    }

    @Test
    public void testAuthFailed() throws Exception {
        final Account account = sut.auth("XXX", "YYY");
        assertThat(account, is(nullValue()));
    }
}

