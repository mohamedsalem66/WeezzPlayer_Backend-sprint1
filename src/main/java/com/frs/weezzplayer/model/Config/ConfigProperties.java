package com.frs.weezzplayer.model.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class ConfigProperties {
    @Bean
    @ConfigurationProperties(prefix="jwt")
    public JwtConfig jwtConfig(){ return new JwtConfig();}

    @Bean
    @ConfigurationProperties(prefix = "storage")
    public StorageConfig storageConfig(){
        return new StorageConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "longpolling")
    public LongPollingConfig longPollingConfig(){
        return new LongPollingConfig();
    }
    @Bean
    public MultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }

}
