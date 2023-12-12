package it.daraloca.ice.takehometask.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "it.daraloca.ice.takehometask.endpoint" })
public class ApiCnfg{

    @Value("${api.cors.alloworigin:*}")
    private String allowOrigin;

    @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/*").allowedOrigins(allowOrigin);
			}
		};
	}

}
