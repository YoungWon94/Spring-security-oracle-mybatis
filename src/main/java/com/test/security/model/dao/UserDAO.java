package com.test.security.model.dao;

import java.util.Map;

public interface UserDAO { //이 예제는 마이바티스에 전달할때 dto로 전달하는 방법 말고 맵으로 전달하는 방법 사용
	//회원가입 처리
	public int insertUser(Map<String,String> map);
	//로그인 처리
	public Map<String,Object> selectUser(String userid);
}
