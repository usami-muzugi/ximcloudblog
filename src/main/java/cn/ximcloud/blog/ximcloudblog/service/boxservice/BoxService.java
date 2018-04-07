package cn.ximcloud.blog.ximcloudblog.service.boxservice;

import cn.ximcloud.blog.ximcloudblog.domain.Admin;
import cn.ximcloud.blog.ximcloudblog.domain.AdminInfo;
import cn.ximcloud.blog.ximcloudblog.service.adminservice.AdminInfoService;
import cn.ximcloud.blog.ximcloudblog.service.adminservice.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: Wizard
 * Date: 2018-04-07
 * Time: 22:16
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
@Service
public class BoxService {

    @Autowired
    AdminInfoService adminInfoService;

    @Autowired
    AdminService adminService;

    public BoxService() {
    }

    public Integer getRrofileSettingsBox(Admin admin, AdminInfo adminInfo) {
        return  adminInfoService.reflect(adminInfo) + adminService.adminIsNull(admin);
    }
}
