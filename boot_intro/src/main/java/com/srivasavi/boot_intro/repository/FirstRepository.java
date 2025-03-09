package com.srivasavi.boot_intro.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.srivasavi.boot_intro.dto.User;

@Repository
public class FirstRepository {

	public FirstRepository() {
		System.out.println("inside FirstRepo no-arg constructor...");
	}

	public void saveUser(User user) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/srivasavi", "root", "root");

		PreparedStatement statement = conn.prepareStatement("insert into user values(?, ?, ?)");

		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword());
		statement.setString(3, user.getName());

		statement.execute();
	}
	
	public User findUserById(String userId) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/srivasavi", "root", "root");

		PreparedStatement statement = conn.prepareStatement("select * from user where username = ?");
		
		statement.setString(1, userId);
		
		ResultSet resultSet = statement.executeQuery();
		
		User user = null;
		
		if( resultSet .next() ) {
			
			String username = resultSet.getString(1);
			String password = resultSet.getString(2);
			String name = resultSet.getString(3);
			
			System.out.println(username+", "+password+", "+name);
			
			user = new User(username, password, name);
		}
		
		return user;
	}
}
