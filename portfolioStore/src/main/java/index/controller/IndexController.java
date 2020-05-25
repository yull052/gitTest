package index.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {	
	//	index화면 
	@RequestMapping(value = "main.do", method = RequestMethod.GET)
	public String main(Locale locale, Model model) {

		return "/main/index";
	}
	
}
