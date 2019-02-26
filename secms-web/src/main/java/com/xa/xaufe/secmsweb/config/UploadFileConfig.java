package com.xa.xaufe.secmsweb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

//上传文件大小配置类
@Configuration
public class UploadFileConfig {

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(uploadFolder);
        //文件最大
        factory.setMaxFileSize("50MB");
        //设置总上传数据总大小
        factory.setMaxRequestSize("500MB");
        return factory.createMultipartConfig();
    }
}
