package com.biz.lesson.dao.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import com.biz.lesson.model.admin.AccessLogPo;
import com.biz.lesson.vo.admin.AccessLogSearchVo;

@NoRepositoryBean
public interface AccessLogDao {

	 Page<AccessLogPo> search(AccessLogSearchVo reqVo, Pageable pageable);
}
