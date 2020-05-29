package com.biz.lesson.dao.admin;

import org.springframework.stereotype.Repository;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.admin.Resource;

@Repository
public interface ResourceRepository extends CommonJpaRepository<Resource, Long>, ResourceDao{



}
