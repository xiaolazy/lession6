package com.biz.lesson.dao.school;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson5.school.model.Grade;
import com.biz.lesson5.school.model.StudentSubject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;


public class StudentSubjectRepositoryImpl extends CommonJpaRepositoryBean<StudentSubject, String> implements StudentSubjectDao {

	@Autowired
	public StudentSubjectRepositoryImpl(EntityManager em) {
		super(StudentSubject.class, em);
	}
	
	
}
