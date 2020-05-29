package com.biz.lesson5.school.model.ext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson5.school.model.Student;
import com.biz.lesson5.school.model.StudentSubject;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
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
public class StudentScoreDTO implements Serializable {
    private static final long serialVersionUID = -1682349799092177474L;

    @NotBlank(message = "学生信息有误，请稍后重试")
    private String id;
    @NotBlank(message = "学生成绩信息有误，请确认是否输入成绩")
    private String stu;

    public String getStu(){
        return this.stu;
    }
    public  List<SubjectScore> getSubjectScore(){
        List<SubjectScore> subjectScores = JSON.parseArray(this.stu, SubjectScore.class);
        return subjectScores;
    }
    public static void main(String[] args) {
        List<SubjectScore> subjectScores = JSON.parseArray( "[{\"subId\":\"4028abb9725f93c901725f96111f0000\",\"score\":\"12\"}]" , SubjectScore.class);
             System.out.println();
    }
    public static class SubjectScore{
        private String subId;
        private String score;

        public void setSubId(String subId) {
            this.subId = subId;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getSubId() {
            return subId;
        }

        public String getScore() {
            return score;
        }
    }
}

