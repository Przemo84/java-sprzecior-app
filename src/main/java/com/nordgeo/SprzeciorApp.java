package com.nordgeo;


import com.nordgeo.security.MySimpleUrlAuthenticationSuccessHandler;
import com.nordgeo.security.encoder.TokenProperties;
import com.nordgeo.storage.StorageProperties;
import com.nordgeo.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class, TokenProperties.class})
@EnableScheduling
public class SprzeciorApp extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(SprzeciorApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SprzeciorApp.class);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> storageService.init();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }
}