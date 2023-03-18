package com.example.ottokeng.global.config;

import com.amazonaws.HttpMethod;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("https://server.ottokeng.site/","https://www.ottoekng.site/","https://ottoekng.site/","http://server.ottokeng.site/","http://www.ottokeng.site/","http://ottoekng.site/")
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.PATCH.name()
                )
                .maxAge(3600);
    }

}
