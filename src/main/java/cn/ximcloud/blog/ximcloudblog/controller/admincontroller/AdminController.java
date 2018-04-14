package cn.ximcloud.blog.ximcloudblog.controller.admincontroller;

import cn.ximcloud.blog.ximcloudblog.repository.AdminInfoRepository;
import cn.ximcloud.blog.ximcloudblog.repository.AdminRepository;
import cn.ximcloud.blog.ximcloudblog.domain.Admin;
import cn.ximcloud.blog.ximcloudblog.domain.AdminInfo;
import cn.ximcloud.blog.ximcloudblog.service.adminservice.AdminService;
import cn.ximcloud.blog.ximcloudblog.service.boxservice.BoxService;
import cn.ximcloud.blog.ximcloudblog.utils.cookieutil.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public ModelAndView newSomething(ModelAndView modelAndView,HttpSession httpSession) {
        Admin admin = (Admin) httpSession.getAttribute("admin_session");
        modelAndView.addObject("admin", admin);
        modelAndView.setViewName("admin/edit");
        return modelAndView;
    }

    /**
     * 管理Dashboard
     *
     * @return ModelAndView
     */
    @GetMapping("/dashboard")
    public ModelAndView dashboard(ModelAndView modelAndView,HttpSession httpSession) {
        if (httpSession.getAttribute("admin_session") != null) {
            //有session
            Admin admin = (Admin) httpSession.getAttribute("admin_session");
            modelAndView.addObject("admin", admin);

            AdminInfo adminInfo = adminInfoRepository.findById(admin.getId()).get();

            modelAndView.addObject("inbox", 2);
            modelAndView.addObject("profilesettings", boxService.getRrofileSettingsBox(admin, adminInfo));
            modelAndView.setViewName("admin/dashboard");
        } else {
            modelAndView.setViewName("redirect:/admin/login");
        }
        return modelAndView;
    }


    @GetMapping("/profile_edit")
    public ModelAndView adminProfileEdit(ModelAndView modelAndView,HttpSession httpSession) {
        if (httpSession.getAttribute("admin_session") != null) {
            //有session
            Admin admin = (Admin) httpSession.getAttribute("admin_session");
            AdminInfo adminInfo = adminInfoRepository.findById(admin.getId()).get();
            modelAndView.addObject("admin", admin);
            modelAndView.addObject("admin_info", adminInfo);
            modelAndView.addObject("inbox", 2);
            modelAndView.addObject("profilesettings", boxService.getRrofileSettingsBox(admin, adminInfo));
            modelAndView.addObject("msg", boxService.getRrofileSettingsBox(admin, adminInfo));
            modelAndView.setViewName("admin/profile_edit");
        } else {
            modelAndView.setViewName("redirect:/admin/login");
        }
        return modelAndView;
    }


    @PostMapping("/profile_edit")
    public ModelAndView adminProfileUpdate(ModelAndView modelAndView,HttpServletRequest httpServletRequest, HttpSession httpSession, @RequestParam String profile_name,
                                           @RequestParam String profile_job, @RequestParam String profile_firstname, @RequestParam String profile_lastname, @RequestParam String profile_bio,
                                           @RequestParam String profile_city, @RequestParam String profile_age, @RequestParam(defaultValue = "off") String profile_gender_group, @RequestParam String profile_password,
                                           @RequestParam String profile_password_new, @RequestParam String profile_password_new_confirm,
                                           @RequestParam(defaultValue = "off") String online_status) {
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
    public ModelAndView adminProfile(ModelAndView modelAndView,HttpSession httpSession) {
        if (httpSession.getAttribute("admin_session") != null) {
            //有session
            Admin admin = (Admin) httpSession.getAttribute("admin_session");
            AdminInfo adminInfo = adminInfoRepository.findById(admin.getId()).get();

            modelAndView.addObject("admin", admin);
            modelAndView.addObject("admin_info", adminInfo);
            modelAndView.addObject("inbox", 2);
            modelAndView.addObject("profilesettings", boxService.getRrofileSettingsBox(admin, adminInfo));
            modelAndView.setViewName("admin/profile");
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
    public ModelAndView password(ModelAndView modelAndView) {
        return modelAndView;
    }

    /**
     * @param reminder_email reminder_email
     * @return return
     */
    @PostMapping("/reminder")
    public ModelAndView password(ModelAndView modelAndView,@RequestParam String reminder_email) {
        if (adminService.isAdmin(reminder_email)) {

            modelAndView.addObject("msg", "一封重置密码的邮件已经发送给:" + reminder_email + "了！请前往邮箱查看!");
        } else {
            modelAndView.addObject("msg", "该邮箱不存在！");
        }
        return modelAndView;
    }
}
