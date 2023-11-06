package com.myweb.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.myweb.www.security.CustomAuthMemberService;
import com.myweb.www.security.LoginFailureHandler;
import com.myweb.www.security.LoginSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	
	//security package를 별도로 생성하여 사용자 핸들러 생성
	//비밀번호 암호화하는 객체 빈 생성
	@Bean
	public PasswordEncoder bcPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//SuccessHandler(성공 객체) 빈 객체 생성 => 사용자가 생성
	@Bean
	public AuthenticationSuccessHandler authSuccessHandler() {
		return new LoginSuccessHandler();
	}

	
	//FailHandler(실패 객체) 빈 객체 생성 => 사용자가 생성
	@Bean
	public AuthenticationFailureHandler authFailureHanlder() {
		return new LoginFailureHandler();
	}
	
	
	//UserDetail 빈 객체 생성 => 사용자가 생성
	@Bean
	public UserDetailsService customUserService() {
		return new CustomAuthMemberService();
	}
	
	//로그인 사용자에 대한 정보
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService())
			.passwordEncoder(bcPasswordEncoder());	//비밀번호 암호화
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		//http의 승인요청 담당
		http.authorizeRequests()
		.antMatchers("/member/list").hasRole("ADMIN")
		.antMatchers("/","/board/list","/board/cntdetail","/upload/**","/comment/list",
				"/board/detail","/resources/**","/member/register","/member/login").permitAll()// 인증된 사용자만 처리
		.anyRequest().authenticated(); // => 나머지 사용자 처리
		
		// 커스텀 로그인 페이지를 구성
		// 컨트롤러의 주소 요청 매핑도 같이 꼭 적어줘야 한다.
		http.formLogin()
		.usernameParameter("email")
		.passwordParameter("pwd")
		.loginPage("/member/login")
		.successHandler(authSuccessHandler())
		.failureHandler(authFailureHanlder());
		
		//로그아웃 페이지 구성
		//.logoutUrl 로그아웃 페이지 반드시 method="post"
		http.logout()
		.logoutUrl("/member/logout")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.logoutSuccessUrl("/");	//홈으로
		
	}
	
	
}
