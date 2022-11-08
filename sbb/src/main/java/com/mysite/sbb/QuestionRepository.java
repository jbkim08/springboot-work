package com.mysite.sbb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	//기본 CRUD는 생략	
	List<Question> findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String content);
	List<Question> findBySubjectLike(String subject);
}
