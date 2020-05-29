package com.biz.lesson.dao.admin;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.admin.MenuItem;


@Repository
public interface MenuItemRepository extends CommonJpaRepository<MenuItem, Long>, MenuItemDao{

    List<MenuItem> findByMainMenu_IdOrderByCodeAsc(Long mainMenuId);

}
