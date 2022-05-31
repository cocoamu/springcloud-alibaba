package com.cocoamu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * <h1>用户账户微服务启动入口</h1>
 * 127.0.0.1:8003/springcloud-account-service/swagger-ui.html
 * 127.0.0.1:8003/springcloud-account-service/doc.html
 * */
@EnableJpaAuditing
@SpringBootApplication
@EnableDiscoveryClient
public class AccountApplication {

    public static void main(String[] args) {

        SpringApplication.run(AccountApplication.class, args);
    }
}
