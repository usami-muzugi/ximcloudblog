package cn.ximcloud.blog.ximcloudblog.controller;

import cn.ximcloud.blog.ximcloudblog.Repository.AdminRepository;
import cn.ximcloud.blog.ximcloudblog.domain.Admin;
import cn.ximcloud.blog.ximcloudblog.service.adminservice.AdminService;
import cn.ximcloud.blog.ximcloudblog.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    private AdminRepository adminRepository;
    @Autowired
    private AdminService adminService;



    @PostMapping("/register")
    public ModelAndView adminDoRegister(@RequestParam String register_email,@RequestParam String register_password,@RequestParam String register_password2,@RequestParam(defaultValue = "off") String register_terms) {
        ModelAndView modelAndView = new ModelAndView();
        if (register_terms.equals("on")) {
            String msg = adminService.createAdmin(register_email, register_password,register_password2);
            if (msg != null) {
                //有问题
                modelAndView.addObject("msg", msg);
            } else {
                //没问题
                modelAndView.setViewName("/admin/login");
            }
            return modelAndView;
        } else {
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
        boolean flag = false;
        if (!flag) {
            modelAndView.setViewName("/admin/login");
        }
        return modelAndView;
    }


    /**
     * 管理员登录
     * @param admin
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ModelAndView dologin(@RequestParam String admin, @RequestParam String password,@RequestParam(defaultValue = "off") String login_remember_me) {
        ModelAndView modelAndView = new ModelAndView();
        if (login_remember_me.equals("on")) {
            //Cookie
        }
        //
        if (adminService.adminlogin(admin, password)) {
            modelAndView.setViewName("/admin/dashboard");
        } else {
            //密码错误
            modelAndView.addObject("msg", "密码错误!");
        }
        return modelAndView;
    }

    /**
     * 管理员登录页面
     * @return
     */
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/login");
        boolean flag = false;
        if (!flag) {
            modelAndView.setViewName("/admin/login");
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
