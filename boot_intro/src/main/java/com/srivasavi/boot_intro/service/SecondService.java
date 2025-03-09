package com.srivasavi.boot_intro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srivasavi.boot_intro.dto.Product;
import com.srivasavi.boot_intro.repository.SecondRepository;

@Service
public class SecondService {

	@Autowired
	SecondRepository secondrepository;

	public void register(Product product) throws Exception {

		secondrepository.saveProduct(product);
	}

	public Product findProduct(int productId) throws Exception {

		Product product = secondrepository.findProductById(productId);

		return product;
	}

	public List<Product> getAllProducts() throws Exception {
		return secondrepository.findAllProducts();
	}

	public String deleteProduct(Integer  productId) throws Exception {

		String res = secondrepository.deleteProduct(productId);

		return res;
	}

}