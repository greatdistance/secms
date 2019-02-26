package com.xa.xaufe.secmsweb.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xa.xaufe.secmsweb.entity.Admin;
import com.xa.xaufe.secmsweb.json.MyResponse;
import com.xa.xaufe.secmsweb.service.AdminService;

import com.xa.xaufe.secmsweb.utils.BCrypt;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    /**
     * 管理员登录
     *
     * @param param   json 格式的用户名密码验证码
     * @param session session
     * @return MyResponse
     */
    @RequestMapping(value = "login", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MyResponse login(@RequestParam(name = "param", required = true) String param, HttpSession session) {
        Admin admin = JSON.parseObject(param, new TypeReference<Admin>() {
        });
        // Admin loginUser = adminService.login(admin);
        Admin loginUser = adminService.findByAno(admin.getAno());
        String captchaId = (String) session.getAttribute("vrifyCode");
        String inputRandomCode = admin.getRandomCode();
        MyResponse resp = new MyResponse();
        if (!inputRandomCode.equals(captchaId)) {
            //说明验证码不对
            resp.failure("randomCodeError");
            return resp;
        }
        if (loginUser == null) {
            resp.failure("anoError");
            return resp;
        }
        if (BCrypt.checkpw(admin.getPassword(), loginUser.getPassword())) {
            session.setAttribute("loginUser", loginUser);
            session.setAttribute("status", "admin");
            resp.success();
        } else {
            resp.failure("passwordError");
        }
        return resp;
    }


}
