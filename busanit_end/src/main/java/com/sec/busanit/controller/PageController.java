package com.sec.busanit.controller;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sec.busanit.model.BoardVO;
import com.sec.busanit.model.ReplyVO;
import com.sec.busanit.service.BoardService;
import com.sec.busanit.service.ReplyService;

@Controller
@RequestMapping("/")
public class PageController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ReplyService replyService;
	
	@GetMapping({"","home"})
	public String home(Model model) {

		int count = boardService.NoticeCount()+1;
		model.addAttribute("count", count);
		System.out.println("count================="+count);
		return "home";
	}
	
	@GetMapping("list/{pageNum}")
	public ResponseEntity<List<BoardVO>> list(Model model, @PathVariable("pageNum") String pageNum) {

		System.out.println("====================="+pageNum);
		
		int currentPage = pageNum==null?0:Integer.parseInt(pageNum);
		int pageSize=3+currentPage;
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("pageStart", 0);
		hm.put("pageSize",  pageSize);
		hm.put("role",  null);
		hm.put("course",  null);
		
		List<BoardVO> list = boardService.getNotice(hm);
		
		for(BoardVO vo:list)
		System.out.println(vo.toString());
		
		return new ResponseEntity<List<BoardVO>>(boardService.getNotice(hm),HttpStatus.OK);
	}
	
	@GetMapping("register")
	public String register() {
		return "register";
	}
	
//	그냥 글 쓰기
	@PostMapping("insert")
	public String classInsert(BoardVO board) {
		
		boardService.insert(board);
		
		return "redirect:/home";
	}
	
	@GetMapping("detail/{bno}")
	public String noticeDetail(@PathVariable("bno") int bno, Model model) {
		
		BoardVO board = boardService.read(bno);
		List<ReplyVO> reply = replyService.getReplyList(bno);
		int replycount = replyService.replyCount(bno);
		
		model.addAttribute("replycount", replycount);
		model.addAttribute("reply", reply);
		model.addAttribute("board", board);
		model.addAttribute("bno", bno);
		
		return "/detail";
	}
	
	@GetMapping("update")
	public String noticeUpdate(@RequestParam("bno") int bno, Model model) {

		BoardVO board = boardService.read(bno);
		
		model.addAttribute("board", board);
		
		return "update";
	}
	
	@PostMapping("update")
	public String noticeUpdate(BoardVO board) {
		System.out.println(board);
	
		BoardVO b = boardService.read(board.getBno());
		
		if(board.getWriter()==null)
			board.setWriter(b.getWriter());
		if(board.getRole()==null)
			board.setRole(b.getRole());
		if(board.getCourse()==null)
			board.setCourse(b.getCourse());
			
		boardService.update(board);
		
		return "redirect:/home";
	}

//	공지 삭제
	@GetMapping("delete")
	public String noticeDelete(int bno) {
		boardService.delete(bno);
		
		return "redirect:/home";
	}
	
}