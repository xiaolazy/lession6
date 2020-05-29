package com.biz.lesson.dao.school;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson5.school.model.Grade;
import com.biz.lesson5.school.model.StudentSubject;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentSubjectRepository extends CommonJpaRepository<StudentSubject, String>, StudentDao{

}
