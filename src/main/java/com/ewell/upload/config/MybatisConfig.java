package com.ewell.upload.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Slf4j
@Configuration
@MapperScan(basePackages = "com.ewell.upload.dao")
public class MybatisConfig {
    @Bean
    @ConditionalOnProperty(name = "muti-datasource-open", havingValue = "false")
    public SqlSessionFactoryBean sqlSessionFactorMaster(@Qualifier("singleDatasource")DataSource singleDatasource) {
        SqlSessionFactoryBean singlesfb = new SqlSessionFactoryBean();
        try {
            config(singlesfb);
            singlesfb.setDataSource(singleDatasource);
        }catch (IOException e2){
            log.error(e2.getMessage());
        }
        return singlesfb;
    }

    @Bean
    @ConditionalOnProperty(name = "muti-datasource-open", havingValue = "true")
    public SqlSessionFactoryBean sqlSessionFactorMutil(@Qualifier("mutiDataSource")DataSource mutiDataSource) {
        SqlSessionFactoryBean mutiSfb = new SqlSessionFactoryBean();
        try {
            config(mutiSfb);
            mutiSfb.setDataSource(mutiDataSource);
        }catch (IOException e2){
            log.error(e2.getMessage());
        }
        return mutiSfb;
    }

    private void config(SqlSessionFactoryBean sqlSessionFactoryBean)throws IOException{
        PathMatchingResourcePatternResolver mutiRes = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean mutiSfb = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setMapperLocations(mutiRes.getResources("classpath:mapper/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.ewell.upload.bean");
    }
}
