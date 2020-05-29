package com.biz.lesson.dao.admin;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.admin.Role;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends CommonJpaRepository<Role, Long>, RoleDao{


}
