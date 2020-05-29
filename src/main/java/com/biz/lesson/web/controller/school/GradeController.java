package com.biz.lesson.web.controller.school;

import com.biz.lesson.business.system.PageGrid;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.web.controller.BaseController;
import com.biz.lesson5.school.model.Grade;
import com.biz.lesson5.school.model.ResultBean;
import com.biz.lesson5.school.model.ext.GradeDTO;
import com.biz.lesson5.school.service.GradeService;
import com.biz.lesson5.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("grade/")
public class GradeController extends BaseController {

    @Autowired
    private GradeService gradeService;
    @Autowired
    private StudentService studentService;


    @RequestMapping("list")
    public ModelAndView pageLink(){
        ModelAndView modelAndView = new ModelAndView("lesson6/grade/list");
        return modelAndView;
    }

    @RequestMapping("findAll")
    @ResponseBody
    public Object findAll() throws Exception{
        return ResultBean.successData(gradeService.findAll());
    }

    @RequestMapping("page")
    @ResponseBody
    public Object page(PageGrid page, String name) throws Exception{
        Page<Grade> page1 = gradeService.page(page, name);
        return page1;
    }

    @RequestMapping("add")
//    @PreAuthorize("hasAuthority('OPT_USER_ADD')")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("lesson6/grade/add");

        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("edit")
//    @PreAuthorize("hasAuthority('OPT_USER_EDIT')")
    public ModelAndView edit(@RequestParam("id") String id, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("lesson6/grade/edit");
        Grade one = gradeService.getOne(id);
        BusinessAsserts.notNull(one,"所编辑的班级已不存在！");
        modelAndView.addObject("data", one);
        modelAndView.addObject("students", studentService.findAll());
        return modelAndView;
    }

    @RequestMapping("/detail")
    public ModelAndView detail(String userId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("lesson6/grade/detail");

        return modelAndView;
    }

    @RequestMapping("/save_add")
    @ResponseBody
//    @PreAuthorize("hasAuthority('OPT_USER_ADD')")
    public Object save_add(@Valid GradeDTO gradeDTO, BindingResult result, HttpServletRequest request) throws Exception {
        Grade grade = gradeService.saveAndFlush(gradeDTO.getGrade());
        return ResultBean.success("success");
    }

    @RequestMapping("/save_edit")
    @ResponseBody
//    @PreAuthorize("hasAuthority('OPT_USER_EDIT')")
    public Object save_edit(@RequestParam("id") String id,@Valid GradeDTO gradeDTO, BindingResult result, HttpServletRequest request) throws Exception {
        gradeService.saveGrade(id,gradeDTO);
        return ResultBean.success("success");
    }

    @RequestMapping(value = "delete", method = GET)
    @ResponseBody
    public Object save_delete(@RequestParam("id") String id) {
        gradeService.delete(id);
        return ResultBean.success("删除成功");
    }
}
