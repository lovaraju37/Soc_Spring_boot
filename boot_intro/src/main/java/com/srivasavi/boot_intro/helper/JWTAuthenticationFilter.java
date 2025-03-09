package com.srivasavi.boot_intro.helper;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.srivasavi.boot_intro.service.CustomUserDetailsService;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	JWTHelper jwtHelper;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		System.out.println("insdie first of Filter...");
		
		
		String tokenHeader =  request.getHeader("Authorization");
		System.out.println("token header is "+tokenHeader);
		
		String token = null;
		String username = null;
		
		System.out.println("inside filter...");
		
		
		if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {

			token = tokenHeader.substring(7);

			username = jwtHelper.extractUsername(token);
			System.out.println("Username is after token decrypt "+username);

			UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
			System.out.println("Userdetails after loading in filter "+userDetails);

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			System.out.println("inside filter if...");
			} else {
				System.out.println("insdie else of filter");
			}
		}
		filterChain.doFilter(request, response);
	}
	
	
}


//@Component
//public class JWTAuthenticationFilter extends OncePerRequestFilter{
//	
//	@Autowired
//	CustomUserDetailsService customUserDetailsService;
//	
//	@Autowired
//	JWTHelper jwtHelper;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		
//		System.out.println("inside JWTAuthenticationFilter...");
//		String tokenHeader = request.getHeader("Authorization");
//		String token = null;
//		String username = null;
//
//		if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
//
//			token = tokenHeader.substring(7);
//
//			username = jwtHelper.extractUsername(token);
//
//			UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
//
//			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//						userDetails, null, userDetails.getAuthorities());
//
//				usernamePasswordAuthenticationToken
//						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//			} else {
//				System.out.println();
//			}
//			
//		}
//		filterChain.doFilter(request, response);
//		
//	}
//	
//	
//
//}