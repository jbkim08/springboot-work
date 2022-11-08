package com.mysite.sbb.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuestionController {
	
	@Autowired
	private QuestionRepository qRepo;

	@RequestMapping("/question/list")
	public String list(Model model) {
		List<Question> qList = qRepo.findAll();
		model.addAttribute("qList", qList);
		return "question_list";
	}
}
