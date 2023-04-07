package com.gamsungjin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**")	// http://localhost/images/qwer_1629441269853/green.jpg
		//.addResourceLocations("file:///C:\\Users\\ganjinam\\Desktop\\class\\7_project\\workspace\\gamsungjin\\images/");	// 실제파일위치
		.addResourceLocations("file:///home/ec2-user/images/");
	}
}
