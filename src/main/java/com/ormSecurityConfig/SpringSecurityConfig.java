package com.ormSecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ormService.AuthenticationEntryPointConfig;
import com.ormService.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationEntryPointConfig entryPoint;
	
	@Autowired
	private UserDetailsServiceImpl detailsImpl;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// AuthenticationProvider retrieves user details from a UserDetailsService and is the interface which is implemented by DAOAuthenticationProvider
		auth.userDetailsService(detailsImpl).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/signup").permitAll();
		
		/*
		 * http.csrf().disable().authorizeRequests().antMatchers("/employee/*").
		 * authenticated() .and().httpBasic().authenticationEntryPoint(entryPoint);
		 */
		
		http.csrf().disable().authorizeRequests().antMatchers("/employee/*")
		.hasAnyAuthority("admin","unit approver")
		.and().httpBasic().authenticationEntryPoint(entryPoint);
		 
	}
	
}
