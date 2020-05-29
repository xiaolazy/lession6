package com.biz.lesson.business;

import com.biz.lesson.business.system.PageGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface BaseCrudService<T,PK extends Serializable> {

    JpaRepository<T, PK> getEntityDao();

    @Transactional
    default T getOne(final PK id){
        return getEntityDao().getOne(id);
    }

    @Transactional
    default List<T> findAll(){
        return getEntityDao().findAll();
    }

    Page<T> page(PageGrid var1, String name) throws Exception;

    @Transactional
    default Page<T> page(PageRequest var1){
        return getEntityDao().findAll(var1);
    }
    default T save(T entity){
        return getEntityDao().save(entity);
    }
    @Transactional
    default T saveAndFlush(T entity){
        return getEntityDao().saveAndFlush(entity);
    }

    @Transactional
    default void delete(PK id){
        getEntityDao().delete(id);
    }
}
