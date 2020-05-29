package com.biz.lesson.dao.admin;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson.model.admin.MenuItem;


public class MenuItemRepositoryImpl extends CommonJpaRepositoryBean<MenuItem, String> implements MenuItemDao{

	@Autowired
	public MenuItemRepositoryImpl(EntityManager em) {
		super(MenuItem.class, em);
	}
	
	
}
