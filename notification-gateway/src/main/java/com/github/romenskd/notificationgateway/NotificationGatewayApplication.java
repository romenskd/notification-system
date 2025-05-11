package com.github.romenskd.notificationgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class NotificationGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationGatewayApplication.class, args);
    }

}
