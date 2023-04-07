package com.question.infra.in;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.question.infra.in.resolver.CurrentUserResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${cors.allow-origins.urls}")
	private List<String> allowOriginUrlPatterns;

	@Value("${cors.allow-origins.method}")
	private List<String> allowOriginMethods;

	private final CurrentUserResolver currentUserArgumentResovler;

	public WebConfig(
		final CurrentUserResolver resolver
	) {
		this.currentUserArgumentResovler = resolver;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		String[] patterns = allowOriginUrlPatterns.toArray(String[]::new);
		String[] methods = allowOriginMethods.toArray(String[]::new);

		registry.addMapping("/**")
			.allowedMethods(methods)
			.allowedOriginPatterns(patterns);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(currentUserArgumentResovler);
	}
}