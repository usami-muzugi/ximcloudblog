package cn.ximcloud.blog.ximcloudblog.controller;

import cn.ximcloud.blog.ximcloudblog.repository.AdminInfoRepository;
import cn.ximcloud.blog.ximcloudblog.repository.AdminRepository;
import cn.ximcloud.blog.ximcloudblog.domain.Admin;
import cn.ximcloud.blog.ximcloudblog.domain.AdminInfo;
import cn.ximcloud.blog.ximcloudblog.service.adminservice.AdminInfoService;
import cn.ximcloud.blog.ximcloudblog.service.adminservice.AdminService;
import cn.ximcloud.blog.ximcloudblog.service.boxservice.BoxService;
import cn.ximcloud.blog.ximcloudblog.utils.cookieutil.CookieUtil;
import cn.ximcloud.blog.ximcloudblog.utils.fileutil.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;

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

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminInfoRepository adminInfoRepository;

    @Autowired
    BoxService boxService;


    @GetMapping("/dashboard/newsomething")
    public ModelAndView newSomething(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        Admin admin = (Admin) httpSession.getAttribute("admin_session");
        modelAndView.addObject("admin", admin);
        modelAndView.setViewName("/admin/edit");
        return modelAndView;
    }

    /**
     * 管理Dashboard
     *
     * @return ModelAndView
     */
    @GetMapping("/dashboard")
    public ModelAndView dashboard(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        if (httpSession.getAttribute("admin_session") != null) {
            //有session
            Admin admin = (Admin) httpSession.getAttribute("admin_session");
            modelAndView.addObject("admin", admin);

            AdminInfo adminInfo = adminInfoRepository.findById(admin.getId()).get();

            modelAndView.addObject("inbox", 2);
            modelAndView.addObject("profilesettings", boxService.getRrofileSettingsBox(admin, adminInfo));
            modelAndView.setViewName("/admin/dashboard");
        } else {
            modelAndView.setViewName("redirect:/admin/login");
        }
        return modelAndView;
    }


    @GetMapping("/profile_edit")
    public ModelAndView adminProfileEdit(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        if (httpSession.getAttribute("admin_session") != null) {
            //有session
            Admin admin = (Admin) httpSession.getAttribute("admin_session");
            AdminInfo adminInfo = adminInfoRepository.findById(admin.getId()).get();
            modelAndView.addObject("admin", admin);
            modelAndView.addObject("admin_info", adminInfo);
            modelAndView.addObject("inbox", 2);
            modelAndView.addObject("profilesettings", boxService.getRrofileSettingsBox(admin, adminInfo));
            modelAndView.addObject("msg", boxService.getRrofileSettingsBox(admin, adminInfo));
            modelAndView.setViewName("/admin/profile_edit");
        } else {
            modelAndView.setViewName("redirect:/admin/login");
        }
        return modelAndView;
    }


    @PostMapping("/profile_edit")
    public ModelAndView adminProfileUpdate(HttpServletRequest httpServletRequest, HttpSession httpSession, @RequestParam String profile_name,
                                           @RequestParam String profile_job, @RequestParam String profile_firstname, @RequestParam String profile_lastname, @RequestParam String profile_bio,
                                           @RequestParam String profile_city, @RequestParam String profile_age, @RequestParam(defaultValue = "off") String profile_gender_group, @RequestParam String profile_password,
                                           @RequestParam String profile_password_new, @RequestParam String profile_password_new_confirm,
                                           @RequestParam(defaultValue = "off") String online_status) {
        ModelAndView modelAndView = new ModelAndView();
        if (httpSession.getAttribute("admin_session") != null) {
            //有session
            Admin admin = (Admin) httpSession.getAttribute("admin_session");
            AdminInfo adminInfo = adminInfoRepository.findById(admin.getId()).get();


        } else {
            //没有
            modelAndView.setViewName("redirect:/admin/login");
        }
        return modelAndView;
    }


