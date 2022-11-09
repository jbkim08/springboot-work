package com.mysite.sbb.question;

import java.time.LocalDateTime;
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
	// 새로운 질문을 저장
    public void create(String subject, String content) {
        Question q = new Question();
        q.setSubject(subject);				//제목
        q.setContent(content);				//내용
        q.setCreateDate(LocalDateTime.now()); //현재시간
        this.qRepo.save(q);			//리포지토리에 저장
    }
}





