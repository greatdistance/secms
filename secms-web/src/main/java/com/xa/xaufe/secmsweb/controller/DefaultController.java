package com.xa.xaufe.secmsweb.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.xa.xaufe.secmsweb.entity.Admin;
import com.xa.xaufe.secmsweb.entity.Course;
import com.xa.xaufe.secmsweb.entity.Student;
import com.xa.xaufe.secmsweb.entity.Teacher;
import com.xa.xaufe.secmsweb.json.MyResponse;
import com.xa.xaufe.secmsweb.service.*;
import com.xa.xaufe.secmsweb.utils.BCrypt;
import com.xa.xaufe.secmsweb.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;

@Controller
public class DefaultController {

    @Value("${file.uploadFolder}")
    private String uploadPath;  //上传路径，从application.properties中读取。
    @Autowired
    private DefaultKaptcha captchaProducer;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CourseService courseService;

    /**
     * 跳转到登录页面
     *
     * @return String
     */
    @RequestMapping("login")
    public String login() {
        return "admin/login";
    }

    /**
     * 跳转到登录后的主页面
     *
     * @return String
     */
    @RequestMapping("toMain")
    public String toMain() {
        return "admin/main";
    }

    /**
     * 跳转到个人资料页面
     *
     * @return String
     */
    @RequestMapping("toSet")
    public String toSet() {
        return "admin/set";
    }

    /**
     * 跳转到通知页面
     *
     * @return String
     */
    @RequestMapping("toNotice")
    public String toNotice() {
        return "admin/notice";
    }

    /**
     * 跳转到修改密码页面
     *
     * @return String
     */
    @RequestMapping("toUpdatePassword")
    public String toUpdatePassword() {
        return "admin/password_update";
    }

    /**
     * 注销登录
     *
     * @param session Session
     * @return String
     */

    @RequestMapping("logout")
    public String logout(HttpSession session) {

        if (session.getAttribute("loginUser") != null) {
            session.removeAttribute("loginUser");
        }
        return "redirect:/login";
    }

    /**
     * 生成验证码
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @RequestMapping("defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = captchaProducer.createText();
            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = captchaProducer.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /**
     * 头像上传
     *
     * @param file 上传的头像数据
     * @param request HttpServletRequest
     * @return MyResponse
     */
    @RequestMapping("upload")
    @ResponseBody
    public MyResponse uploadAvatar(MultipartFile file, HttpServletRequest request) {
        String path = uploadPath;
        MyResponse resp = new MyResponse();
        try {
            String uploadSuccessFileName = FileUploadUtils.uploadFile(file, path);

            resp.success(uploadSuccessFileName);
            //更新用户的头像。
            String status = (String) request.getSession().getAttribute("status");
            if ("student".equals(status)) {
                // 是学生用户在上传头像
                Student loginUser = (Student) request.getSession().getAttribute("loginUser");
                loginUser.setAvatar(uploadSuccessFileName);
                studentService.save(loginUser);
            } else if ("teacher".equals(status)) {
                // 是教师用户在上传头像
                Teacher loginUser = (Teacher) request.getSession().getAttribute("loginUser");
                loginUser.setAvatar(uploadSuccessFileName);
                teacherService.save(loginUser);
            } else {
                // 是教务管理员在上传头像
                Admin loginUser = (Admin) request.getSession().getAttribute("loginUser");
                loginUser.setAvatar(uploadSuccessFileName);
                adminService.save(loginUser);
            }

            return resp;
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.failure();
            return resp;
        }
    }

