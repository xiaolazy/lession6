package com.biz.lesson5.school.model.ext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.PersistentAble;
import com.biz.lesson5.school.model.Grade;
import com.biz.lesson5.school.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: lession6
 * @description:
 * @author: lyy
 * @generate: 2020-05-25 16:11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeDTO implements Serializable {
    private static final long serialVersionUID = -1682349799092177474L;
    private String id;
    @NotBlank
    @Length(max = 10)
    private String name;

    private String ids;
    public String getId() {
        BusinessAsserts.notBlank(this.id,"所编辑班级不存在");
        return id;
    }

    public Set<Object> getStudentIds(){
        return new HashSet<>(JSONArray.parseArray(this.ids));
    }
    public Grade getGrade(){
        BusinessAsserts.notNull(this,"类型转换异常：{不能将一个null转为Grade对象}");
        return Grade.builder().name(this.name).build();
    }
    public Grade getGrade(Grade grade){
        BusinessAsserts.notNull(this,"类型转换异常：{不能将一个null转为Grade对象}");
        grade.setName(name);
        return grade;
    }
}
