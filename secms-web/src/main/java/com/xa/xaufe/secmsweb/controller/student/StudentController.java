package com.xa.xaufe.secmsweb.controller.student;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xa.xaufe.secmsweb.entity.Course;
import com.xa.xaufe.secmsweb.entity.Student;
import com.xa.xaufe.secmsweb.json.MyResponse;
import com.xa.xaufe.secmsweb.service.StudentService;
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
import java.util.Set;

@Controller
@RequestMapping("student")
public class StudentController {
    private static final int PERPAGE_SIZE = 5;
    @Resource
    private StudentService studentService;

    /**
     * 学生登录
     *
     * @param param json格式的用户登录信息
     * @return MyResponse
     */
    @RequestMapping(value = "login", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MyResponse login(@RequestParam(name = "param", required = true) String param, HttpSession session) {
        Student student = JSON.parseObject(param, new TypeReference<Student>() {
        });
        //Student loginUser = studentService.login(student);
        Student loginUser = studentService.findBySno(student.getSno());
        // 从session中取出生成的验证码字符串
        String captchaId = (String) session.getAttribute("vrifyCode");
        String inputRandomCode = student.getRandomCode();
        MyResponse resp = new MyResponse();
        if (!inputRandomCode.equals(captchaId)) {
            //说明验证码不对.
            resp.failure("randomCodeError");
            return resp;
        }
        if (loginUser == null) {
            resp.failure("snoError");
            return resp;
        }
        if (BCrypt.checkpw(student.getPassword(), loginUser.getPassword())) {
            // 密码验证通过
            session.setAttribute("loginUser", loginUser);
            session.setAttribute("status", "student");
            resp.success();
        } else {
            resp.failure("passwordError");
        }
        return resp;
    }

    /**
     * 学生列表
     *
     * @param currentPageNumber 当前页码
     * @param model             Model
     * @return String
     */
    @RequestMapping("toStudentList")
    public String toStudentList(@RequestParam(name = "currentPageNumber", defaultValue = "0") int currentPageNumber, Model model) {
        Page<Student> studentPage = studentService.findAll(currentPageNumber, PERPAGE_SIZE);

        List<Student> studentsList = studentPage.getContent();
        model.addAttribute("studentsList", studentsList);
        model.addAttribute("pager", studentPage);
        return "admin/student_list";
    }

    /**
     * 跳转到添加学生页面
     *
     * @return String
     */
    @RequestMapping("toSave")
    public String toUpdate() {
        return "admin/student_save";
    }

    /**
     * 真正的添加学生
     *
     * @param param json格式的学生信息
     * @return MyResponse
     */
    @RequestMapping(value = "save", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MyResponse save(@RequestParam(name = "param", required = true) String param, HttpSession session) {
        Student student = JSON.parseObject(param, new TypeReference<Student>() {
        });
        // 初始密码为学号
        student.setPassword(BCrypt.hashpw(student.getSno(), BCrypt.gensalt(10)));
        MyResponse resp = new MyResponse();
        try {
            studentService.save(student);
            resp.success();
        } catch (Exception e) {
            e.printStackTrace();
            resp.failure();
        }
        return resp;
    }

    /**
     * 跳转到更新学生信息页面
     *
     * @return String
     */
    @RequestMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable(name = "id") Integer id, Model model) {
        Student student = studentService.getOne(id);
        model.addAttribute("studentDetails", student);
        return "admin/student_update";
    }

    /**
     * 真正的更新学生信息
     *
     * @param param json格式的学生实体信息
     * @return json
     */
    @RequestMapping(value = "update", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MyResponse update(@RequestParam(name = "param", required = true) String param, HttpSession session) {
        Student student = JSON.parseObject(param, new TypeReference<Student>() {
        });
        MyResponse resp = new MyResponse();
        try {
            studentService.update(student);
            resp.success();
        } catch (Exception e) {
            e.printStackTrace();
            resp.failure();
        }
        return resp;
    }

    /**
     * 批量删除学生
     *
     * @param param json格式要删除学生的id集合
     * @return MyResponse
     */
    @RequestMapping("deleteList")
    @ResponseBody
    public MyResponse deleteList(@RequestParam(name = "param", required = true) String param) {
        List<Integer> ids = JSON.parseObject(param, new TypeReference<ArrayList<Integer>>() {
        });
        MyResponse resp = new MyResponse();
        try {
            studentService.deleteList(ids);
            resp.success();
        } catch (Exception e) {
            e.printStackTrace();
            resp.failure();
        }
        return resp;
    }

    /**
     * 跳转到学生选修的课程页面
     * @param session Session
     * @param model Model
     * @return String
     */
    @RequestMapping("toStudentCourse")
    public String toStudentCourse(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("loginUser");
        Student student1 = studentService.getOne(student.getId());
        List<Course> list = new ArrayList<Course>();
        Set<Course> set = student1.getCourses();
        for (Course course : set) {
            list.add(course);
        }
        model.addAttribute("list", list);
        return "admin/student_course_list";
    }
}