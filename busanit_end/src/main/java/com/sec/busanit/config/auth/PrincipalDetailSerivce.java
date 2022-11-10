package com.sec.busanit.config.auth;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sec.busanit.mapper.UserMapper;
import com.sec.busanit.model.UserVO;

@Service
public class PrincipalDetailSerivce implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO user = userMapper.idCheck(username);
		if(user == null)
			return null;
		
		PrincipalDetails puser = new PrincipalDetails(user);
		session.setAttribute("suser", user);
		System.out.println(puser);
		return puser;
	}

}
