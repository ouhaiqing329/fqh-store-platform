package com.fqh.storeserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File file = new File("../");
        String filePath = "/";
        try {
            filePath = file.getCanonicalPath().replaceAll("\\\\","/") + "/" + "images/";
        } catch (IOException e) {
            e.printStackTrace();
        }
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + filePath);
    }
}
