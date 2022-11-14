package com.demo.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mybatis.mapper.ProductMapper;
import com.demo.mybatis.model.Product;

@RestController
public class ProductController {

	@Autowired
	ProductMapper proMapper;
	
	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable Long id) {
		Product product = proMapper.selectProductById(id);
		return product;
	}
}
