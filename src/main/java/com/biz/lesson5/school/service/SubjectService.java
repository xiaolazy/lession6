package com.biz.lesson5.school.service;

import com.biz.lesson.business.BaseCrudService;
import com.biz.lesson.business.BaseService;
import com.biz.lesson.business.system.PageGrid;
import com.biz.lesson.dao.school.GradeRepository;
import com.biz.lesson.dao.school.SubjectRepository;
import com.biz.lesson5.school.model.Subject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户服务类
 */
@Transactional
@Service("subjectService")
@SuppressWarnings("all")
public class SubjectService extends BaseService implements BaseCrudService<Subject,String> {

    private static final Logger logger = LoggerFactory.getLogger(SubjectService.class);

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private GradeRepository gradeRepository;


    @Override
    public Page<Subject> page(PageGrid var1, String name) throws Exception {
        Page<Subject> all = subjectRepository.findAll(getSpec(name),var1.getPageRequest());
        return all;
    }

    public Specification<Subject> getSpec(String name){
        return new Specification<Subject>(){
            @Override
            public Predicate toPredicate(Root<Subject> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(StringUtils.isNotBlank(name)){
                    Predicate likeName = criteriaBuilder.like(root.get("name").as(String.class),"%"+name+"%");
                    predicates.add(likeName);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }


    @Override
    public JpaRepository<Subject, String> getEntityDao() {
        return subjectRepository;
    }
}