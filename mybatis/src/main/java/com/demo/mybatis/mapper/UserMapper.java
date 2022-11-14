package com.demo.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.demo.mybatis.model.User;

@Mapper
public interface UserMapper {

	@Select("select * from user where id = #{id}")
	User getUser(String id);
	
	@Select("select * from user")
	List<User> getUserList();

	@Insert("insert into user values(#{id}, #{name}, #{phone}, #{address})")
	int insertUser(String id, String name, String phone, String address);
}
