package com.mysite.sbb.answer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;

@Controller
@RequestMapping("/answer")
public class AnswerController {

	@Autowired
	private QuestionService qService;
	
	@Autowired
	private AnswerService aService;

	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id, 
							@Valid AnswerForm answerForm, BindingResult bindingResult) {
		Question question = this.qService.getQuestion(id);
		if(bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "question_detail";
		}		
		this.aService.create(question, answerForm.getContent());
		return String.format("redirect:/question/detail/%s", id);
	}

}
