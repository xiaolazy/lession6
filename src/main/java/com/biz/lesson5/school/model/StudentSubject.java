package com.biz.lesson5.school.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @program: lession6
 * @description:
 * @author: lyy
 * @generate: 2020-05-26 17:44
 **/
@Entity
@Table(name = "sc_student_subject")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentSubject {
    @Id
    @GenericGenerator(name = "g_uuid",strategy = "uuid")
    @GeneratedValue(generator = "g_uuid")
    @Column(nullable = false,length = 32)
    private String id;

    @ManyToOne
    @JoinColumn(name = "stu_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "sub_id")
    private Subject subject;

    @Column(columnDefinition = "decimal(6,2)",nullable = false)
    private BigDecimal score;

}
