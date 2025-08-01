package com.campuscart.app.campus_cart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // allow all paths
                    .allowedOrigins("http://localhost:3000", "https://campus-cart-three.vercel.app/") // allow your React app origin
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // allowed methods
                    .allowCredentials(true);
            }
        };
    }
}
