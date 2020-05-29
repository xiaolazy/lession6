package com.biz.lesson5.school.model.ext;

import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.PersistentAble;
import com.biz.lesson5.school.model.Grade;
import com.biz.lesson5.school.model.Student;
import com.biz.lesson5.school.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.CascadeType.REMOVE;

/**
 * @program: lession6
 * @description:
 * @author: lyy
 * @generate: 2020-05-25 16:10
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO implements Serializable {
    private static final long serialVersionUID = -1682349799092177474L;

    private String id;
    @NotBlank(message = "姓名不能为空！")
    @Length(max = 10,message = "姓名长度不能大于10个字符")
    private String name;

    @Length(max = 222,message = "头像长度不能大于222个字符")
    private String avatar;
    /**
     * 0-> 男(FALSE)   1- > 女(TRUE)
     **/
    private Boolean sex;

    private Timestamp birthday;

    public Student getSubject(){
        BusinessAsserts.notNull(this,"类型转换异常：{不能将一个null转为Student对象}");
        return Student.builder().name(this.name).avatar(this.avatar).sex(this.sex)
                .birthday(this.birthday).build();
    }
    public Student getSubject(Student stu){
        BusinessAsserts.notNull(this,"类型转换异常：{不能将一个null转为Student对象}");
        if(StringUtils.isNotBlank(this.name))stu.setName(this.name);
        if(StringUtils.isNotBlank(this.avatar)) stu.setAvatar(this.avatar);
        stu.setSex(this.sex);
        stu.setBirthday(this.birthday);
        return stu;
    }
}
