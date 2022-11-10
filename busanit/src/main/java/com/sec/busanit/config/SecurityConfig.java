package com.sec.busanit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true )
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		
		return new BCryptPasswordEncoder();
	}
	
	private final AuthenticationFailureHandler customFailurHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeHttpRequests()
			.antMatchers("/user/*").authenticated()
			.anyRequest().permitAll()
		.and()
			.formLogin()
			.loginPage("/member/login")
			.loginProcessingUrl("/loginProc")
			.failureHandler(customFailurHandler)
			.defaultSuccessUrl("/home")
		.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/member/login")
			.invalidateHttpSession(true);
	}
	
}
