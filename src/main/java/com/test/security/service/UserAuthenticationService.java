package com.test.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.test.security.model.dto.UserDTO;

/*
 *
 * /user/login_check.do 요청이 오면 UserDetailsService를 구현한 클래스를 찾아서 
 * loadUserByUsernmae(String userid)메서드를 실행시킴. 
 * 이 메서드는 사용자의 아이디(userid)를 인자로 받아 사용자 정보를 확인한 후 
 * UserDetails 타입으로 사용자의 세부정보를 리턴함. 
 * 인자로 받은 아이디(userid)을 가지고 디비에서 사용자의 정보를 얻어와야 함으로 
 * sqlSessionTemplate sqlSession이 필요(마이바티스 사용할 경우).
 * 이후 이 메서드의 성공 실패 여부에 따라서 시큐리티 설정 파일에서 정의한 
 * 성공했을때의 핸들러, 실패했을때의 핸들러로 분기하게 됨.
 * 
 */


public class UserAuthenticationService  implements UserDetailsService{

	private SqlSessionTemplate sqlSession;
	public UserAuthenticationService() {}
	public UserAuthenticationService(SqlSessionTemplate sqlSession) { //bean 생성을 위한 생성자
		this.sqlSession=sqlSession;
	}
	
	@Override //로그인 처리 메서드
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		Map<String,Object> user = sqlSession.selectOne("user.selectUser", userid);//아이디로만 가져오지만 내부적으로 비밀번호 체크함
		if(user==null)
			throw new UsernameNotFoundException(userid);
		List<GrantedAuthority> authority = new ArrayList<>();
		authority.add(new SimpleGrantedAuthority(
				user.get("AUTHORITY").toString())); //필드명 대문자로
		return new UserDTO(
				user.get("USERNAME").toString(),
				user.get("PASSWORD").toString(),
				(Integer)Integer.valueOf(user.get("ENABLED").toString())==1, //Integer로 바꿔줘야됨.
																			//안바꿔주면 오라클에서 bigIntger타입으로 넘어와서 에러남.
				true,true,true,authority,
				user.get("USERNAME").toString()
				);
	}	
}
