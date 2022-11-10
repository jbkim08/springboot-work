package com.sec.busanit.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileVO {

	private int fno;
	private String savefolder;
	private String originfile;
	private String savefile;
	private String filetype;
	private String role;
	private String writer;
	private String content;
	@JsonFormat(pattern = "MM.dd a HH:mm", timezone = "Asia/Seoul")
	private Date regdate;
	private int bno;
	
}
