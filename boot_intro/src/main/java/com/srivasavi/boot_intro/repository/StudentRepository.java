package com.srivasavi.boot_intro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srivasavi.boot_intro.dto.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>{
	public  void deleteByPassword(String password);
	public List<Student> findByName(String name);
}