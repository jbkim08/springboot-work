package com.sec.busanit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sec.busanit.model.ReplyVO;
import com.sec.busanit.service.ReplyService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/replies/*")
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@PostMapping(value = "/new", consumes = "application/json")
	public ResponseEntity<String> create (@RequestBody ReplyVO reply) {
		
		int insertCount = replyService.register(reply);
		
		return insertCount == 1 ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("getList/{bno}")
	public ResponseEntity<List<ReplyVO>> getList(@PathVariable("bno") int bno) {
		
		return new ResponseEntity<List<ReplyVO>>(replyService.getList(bno), HttpStatus.OK);
	}
	

//	댓글 삭제
	@DeleteMapping(value = "/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno") int rno) {

		return replyService.remove(rno) == 1 ? new ResponseEntity<String>(rno + "", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
