package com.amazon.backend.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SetPrefixeInUrl implements WebMvcConfigurer {
 
	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.addPathPrefix("Api", c -> c.isAnnotationPresent(RestController.class));
	}
}
 