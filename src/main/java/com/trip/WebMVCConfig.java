package com.trip;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

	private final long MAX_AGE_SECS = 3600;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		registry.addMapping("/**")
		//Orgin 이 http:localhost:8081에 허용
		.allowedOrigins("http://localhost:8081")
		.allowedOrigins("http://api.visitkorea.or.kr/openapi/service/rest/KorService/")
		//GET,PUT,PATCH,DELETE,OPTIONS,POST 메서드 허용한다
		.allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS")
		//헤더 종류는 아무거나
		.allowedHeaders("*")
		.allowCredentials(true)
		//기간
		.maxAge(MAX_AGE_SECS);
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**/*")
				.addResourceLocations("classpath:/static/")
				.resourceChain(true)
				.addResolver(new PathResourceResolver() {
					@Override
					protected Resource getResource(String resourcePath, Resource location) throws IOException {
						Resource requestedResource = location.createRelative(resourcePath);
						return requestedResource.exists() && requestedResource.isReadable() ? requestedResource : new ClassPathResource("/static/index.html");
					}
				});
	}
	@Bean
	public RestTemplate resttemplate() {
		return new RestTemplate();
	}
}
