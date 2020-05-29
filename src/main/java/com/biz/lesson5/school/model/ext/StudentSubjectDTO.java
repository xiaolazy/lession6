package com.biz.lesson5.school.model.ext;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @program: lession6
 * @description:
 * @author: lyy
 * @generate: 2020-05-25 16:10
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentSubjectDTO implements Serializable {
    private static final long serialVersionUID = -1682349799092177474L;

    @NotBlank(message = "学生信息有误，请稍后重试")
    private String id;
    @NotEmpty(message = "你所选课程信息有误，请再次确认")
    private Set<String> ids;
}

