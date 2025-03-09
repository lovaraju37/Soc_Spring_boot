package com.srivasavi.boot_intro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.srivasavi.boot_intro.helper.JWTAuthenticationFilter;
import com.srivasavi.boot_intro.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	JWTAuthenticationFilter authenticationFilter;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	protected void configure(HttpSecurity http) throws Exception {

		System.out.println("first configure()");

		http.csrf().disable().cors().disable()
		.authorizeRequests()			
				//.antMatchers("/firstMessage", "/secondMessage", "/allUsers").permitAll()
				.antMatchers("/student/create").permitAll()
				.antMatchers("/student/allStudents").hasRole("EMPLOYEE")
				.antMatchers("/student/getByName/**", "/public/firstMessage").hasRole("ADMIN")
				.antMatchers("/student/deleteById/**").hasRole("ADMIN")
				.antMatchers("/token").permitAll()
				//.antMatchers("/student/*").hasRole("Admin")
				.anyRequest().authenticated().and()//.httpBasic();
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

	}

	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("configure()");
//		auth.inMemoryAuthentication().withUser("xi").password(passwordEncoder().encode("123")).roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("jing").password(passwordEncoder().encode("234")).roles("EMPLOYEE");

		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
		
		
	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		System.out.println("passwordEncoder()");
//		return NoOpPasswordEncoder.getInstance();
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("passwordEncoder()");
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		
		return super.authenticationManager();
	}

}