    /**
     * 真正的修改密码
     * @param param json格式的旧密码与新密码
     * @param session Session
     * @return MyResponse
     */
    @RequestMapping(value = "updatePassword", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MyResponse login(@RequestParam(name = "param", required = true) String param, HttpSession session) {
        Map<String, String> map = JSON.parseObject(param, new TypeReference<Map<String, String>>() {
        });
        String oldPassword = map.get("password");
        String newPassword = BCrypt.hashpw(map.get("newpassword"), BCrypt.gensalt(10));
        String status = (String) session.getAttribute("status");
        MyResponse resp = new MyResponse();
        if ("student".equals(status)) {
            // 学生用户申请修改密码
            Student student = (Student) session.getAttribute("loginUser");
            if (BCrypt.checkpw(oldPassword, student.getPassword())) {
                // 密码比对成功允许修改密码
                student.setPassword(newPassword);
                studentService.save(student);
                resp.success();
            } else {
                resp.failure("passwordError");
            }

        }
        if ("teacher".equals(status)) {
            // 教师用户申请修改密码
            Teacher teacher = (Teacher) session.getAttribute("loginUser");
            if (BCrypt.checkpw(oldPassword, teacher.getPassword())) {
                // 密码比对成功允许修改密码
                teacher.setPassword(newPassword);
                teacherService.save(teacher);
                resp.success();

            } else {
                resp.failure("passwordError");
            }
        }
        if ("admin".equals(status)) {
            // 教务管理员用户申请修改密码
            Admin admin = (Admin) session.getAttribute("loginUser");
            if (BCrypt.checkpw(oldPassword, admin.getPassword())) {
                // 密码比对成功允许修改密码
                admin.setPassword(newPassword);
                adminService.save(admin);
                resp.success();
            } else {
                resp.failure("passwordError");
            }
        }
        return resp;
    }

    /**
     * 分配课程教师
     *
     * @param param json 课程id id和教师id
     * @return json
     */
    @RequestMapping(value = "arrange/teacher", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MyResponse arrangeTeacher(@RequestParam(name = "param", required = true) String param) {
        MyResponse resp = new MyResponse();
        try {
            Map<String, Integer> map = JSON.parseObject(param, new TypeReference<Map<String, Integer>>() {
            });
            Integer course_id = map.get("course_id");
            Integer tea_id = map.get("tea_id");
            Course course = courseService.getOne(course_id);
            Teacher teacher = teacherService.getOne(tea_id);
            Set<Course> set = new HashSet<>();
            set.add(course);
            teacher.setCourses(set);
            course.setTeacher(teacher);
            teacherService.save(teacher);
            resp.success();
        } catch (Exception e) {
            resp.failure();
        }
        return resp;
    }

    /**
     * 学生选择课程
     * @param id 要选择的课程，课程id
     * @param session Session
     * @return  String
     */
    @RequestMapping(value = "selectCourse/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MyResponse selectCourse(@PathVariable(name = "id") Integer id, HttpSession session) {
        MyResponse resp = new MyResponse();
        try {
            Course course = courseService.getOne(id); // 得到想选择的课程实体
            Student student = (Student) session.getAttribute("loginUser"); // 从session中取出选课的学生实体
            Student student1 = studentService.getOne(student.getId()); // 通过id得到当前对象
            Set<Course> set = student1.getCourses(); // 得到该学生已选择课程列表
            set.add(course); // 将当前选课加入学生选课列表集合
            student1.setCourses(set);// 学生集合与课程映射
            studentService.save(student1);// 保存信息完成选课
            resp.success();
        } catch (Exception e) {
            resp.failure();
        }
        return resp;
    }

    /**
     * 学生取消课程
     * @param id 要取消的课程课程id
     * @param session Session
     * @return MyResponse
     */
    @RequestMapping(value = "cancelCourse/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MyResponse cancelCourse(@PathVariable(name = "id") Integer id, HttpSession session) {
        MyResponse resp = new MyResponse();
        try {
            Course course = courseService.getOne(id); // 得到想选择的课程实体
            Student student = (Student) session.getAttribute("loginUser"); // 从session中取出选课的学生实体
            Student student1 = studentService.getOne(student.getId());
            Set<Course> set = student1.getCourses();
            set.remove(course);
            studentService.save(student1);
            courseService.save(course);
            resp.success();
        } catch (Exception e) {
            resp.failure();
        }
        return resp;
    }
}
