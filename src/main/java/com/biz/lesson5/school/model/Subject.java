package com.biz.lesson5.school.model;

import com.biz.lesson.model.PersistentAble;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @program: lession6
 * @description:
 * @author: lyy
 * @generate: 2020-05-25 16:12
 **/
@Entity
@Table(name = "sc_subject")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject implements PersistentAble {

    private static final long serialVersionUID = -1682349799092177474L;

    @Id
    @GenericGenerator(name = "g_uuid",strategy = "uuid")
    @GeneratedValue(generator = "g_uuid")
    @Column(nullable = false,length = 32)
    private String id;

    @Column(length = 10,nullable = false)
    private String name;

    @Column(length = 10)
    private Integer num;

    @Column(name = "avg_score",columnDefinition = "decimal(6,2)")
    private BigDecimal avgScore;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "subjects")
    private Set<Student> students;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "subject",cascade = CascadeType.REFRESH)
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
}
