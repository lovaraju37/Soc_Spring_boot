package com.srivasavi.boot_intro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirstConfiguration {
	public FirstConfiguration() {
	
		// TODO Auto-generated constructor stub
	System.out.println("Configuration:No-Argument...");
	}
	
	@Bean
	public Thread getThread() {
		System.out.println("inside getThread()");
		return new Thread();
		
	}
}
