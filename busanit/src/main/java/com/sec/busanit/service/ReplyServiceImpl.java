package com.sec.busanit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sec.busanit.mapper.ReplyMapper;
import com.sec.busanit.model.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replyMapper;
	
	@Override
	public int register(ReplyVO reply) {
		return replyMapper.insert(reply);
	}

	@Override
	public ReplyVO read(int rno) {
		return replyMapper.read(rno);
	}

	@Override
	public int remove(int rno) {
		return replyMapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(int bno) {
		return replyMapper.getReplyList(bno);
	}

	@Override
	public List<ReplyVO> getReplyList(int bno) {
		return replyMapper.getReplyList(bno);
	}

	@Override
	public int replyCount(int bno) {
		return replyMapper.replyCount(bno);
	}

}
