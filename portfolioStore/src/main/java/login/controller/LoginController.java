package login.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import login.service.KakaoAPI;
import user.bean.UserDTO;
import user.dao.UserDAO;
 
@Controller
public class LoginController {
	@Autowired
	private KakaoAPI kakao;
	
	@Autowired
	private UserDAO userDAO;
	
	@Inject
    PasswordEncoder passwordEncoder;
	
	//	로그인 폼
    @RequestMapping(value="loginForm.do")
    public String loginForm(Locale locale, Model model) {
        
        return "/login/loginForm";
    }
    
    //	로그인 
    @ResponseBody
    @RequestMapping(value="login.do")
    public boolean login(@RequestParam Map<String,String> map , HttpSession session) {
    	
    	UserDTO userDTO = userDAO.login(map);	
    	String rawPwd = map.get("userPwd");
    	
    	 if(passwordEncoder.matches(rawPwd, userDTO.getUserPwd())) {// 비밀번호 암호화 값 비교
    		session.setAttribute("userId", userDTO.getUserId());
     		session.setAttribute("userName", userDTO.getUserName());
     		return true;
         }else {
     		return false;
         }

    }
    
    //	카카오 로그인
    @RequestMapping(value="kakaoLogin.do")
    public String kakoLogin(@RequestParam(value="code", required=false) String code, HttpSession session) {
    	if(session.getAttribute("userId")==null) {
	    	 String access_Token = kakao.getAccessToken(code);//API 토큰 받아오기
	    	 HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);//유저 정보
	    	    System.out.println("login Controller : " + userInfo);
	    	    
	    	    //	클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
	    	    if (userInfo.get("email") != null) {
	    	        session.setAttribute("userId", userInfo.get("email"));
	    	        session.setAttribute("userName", userInfo.get("nickname"));
	    	        session.setAttribute("access_Token", access_Token);
	    	        return "/store/storeMain";
	    	    }else {
	    	    	return "/login/loginForm";
	    	    }
    	}else {
    		return "/login/loginForm";
    	}
    }
    
    //	로그아웃
    @RequestMapping(value="kakaoLogout.do")
    public String logout(HttpSession session) {
    	if(session.getAttribute("access_Token")!=null) {
    		kakao.kakaoLogout((String)session.getAttribute("access_Token"));
    	}
        session.removeAttribute("access_Token");
        session.removeAttribute("userId");
        session.removeAttribute("userName");
        
        return "/main/index";
    }
}
