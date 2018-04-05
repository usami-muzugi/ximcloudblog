package cn.ximcloud.blog.ximcloudblog.controller;

import cn.ximcloud.blog.ximcloudblog.Repository.AdminRepository;
import cn.ximcloud.blog.ximcloudblog.domain.Admin;
import cn.ximcloud.blog.ximcloudblog.service.adminservice.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Wizard
 * Date: 2018-04-05
 * Time: 15:01
 * ProjectName: ximcloudblog
 * To change this template use File | Settings | File Templates.
 * <p>
 * ////////////////////////////////////////////////////////////////////
 * //                          _ooOoo_                               //
 * //                         o8888888o                              //
 * //                         88" . "88                              //
 * //                         (| ^_^ |)                              //
 * //                         O\  =  /O                              //
 * //                      ____/`---'\____                           //
 * //                    .'  \\|     |//  `.                         //
 * //                   /  \\|||  :  |||//  \                        //
 * //                  /  _||||| -:- |||||-  \                       //
 * //                  |   | \\\  -  /// |   |                       //
 * //                  | \_|  ''\---/''  |   |                       //
 * //                  \  .-\__  `-`  ___/-. /                       //
 * //                ___`. .'  /--.--\  `. . ___                     //
 * //              ."" '<  `.___\_<|>_/___.'  >'"".                  //
 * //            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
 * //            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
 * //      ========`-.____`-.___\_____/___.-`____.-'========         //
 * //                           `=---='                              //
 * //      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
 * //         佛祖保佑          永无BUG     永不修改                  //
 * ////////////////////////////////////////////////////////////////////
 **/
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    /**
     * 管理员注册页面
     *
     * @param register_email
     * @param register_password
     * @param register_password2
     * @param register_terms
     * @return
     */
    @PostMapping("/register")
    public ModelAndView adminDoRegister(HttpServletResponse httpServletResponse, @RequestParam String register_email, @RequestParam String register_password, @RequestParam String register_password2, @RequestParam(defaultValue = "off") String register_terms) {

        ModelAndView modelAndView = new ModelAndView();
        if (register_terms.equals("on")) {
            String msg = adminService.createAdmin(register_email, register_password, register_password2);
            if (msg != null) {
                //有问题
                modelAndView.addObject("email", register_email);
                modelAndView.addObject("msg", msg);
            } else {
                //没问题  cookie名 email
                Cookie cookie = new Cookie("email", register_email);
                httpServletResponse.addCookie(cookie);
                modelAndView.setViewName("/admin/login");
            }
            return modelAndView;
        } else {
            modelAndView.addObject("email", register_email);
            modelAndView.addObject("msg", "需要同意用户条款");
        }
        return modelAndView;
    }

    /**
     * 管理员注册页面
     * @return
     */
    @GetMapping("/register")
    public ModelAndView adminRegister() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/register");
        return modelAndView;
    }


    /**
     * 管理员登录页面
     * @return
     */
    @GetMapping("")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/admin/login");
        return modelAndView;
    }


    /**
     * 管理员登录
     * @param admin
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ModelAndView dologin(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse, @RequestParam String admin, @RequestParam String password, @RequestParam(defaultValue = "off") String login_remember_me) {
        ModelAndView modelAndView = new ModelAndView();
        Admin indexadmin;
        if ((indexadmin = adminService.adminlogin(admin, password)) != null) {
            if (login_remember_me.equals("on")) {
                //Cookie
                Cookie cookie = new Cookie("uuid", indexadmin.getUuid());
                cookie.setMaxAge(60 * 60 * 24 * 7);
                cookie.setPath("/admin");
                httpServletResponse.addCookie(cookie);
            }
            //session



            modelAndView.setViewName("/admin/dashboard");
        } else {
            //密码错误
            modelAndView.addObject("msg", "密码错误或用户不存在!");
        }
        return modelAndView;
    }

    /**
     * 管理员登录页面
     * @return
     */
    @GetMapping("/login")
    public ModelAndView login(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView();
        Cookie[] cookies = httpServletRequest.getCookies();
        String index;
        Admin admin;
        for (Cookie cookie: cookies
             ) {
            //存在cookie叫email
            if (cookie.getName().equals("email")) {
                index = cookie.getValue();
                modelAndView.addObject("email", index);
                break;
            } else if (cookie.getName().equals("uuid")) {
                //存在cookie叫 uuid
                index = cookie.getValue();
                if (index == null || index.equals("")) {
                    //没有
                    break;
                } else {
                    //有
                    if ((admin = adminService.findByUUID(index)) != null) {
                        //存在这个用户
                        modelAndView.addObject("email", admin.getEmail());
                    } else {
                        modelAndView.addObject("email","");
                        break;
                    }
                }
                break;
            }
        }
        return modelAndView;
    }

    /**
     * 管理Dashboard
     * @return
     */
    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/dashboard");
        return modelAndView;
    }
}
