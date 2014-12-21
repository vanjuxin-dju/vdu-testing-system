package com.epam.testingsystem.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.testingsystem.domain.User;
import com.epam.testingsystem.domain.enums.RoleNum;
import com.epam.testingsystem.service.UserService;

@Controller
@RequestMapping("")
public class LoginController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public String login(HttpSession session) {
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
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String registerForm() {
		return "register";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String registerForm(
			@RequestParam(value="username", required=true) String username,
			@RequestParam(value="password", required=true) String password,
			@RequestParam(value="reppassword", required=true) String reppassword,
			@RequestParam(value="uname", required=true) String uname,
			HttpServletRequest request, HttpSession session, Model model) {
		User user = new User();
		user.setNickName(username);
		if (!password.equals(reppassword)) {
			model.addAttribute("error", "Passwords are not equal!");
			return "register";
		} else {
			user.setPassword(password);
		}
		user.setRole(RoleNum.user);
		user.setName(uname);
		userService.saveUser(user);
		user = userService.loadUser(username, password);
		session.setAttribute("user", user);
		return "redirect:/profile/" + user.getId();
	}
}
