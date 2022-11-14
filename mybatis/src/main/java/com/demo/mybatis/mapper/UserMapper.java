package com.demo.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.demo.mybatis.model.User;

@Mapper
public interface UserMapper {

	@Select("select * from user where id = #{id}")
	User getUser(String id);
}
