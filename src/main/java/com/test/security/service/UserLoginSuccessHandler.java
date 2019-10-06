package com.test.security.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.test.security.model.dto.UserDTO;

public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
	
	//로그인 성공 했을때의 처리
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		UserDTO dto = (UserDTO)authentication.getPrincipal();
		String msg=authentication.getName()+"님 환영합니다.";
		request.setAttribute("msg", msg);		
		request.getRequestDispatcher("/").forward(request, response);		
	}
}
