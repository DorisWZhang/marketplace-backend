package com.campuscart.app.campus_cart.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "du3ie1bas",
                "api_key", "159723162451724",
                "api_secret", "JOrAH4D26iVLLmKf6Jf3RBfP1Y8"));
    }
}
