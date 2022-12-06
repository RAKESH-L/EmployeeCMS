package com.springrest.employeecms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

//	static {
//		System.out.println("Security config called....");
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth
			.inMemoryAuthentication()
			.passwordEncoder(getPasswordEncoder())
			.withUser("rakesh@gmail.com").password(getPasswordEncoder().encode("rakesh@12")).authorities("EMPLOYEE")
			.and()
			.withUser("lokesh@gmail.com").password(getPasswordEncoder().encode("lokesh@12")).authorities("MANAGER")
			.and()
			.withUser("yashas@gmail.com").password(getPasswordEncoder().encode("yashas@12")).authorities("MANAGER");
			
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/api/manager/hello").permitAll()
		.antMatchers(HttpMethod.GET,"/api/manager/auth/hello").authenticated()
		.antMatchers(HttpMethod.GET,"/api/manager/auth/role/hello").hasAuthority("MANAGER")
		.antMatchers(HttpMethod.PUT,"/api/employee/status/{status}/{id}").hasAuthority("MANAGER")
		.antMatchers(HttpMethod.GET,"/api/user/login").authenticated()
		.antMatchers(HttpMethod.POST,"/api/leave/add").hasAuthority("EMPLOYEE")
		.anyRequest().permitAll()
		.and()
		.httpBasic()
		.and()
		.csrf().disable();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
