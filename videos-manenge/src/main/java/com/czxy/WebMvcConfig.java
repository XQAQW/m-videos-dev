package com.czxy;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
                //资源映射
        registry.addResourceHandler("/**")
                //添加映射可以让外界访问
                .addResourceLocations("classpath:/META-INF/resources/")
                //所在的目录
                .addResourceLocations("file:/usr/local/programing/WeChartProject/video_dev/videos_dev_image/");
    }
}
