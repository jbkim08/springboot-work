package com.sec.busanit.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sec.busanit.model.PageVO;
import com.sec.busanit.model.UserVO;
import com.sec.busanit.service.UserService;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/member/*")
public class UserController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserService userService;
	
	//user update
	@GetMapping("update")
	public String update(@RequestParam("username") String username, Model model) {
		model.addAttribute("list", userService.detail(username));
		log.info("update............controller.........."+username);
		return "/member/detail";
	}
	
	@PostMapping("update")
	@ResponseBody
	public String update(@RequestBody UserVO user, HttpSession session, Model model) {
//		UserVO vo = (UserVO)session.getAttribute("suser");
//		model.addAttribute("suser", vo);
		int nCount = userService.nickCheck(user.getNickname());
		log.info("update session............"+user);
		if (nCount != 0) {
			return "fail";
		}
		userService.update(user);
		session.setAttribute("suser", user);
//		session.invalidate();
		return "success";
	}
	
	//user delete
	@GetMapping("delete")
	public String delete(@RequestParam("username") String username) {
		userService.delete(username);
		log.info("======================="+username);
		return "redirect:/logout";
	}
	
	//user Delete - admin delete 
	@GetMapping("adminDelete")
	public String deleteOneUser(@RequestParam("username") String username) {
		log.info("delete.......controller......."+username);
		userService.deleteOneUser(username);
		return "redirect:/member/userInfo";
	}
	
	//one user
	@GetMapping("detail")
	public String detail(@RequestParam("username") String username, Model model, HttpSession session) {
		UserVO user = userService.loginCheck(username); 
		session.setAttribute("suser", user);
		model.addAttribute("list", userService.detail(username));
		log.info("controller...detail....username......"+username);
		return "/member/detail";
	}
	
	//idDupCheck
	@PostMapping("idDupCheck")
	@ResponseBody
	public String idDupCheck(String username) {
		log.info("controller......username..." + username);
		int cnt = userService.idDupCheck(username);
		if(cnt != 0) { //0은 아이디중복없음. 아이디로 회원가입가능
			return "true";
		}else {
			return "false"; 
		}
	}
	
	//JOIN
	@GetMapping("join")
	public String join(Model model) {
		String course = "JOIN US";
		model.addAttribute("course", course);
		return "member/join";
	}
	
	@PostMapping("joinProc")
	@ResponseBody
	public String joinProc(@RequestBody UserVO user) {
		int cnt = userService.idDupCheck(user.getUsername());
		int nCount = userService.nickCheck(user.getNickname());
		if(cnt != 0) {
			return "failForId";
		}else if(nCount != 0) {
			return "failForNick";
		}		
		String rowPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rowPassword);
		user.setPassword(encPassword);
		System.out.println(encPassword);
		System.out.println(rowPassword);
		userService.join(user);
		log.info("controller.....user........"+user);
		return "success";
	}
	
	//Login
	@GetMapping("login")
	public String loginForm(Model model,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "exception", required = false) String exception) {
		String course = "LOGIN";
		model.addAttribute("course", course);
		
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		return "/member/login";
	}
	
	//User list
	@GetMapping("userInfo")
	public String userInfo(Model model, String pageNum, @RequestParam(name="word", defaultValue = "") String word) {
		int currentPage = pageNum == null? 1 : Integer.parseInt(pageNum);
		int pageSize = 5;
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("word", word);
		hm.put("pageStart", (currentPage-1)*pageSize);
		hm.put("pageSize",  pageSize);
		
		List<UserVO> uList = userService.findAll(hm);
		int count = userService.userCount(hm);
		String course = "USER INFO";
		
		PageVO page = new PageVO(count, currentPage, pageSize);
		model.addAttribute("list", uList);
		model.addAttribute("course", course);
		model.addAttribute("p", page);
		model.addAttribute("word", word);
		return "member/userInfo";
		
	}
	
	@PostMapping("userInfo")
	public String userInfo(UserVO user) {
		return "member/userInfo";
	}
}
