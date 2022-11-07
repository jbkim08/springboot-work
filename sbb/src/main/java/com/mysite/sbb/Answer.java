package com.mysite.sbb;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;			//답변 고유번호(기본키, 자동생성)
	
    @Column(columnDefinition = "TEXT")
    private String content;		//답변 내용

    private LocalDateTime createDate; //답변 단 날짜

    @ManyToOne
    private Question question;		//해당 질문(1=> 답변 N)
	
}






