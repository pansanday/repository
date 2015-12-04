package com.git.annotation;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/userController")
public class UserController {

    @RequestMapping(value = "/listUsers.do")
    public String listUsers() {
        System.out.println("List user...");
        return "jsp/userinfo/user_list";
    }

    // 如果用超链接, 必须用GET方法; 这个method的属性也可以不用设置
    @RequestMapping(value = "/addUsers.do", method = RequestMethod.GET)
    public String addUsers() {
        System.out.println("Add user...");
        return "jsp/userinfo/user_add";
    }

    @RequestMapping(value = "/updateUser.do")
    public String updateUser() {
        System.out.println("Update user...");
        return "jsp/userinfo/user_update";
    }

    @RequestMapping(value = "/getData.do")
    public ModelAndView getData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        System.out.println("userId=[" + userId + "]");
        System.out.println("password=[" + password + "]");

        response.getWriter().write("Succeed");
        
        return null;
    }

    @RequestMapping(value = "/start.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("start controller is working !");

        // 使用控制器与视图名称完全分离,具体返回什么视图由后续配置决定
        return new ModelAndView("jsp/userinfo/OK");
        // 访问测试: http://localhost:8080/git-common/userController/start.do
    }

    @RequestMapping(value = "/login.do")
    public ModelAndView loginContr(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("login controller");
        // 由于做了配置(前后缀),这里返回的"jsp/userinfo/OK"实际上是"/view/jsp/userinfo/OK.jsp"
        return new ModelAndView("jsp/userinfo/OK");
        // 访问测试: http://localhost:8080/git-common/userController/login.do
    }
}