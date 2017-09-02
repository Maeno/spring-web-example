package org.maeno.example.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maeno.example.config.MyBatisConfiguration;
import org.maeno.example.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {
        TestDataSourceConfig.class,
        MyBatisConfiguration.class})
public class AccountMapperTest {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDatasource(DataSource datasource) {
        jdbcTemplate = new JdbcTemplate(datasource);
    }

    @Autowired
    private AccountMapper sut;

    @Before
    public void setUp() throws Exception {
        Arrays.asList(
                "CREATE TABLE IF NOT EXISTS ACCOUNT  (" +
                        "    ACCOUNTID INTEGER IDENTITY PRIMARY KEY," +
                        "    USERNAME VARCHAR(20)," +
                        "    EMAIL VARCHAR(20)," +
                        "    PASSWORD VARCHAR(255) NOT NULL" +
                        ");",
                "INSERT INTO ACCOUNT (USERNAME, EMAIL, PASSWORD) VALUES ('SCOTT', 'SCOTT@example.com', 'TIGER');",
                "INSERT INTO ACCOUNT (USERNAME, EMAIL, PASSWORD) VALUES ('FOO', 'bar@example.com', 'BAR');")
                .forEach(s -> jdbcTemplate.execute(s));
    }

    @After
    public void tearDown() throws Exception {
        jdbcTemplate.execute("DROP TABLE ACCOUNT;");
    }

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

@Configuration
class TestDataSourceConfig {

    @Bean
    public DataSource datasource() throws ClassNotFoundException {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .build();
    }
}
