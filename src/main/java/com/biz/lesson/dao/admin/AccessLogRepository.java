package com.biz.lesson.dao.admin;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.admin.AccessLogPo;

@Repository
public interface AccessLogRepository extends CommonJpaRepository<AccessLogPo, Long>, AccessLogDao, JpaSpecificationExecutor<AccessLogPo> {

}
