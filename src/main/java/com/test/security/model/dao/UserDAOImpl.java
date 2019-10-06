package com.test.security.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public int insertUser(Map<String, String> map) {		
		return sqlSession.insert("user.insertUser",map);
	}

	@Override
	public Map<String, Object> selectUser(String userid) {		
		return sqlSession.selectOne("user.selectUser", userid);
	}

}
