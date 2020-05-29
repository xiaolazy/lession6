package com.biz.lesson.test.dao.admin;

import java.util.Set;

import com.biz.lesson.dao.school.StudentRepository;
import com.biz.lesson5.school.model.Student;
import com.biz.lesson5.school.model.Subject;
import com.google.common.collect.Sets;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.biz.lesson.business.user.UserManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application-content.xml")
public class LoginLogTest {

	@Autowired
	private UserManager userManager;

	@Autowired
	private StudentRepository studentRepository;


	@Before
	public void setUp() throws Exception {

	}

	@After
	public void clean() throws Exception {

	}

	@Test
	public void testSearch() throws Exception {
	}



}
