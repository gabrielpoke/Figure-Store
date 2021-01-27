package com.figure.store.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*").allowedOrigins("*").allowedHeaders("*");
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController(
                "/",
                "/api-docs");
        registry.addRedirectViewController(
                "/api-docs",
                "/swagger-ui");
        registry.addRedirectViewController(
                "/swagger-ui",
                "/swagger-ui/index.html?url=/v3/api-docs&validatorUrl=");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
