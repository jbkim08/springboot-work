package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository qRepo;
	
	@Autowired
	private AnswerRepository aRepo;
	
	@Test
	void testJPA() {
		//데이터 입력
//		Question q1 = new Question();
//        q1.setSubject("sbb가 무엇인가요?");
//        q1.setContent("sbb에 대해서 알고 싶습니다.");
//        q1.setCreateDate(LocalDateTime.now());
//        this.qRepo.save(q1);
//        
//        Question q2 = new Question();
//        q2.setSubject("스프링부트 모델 질문입니다.");
//        q2.setContent("id는 자동으로 생성되나요?");
//        q2.setCreateDate(LocalDateTime.now());
//        this.qRepo.save(q2);  // 두번째 질문 저장  
		
		//전체 데이터 조회
//		List<Question> all = this.qRepo.findAll();
//		assertEquals(2, all.size());
//		
//		Question q = all.get(0); //첫번째 질문
//		assertEquals("sbb가 무엇인가요?", q.getSubject());
		
//         Optional<Question> oq = this.qRepo.findById(1);
//        if(oq.isPresent()) {
//            Question q = oq.get();
//            System.out.println(q.getContent());
//            assertEquals("sbb가 무엇인가요?", q.getSubject());
//        }	
		
//        List<Question> list = this.qRepo.findBySubject("sbb가 무엇인가요?");
//        assertEquals(1, list.get(0).getId());	//첫번째 질문인지 확인	
		
//        Question q = this.qRepo.findBySubjectAndContent(
//                "sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
//        assertEquals(1, q.getId());
		
//        List<Question> qList = this.qRepo.findBySubjectLike("sbb%");
//        Question q = qList.get(0);
//        assertEquals("sbb가 무엇인가요?", q.getSubject());	
		
		//수정
//        Optional<Question> oq = this.qRepo.findById(1);
//        assertTrue(oq.isPresent());//true면 합격
//        Question q = oq.get();
//        q.setSubject("수정된 제목");
//        this.qRepo.save(q);	//업데이트	(save의 입력객체가 똑같은 id가 있으면 자동 업데이트)
		
		//삭제
//        assertEquals(2, this.qRepo.count()); //데이터 갯수
//        Optional<Question> oq = this.qRepo.findById(1);
//        assertTrue(oq.isPresent());
//        Question q = oq.get();
//        this.qRepo.delete(q);
//        assertEquals(1, this.qRepo.count());		
		
        Optional<Question> oq = this.qRepo.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        
        Answer a = new Answer();
        a.setContent("두번째 답변입니다.");
        a.setQuestion(q);  // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
        a.setCreateDate(LocalDateTime.now());
        this.aRepo.save(a);        

		
	}

}








