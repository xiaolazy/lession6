package com.biz.lesson.web.controller.school;

import com.biz.lesson.business.system.PageGrid;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.web.controller.BaseController;
import com.biz.lesson5.school.model.ResultBean;
import com.biz.lesson5.school.model.Student;
import com.biz.lesson5.school.model.ext.StudentDTO;
import com.biz.lesson5.school.model.ext.StudentScoreDTO;
import com.biz.lesson5.school.model.ext.StudentSubjectDTO;
import com.biz.lesson5.school.service.StudentService;
import com.biz.lesson5.school.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("student/")
public class StudentController extends BaseController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectService subjectService;

    @RequestMapping("list")
//    @PreAuthorize("hasAuthority('OPT_USER_LIST')")
    public ModelAndView pageLink(){
        ModelAndView modelAndView = new ModelAndView("lesson6/student/list");
        return modelAndView;
    }
    @RequestMapping("findAll")
    @ResponseBody
    public Object findAll() throws Exception{
        return studentService.findAll();
    }
    @RequestMapping("page")
    @ResponseBody
    public Object page(PageGrid page, String name) throws Exception{
        Page<Student> page1 = studentService.page(page, name);
        return page1;
    }

    @RequestMapping("add")
//    @PreAuthorize("hasAuthority('OPT_USER_ADD')")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("lesson6/student/add");

        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("edit")
//    @PreAuthorize("hasAuthority('OPT_USER_EDIT')")
    public ModelAndView edit(@RequestParam("id") String id, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("lesson6/student/edit");
        Student one = studentService.getOne(id);
        BusinessAsserts.notNull(one,"所编辑学生信息的已不存在！");
        modelAndView.addObject("data", one);

        return modelAndView;
    }

    @RequestMapping("/detail")
    public ModelAndView detail(String userId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("lesson6/student/detail");

        return modelAndView;
    }

    @RequestMapping("/enterFraction")
    @ResponseBody
    public Object enterFraction(StudentScoreDTO subjectScore, BindingResult result, HttpServletRequest request) throws Exception {
        studentService.enterFraction(subjectScore);
        return ResultBean.success("success");
    }
    @RequestMapping("/linkenter")
    public ModelAndView linkenter(@RequestParam String id,ModelAndView modelAndView) throws Exception {
        modelAndView.setViewName("lesson6/student/enterFira");
        Student one = studentService.getOne(id);
        BusinessAsserts.notNull(one,"所编辑学生信息的已不存在");
        modelAndView.addObject("data", one);
        modelAndView.addObject("subjects",subjectService.findAll());
        return modelAndView;
    }
    @RequestMapping("/linkelective")
    public ModelAndView linkelective(@RequestParam String id,ModelAndView modelAndView) throws Exception {
        modelAndView.setViewName("lesson6/student/elective");
        Student one = studentService.getOne(id);
        BusinessAsserts.notNull(one,"所编辑学生信息的已不存在");
        modelAndView.addObject("data", one);
        modelAndView.addObject("subjects",subjectService.findAll());
        return modelAndView;
    }
    @RequestMapping("/elective")
    @ResponseBody
    public Object elective(StudentSubjectDTO studentSubjectDTO, BindingResult result, HttpServletRequest request) throws Exception {
        studentService.elective(studentSubjectDTO);
        return ResultBean.success("success");
    }

    @RequestMapping("/save_add")
    @ResponseBody
    public Object save_add( @Valid StudentDTO studentDTO, BindingResult result, HttpServletRequest request) throws Exception {
        Student student = studentService.save(studentDTO.getSubject());
        return ResultBean.success("success");
    }

    @RequestMapping("/save_edit")
    @ResponseBody
//    @PreAuthorize("hasAuthority('OPT_USER_EDIT')")
    public Object save_edit(@RequestParam("id") String id, @Valid StudentDTO studentDTO, BindingResult result, HttpServletRequest request) throws Exception {
        Student one = studentService.getOne(id);
        BusinessAsserts.notNull(one,"您所编辑的信息不存在");
        Student student = studentService.saveAndFlush(studentDTO.getSubject(one));
        return ResultBean.success("success");
    }

    @RequestMapping(value = "delete", method = GET)
    @ResponseBody
    public Object save_delete(@RequestParam("id") String id) {
        studentService.delete(id);
        return ResultBean.success("删除成功");
    }
}
