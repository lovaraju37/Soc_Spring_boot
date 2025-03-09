package com.srivasavi.boot_intro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.srivasavi.boot_intro.dto.Product;
import com.srivasavi.boot_intro.service.SecondService;

@RestController
public class SecondController {
	@Autowired
	SecondService secondservice;

	@GetMapping("/homeproduct")
	public String getMessage() {

		return "Welcome to Products";
	}

	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts() throws Exception {
		return secondservice.getAllProducts();
	}

	@GetMapping("/getProduct/{productId}")
	public Product findUser(@PathVariable int productId) throws Exception {

		System.out.println("The product id is " + productId);

		Product product = secondservice.findProduct(productId);

		return product;

	}

	@PostMapping("/registerProdJson")
	public String registerProduct(@RequestBody Product product) throws Exception {
		System.out.println(product.getProductId() + ", " + product.getProductName() + "," + product.getPrice());

		secondservice.register(product);

		return "Product Successfully Added";
	}

	@DeleteMapping("/deleteProduct/{productId}")
	public String deleteProd(@PathVariable(required = true) Integer productId) throws Exception {

		String res = secondservice.deleteProduct(productId);

		return res;
	}

}
