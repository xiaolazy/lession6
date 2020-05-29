package com.biz.lesson.dao.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.biz.lesson.model.admin.UserLoginLog;
import com.biz.lesson.vo.admin.SearchLoginLog;

public interface UserLoginLogDao {

	public Page<UserLoginLog> search(Pageable pageable,SearchLoginLog condition);
}
