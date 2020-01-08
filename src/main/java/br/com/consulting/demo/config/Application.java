package br.com.consulting.demo.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@ComponentScan("br.com.consulting.demo.*")
@PropertySource("classpath:application.yml")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        org.springframework.context.ApplicationContext ctx = org.springframework.boot.SpringApplication.run(Application.class, args);
    }

}
