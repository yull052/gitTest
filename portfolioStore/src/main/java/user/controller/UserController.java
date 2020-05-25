package user.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import user.bean.UserDTO;
import user.dao.UserDAO;

@Controller
public class UserController {	
	@Autowired
	private UserDAO userDAO;
	
	//	회원가입 폼
	@RequestMapping(value = "joinForm.do", method = RequestMethod.GET)
	public String joinForm(Locale locale, Model model) {
		
		return "/user/joinForm";
	}
	
	//	아이디 중복 체크
	@ResponseBody
	@RequestMapping(value = "dupeId.do", method = RequestMethod.POST)
	public boolean dupeId(@RequestParam String userId) {
		// userId를 DB로 전달
		UserDTO userDTO = userDAO.dupeId(userId);
		
		if(userDTO == null) { //중복아이디 존재하지않음
			return true;
		}
		else {//  중복아이디 존재함 
			return false;
		}
	}
	
	//	회원가입
	@RequestMapping(value = "join.do", method = RequestMethod.POST)
	public ModelAndView join(@ModelAttribute UserDTO userDTO, HttpSession session) {
		//	session 생성
		session.setAttribute("userName", userDTO.getUserName());
		session.setAttribute("userId", userDTO.getUserId());
		
		//	회원가입폼을 DB로 전달 
		if(userDTO.getUserEmail().equals("")) {//	이메일 입력값 없을 시 "-" 추가
			userDTO.setUserEmail("-");
		}
		userDAO.join(userDTO);
		System.out.println("회원가입완료");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/store/storeMain");
		return mav;
	}
	
	//	정보 찾기 폼
	@RequestMapping(value = "findInfoForm.do", method = RequestMethod.GET)
	public String findInfoForm(Locale locale, Model model) {
		return "/user/findInfoForm";
	}
	
	//	정보 찾기
	@ResponseBody
	@RequestMapping(value = "findInfo.do", method = RequestMethod.POST)
	public String findInfo(@ModelAttribute UserDTO userDTO) {
		//	정보를 DB로 전달
		String result = userDAO.findInfo(userDTO);
		System.out.println("정보조회 완료");
		
		if(userDTO.getUserId()!=null && result!=null) {
			result = "pwd";
		}
		return result;
	}

	//	비밀번호 변경 폼
	@RequestMapping(value = "chanPwdForm.do", method = RequestMethod.GET)
	public ModelAndView chanPwdForm(@RequestParam String userId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("userId", userId);
		mav.setViewName("/user/chanPwdForm");
		return mav;
	}
	
	//	비밀번호 변경
	@RequestMapping(value = "chanPwd.do", method = RequestMethod.POST)
	public String chanPwdForm(@ModelAttribute UserDTO userDTO) {
		//	비밀번호 전달
		userDAO.chanPwd(userDTO);
		System.out.println("변경완료");
		return "/user/joinForm";
	}
	
	
		
	

	
}
