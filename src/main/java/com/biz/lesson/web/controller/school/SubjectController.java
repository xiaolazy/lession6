package com.biz.lesson.web.controller.school;

import com.biz.lesson.business.system.PageGrid;
import com.biz.lesson.business.user.UserManager;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.admin.Role;
import com.biz.lesson.model.admin.User;
import com.biz.lesson.vo.user.UserVo;
import com.biz.lesson.web.controller.BaseController;
import com.biz.lesson.web.servlet.DataStorageThreadLocalHolder;
import com.biz.lesson5.school.model.Grade;
import com.biz.lesson5.school.model.ResultBean;
import com.biz.lesson5.school.model.Student;
import com.biz.lesson5.school.model.Subject;
import com.biz.lesson5.school.model.ext.StudentDTO;
import com.biz.lesson5.school.model.ext.SubjectDTO;
import com.biz.lesson5.school.service.StudentService;
import com.biz.lesson5.school.service.SubjectService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("subject/")
public class SubjectController extends BaseController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StudentService studentService;

    @RequestMapping("list")
//    @PreAuthorize("hasAuthority('OPT_USER_LIST')")
    public ModelAndView pageLink(){
        ModelAndView modelAndView = new ModelAndView("lesson6/subject/list");
        return modelAndView;
    }
    @RequestMapping("page")
    @ResponseBody
    public Object page(PageGrid page, String name) throws Exception{
        Page<Subject> page1 = subjectService.page(page, name);
        return page1;
    }

    @RequestMapping("add")
//    @PreAuthorize("hasAuthority('OPT_USER_ADD')")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("lesson6/subject/add");

        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("edit")
//    @PreAuthorize("hasAuthority('OPT_USER_EDIT')")
    public ModelAndView edit(@RequestParam("id") String id, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("lesson6/subject/edit");
        Subject one = subjectService.getOne(id);
        BusinessAsserts.notNull(one,"所编辑的班级已不存在！");
        modelAndView.addObject("data", one);
        return modelAndView;
    }

    @RequestMapping("/detail")
    public ModelAndView detail(String userId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("lesson6/subject/detail");

        return modelAndView;
    }

    @RequestMapping("/save_add")
    @ResponseBody
//    @PreAuthorize("hasAuthority('OPT_USER_ADD')")
    public Object save_add(@Valid SubjectDTO subjectDTO, BindingResult result, HttpServletRequest request) throws Exception {
        Subject subject = subjectService.saveAndFlush(subjectDTO.getSubject());
        return ResultBean.success("success");
    }

    @RequestMapping("/save_edit")
    @ResponseBody
//    @PreAuthorize("hasAuthority('OPT_USER_EDIT')")
    public Object save_edit(@RequestParam("id") String id,@Valid SubjectDTO subjectDTO, BindingResult result, HttpServletRequest request) throws Exception {
        Subject one = subjectService.getOne(id);
        BusinessAsserts.notNull(one,"您所编辑的信息不存在");
        Subject subject = subjectService.saveAndFlush(subjectDTO.getSubject(one));
        return ResultBean.success("success");
    }

    @RequestMapping(value = "delete", method = GET)
//    @PreAuthorize("hasAuthority('OPT_USER_DELETE')")
    @ResponseBody
    public Object save_delete(@RequestParam("id") String id) {
        subjectService.delete(id);
        return ResultBean.success("删除成功");
    }
}
