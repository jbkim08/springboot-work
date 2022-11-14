package com.demo.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mybatis.mapper.UserMapper;
import com.demo.mybatis.model.User;

//Rest는 메소드 실행시 view없이 데이터를 jason형식으로 리턴
@RestController
public class UserController {

	@Autowired
	UserMapper userMapper;
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable String id) {
		User user = userMapper.getUser(id);
		return user;
	}
	
	@GetMapping("/users")
	public List<User> getUserList() {
		List<User> userList = userMapper.getUserList();
		return userList;
	}
	
	@PostMapping("/users")
	public void createUser( @RequestParam("id") String id, 
							@RequestParam("name") String name,
							@RequestParam("phone") String phone,
							@RequestParam("address") String address  ) {	
		
		userMapper.insertUser(id, name, phone, address);		
	}
	
	@PutMapping("/users/{id}")
	public void editUser(@PathVariable String id,
						 @RequestParam("name") String name,
						 @RequestParam("phone") String phone,
						 @RequestParam("address") String address) {
		userMapper.updateUser(id, name, phone, address);
	}
	
}




