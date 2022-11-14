package com.demo.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.demo.mybatis.model.User;

@Mapper
public interface UserMapper {

	@Select("select * from user where id = #{id}")
	User getUser(String id);
	
	@Select("select * from user")
	List<User> getUserList();

	@Insert("insert into user values(#{id}, #{name}, #{phone}, #{address})")
	int insertUser(String id, String name, String phone, String address);
	
	@Update("Update user SET name=#{name}, phone=#{phone}, address=#{address} WHERE id=#{id}")
	int updateUser(String id, String name, String phone, String address);
	
	@Delete("delete from user where id = #{id}")
	int deleteUser(String id);
}
