package com.hard.cegAndranovelona.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hard.cegAndranovelona.intercepeteur.MyInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Enregistre l'intercepteur pour toutes les URLs, mais exclut les fichiers statiques
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/**") // Intercepte toutes les URL
                .excludePathPatterns("/plugins/**", "/dist/**", "/static/**", "/css/**", "/js/**", "/images/**", "/favicon.ico","/error");
    }
}
