package com.biz.lesson5.school.service;

import com.biz.lesson.business.BaseCrudService;
import com.biz.lesson.business.BaseService;
import com.biz.lesson.business.system.PageGrid;
import com.biz.lesson.dao.school.GradeRepository;
import com.biz.lesson.dao.school.StudentRepository;
import com.biz.lesson.dao.school.StudentSubjectRepository;
import com.biz.lesson.dao.school.SubjectRepository;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson5.school.model.Student;
import com.biz.lesson5.school.model.StudentSubject;
import com.biz.lesson5.school.model.Subject;
import com.biz.lesson5.school.model.ext.StudentScoreDTO;
import com.biz.lesson5.school.model.ext.StudentSubjectDTO;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户服务类
 */
@Transactional
@Service("studentService")
@SuppressWarnings("all")
public class StudentService extends BaseService implements BaseCrudService<Student,String> {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentSubjectRepository studentSubjectRepository;

    /**
     * 录入学分
     **/
    public void enterFraction(StudentScoreDTO studentScoreDTO){
        String stuid = studentScoreDTO.getId();
        Student student = studentRepository.getOne(stuid);
        System.out.println(student.getName().toString());
        BusinessAsserts.exists(student,stuid);
        List<StudentScoreDTO.SubjectScore> stu = studentScoreDTO.getSubjectScore();
        Set<StudentSubject> studentSubjects = Sets.newHashSet();
        Set<Subject> subjects = Sets.newHashSet();
        stu.forEach(e->{
            if(StringUtils.isNotBlank(e.getScore())){
                Subject subject = subjectRepository.getOne(e.getSubId());
                BusinessAsserts.exists(subject,e.getSubId());
                StudentSubject studentSubject = StudentSubject.builder().score(new BigDecimal(e.getScore())).student(student).subject(subject).build();
                StudentSubject save = studentSubjectRepository.save(studentSubject);
                subjects.add(subject);
                studentSubjects.add(save);
            }
        });
        student.setSubjects(subjects);
        student.setStudentSubjects(studentSubjects);
        studentRepository.saveAndFlush(student);
    }
    /**
     * 计算每门科平均分
     **/
    public void avgSubject(Subject subject){

    }
    /**
     * 计算每人平均分
     **/
    public void avgStudent(Student student){

    }
    /**
     * 选课
     **/
    public void elective(StudentSubjectDTO studentSubjectDTO)  throws Exception{
        String id = studentSubjectDTO.getId();
        Set<String> ids = studentSubjectDTO.getIds();
        Student student = studentRepository.getOne(id);
        BusinessAsserts.exists(student,id);
        //查询所选学科
        HashSet<Subject> subjects = new HashSet<>(ids.stream().flatMap(e -> {
            List<Subject> temp = Lists.newArrayList();
            Subject one = subjectRepository.getOne(e);
            BusinessAsserts.exists(one,id);
            temp.add(one);
            return temp.stream();
        }).collect(Collectors.toList()));
//        student.setSubjects(null);
        student.setSubjects(subjects);
        Student rest = studentRepository.save(student);

    }


    @Override
    public Page<Student> page(PageGrid var1, String name) throws Exception {
        Page<Student> all = studentRepository.findAll(getSpec(name),var1.getPageRequest());
        return all;
    }

    public Specification<Student> getSpec(String name){
        return new Specification<Student>(){
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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
    public JpaRepository<Student, String> getEntityDao() {
        return studentRepository;
    }
}