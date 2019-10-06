package com.test.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class ShaEncoder {
	
	@Autowired //security-context.xml에 선언된 passwordEncoder bean 주입
	private ShaPasswordEncoder encoder;	
	
	//평문을 암호화하는 코드
	public String saltEncoding(String str, String salt) { //salt = 키값, 평문(str)을 키값(salt)에 따라 암호화 시킴.
		return encoder.encodePassword(str, salt);
	}
}
