package com.ewell.upload.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan(basePackages = "com.ewell.upload.dao")
public class MybatisConfig {
    @Autowired
    private DataSource dataSource;
    @Bean
    public SqlSessionFactoryBean sqlSessionFactorMaster() {
        SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
        try {
            PathMatchingResourcePatternResolver res = new PathMatchingResourcePatternResolver();
            sfb.setDataSource(dataSource);
            sfb.setMapperLocations(res.getResources("classpath:mapper/*.xml"));
            sfb.setTypeAliasesPackage("com.ewell.upload.bean");
        }catch (IOException e){
            e.printStackTrace();
        }
        return sfb;
    }
}
