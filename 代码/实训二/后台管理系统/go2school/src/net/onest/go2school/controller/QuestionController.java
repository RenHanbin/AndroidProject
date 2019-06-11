package net.onest.go2school.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.onest.go2school.entity.Question;
import net.onest.go2school.service.impl.QuestionServiceImpl;

@Controller
@RequestMapping("question")
public class QuestionController {
	@Autowired
	private QuestionServiceImpl questionServiceImpl;

	@RequestMapping(value = "getQuestionList", produces = "application/json;charset=utf-8")
	public String getQuestionList(Model model) {
		List<Question> questionList = questionServiceImpl.findAllQuestions();
		model.addAttribute("questionList", questionList);
		model.addAttribute("remark", "getQuestionList");
		System.out.println("ִ执行了findAllQuestions");
		return "questions";
	}

	@RequestMapping(value = "getLikeQuestionList", produces = "application/json;charset=utf-8")
	public String getLikeQuestionList(@RequestParam("searchstr") String str, Model model, HttpSession session) {
		List<Question> questionListLike = questionServiceImpl.findQuestionByLike(str);
		session.setAttribute("search", str);
		model.addAttribute("questionListLike", questionListLike);
		model.addAttribute("remark", "getLikeQuestionList");
		System.out.println("ִ执行了findQuestionByLike" + questionListLike);
		return "questions";
	}

	@RequestMapping(value = "deleteQuestion", produces = "application/json;charset=utf-8")
	public String deleteQuestion(@RequestParam("questionId") int questionId, @RequestParam("remark1") String remark1,
			HttpSession session, Model model) {
		questionServiceImpl.deleteQuestion(questionId);
		if ("all".equals(remark1)) {
			List<Question> questionList = questionServiceImpl.findAllQuestions();
			model.addAttribute("questionList", questionList);
			model.addAttribute("remark", "getQuestionList");
			System.out.println("ִ执行了findAllQuestions");
		} else if ("like".equals(remark1)) {
			String str = (String) session.getAttribute("search");
			System.out.println("searchsearchsearchsearch" + str);
			List<Question> questionListLike = questionServiceImpl.findQuestionByLike(str);
			model.addAttribute("questionListLike", questionListLike);
			model.addAttribute("remark", "getLikeQuestionList");
			System.out.println("ִ执行了findQuestionByLike" + questionListLike);
		}
		return "questions";
	}

}
