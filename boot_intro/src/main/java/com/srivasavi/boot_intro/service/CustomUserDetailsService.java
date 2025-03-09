package com.srivasavi.boot_intro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.srivasavi.boot_intro.dto.CustomUserDetails;
import com.srivasavi.boot_intro.dto.Student;
import com.srivasavi.boot_intro.repository.StudentRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	StudentRepository studentRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Student student = studentRepository.findById(username).get();
		return new CustomUserDetails(student);
	}

}