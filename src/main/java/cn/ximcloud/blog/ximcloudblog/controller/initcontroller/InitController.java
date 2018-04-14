package cn.ximcloud.blog.ximcloudblog.controller.initcontroller;

import cn.ximcloud.blog.ximcloudblog.domain.Admin;
import cn.ximcloud.blog.ximcloudblog.repository.AdminRepository;
import cn.ximcloud.blog.ximcloudblog.service.adminservice.AdminService;
import cn.ximcloud.blog.ximcloudblog.utils.cookieutil.CookieUtil;
import cn.ximcloud.blog.ximcloudblog.utils.emailutil.EmailUtil;
import cn.ximcloud.blog.ximcloudblog.utils.encryptutil.EncryptUtil;
import cn.ximcloud.blog.ximcloudblog.utils.passwordutil.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wzard
 * Date: 2018-04-08
 * Time: 10:50
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
public class InitController {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AdminService adminService;

    @GetMapping("/")
    public ModelAndView initBlog(ModelAndView modelAndView) {
        if (findAdmin()) {
            //有用户
            modelAndView.setViewName("redirect:/admin/login");
        } else {
            //没用户
            modelAndView.setViewName("redirect:/admin/XIMCloudBlogSetting");
        }
        return modelAndView;
    }

    @GetMapping("/admin/XIMCloudBlogSetting")
    public ModelAndView XIMCloudBlogSetting(ModelAndView modelAndView) {
        if (findAdmin()) {
            //有用户
            modelAndView.setViewName("redirect:/admin/login");
        } else {
            //没用户
            modelAndView.setViewName("admin/index");
        }
        return modelAndView;
    }

    @PostMapping("/admin/XIMCloudBlogSetting")
    public ModelAndView XIMCloudBlogSetting(ModelAndView modelAndView,HttpServletResponse httpServletResponse,
           @RequestParam String name,@RequestParam String domain,@RequestParam String email,@RequestParam String password,@RequestParam String repassword,@RequestParam(defaultValue = "off") String terms) {
        if (terms.equals("on")) {
            String string;
            if ((string=PasswordUtil.passwordTest(password, repassword))== null) {
                if (EmailUtil.checkEmail(email)) {
                    adminService.createAdmin(email,password,repassword);
                    modelAndView.setViewName("redirect:/admin/login");
                    CookieUtil.addCookie(httpServletResponse, "uuid", adminRepository.findAdminByEmail(email).getUuid(), "/admin", 60 * 60 * 24 * 7);
                    //添加一个checked cookie
                    CookieUtil.addCookie(httpServletResponse, "checked", "checked", "/admin", 60 * 60 * 24 * 7);
                    return modelAndView;
                } else {
                    modelAndView.addObject("msg", "邮箱格式不正确！");
                }
            } else {
                modelAndView.addObject("msg", string);
            }
        } else {
            String[] strings = new String[]{name, domain, email, password, repassword};
            modelAndView.addObject("value", strings);
            modelAndView.addObject("msg", "需要同意terms！");
        }
        modelAndView.setViewName("admin/index");
        return modelAndView;
    }

    boolean findAdmin() {
        if (adminRepository.findAll().size() != 0) {
            //有用户了
            return true;
        } else {
            //没有
            return false;
        }
    }

}
