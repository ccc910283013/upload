package com.ewell.upload;

import com.ewell.upload.config.handler.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySource(value = "file:config/upload.properties",ignoreResourceNotFound = true)
public class UploadApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(UploadApplication.class, args);
    }
    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
