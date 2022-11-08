package com.mysite.sbb.question;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository qRepo;
	
	public List<Question> getList(){
		return this.qRepo.findAll();
	}
	
	public Question getQuestion(Integer id) {
		Optional<Question> question = this.qRepo.findById(id);
		if(question.isPresent()) {
			return question.get(); //있으면 질문객체 리턴
		} else {
			//질문이 없을 경우에 예외처리 메세지 : 질문을 못 찾음
			throw new DataNotFoundException("question not found");
		}
	}
}





