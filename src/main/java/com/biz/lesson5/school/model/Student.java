package com.biz.lesson5.school.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.biz.lesson.model.PersistentAble;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;
import static javax.persistence.CascadeType.*;

/**
 * @program: lession6
 * @description:
 * @author: lyy
 * @generate: 2020-05-25 16:10
 **/
@Entity
@Table(name = "sc_student")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","grade"})
public class Student implements PersistentAble {
    private static final long serialVersionUID = -1682349799092177474L;

    @Id
    @GenericGenerator(name = "g_uuid",strategy = "uuid")
    @GeneratedValue(generator = "g_uuid")
    @Column(nullable = false,length = 32)
    private String id;

    @Column(length = 10,nullable = false)
    private String name;
    @Column(length = 222)
    private String avatar;
    /**
     * 0-> 男(FALSE)   1- > 女(TRUE)
     **/
    @Column(columnDefinition = "tinyint default 0")
    private Boolean sex;

    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp birthday;

    @Column(name = "avg_score",columnDefinition = "decimal(6,2)",nullable = false)
    private BigDecimal avgScore;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {REFRESH,REMOVE})
    @JoinColumn(name = "grade_id")
    @JSONField(serialize = false)
    private Grade grade;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {REFRESH,REMOVE})
    @JoinTable(name = "sc_student_subject",
    inverseJoinColumns = @JoinColumn(name = "stu_id"),
    joinColumns = @JoinColumn(name = "sub_id"))
    private Set<Subject> subjects;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "student",cascade = CascadeType.REFRESH)
    private Set<StudentSubject> studentSubjects;

    /**
     * 插入时间
     **/
    @Column(name = "gmt_create",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
            ,insertable = false,updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp gmtCreate;
    /**
     * 更新时间
     **/
    @Column(name = "gmt_modified",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
            ,insertable = false,updatable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp gmtModified;

    @Override
    public Serializable getId() {
        return id;
    }

    @Transient
    private String sexName;
    @Transient
    private String gradeName;

    public String getGradeName() {
        return this.grade == null ? "" : this.grade.getName();
    }

    public String getSexName() {
        return sex.equals(Boolean.valueOf(false)) ? "男" : "女";
    }
}
