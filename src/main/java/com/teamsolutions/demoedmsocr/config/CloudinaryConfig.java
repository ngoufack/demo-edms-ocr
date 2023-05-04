package com.teamsolutions.demoedmsocr.config;


import org.springframework.context.annotation.Configuration;
import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class CloudinaryConfig {

    private final String CLOUD_NAME = "dfykzpxef";
    private final String API_KEY = "368926148236766";
    private final String API_SECRET = "fyrdoF8FDflwcvuIBz6vPCMJ44w";

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);
        return new Cloudinary(config);
    }

}
