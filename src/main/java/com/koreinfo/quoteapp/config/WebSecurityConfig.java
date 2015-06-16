/*package com.koreinfo.quoteapp.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Resource
	private UserDetailsService userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/",
						"/home",
						"/error",
						"/signup",
						"forgot-password",
						"reset-password/*",
						"/views/jquery/**",
						"/views/css/**",
						"/views/fonts/**",
						"/views/js/**"
						).permitAll() 		//available for anonymous users
			.anyRequest().authenticated();	//All other URLs should be authenticated
		http
			.formLogin() // provide a login form for userid/password
			.loginPage("/login")
			.permitAll()
			.and()
			.logout()
			.permitAll();
	}
	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		authManagerBuilder.userDetailsService(userService);
	}
	
}
*/