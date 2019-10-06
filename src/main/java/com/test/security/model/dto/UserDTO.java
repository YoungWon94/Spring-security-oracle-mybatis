package com.test.security.model.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDTO extends User{ //SpringSecurity에서 제공해주는 user를 상속받아 작성.
	
	private String userid;
	
	/* 부모 클래스의 생성자 */
	public UserDTO(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,
			String userid) { //추가한 필드를 파라미터에 추가
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userid = userid; //필드에 주입(?)
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "UserDTO [userid=" + userid + "]";
	}
	
	
	


	
}
