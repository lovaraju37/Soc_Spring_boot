package com.srivasavi.boot_intro.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.srivasavi.boot_intro.dto.Product;

@Repository
public class SecondRepository {

	public void saveProduct(Product product) throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/srivasavi", "root", "root");

		PreparedStatement statement = conn.prepareStatement("insert into product values(?, ?, ?)");

		statement.setInt(1, product.getProductId());
		statement.setString(2, product.getProductName());
		statement.setFloat(3, product.getPrice());

		statement.execute();
	    conn.close();


	}

	public Product findProductById(int productId) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/srivasavi", "root", "root");

		PreparedStatement statement = conn.prepareStatement("select * from product where productId = ?");

		statement.setInt(1, productId);

		ResultSet resultSet = statement.executeQuery();

		Product product = null;

		if (resultSet.next()) {

			int productId1 = resultSet.getInt(1);
			String productName = resultSet.getString(2);
			float price = resultSet.getFloat(3);

			System.out.println(productId1 + ", " + productName + ", " + price);

			product = new Product(productId1, productName, price);
		}
	    conn.close();


		return product;
	}

	public List<Product> findAllProducts() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/srivasavi", "root", "root");
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM product");

		ResultSet resultSet = statement.executeQuery();
		List<Product> productList = new ArrayList<>();

		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			float price = resultSet.getFloat(3);

			System.out.println(id + ", " + name + ", " + price);
			productList.add(new Product(id, name, price));
		}

		resultSet.close();
		statement.close();
		conn.close();

		return productList;
	}
	public String deleteProduct(Integer  productId) throws ClassNotFoundException, SQLException {
		if (productId == null) {
	        return "Invalid Product ID";
	    }
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/srivasavi", "root", "root");

		PreparedStatement statement = conn.prepareStatement("delete from product where productId = ?");
		statement.setInt(1,productId);
		int rowsAffected = statement.executeUpdate();
	    statement.close();
	    conn.close();

	    if (rowsAffected > 0) {
	        return "Product  Deleted Successfully with Id "+productId;
	    } else {
	        return "No Record Found with Product ID: " + productId;
	    }
	}

}
