package com.frs.weezzplayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties()
public class WeezzPlayerApplication {

    public static void main(String[] args) {
        ApplicationContext cnt = SpringApplication.run(WeezzPlayerApplication.class, args);
        
    }

}
