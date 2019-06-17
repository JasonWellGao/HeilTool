package com.heil.timeStamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
public class TimeStampApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(TimeStampApplication.class, args);
	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/rest/api/doc/**").addResourceLocations("classpath:/swagger/dist/");
    }
}
