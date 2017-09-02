package org.maeno.example.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.sql.DataSource;

@Configuration
@MapperScan("org.maeno.example.repository")
public class MyBatisConfiguration {


    private DataSource datasource;

    @Autowired
    @Lazy // Workaround : The dependencies of some of the beans in the application context form a cycle:
    public MyBatisConfiguration(DataSource dataSource) {
        this.datasource = dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(datasource);
        return sqlSessionFactoryBean;
    }

}
