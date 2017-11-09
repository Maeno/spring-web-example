package org.maeno.example.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.maeno.example.config.MyBatisConfiguration;
import org.maeno.example.domain.Project;
import org.maeno.example.test.DatabaseTestSupport;
import org.maeno.example.test.TestDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {
        TestDataSourceConfig.class,
        MyBatisConfiguration.class,
        ProjectService.class})
public class ProjectServiceTest extends DatabaseTestSupport{

    @Autowired
    private ProjectService sut;

    @Test
    public void list() throws Exception {
        final List<Project> list = sut.selectProject();
        assertThat(list, is(notNullValue()));
    }
}
