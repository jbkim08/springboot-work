package com.sec.busanit.service;

import java.util.List;

import com.sec.busanit.model.ReplyVO;

public interface ReplyService {
	
	public int register(ReplyVO reply);
	public ReplyVO read(int rno);
	public int remove(int rno);
	public List<ReplyVO> getList(int bno);
	
	public List<ReplyVO> getReplyList(int bno);
	public int replyCount(int bno);
}
