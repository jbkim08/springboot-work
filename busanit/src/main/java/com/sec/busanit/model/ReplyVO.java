package com.sec.busanit.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReplyVO {
	
	private int rno;
	private int bno;
	private String reply;
	private String replyer;
	@JsonFormat(pattern = "HH:mm", timezone = "Asia/Seoul")
	private Date replydate;
}
