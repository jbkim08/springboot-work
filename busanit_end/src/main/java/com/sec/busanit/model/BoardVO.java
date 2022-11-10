package com.sec.busanit.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BoardVO {
	private int bno;
	private String writer; // User userid
	private String title;
	private String content;
	@JsonFormat(pattern = "yy.MM.dd a HH:mm", timezone = "Asia/Seoul")
	private Date regdate;
	private int hitcount;
	private String course;
	private String role;
	private int replycount;
	private int fileCount;
	
	private List<FileVO> fileList;
}
