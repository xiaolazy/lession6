package com.biz.lesson5.school.model.ext;

import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.PersistentAble;
import com.biz.lesson5.school.model.Student;
import com.biz.lesson5.school.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO implements Serializable {

    private static final long serialVersionUID = -1682349799092177474L;
    private String id;
    @NotBlank(message = "姓名不能为空！")
    @Length(max = 10,message = "姓名长度不能大于10个字符")
    private String name;

    public Subject getSubject(){
        BusinessAsserts.notNull(this,"类型转换异常：{不能将一个null转为Subject对象}");
        return Subject.builder().name(this.name).build();
    }
    public Subject getSubject(Subject subject){
        BusinessAsserts.notNull(this,"类型转换异常：{不能将一个null转为Subject对象}");
        subject.setName(this.name);
        return subject;
    }

}
