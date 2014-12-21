package com.epam.testingsystem.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epam.testingsystem.domain.Subject;
import com.epam.testingsystem.domain.Test;
import com.epam.testingsystem.domain.User;
import com.epam.testingsystem.domain.enums.RoleNum;
import com.epam.testingsystem.service.TestService;
import com.epam.testingsystem.web.exception.ForbiddenException;
import com.epam.testingsystem.web.exception.NeedAuthorizationException;
import com.epam.testingsystem.web.responses.Action;
import com.epam.testingsystem.web.responses.TestWrapper;

@Controller
@RequestMapping(value = "/test")
public class TestsController {
	@Autowired
	private TestService testService;
	
	@RequestMapping(value = "/list/{subjectId}", method = RequestMethod.GET)
	@ResponseBody
	public TestWrapper[] listTests(
			@PathVariable("subjectId") Subject subj, HttpSession session ) {
		return TestWrapper.tests(testService.getTestsBySubject(subj));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String testDetails(
			@PathVariable("id") Test test,
			HttpSession session, Model model) {
		model.addAttribute("test", test);
		return "test.details";
	}
	
	@RequestMapping(value = "/subject/{id}/new", method = RequestMethod.GET)
	public ModelAndView newTestForm(
			@PathVariable("id") String subj,
			Model model) {
		Test test = new Test();
		test.setId(0);
		test.setName("Enter test name");
		
		model.addAttribute("test", test);
		//model.addAttribute("subjId", subj);
		model.addAttribute("action", "new");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("test.form");
		return mav;
	}
	@RequestMapping(value = "/subject/{id}/new", method = RequestMethod.POST)
	public String newTestCreate(
			@PathVariable("id") Subject subj,
			@ModelAttribute Test test,
			HttpSession session, Model model) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			throw new NeedAuthorizationException();
		} else if (user.getRole() != RoleNum.admin) {
			throw new ForbiddenException();
		}
		
		if (test != null) {
			test.setSubject(subj);
			testService.saveTest(test);
		}
		model.asMap().remove("test");
		return "redirect:/subject/" + subj.getId();
	}
	
	@RequestMapping(value = "/subject/{sid}/edit/{id}", method = RequestMethod.GET)
	public String editTestForm(
			@PathVariable("id") Test test,
			HttpSession session, Model model) {
		model.addAttribute("test", test);
		model.addAttribute("action", test.getId());
		return "test.form.edit";
	}
	
	@RequestMapping(value = "/subject/{sid}/edit/{id}", method = RequestMethod.POST)
	public String editTest(
			@PathVariable("sid") Subject subject,
			@ModelAttribute Test test,
			HttpSession session, Model model) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			throw new NeedAuthorizationException();
		} else if (user.getRole() != RoleNum.admin) {
			throw new ForbiddenException();
		}
		
		if (test != null) {
			test.setSubject(subject);
			testService.updateTest(test);
		}
		model.asMap().remove("test");
		return "redirect:/subject/" + test.getSubject().getId();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Action deleteSubject(
			@PathVariable("id") Test test, HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			throw new NeedAuthorizationException();
		} else if (user.getRole() != RoleNum.admin) {
			throw new ForbiddenException();
		}
		
		testService.deleteTest(test);
		return new Action(true);
	}
}