//    /**
//     *
//     * @return
//     */
//    @PostMapping("/profile_edit")
//    public ModelAndView adminProfileUpdate() {
//        ModelAndView modelAndView = new ModelAndView();
//        return modelAndView;
//    }
//
//    /**
//     *
//     * @return
//     */
//    @PostMapping("/profile_edit")
//    public ModelAndView adminProfileUpdate() {
//        ModelAndView modelAndView = new ModelAndView();
//        return modelAndView;
//    }


    /**
     * @param httpSession
     * @return
     */
    @GetMapping("/profile")
    public ModelAndView adminProfile(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        if (httpSession.getAttribute("admin_session") != null) {
            //有session
            Admin admin = (Admin) httpSession.getAttribute("admin_session");
            AdminInfo adminInfo = adminInfoRepository.findById(admin.getId()).get();

            modelAndView.addObject("admin", admin);
            modelAndView.addObject("admin_info", adminInfo);
            modelAndView.addObject("inbox", 2);
            modelAndView.addObject("profilesettings", boxService.getRrofileSettingsBox(admin, adminInfo));
            modelAndView.setViewName("/admin/profile");
        } else {
            modelAndView.setViewName("redirect:/admin/login");
        }
        return modelAndView;
    }


    /**
     * 忘记密码
     *
     * @return
     */
    @GetMapping("/reminder")
    public ModelAndView password() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    /**
     * @param reminder_email reminder_email
     * @return return
     */
    @PostMapping("/reminder")
    public ModelAndView password(@RequestParam String reminder_email) {
        ModelAndView modelAndView = new ModelAndView();
        if (adminService.isAdmin(reminder_email)) {

            modelAndView.addObject("msg", "一封重置密码的邮件已经发送给:" + reminder_email + "了！请前往邮箱查看!");
        } else {
            modelAndView.addObject("msg", "该邮箱不存在！");
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
    public ModelAndView logout(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView();
        httpServletRequest.getSession().setAttribute("admin_session", null);
        modelAndView.setViewName("redirect:/admin/login");
        return modelAndView;
    }


    /**
     * 管理员注册页面
     *
     * @return ModelAndView
     */
    @GetMapping("/register")
    public ModelAndView adminRegister() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/register");
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
    public ModelAndView adminDoRegister(HttpServletResponse httpServletResponse, @RequestParam String register_email, @RequestParam String register_password, @RequestParam String register_password2, @RequestParam(defaultValue = "off") String register_terms) {
        ModelAndView modelAndView = new ModelAndView();

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
                CookieUtil.addCookie(httpServletResponse, "uuid", adminRepository.findAdminByEmail(register_email).getUuid(), "/admin", 60 * 60 * 24 * 7);
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
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/login");
//        if (httpSession.getAttribute("admin_session") != null) {
//            //有Session
//            Admin admin = (Admin) httpSession.getAttribute("admin_session");
//            if (adminService.adminlogin(admin.getEmail(), admin.getPassword()) != null) {
//                //Session正确
//                modelAndView.setViewName("redirect:/admin/dashboard");
//            } else {
//                modelAndView.setViewName("redirect:/admin/login");
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
    public ModelAndView login(HttpServletRequest httpServletRequest, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
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
                modelAndView.setViewName("/admin/login");
            } catch (NullPointerException e) {
                //没有cookie
                modelAndView.setViewName("/admin/login");
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
    public ModelAndView dologin(HttpSession httpSession, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam String admin, @RequestParam String password, @RequestParam(defaultValue = "off") String login_remember_me) {
        ModelAndView modelAndView = new ModelAndView();

        if (login_remember_me.equals("on")) {
            //set Cookie  可以说是更新Cookie
            CookieUtil.addCookie(httpServletResponse, "checked", "checked", "/admin", 60 * 60 * 24 * 7);
            modelAndView.addObject("checked", "checked");
        } else CookieUtil.addCookie(httpServletResponse, "checked", "unchecked", "/admin", 60 * 60 * 24 * 7);

        if (admin == null || admin.equals("")) {
            modelAndView.setViewName("/admin/login");
            modelAndView.addObject("msg", "用户名或ID不能为空!");
        } else {
            Admin indexadmin;
            if ((indexadmin = adminService.adminlogin(admin, password)) != null) {
                //用户存在且密码正确
                if (login_remember_me.equals("on")) {
                    //set Cookie  可以说是更新Cookie
                    CookieUtil.addCookie(httpServletResponse, "uuid", indexadmin.getUuid(), "/admin", 60 * 60 * 24 * 7);
                }
                //update admin sysinfo
                adminService.adminLoginEventUpdate(indexadmin.getId());
                //set session
                httpSession.setAttribute("admin_session", indexadmin);
                modelAndView.setViewName("redirect:/admin/dashboard");
                modelAndView.addObject("admin", admin);
            } else {
                modelAndView.addObject("msg", "密码错误或用户不存在!");
                modelAndView.addObject("email", admin);
                modelAndView.setViewName("/admin/login");
            }
        }
        return modelAndView;
    }


}
