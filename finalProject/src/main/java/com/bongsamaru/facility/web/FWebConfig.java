package com.bongsamaru.facility.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FWebConfig implements WebMvcConfigurer {

    @Autowired
    private FacilityInfoInterceptor facilityInfoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(facilityInfoInterceptor)
                .addPathPatterns("/facilityInfo/**");  // "/facilityInfo/**" 경로에만 적용
    }
}