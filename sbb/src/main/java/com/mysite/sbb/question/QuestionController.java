package com.mysite.sbb.question;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.sbb.answer.AnswerForm;

@Controller
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService qService;

	@RequestMapping("/list")
	public String list(Model model) {
		List<Question> qList = qService.getList();
		model.addAttribute("qList", qList);
		return "question_list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id,
						AnswerForm answerForm) {
		Question question = this.qService.getQuestion(id);
		model.addAttribute("question", question);
        return "question_detail";
    }
	
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }
    
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, 
    							 BindingResult bindingResult ) {
    	if(bindingResult.hasErrors()) {
    		return "question_form";
    	}    	
        this.qService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
    }
    
    
}






