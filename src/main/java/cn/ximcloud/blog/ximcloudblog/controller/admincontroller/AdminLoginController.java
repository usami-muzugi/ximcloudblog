package cn.ximcloud.blog.ximcloudblog.controller.admincontroller;

import cn.ximcloud.blog.ximcloudblog.domain.Admin;
import cn.ximcloud.blog.ximcloudblog.repository.AdminInfoRepository;
import cn.ximcloud.blog.ximcloudblog.repository.AdminRepository;
import cn.ximcloud.blog.ximcloudblog.service.adminservice.AdminService;
import cn.ximcloud.blog.ximcloudblog.service.boxservice.BoxService;
import cn.ximcloud.blog.ximcloudblog.utils.cookieutil.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: wzard
 * Date: 2018-04-08
 * Time: 10:43
 * ProjectName: ximcloudblog
 * To change this template use File | Settings | Editor | File and Code Templates.
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
 * //         佛祖保佑        永无BUG      永不修改                  //
 * ////////////////////////////////////////////////////////////////////
 **/

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private AdminService adminService;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminInfoRepository adminInfoRepository;

    @Autowired
    BoxService boxService;

    /**
     * 管理员注册页面
     *
     * @return ModelAndView
     */
    @GetMapping("/register")
    public ModelAndView adminRegister(ModelAndView modelAndView) {
        modelAndView.setViewName("admin/register");
        return modelAndView;
    }


    /**
     * 管理员注册页面
     *
     * @param register_email     注册的email
     * @param register_password  密码1
     * @param register_password2 密码2
     * @param register_terms     同意用户规则吗
     * @return modelandview
     */
    @PostMapping("/register")
    public ModelAndView adminDoRegister(ModelAndView modelAndView, HttpServletResponse httpServletResponse, @RequestParam String register_email, @RequestParam String register_password, @RequestParam String register_password2, @RequestParam(defaultValue = "off") String register_terms) {
        //同意条款
        if (register_terms.equals("on")) {
            //查询下数据库，这个邮箱是否被占用
            String msg = adminService.createAdmin(register_email, register_password, register_password2);
            //如果msg==null就没问题
            if (msg != null) {
                //有问题
                modelAndView.addObject("email", register_email);
                modelAndView.addObject("msg", msg);
            } else {
                //没问题  cookie名 email -->//改为uuid
                CookieUtil.addCookie(httpServletResponse, "uuid", adminRepository.findAdminByEmail(register_email).getUuid(), "/admincontroller", 60 * 60 * 24 * 7);
                //添加一个checked cookie
                CookieUtil.addCookie(httpServletResponse, "checked", "checked", "/admin", 60 * 60 * 24 * 7);
                modelAndView.setViewName("redirect:/admin/login");
            }
        } else {
            modelAndView.addObject("email", register_email);
            modelAndView.addObject("msg", "需要同意用户条款!");
        }
        return modelAndView;
    }

    /**
     * 管理员登录页面
     *
     * @return
     */
    @GetMapping("")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/admin/login");
//        if (httpSession.getAttribute("admin_session") != null) {
//            //有Session
//            Admin admincontroller = (Admin) httpSession.getAttribute("admin_session");
//            if (adminService.adminlogin(admincontroller.getEmail(), admincontroller.getPassword()) != null) {
//                //Session正确
//                modelAndView.setViewName("redirect:/admincontroller/dashboard");
//            } else {
//                modelAndView.setViewName("redirect:/admincontroller/login");
//            }
//        }
        return modelAndView;
    }


    /**
     * 管理员登录页面
     * 判断是否有session，有的话，就跳转到dashboard。没有得话有cookie就输出吧
     *
     * @return
     */
    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView, HttpServletRequest httpServletRequest, HttpSession httpSession) {
        //是否有session
        if (httpSession.getAttribute("admin_session") != null) {
            //有
            Admin admin = (Admin) httpSession.getAttribute("admin_session");
            modelAndView.setViewName("redirect:/admin/dashboard");
            modelAndView.addObject("admin", admin);
        } else {
            //没有session，看看有没有cookie
            try {
                if (CookieUtil.getCookieByName(httpServletRequest, "checked").getValue().equals("checked")) {
                    //checked
                    modelAndView.addObject("checked", "checked");
                    String string;
                    if ((string = CookieUtil.getCookieByName(httpServletRequest, "uuid").getValue()) != null) {
                        //有
                        modelAndView.addObject("email", adminService.findByUUID(string).getEmail());
                    }
                } else modelAndView.addObject("checked", "");
                modelAndView.setViewName("admin/login");
            } catch (NullPointerException e) {
                //没有cookie
                modelAndView.setViewName("admin/login");
            }
        }
        return modelAndView;
    }

    /**
     * 管理员登录
     *
     * @param admin    email or id
     * @param password password
     * @return ModelAndView
     */
    @PostMapping("/login")
    public ModelAndView dologin(ModelAndView modelAndView,HttpSession httpSession, HttpServletResponse httpServletResponse, @RequestParam String admin, @RequestParam String password, @RequestParam(defaultValue = "off") String login_remember_me) {
        if (login_remember_me.equals("on")) {
            //set Cookie  可以说是更新Cookie
            CookieUtil.addCookie(httpServletResponse, "checked", "checked", "/admin", 60 * 60 * 24 * 7);
            modelAndView.addObject("checked", "checked");
        } else CookieUtil.addCookie(httpServletResponse, "checked", "unchecked", "/admin", 60 * 60 * 24 * 7);

        if (admin == null || admin.equals("")) {
            modelAndView.setViewName("admin/login");
            modelAndView.addObject("msg", "用户名或ID不能为空!");
        } else {
            Admin indexadmin;
            if ((indexadmin = adminService.adminlogin(admin, password)) != null) {
                //用户存在且密码正确
                if (login_remember_me.equals("on")) {
                    //set Cookie  可以说是更新Cookie
                    CookieUtil.addCookie(httpServletResponse, "uuid", indexadmin.getUuid(), "/admin", 60 * 60 * 24 * 7);
                }
                //update admincontroller sysinfo
                adminService.adminLoginEventUpdate(indexadmin.getId());
                //set session
                httpSession.setAttribute("admin_session", indexadmin);
                modelAndView.setViewName("redirect:/admin/dashboard");
                modelAndView.addObject("admin", admin);
            } else {
                modelAndView.addObject("msg", "密码错误或用户不存在!");
                modelAndView.addObject("email", admin);
                modelAndView.setViewName("admin/login");
            }
        }
        return modelAndView;
    }

    /**
     * 登出
     *
     * @param httpServletRequest httpservletrequest
     * @return modelAndView
     */
    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView,HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().setAttribute("admin_session", null);
        modelAndView.setViewName("redirect:/admin/login");
        return modelAndView;
    }

}
