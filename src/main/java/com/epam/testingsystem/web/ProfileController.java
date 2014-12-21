package com.epam.testingsystem.web;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.testingsystem.domain.User;
import com.epam.testingsystem.service.UserService;
import com.epam.testingsystem.web.exception.NeedAuthorizationException;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/{idUser}", method = RequestMethod.GET)
	public ModelAndView userProfile(
			@PathVariable String idUser,
			@PathVariable("idUser") User user,
			HttpSession session, Model model) {
		Object userObject = session.getAttribute("user");
		if ((userObject != null) && (userObject instanceof User)) {
			User user1 = (User) userObject;
			System.out.println("user with id = " + user1.getId());
		} else {
			throw new NeedAuthorizationException();
		}
		
		Locale locale = LocaleContextHolder.getLocale();
		String language = locale.getLanguage();
		
		System.out.println("language = " + language);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("profile");
		mav.addObject("user", user);
		
		
		return mav;
	}
}
