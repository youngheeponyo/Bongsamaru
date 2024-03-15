package com.bongsamaru.securing;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		WebMvcConfigurer.super.addViewControllers(registry);
		registry.addViewController("/home").setViewName("home");
//		registry.addViewController("/").setViewName("layout");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/intro").setViewName("intro");
		registry.addViewController("/intro2").setViewName("testintro");
		registry.addViewController("/test").setViewName("admin/ui-buttons");
//		registry.addViewController("/login").setViewName("login/FacilityLogin");
	}

}