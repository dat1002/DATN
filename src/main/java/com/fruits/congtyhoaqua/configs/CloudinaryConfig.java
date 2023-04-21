package com.fruits.congtyhoaqua.configs;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary config() {
        Map<String,String> config = new HashMap<>();
        config.put("cloud_name", "djrisdvxu");
        config.put("api_key", "536163645655515");
        config.put("api_secret", "VP0fUi54O8pWMeQZwOFcA0IvbCg");
        return new Cloudinary(config);
    }

}