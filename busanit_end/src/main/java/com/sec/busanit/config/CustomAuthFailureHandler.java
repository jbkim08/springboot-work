package com.sec.busanit.config;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String errorMessage;
		
		if(exception instanceof BadCredentialsException) {	
			errorMessage = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
		}else if(exception instanceof InternalAuthenticationServiceException){
			errorMessage = "계정이 존재하지 않습니다. <a href='/member/join'>회원가입</a> 진행 후 로그인 해주세요.";  //없는 계정일 때 출력되는 메세지
		}else if(exception instanceof AuthenticationCredentialsNotFoundException){
			errorMessage = "인증 요청 거부";
		}else{
			errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다 관리자에게 문의하세요.";
		}
		errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
		setDefaultFailureUrl("/member/login?error=true&exception="+errorMessage);
		super.onAuthenticationFailure(request, response, exception);
	}
	
	/*
	로그인 실패 관련 처리 중에서
	usernameNotFoundException 예외로 계정 없음 처리가 되어야 하는데
	InternalAuthenticationServiceException 예외처리가 되는데. 이 예외 범위가 넓어서 계정없음보다 먼저 처리되기 때문에.
	★AuthenticationException 종류★
	BadCredentialsException : 비밀번호 불일치
	UsernameNotFoundException : 계정 없음
	AccountExpiredException : 계정만료
	CredentialsExpiredException : 비밀번호 만료
	DisabledException : 계정 비활성화
	LockedException : 계정잠김
	*/
	/*
	InternalAuthenticationServiceExceptionr관련 설명
	https://docs.spring.io/spring-security/site/docs/4.0.x/apidocs/org/springframework/security/authentication/InternalAuthenticationServiceException.html
	 */
}
