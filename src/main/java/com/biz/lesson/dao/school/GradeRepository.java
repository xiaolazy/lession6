package com.biz.lesson.dao.school;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson5.school.model.Grade;
import org.springframework.stereotype.Repository;


@Repository
public interface GradeRepository extends CommonJpaRepository<Grade, String>, GradeDao{
}
