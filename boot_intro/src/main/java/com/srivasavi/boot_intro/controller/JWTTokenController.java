package com.srivasavi.boot_intro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.srivasavi.boot_intro.dto.JWTRequest;
import com.srivasavi.boot_intro.helper.JWTHelper;
import com.srivasavi.boot_intro.service.CustomUserDetailsService;


@RestController
@CrossOrigin
public class JWTTokenController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	JWTHelper jwtHelper;
	
	@PostMapping("/token")
	public String generateToken(@RequestBody JWTRequest jwtRequest) {
		
		System.out.println("credentails are "+jwtRequest);
		
		String token = null;
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		
		token = jwtHelper.generateToken(userDetails);
		
//		JWTResponse jwtResponse = new JWTResponse(token);
		
		return token;
		
		
		
	}
}







//@RestController
//@CrossOrigin
//public class JWTTokenController {
//	
//	@Autowired
//	AuthenticationManager authenticationManager;
//	
//	@Autowired
//	CustomUserDetailsService customUserDetailsService;
//	
//	@Autowired
//	JWTHelper jwtHelper;
//	
//	@PostMapping("/token")
//	public String generateToken( @RequestBody JWTRequest jwtRequest) {
//		
//		System.out.println("username and password is "+jwtRequest);
//		
//		String token = null;
//		
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
//			UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
//			
//			token = jwtHelper.generateToken(userDetails);
//			
//			System.out.println("generated token is "+token);
//		}
//		catch (Exception e) {
//			System.out.println("insdie catch of login()....");
//			e.printStackTrace();
//		}
//		
//		return token;
//	}
//
//}