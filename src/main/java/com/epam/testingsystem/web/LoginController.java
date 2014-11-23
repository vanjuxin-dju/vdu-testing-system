package com.epam.testingsystem.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.testingsystem.domain.User;
import com.epam.testingsystem.service.UserService;

@Controller
@RequestMapping("")
public class LoginController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(
			@RequestParam(value="username", required=true) String username,
			@RequestParam(value="password", required=true) String password,
			HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		User user = userService.loadUser(username, password);
		if (user != null) {
			session.setAttribute("user", user);
			mav = new ModelAndView("redirect:/profile/" + user.getId());
		} else {
			mav.addObject("error", "Incorrect username and/or password!");
			mav.setViewName("login");
		}
		return mav;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.setAttribute("user", null);
		session.invalidate();
		return "login";
	}
}
