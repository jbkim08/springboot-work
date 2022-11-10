package com.sec.busanit.service;

import java.util.HashMap;
import java.util.List;

import com.sec.busanit.model.UserVO;

public interface UserService {
	public void join(UserVO user);
	public UserVO idCheck(String username);
	public UserVO loginCheck(String username);
	//아이디 중복체크
	public int idDupCheck(String username);

	//userInfo - 회원 리스트 조회
	public List<UserVO> getList();
	
	//유저 한명 삭제
	boolean deleteOneUser(String username);
	
//	회원 삭제, 정보 수정, 정보 확인
	public UserVO detail(String username);
	public boolean delete(String username);
	public boolean update(UserVO user);
	public int userCount(HashMap<String, Object> hm);
	public List<UserVO> findAll(HashMap<String, Object> hm);
//	닉네임 중복 확인
	public int nickCheck(String nickname);
}
