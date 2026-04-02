package org.kristaps.PP12Serveris.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // attiecas uz visiem /api/... ceļiem
                .allowedOrigins(
                        "https://komarovs.lv", // produkcija
                        "http://localhost:4200" // lokālā izstrāde
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false); // ja nevajag sūtīt cookies/auth headerus
    }
}
