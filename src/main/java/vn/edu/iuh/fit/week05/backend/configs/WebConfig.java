package vn.edu.iuh.fit.week05.backend.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Cấu hình tài nguyên tĩnh (ví dụ: hình ảnh, CSS, JS)
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}

