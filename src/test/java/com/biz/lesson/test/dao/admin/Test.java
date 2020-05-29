package com.biz.lesson.test.dao.admin;

import com.biz.lesson5.school.service.GradeService;
import com.biz.lesson5.school.model.Grade;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: lession6
 * @description:
 * @author: lyy
 * @generate: 2020-05-26 10:47
 **/
public class Test {
    @Autowired
    private GradeService gradeService;

    @org.junit.Test
    public void test(){
        Map<String,String> map = Maps.newHashMap();
        map.put("1","110");
        map.put("2","120");
        map.put("3","130");
        List<String> ids = Lists.newArrayList();
        ids.add("1");
        ids.add("2");
        ids.add("3");

        Stream<String> stringStream = ids.stream().flatMap(e -> {
            List<String> maps = Lists.newArrayList();
            maps.add(map.get(e));
//            cc.add(map.get(e));
            return maps.stream();
        });
        List<String> collect = stringStream.collect(Collectors.toList());
        collect.forEach(System.out::println);
        Grade grade= Grade.builder().name("终极一班").build();
//        Grade grade1 = gradeService.saveAndFlush(grade);
        System.out.println();
    }

//    @org.junit.Test
//    public void test(){
//        List<String> list = Arrays.asList("img/jpa/cc.png,img/jpa/aa.png", "img/jpa/dd.png,img/jpa/ee.png");
//
//    //将每个元素转成一个新的且不带逗号的元素
////        Stream<String> s3 = list.stream().flatMap(s -> {
////            //将每个元素转换成一个stream
////            String[] split = s.split(",");
////            Stream<String> s2 = Arrays.stream(split);
////            return s2;
////        });
////        s3.forEach(System.out::println);
////        String s = "　　Password!1　　　　　　　".replaceAll(" ", "");
////        System.out.println(isBlank(s));
////        System.out.println(replace("　　Passw  ord!1　　　　　　　")+":00000000");
////
////        Stream<Integer> test = Stream.of(6, 4, 6, 7, 3, 9, 8, 10, 12, 14, 14);
////        test.filter(e -> e > 7).distinct().skip(2).limit(3).sorted().forEach(System.out::println);
//        List<String> aa = Arrays.asList("img/jpa/cc.png,img/jpa/aa.png","img/jpa/dd.png,img/jpa/ee.png");
//        Stream<String> stringStream = aa.stream().flatMap(e -> {
//            String[] split = e.split(",");
//            Stream<String> s2 = Arrays.stream(split);
//            return s2.sorted();
//        });
//        stringStream.forEach(System.out::println);
////        list.stream().map(e -> {
////            String[] split = e.split(",");
////            Stream<String> stream = Arrays.stream(split);
////            return stream;
////        });
////        list.forEach(System.out::println);
//    }
//
//    private static String replace(CharSequence cs){
//        StringBuffer rest = new StringBuffer();
//        for (int i = 0; i < cs.length(); i++) {
//            if (!Character.isWhitespace(cs.charAt(i))) {
//                rest.append(cs.charAt(i));
//            }
//        }
//        return rest.toString();
//    }
//
//    private static Boolean isBlank(CharSequence cs){
//        if(cs == null || cs.length() == 0)
//            return true;
//        for (int i = 0; i < cs.length(); i++) {
//            if (!Character.isWhitespace(cs.charAt(i))) {
//                return false;
//            }
//        }
//        return true;
//    }
}
