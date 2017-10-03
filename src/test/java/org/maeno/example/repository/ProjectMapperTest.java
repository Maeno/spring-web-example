package org.maeno.example.repository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.maeno.example.config.MyBatisConfiguration;
import org.maeno.example.domain.Project;
import org.maeno.example.domain.ProjectType;
import org.maeno.example.test.DatabaseTestSupport;
import org.maeno.example.test.TestDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {
        TestDataSourceConfig.class,
        MyBatisConfiguration.class})
public class ProjectMapperTest extends DatabaseTestSupport{

    @Autowired
    private ProjectMapper sut;

    @Test
    public void testList() throws Exception {
        List<Project> projectList = sut.list();
        assertThat(projectList, is(notNullValue()));
        assertThat(projectList.size(), is(2));
        final Project project = new Project();
        project.setId(0);
        project.setName("ProA");
        project.setType(ProjectType.PRIVATE);

        String dateStr = "2017-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date formatDate = sdf.parse(dateStr);

        project.setStartDate(formatDate);
        project.setVersionNo(0l);
        assertThat(projectList.get(0), is(samePropertyValuesAs(project)));

    }
}
