package com.mysite.sbb.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository qRepo;
	
	public List<Question> getList(){
		return this.qRepo.findAll();
	}
}
