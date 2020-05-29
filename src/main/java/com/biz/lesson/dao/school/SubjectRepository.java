package com.biz.lesson.dao.school;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson5.school.model.Subject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SubjectRepository extends CommonJpaRepository<Subject, String>, SubjectDao {


    @Modifying
    @Query(value = "update adm_menuitem set mainMenu_id=:mainMenu_id where id=:id", nativeQuery = true)
    void updateItemParentId(@Param("mainMenu_id") Long mainMenu_id, @Param("id") Long id);
}
