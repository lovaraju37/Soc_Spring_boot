package com.srivasavi.boot_intro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srivasavi.boot_intro.dto.Student;
import com.srivasavi.boot_intro.service.StudentService;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping("/create")
	public String register(@RequestBody @Valid Student student) {
		
		System.out.println(student.getStudentId()+", "+student.getName());
		
		String planePassword = student.getPassword();
		
		String encodedPassword = passwordEncoder.encode(planePassword);
		
		student.setPassword(encodedPassword);
		
		studentService.create(student);
		
		return "Registration Successful";
	}
	
	@GetMapping("/allStudents")
	public List<Student> getAllStudents() {
		
		List<Student> students = studentService.allUsers();
		
		return students;
	}
	
	@DeleteMapping("deleteById/{studentId}")
	public String deleteStudent( @PathVariable @Valid String studentId ) {
		
		System.out.println(studentId);
		
		studentService.deleteById(studentId);
		
		return "Student deleted Successfully";
	}
	
	@DeleteMapping("deleteByPassword/{password}")
	public String deleteStudentByPassword( @PathVariable String password ) {
		
		System.out.println(password);
		
		studentService.deleteByPassword(password);
		
		return "Student deleted Successfully by password";
	}
	
	@GetMapping("/getByName/{name}")
	public List<Student> allUsers(@PathVariable String name) {
		
		System.out.println("the name is "+name);
		
		
		
		return studentService.allStudents(name);
	}
}