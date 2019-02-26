package com.xa.xaufe.secmsweb.controller.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xa.xaufe.secmsweb.entity.Course;
import com.xa.xaufe.secmsweb.json.MyResponse;
import com.xa.xaufe.secmsweb.service.CourseService;
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
@RequestMapping("course")
public class CourseController {
    // 页面大小 每页数据条数
    private static final int PERPAGE_SIZE = 5;
    @Resource
    private CourseService courseService;

    /**
     * 跳转到课程列表
     *
     * @param currentPageNumber 当前页数
     * @param model             Model
     * @return String
     */
    @RequestMapping("toCourseList")
    public String toStudentList(@RequestParam(name = "currentPageNumber", defaultValue = "0") int currentPageNumber, Model model) {
        Page<Course> studentPage = courseService.findAll(currentPageNumber, PERPAGE_SIZE);

        List<Course> coursesList = studentPage.getContent();
        model.addAttribute("coursesList", coursesList);
        model.addAttribute("pager", studentPage);
        return "admin/course_list";
    }

    /**
     * 跳转到课程增加页面
     *
     * @return String
     */
    @RequestMapping("toSave")
    public String toUpdate() {
        return "admin/course_save";
    }

    /**
     * 真正的添加
     *
     * @param param json格式课程数据
     * @return json
     */
    @RequestMapping(value = "save", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MyResponse save(@RequestParam(name = "param", required = true) String param, HttpSession session) {
        Course course = JSON.parseObject(param, new TypeReference<Course>() {
        });
        MyResponse resp = new MyResponse();
        try {
            courseService.save(course);
            resp.success();
        } catch (Exception e) {
            resp.failure();
        }
        return resp;
    }

    /**
     * 跳转到更新页面
     *
     * @return String
     */
    @RequestMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable(name = "id") Integer id, Model model) {
        Course course = courseService.getOne(id);
        model.addAttribute("courseDetails", course);
        return "admin/course_update";
    }

    /**
     * 更新课程信息
     *
     * @param param json格式的课程更新数据
     * @return MyResponse
     */
    @RequestMapping(value = "update", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MyResponse update(@RequestParam(name = "param", required = true) String param, HttpSession session) {
        Course course = JSON.parseObject(param, new TypeReference<Course>() {
        });
        MyResponse resp = new MyResponse();
        try {
            courseService.update(course);
            resp.success();
        } catch (Exception e) {
            resp.failure();
        }
        return resp;
    }

    /**
     * 删除用户的用户id集合
     *
     * @param param json格式的要删除课程的id集合
     * @return MyResponse
     */
    @RequestMapping("deleteList")
    @ResponseBody
    public MyResponse deleteList(@RequestParam(name = "param", required = true) String param) {
        List<Integer> ids = JSON.parseObject(param, new TypeReference<ArrayList<Integer>>() {
        });
        MyResponse resp = new MyResponse();
        try {
            courseService.deleteList(ids);
            resp.success();
        } catch (Exception e) {
            resp.failure();
        }
        return resp;
    }

    /**
     * 跳转到安排课程页面
     *
     * @return String
     */
    @RequestMapping("toArrangeCourse/{id}")
    public String toArrangeCourse(@PathVariable(name = "id") Integer id, Model model) {
        List<Course> coursesList = courseService.findAll();
        model.addAttribute("coursesList", coursesList);
        return "admin/arrange_course";
    }

}
