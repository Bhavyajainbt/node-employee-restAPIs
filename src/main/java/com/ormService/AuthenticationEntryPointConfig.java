package com.ormService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEntryPointConfig extends BasicAuthenticationEntryPoint {
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		
		response.addHeader("WWW-Authenticate","Basic realm - "+getRealmName());
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	}
	
	@Override
	public void afterPropertiesSet() {
		// TODO Auto-generated method stub
		setRealmName("ORM-application");
		super.afterPropertiesSet();
	}
}
