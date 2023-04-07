package com.question.infra.in.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.question.auth.application.AuthService;
import com.question.auth.domain.InvalidAuthenticationException;
import com.question.infra.in.aop.support.CurrentUser;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CurrentUserResolver implements HandlerMethodArgumentResolver {

	private final AuthService authService;

	public CurrentUserResolver(
		final AuthService authService
	) {
		this.authService = authService;
	}

	public static final String AUTHORIZTION_HEADER_NAME = HttpHeaders.AUTHORIZATION;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(CurrentUser.class);
	}

	@Override
	public String resolveArgument(MethodParameter parameter,
		ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest,
		WebDataBinderFactory binderFactory) throws Exception {

		String authorization = webRequest.getHeader(AUTHORIZTION_HEADER_NAME);

		if (authorization == null || !authorization.startsWith("Bearer ")) {
			throw new InvalidAuthenticationException();
		}

		authService.validateToken(authorization.substring(7));
		return authService.getUserIdFromToken(authorization.substring(7));
	}
}
