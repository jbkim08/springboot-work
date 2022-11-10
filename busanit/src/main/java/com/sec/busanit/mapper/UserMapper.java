package com.sec.busanit.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sec.busanit.model.UserVO;

@Mapper
public interface UserMapper {
	
	public void join(UserVO user);
	public UserVO idCheck(String username);
//	jh code
//	public int idCheck(String userid);

	public UserVO loginCheck(String username);
	
	//아이디 중복체크
	public int idDupCheck(String username);
	
	//유저 리스트 조회
	public List<UserVO> getList();

	//유저 한명 삭제
	public int deleteOneUser(String username);
	
//	회원 수정, 삭제, 정보 조회
	public UserVO detail(String username);
	public int delete(String username);
	public int update(UserVO user);
	public int userCount(HashMap<String, Object> hm);
	public List<UserVO> findAll(HashMap<String, Object> hm);
//	닉네임 중복 확인
	public int nickCheck(String nickname);
	
}
