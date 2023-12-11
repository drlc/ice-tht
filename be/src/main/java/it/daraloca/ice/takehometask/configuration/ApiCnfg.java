package it.daraloca.ice.takehometask.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "it.daraloca.ice.takehometask.endpoint" })
public class ApiCnfg {
    
}
