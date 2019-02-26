package com.xa.xaufe.secmsweb.controller.teacher;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xa.xaufe.secmsweb.entity.Teacher;
import com.xa.xaufe.secmsweb.json.MyResponse;
import com.xa.xaufe.secmsweb.service.TeacherService;
import com.xa.xaufe.secmsweb.utils.BCrypt;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("teacher")
public class TeacherController {
    private static final int PERPAGE_SIZE = 5;
    @Resource
    private TeacherService teacherService;

    /**
     * 教师登录
     *
     * @param param json格式的用户登录数据
     * @return String
     */
    @RequestMapping(value = "login", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MyResponse login(@RequestParam(name = "param", required = true) String param, HttpSession session) {
        Teacher teacher = JSON.parseObject(param, new TypeReference<Teacher>() {
        });
        //Teacher loginUser = teacherService.login(teacher);
        Teacher loginUser = teacherService.findByTno(teacher.getTno());
        String captchaId = (String) session.getAttribute("vrifyCode");
        String inputRandomCode = teacher.getRandomCode();
        MyResponse resp = new MyResponse();
        if (!inputRandomCode.equals(captchaId)) {
            //说明验证码不对.
            resp.failure("randomCodeError");
            return resp;
        }
        if (loginUser == null) {
            resp.failure("tnoError");
            return resp;

        }
        if (BCrypt.checkpw(teacher.getPassword(), loginUser.getPassword())) {
            session.setAttribute("loginUser", loginUser);
            session.setAttribute("status", "teacher");
            resp.success();
        } else {
            resp.failure("passwordError");
        }
        return resp;
    }

    /**
     * 教师列表
     *
     * @param currentPageNumber 当前页码
     * @param model             Model
     * @return String
     */
    @RequestMapping("toTeacherList")
    public String toTeacherList(@RequestParam(name = "currentPageNumber", defaultValue = "0") int currentPageNumber, Model model) {
        Page<Teacher> teacherPage = teacherService.findAll(currentPageNumber, PERPAGE_SIZE);
        List<Teacher> teachersList = teacherPage.getContent();
        model.addAttribute("teachersList", teachersList);
        model.addAttribute("pager", teacherPage);
        return "admin/teacher_list";
    }

    /**
     * 跳转到教师课程信息
     *
     * @param model Model
     * @return String
     */
    @RequestMapping("teacherList")
    public String teacherList(Model model) {
        // Page<Teacher> studentPage = teacherService.findAll(currentPageNumber, PERPAGE_SIZE);
        List<Teacher> teacherList = teacherService.findAll();
        System.out.println("123");
        model.addAttribute("teachersList", teacherList);
        return JSON.toJSONString(teacherList);
    }

    /**
     * 跳转到教师添加页面
     *
     * @return String
     */
    @RequestMapping("toSave")
    public String toUpdate() {
        return "admin/teacher_save";
    }

    /**
     * 真正的添加
     *
     * @param param json格式的教师信息数据
     * @return json
     */
    @RequestMapping(value = "save", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MyResponse save(@RequestParam(name = "param", required = true) String param, HttpSession session) {
        Teacher teacher = JSON.parseObject(param, new TypeReference<Teacher>() {
        });
        // 初始密码为教工号
        teacher.setPassword(BCrypt.hashpw(teacher.getTno(), BCrypt.gensalt(10)));
        MyResponse resp = new MyResponse();
        try {
            teacherService.save(teacher);
            resp.success();
        } catch (Exception e) {
            resp.failure();
        }
        return resp;
    }

    /**
     * 跳转到教师更新页面
     *
     * @param id    要更新教师的id
     * @param model Model
     * @return String
     */
    @RequestMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable(name = "id") Integer id, Model model) {
        Teacher teacher = teacherService.getOne(id);
        model.addAttribute("teacherDetails", teacher);
        return "admin/teacher_update";
    }

    /**
     * 真正的更新
     *
     * @param param   json格式的教师信息
     * @param session
     * @return
     */
    @RequestMapping(value = "update", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MyResponse update(@RequestParam(name = "param", required = true) String param, HttpSession session) {
        Teacher teacher = JSON.parseObject(param, new TypeReference<Teacher>() {
        });
        MyResponse resp = new MyResponse();
        try {
            teacherService.update(teacher);
            resp.success();
        } catch (Exception e) {
            resp.failure();
        }
        return resp;
    }

    /**
     * 要删除教师id集合
     *
     * @param param
     * @return MyResponse
     */
    @RequestMapping("deleteList")
    @ResponseBody
    public MyResponse deleteList(@RequestParam(name = "param", required = true) String param) {
        List<Integer> ids = JSON.parseObject(param, new TypeReference<ArrayList<Integer>>() {
        });
        MyResponse resp = new MyResponse();
        try {
            teacherService.deleteList(ids);
            resp.success();
        } catch (Exception e) {
            resp.failure();
        }
        return resp;
    }

    /**
     * 跳转到安排教师课程页面
     * @param id 要安排的课程id
     * @param model Model
     * @return String
     */
    @RequestMapping("toArrangeTeacher/{id}")
    public String toArrangeTeacher(@PathVariable(name = "id") Integer id, Model model) {
        List<Teacher> teachersList = teacherService.findAll();
        model.addAttribute("teachersList", teachersList);
        model.addAttribute("id", id); // 将课程id传入选择教师页面
        return "admin/arrange_teacher";
    }
}
