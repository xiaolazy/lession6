package com.biz.lesson.dao.admin;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson.model.admin.User;


public class UserRepositoryImpl extends CommonJpaRepositoryBean<User, String> implements UserDao{

	@Autowired
	public UserRepositoryImpl(EntityManager em) {
		super(User.class, em);
	}
	
	
}
