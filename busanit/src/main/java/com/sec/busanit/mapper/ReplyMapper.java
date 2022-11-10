package com.sec.busanit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sec.busanit.model.ReplyVO;

@Mapper
public interface ReplyMapper {

	public int insert(ReplyVO reply);

	public ReplyVO read(int rno);

	public int delete(int rno);

	public List<ReplyVO> getReplyList(int bno);

	public int replyCount(int bno);

}
