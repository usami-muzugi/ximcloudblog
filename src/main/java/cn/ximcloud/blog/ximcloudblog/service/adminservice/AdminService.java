package cn.ximcloud.blog.ximcloudblog.service.adminservice;

import cn.ximcloud.blog.ximcloudblog.Repository.AdminRepository;
import cn.ximcloud.blog.ximcloudblog.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: wzard
 * Date: 2018-04-03
 * Time: 17:45
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
@Service
public class AdminService {
    private AdminService(){}

    @Autowired
    private static AdminRepository adminRepository;

    //可通过Email、id登录
    public boolean isAdmin(String string, String password) {
        try {
            Integer adminId = Integer.valueOf(string);
            if (adminRepository.findAdminById(adminId).getPassword().equals(password)) {
                return true;
            } else return false;
        } catch (NumberFormatException e) {
            //不是数字
            if (EmailUtil.checkEmail(string)) { //Email Check
                //是Email
                if (adminRepository.findAdminByEmail(string).getPassword().equals(password)) {
                    return true;
                } else return false;
            } else {
                //不是Email
                return false;
            }
        }
    }
}
