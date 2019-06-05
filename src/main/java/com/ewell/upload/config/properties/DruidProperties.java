package com.ewell.upload.config.properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.JdbcConstants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Slf4j
@Component
@Data
public class DruidProperties {
    @Value("${spring.datasource.druid.driverClassName}")
    private String driverClass;
    @Value("${spring.datasource.druid.initial-size}")
    private Integer initialSize;
    @Value("${spring.datasource.druid.min-idle}")
    private Integer minIdle;
    @Value("${spring.datasource.druid.max-active}")
    private Integer maxActive;
    @Value("${spring.datasource.druid.max-wait}")
    private Integer maxWait;
    @Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
    private Integer timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
    private Integer minEvictableIdleTimeMillis;
    @Value("${spring.datasource.druid.validation-query}")
    private String validationQuery;
    @Value("${spring.datasource.druid.test-while-idle}")
    private Boolean testWhileIdle;
    @Value("${spring.datasource.druid.test-on-borrow}")
    private Boolean testOnBorrow;
    @Value("${spring.datasource.druid.test-on-return}")
    private Boolean testOnReturn;
    @Value("${spring.datasource.druid.pool-prepared-statements}")
    private Boolean poolPreparedStatements;
    @Value("${spring.datasource.druid.max-open-prepared-statements}")
    private Integer maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.datasource.druid.filters}")
    private String filters;

    public void config(DruidDataSource dataSource) {
        dataSource.setDriverClassName(driverClass);
        dataSource.setDbType(JdbcConstants.ORACLE_DRIVER);
        dataSource.setInitialSize(initialSize);     // 定义初始连接数
        dataSource.setMinIdle(minIdle);             // 最小空闲
        dataSource.setMaxActive(maxActive);         // 定义最大连接数
        dataSource.setMaxWait(maxWait);             // 获取连接等待超时的时间
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 用来检测连接是否有效的sql，要求是一个查询语句
        dataSource.setValidationQuery(validationQuery);
        // 申请连接的时候检测
        dataSource.setTestWhileIdle(testWhileIdle);
        // 申请连接时执行validationQuery检测连接是否有效，配置为true会降低性能
        dataSource.setTestOnBorrow(testOnBorrow);
        // 归还连接时执行validationQuery检测连接是否有效，配置为true会降低性能
        dataSource.setTestOnReturn(testOnReturn);
        // 打开PSCache，并且指定每个连接上PSCache的大小
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);


        // 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：
        // 监控统计用的filter:stat
        // 日志用的filter:log4j
        // 防御SQL注入的filter:wall
        try {
            dataSource.setFilters(filters);
        } catch (SQLException e) {
            log.error("配置druid监控异常");
        }
    }
}
