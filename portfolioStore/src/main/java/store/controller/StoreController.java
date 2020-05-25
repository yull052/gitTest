package store.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StoreController {
	//	store 메인
	@RequestMapping(value = "storeMain.do", method = RequestMethod.GET)
	public String storeMain(Locale locale, Model model) {

		return "/store/storeMain";
	}
}
