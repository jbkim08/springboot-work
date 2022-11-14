package com.demo.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
