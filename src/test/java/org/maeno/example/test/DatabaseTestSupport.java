package org.maeno.example.test;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Arrays;

public class DatabaseTestSupport {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDatasource(DataSource datasource) {
        jdbcTemplate = new JdbcTemplate(datasource);
    }

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

        Arrays.asList(
                "CREATE TABLE IF NOT EXISTS PROJECT (" +
                        " ID INTEGER IDENTITY PRIMARY KEY, " +
                        " NAME VARCHAR(20), " +
                        " TYPE VARCHAR(20), " +
                        " STARTDATE DATE, " +
                        " VERSIONNO INTEGER " +
                        ");",
                "INSERT INTO PROJECT (NAME, TYPE, STARTDATE, VERSIONNO) VALUES ('ProA', 'PRIVATE', '2017-01-01', 0);",
                "INSERT INTO PROJECT (NAME, TYPE, STARTDATE, VERSIONNO) VALUES ('ProB', 'PUBLIC', '2017-07-07', 0);"

        ).forEach(s -> jdbcTemplate.execute(s));
    }

    @After
    public void tearDown() throws Exception {
        jdbcTemplate.execute("DROP TABLE ACCOUNT;");
        jdbcTemplate.execute("DROP TABLE PROJECT;");
    }
}
