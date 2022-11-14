package com.demo.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.demo.mybatis.model.Product;

@Mapper
public interface ProductMapper {
	//실제 sql은 xml파일에 적는다(설정이 필요)
    Product selectProductById(Long id);
    List<Product> selectAllProducts();
    void insertProduct(Product product);
    void updateProduct(Product product);
    void deleteProductById(Long id);

}
