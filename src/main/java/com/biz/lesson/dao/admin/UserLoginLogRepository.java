package com.biz.lesson.dao.admin;

import org.springframework.stereotype.Repository;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.admin.UserLoginLog;


@Repository
public interface UserLoginLogRepository extends CommonJpaRepository<UserLoginLog, String>, UserLoginLogDao{


}
