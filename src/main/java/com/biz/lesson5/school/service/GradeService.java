package com.biz.lesson5.school.service;

import com.biz.lesson.business.BaseCrudService;
import com.biz.lesson.business.BaseService;
import com.biz.lesson.business.system.PageGrid;
import com.biz.lesson.dao.school.GradeRepository;
import com.biz.lesson.dao.school.StudentRepository;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson5.school.model.Grade;
import com.biz.lesson5.school.model.Student;
import com.biz.lesson5.school.model.ext.GradeDTO;
import com.google.common.collect.Lists;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 用户服务类
 */
@Transactional
@Service("gradeService")
@SuppressWarnings("all")
public class GradeService extends BaseService implements BaseCrudService<Grade,String> {

    private static final Logger logger = LoggerFactory.getLogger(GradeService.class);

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private StudentRepository studentRepository;

    public void saveGrade(String id,GradeDTO gradeDTO){
        Grade one = gradeRepository.getOne(id);
        BusinessAsserts.notNull(one,"您所编辑的信息不存在");
        Grade newGrade = gradeDTO.getGrade(one);
        Set<Object> studentIds = gradeDTO.getStudentIds();
        List<Student> studentList = studentIds.stream().flatMap(e -> {
            List<Student> students = Lists.newArrayList();
            Student temp = studentRepository.getOne((String) e);
            BusinessAsserts.notNull(temp, "您所选择的学生不存在");
            students.add(temp);
            return students.stream();
        }).collect(Collectors.toList());
        newGrade.setNum(studentList.size());
        Grade grade = gradeRepository.save(newGrade);
        studentList.forEach(e->{
            e.setGrade(newGrade);
            studentRepository.save(e);
        });
    }

    @Override
    public Page<Grade> page(PageGrid var1, String name) throws Exception {
        Page<Grade> all = gradeRepository.findAll(getSpec(name),var1.getPageRequest());
        return all;
    }

    public Specification<Grade> getSpec(String name){
        return new Specification<Grade>(){
            @Override
            public Predicate toPredicate(Root<Grade> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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
    public JpaRepository<Grade, String> getEntityDao() {
        return gradeRepository;
    }
}