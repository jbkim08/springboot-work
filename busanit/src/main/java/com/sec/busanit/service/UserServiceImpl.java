package com.sec.busanit.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sec.busanit.mapper.UserMapper;
import com.sec.busanit.model.UserVO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public void join(UserVO user) {
		userMapper.join(user);

	}

	@Override
	public UserVO idCheck(String username) {
		return userMapper.idCheck(username);
	}

	@Override
	public UserVO loginCheck(String username) {
		return userMapper.loginCheck(username);
	}
	
	@Override
	public int idDupCheck(String username) {
		return userMapper.idDupCheck(username);
	}

	@Override
	public List<UserVO> getList() {
		return userMapper.getList();
	}

	@Override
	public boolean deleteOneUser(String username) {
		return userMapper.deleteOneUser(username) == 1;
	}

	@Override
	public UserVO detail(String username) {
		return userMapper.detail(username);
	}

	@Override
	public boolean delete(String username) {
		return userMapper.delete(username) == 1;
	}

	@Override
	public boolean update(UserVO user) {
		return userMapper.update(user) == 1;
	}

	@Override
	public int userCount(HashMap<String, Object> hm) {
		return userMapper.userCount(hm);
	}

	@Override
	public List<UserVO> findAll(HashMap<String, Object> hm) {
		return userMapper.findAll(hm);
	}

	@Override
	public int nickCheck(String nickname) {
		return userMapper.nickCheck(nickname);
	}

}
