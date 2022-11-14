package com.demo.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {

	private Long prodId;	 //CamelCase 소문자단어 다음에 대문자로시작함 
	private String prodName; //DB에서는 prod_id , prod_name, prod_price	  
	private int prodPrice;
		
	public Product(String prodName, int prodPrice) {
		//id는 자동생성이므로 제외
		this.prodName = prodName;
		this.prodPrice = prodPrice;
	}
	
}
