package com.test.security.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.security.model.dao.UserDAO;
import com.test.security.service.ShaEncoder;

@Controller
public class UserController {
	
	@Autowired
	ShaEncoder shaEncoder;
	
	@Autowired
	UserDAO userDao;
	
	@RequestMapping("/")
	public String home(Model model) {
		return "home";
	}
	
	//로그인 페이지로 이동
	@RequestMapping("/user/login.do")
	public String login() {
		return "user/login";
	}
	
	// 회원가입 페이지로 이동
	@RequestMapping("/user/join.do")
	public String join() {
		return "user/join";
	}
	
	// 회원가입 
		@RequestMapping("/user/insertUser.do")
		public String insertUser(@RequestParam String userid,
				@RequestParam String passwd,
				@RequestParam String name,
				@RequestParam String authority) {
			//비밀번호 암호화.
			String dbpw = shaEncoder.saltEncoding(passwd, userid);
			Map<String,String> map = new HashMap<>();
			map.put("userid", userid);
			map.put("passwd", dbpw);
			map.put("name", name);
			map.put("authority", authority);
			//affected rows, 영향을 받은 행의 수가 리턴됨.
			int result=userDao.insertUser(map);
			System.out.println("result:::"+result);
			return "user/login";
		}
		
		
		//관리자 페이지로 이동
		@RequestMapping("/admin/")
		public String admin() {
			return "user/admin";
		}
	
	
}
