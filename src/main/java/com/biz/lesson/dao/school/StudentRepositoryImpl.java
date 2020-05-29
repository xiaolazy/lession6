package com.biz.lesson.dao.school;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson5.school.model.Student;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;


public class StudentRepositoryImpl extends CommonJpaRepositoryBean<Student, String> implements StudentDao {

	@Autowired
	public StudentRepositoryImpl(EntityManager em) {
		super(Student.class, em);
	}
	
	
}
