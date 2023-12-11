package it.daraloca.ice.takehometask.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("it.daraloca.ice.takehometask.data")
@EnableJpaRepositories(basePackages = "it.daraloca.ice.takehometask.data")
public class DataCnfg {
    
}
