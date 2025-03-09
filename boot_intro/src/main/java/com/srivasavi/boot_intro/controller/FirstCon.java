package com.srivasavi.boot_intro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srivasavi.boot_intro.dto.User;
import com.srivasavi.boot_intro.service.FirstService;

@RestController
@RequestMapping("/public")
public class FirstCon {
//	
//	FirstRepository fs=new FirstRepository();
	
//	@Autowired
//	FirstRepository fs;
	@Autowired
	FirstService firstService;

	
//	FirstRepository firstRepository = new FirstRepository();
	
	public FirstCon() {
		System.out.println("insdie First No-Arg Constructor...");
	}
	
	@GetMapping("/firstMessage")
	public String getMessage() {
		
		return "Welcome to Spring Bootdhbfvfvjh";
	}
	
	@GetMapping("/secondMessage")
	public String secondMessage() {
		
		return "Welcome to Second Message";
	}
	
	@GetMapping("/allUsers")
	public List<User> getUsers() {
		
		/*
		 * User user = new User(); //user.username = "val@123"; user.password = "123";
		 * user.name = "Valni"; user.setName("val@123"); user.setPassword("123");
		 * user.setName("Valni");
		 */
		
		User user1 = new User("sai@123", "123", "Sai");
		User user2 = new User("Kamal@123", "234", "Kamal");
		
		List<User> users = new ArrayList<>();
		users.add(user1);    users.add(user2);
		
		return users;
	}
	
	@GetMapping("/getUser/{userId}")
	public User findUser(@PathVariable String userId) throws Exception {
		
		System.out.println("The userid is "+userId);
		
//		FirstRepository firstRepository = new FirstRepository();
		
		User user = firstService.findUser(userId);
		
		return user;
		
	}
	
//	@PostMapping("/register")
//	public void saveUser(@RequestParam("username") String username, @RequestParam("password") String password) {
//		
//		System.out.println(username+", "+password);
//	}
	
	@PostMapping("/register")
	public void signupRequestParam(@RequestParam String username, @RequestParam String password) {
		
		System.out.println(username+", "+password);
	}
	
//	@PostMapping("/registerJson")
//	public String registerJson( @RequestBody User user) throws ClassNotFoundException, SQLException {
//		System.out.println(user.getUsername()+", "+user.getPassword());
//		
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		
//		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/srivasavi", "root", "root");
//		
//		PreparedStatement statement =  conn.prepareStatement("insert into user values(?, ?, ?)");
//		
//		statement.setString(1, user.getUsername());
//		statement.setString(2, user.getPassword());
//		statement.setString(3, user.getName());
//		
//		statement.execute();
//		
//		return "Registration Successfull";
//	}
	
	
	@PostMapping("/registerJson")
	public String registerJson( @RequestBody User user) throws Exception {
		System.out.println(user.getUsername()+", "+user.getPassword());
		
		firstService.register(user);
		
		return "Registration Successfull";
	}
	
	
	
	
}
