package com.epam.testingsystem.web;

import javax.servlet.http.HttpServletResponse;
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
import com.epam.testingsystem.domain.User;
import com.epam.testingsystem.service.SubjectService;
import com.epam.testingsystem.web.responses.Action;

@Controller
@RequestMapping("/subject")
public class SubjectController {
	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listSubjects() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("subjects", subjectService.getSubjects());
		mav.setViewName("subject.list");
		return mav;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showSubjectDetails(
			@PathVariable("id") Subject subject,
			HttpSession session, Model model) {
		model.addAttribute("subject", subject);
		return "subject.details";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newSubjectForm(Model model) {
		Subject s = new Subject();
		s.setId(0);
		s.setName("Enter subject name");
		
		model.addAttribute("subject", s);
		model.addAttribute("action", "new");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("subject.form");
		return mav;
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String newSubjectSubmit(
			@ModelAttribute Subject subject,
			HttpSession session, Model model) {
		if (subject != null) {
			User user = (User)session.getAttribute("user");
			subject.setAdmin(user);
			subjectService.saveSubject(subject);
			model.addAttribute("subjects", subjectService.getSubjects());
		}
		model.asMap().remove("subject");
		return "subject.list";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editSubjectForm(
			@PathVariable("id") Subject subject,
			HttpSession session, Model model) {
		model.addAttribute("subject", subject);
		model.addAttribute("action", subject.getId());
		return "subject.form.edit";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String editSubjectSubmit(
			@ModelAttribute Subject subject,
			Model model) {
		if (subject != null) {
			subjectService.updateSubject(subject);
			model.addAttribute("subjects", subjectService.getSubjects());
		}
		model.asMap().remove("subject");
		return "subject.list";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Action deleteSubject(
			@PathVariable("id") Subject subject) {
		subjectService.deleteSubject(subject);
		return new Action(true);
	}
}
