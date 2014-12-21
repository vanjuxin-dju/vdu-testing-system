package com.epam.testingsystem.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epam.testingsystem.domain.Option;
import com.epam.testingsystem.domain.Question;
import com.epam.testingsystem.domain.Test;
import com.epam.testingsystem.domain.User;
import com.epam.testingsystem.domain.enums.RoleNum;
import com.epam.testingsystem.service.OptionService;
import com.epam.testingsystem.service.QuestionService;
import com.epam.testingsystem.service.UserService;
import com.epam.testingsystem.web.exception.ForbiddenException;
import com.epam.testingsystem.web.exception.NeedAuthorizationException;
import com.epam.testingsystem.web.responses.Action;
import com.epam.testingsystem.web.responses.QuestionWrapper;

@Controller
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	@Autowired
	private UserService userService;
	@Autowired
	private OptionService optionService;
	
	@RequestMapping(value = "/list/{testId}", method = RequestMethod.GET)
	@ResponseBody
	public QuestionWrapper[] listQuestions(
			@PathVariable("testId") Test test, HttpSession session) {
		User user = (User)session.getAttribute("user");
		return QuestionWrapper.questions(questionService.getQuestionsByTest(test), user.getRole() == RoleNum.admin);
	}
	
	@RequestMapping(value = "/test/{id}/new", method = RequestMethod.GET)
	public ModelAndView questionForm(
			@PathVariable("id") Test test, HttpSession session, Model model) {
		model.addAttribute("action", "new");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("question.form");
		return mav;
	}
	
	@RequestMapping(value = "/test/{id}/new", method = RequestMethod.POST)
	public String questionSubmit(
			@PathVariable("id") Test test,
			@RequestParam(value="question", required=true) String question,
			@RequestParam(value="option[]", required=true) String[] options,
			@RequestParam(value="correct[]", required=true) Integer[] correct,
			HttpSession session, Model model) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			throw new NeedAuthorizationException();
		} else if (user.getRole() != RoleNum.admin) {
			throw new ForbiddenException();
		}
		
		Question questn = new Question();
		questn.setId(0);
		questn.setQuestionContainer(question);
		questn.setTest(test);
		Integer id = questionService.saveQuestion(questn);
		
		questn.setId(id);
		//System.out.println("options " + options.length + " correct " + correct.length);
		
		List<Option> opts = new ArrayList<Option>();
		for (int i = 0; i < options.length; i++) {
			Option opt = new Option();
			//opt.setId(0);
			opt.setOptionContent(options[i]);
			if (Arrays.asList(correct).contains(i)) {
				opt.setCorrect(true);
			} else {
				opt.setCorrect(false);
			}
			opt.setQuestion(questn);
			opts.add(opt);
		}
		
		questn.setOptions(opts);
		questionService.updateQuestion(questn);
		return "redirect:/test/" + test.getId();
	}
	
	@RequestMapping(value = "/test/{id}/answers", method = RequestMethod.POST)
	@ResponseBody
	public Action answers(@PathVariable("id") Test test,
			@RequestParam(value="answers[]", required=true) Integer[] options,
			HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			throw new NeedAuthorizationException();
		} else if (user.getRole() != RoleNum.user) {
			throw new ForbiddenException();
		}
		
		List<Option> opts = new ArrayList<Option>();
		for (int i = 0; i < options.length; i++) {
			opts.add(optionService.getOptionById(options[i]));
		}
		user.getTests().add(test);
		user.getOptions().addAll(opts);
		
		userService.updateUser(user);
		return new Action(true);
	}
}
