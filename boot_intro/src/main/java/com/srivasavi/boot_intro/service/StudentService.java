package com.srivasavi.boot_intro.service;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srivasavi.boot_intro.dto.Student;
import com.srivasavi.boot_intro.repository.StudentRepository;
@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	public void create(Student student) {

		studentRepository.save(student);
	}

	public List<Student> allUsers() {

		List<Student> students = studentRepository.findAll();

		return students;
	}

	public void deleteById(String studentId) {

		studentRepository.deleteById(studentId);
	}
	@Transactional
	public void deleteByPassword(String password) {

		studentRepository.deleteByPassword(password);
	}
	
	
	public List<Student> allStudents(String name) {
		
		return studentRepository.findByName(name);
	}
	
}