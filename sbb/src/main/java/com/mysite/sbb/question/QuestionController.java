package com.mysite.sbb.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuestionController {
	
	@Autowired
	private QuestionService qService;

	@RequestMapping("/question/list")
	public String list(Model model) {
		List<Question> qList = qService.getList();
		model.addAttribute("qList", qList);
		return "question_list";
	}
	
	@GetMapping("/question/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
        return "question_detail";
    }
}
