package com.example.dpms_research_sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/images/**",
                "/css/**",
                "/assets/**")
                .addResourceLocations(
                        "classpath:/static/images/",
                        "classpath:/static/css/",
                        "classpath:/static/assets/");
    }

    @Bean
    public ClassLoaderTemplateResolver webPageTemplateResolver(){
        ClassLoaderTemplateResolver webPageTemplateResolver = new ClassLoaderTemplateResolver();
        webPageTemplateResolver.setPrefix("templates/");
        webPageTemplateResolver.setSuffix(".html");
        webPageTemplateResolver.setTemplateMode("HTML5");
        webPageTemplateResolver.setOrder(1);
        return webPageTemplateResolver;
    }
}
