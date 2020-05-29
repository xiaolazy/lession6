package com.biz.lesson.dao.admin;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson.model.admin.Role;


public class RoleRepositoryImpl extends CommonJpaRepositoryBean<Role, String> implements RoleDao{

	@Autowired
	public RoleRepositoryImpl(EntityManager em) {
		super(Role.class, em);
	}
	
	
}
