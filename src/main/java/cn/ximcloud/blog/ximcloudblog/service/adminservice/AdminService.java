package cn.ximcloud.blog.ximcloudblog.service.adminservice;

import cn.ximcloud.blog.ximcloudblog.Repository.AdminInfoRepository;
import cn.ximcloud.blog.ximcloudblog.Repository.AdminRepository;
import cn.ximcloud.blog.ximcloudblog.domain.Admin;
import cn.ximcloud.blog.ximcloudblog.domain.AdminInfo;
import cn.ximcloud.blog.ximcloudblog.utils.UUID.UuidUtil;
import cn.ximcloud.blog.ximcloudblog.utils.email.EmailUtil;
import cn.ximcloud.blog.ximcloudblog.utils.encryptutil.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

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
    private AdminRepository adminRepository;

    @Autowired
    private AdminInfoRepository adminInfoRepository;


    public void adminLoginUpdate(Integer id) {
        //admin update
        Admin admin = adminRepository.findAdminById(id);
        String string = null;
        try {
            string = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        admin.setLastLoginIP(string);

        //admininfo update
        Integer adminInfoId = admin.getId();
        AdminInfo adminInfo = adminInfoRepository.findById(adminInfoId).get();
        adminInfo.setLastLoginTime(new Date().getTime());
        if (adminInfo.getLoginIPArrayList() == null || adminInfo.getLoginIPArrayList().equals("")) {
            adminInfo.setLoginIPArrayList(string);
        }else adminInfo.setLoginIPArrayList(adminInfo.getLoginIPArrayList() + "," + string);
        adminInfoRepository.save(adminInfo);
    }









    /**
     * findByUUID
     * @param uuid
     * @return
     */
    public Admin findByUUID(String uuid) {
        Admin admin = null;
        if ((admin = adminRepository.findAdminByUuid(uuid)) != null) {
            return admin;
        } else return null;
    }


    /**
     * 用户登录
     * 返回为用户实体，因为返回为boolean 还是要重新再查询一遍所以返回为admin
     * @param admin
     * @param password
     * @return
     */
    public Admin adminlogin(String admin, String password) {
        Admin indexAdmin = null;
        //加密后的密码
        String enc_password = EncryptUtil.md5Password(password);
        try {
            Integer id = Integer.valueOf(admin);
            if ((indexAdmin=adminRepository.findAdminByIdAndPassword(id, enc_password)) == null) {
                return null;
            } else return indexAdmin;
        } catch (NumberFormatException e) {
            //不是数字
            if ((indexAdmin = adminRepository.findAdminByEmailAndPassword(admin, enc_password)) == null) {
                return null;
            } else return indexAdmin;
        }
    }


    /**
     * 创建用户
     * @param register_email
     * @param register_password
     * @param register_password2
     * @return
     */
    public String createAdmin(String register_email, String register_password, String register_password2) {
        if (EmailUtil.checkEmail(register_email)) {
            //是Email？
            if (adminRepository.findAdminByEmail(register_email) != null) {
                //找到了该邮箱存在
                return "邮箱被占用！";
            } else if (!register_password.equals(register_password2)) {
                //两个密码相同？
                return "两次密码不相同！";
            }else if(register_password.equals("")){
                return "密码不能为空字符！";
            } else if (register_password.length() < 6) {
                return "密码需为6位字符以上!";
            }

            //用户设置
            Admin admin = new Admin();
            admin.setEmail(register_email);  //email
            admin.setPassword(EncryptUtil.md5Password(register_password)); //password 密码加密
            admin.setUuid(UuidUtil.getUUID());  //uuid
            adminRepository.save(admin);  //save

            //BUG admin id =1,admininfo id =2 这种


            //用户信息设置
            AdminInfo adminInfo = new AdminInfo();
//            adminInfo.setAdmin_id(adminRepository.findAdminByEmail(register_email).getId());  也不行
            adminInfo.setAdminAccountAvailableStatus(true);  //availableStatues
            adminInfo.setRegisterTime(new Date().getTime());  //时间戳
            try {
                adminInfo.setRegisterIP(InetAddress.getLocalHost().getHostAddress());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            adminInfoRepository.save(adminInfo);
            return null;  //创建成功

//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//            String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳


        } else return "邮箱格式不正确！";
    }

    /**
     * 判断是否为已存在的用户 可通过邮箱和用户ID判断
     * @param string
     * @return
     */
    public boolean isAdmin(String string) {
        if (string == null || string.equals("")) {
            return false;
        } else {
            try {
                //ID
                Integer integer = Integer.valueOf(string);
                try {
                    if (adminRepository.findAdminById(integer) != null) {
                        return true;
                    } else return false;
                } catch (NullPointerException e) {
                    return false;
                }
            } catch (NumberFormatException e) {
                //Email
                if (EmailUtil.checkEmail(string)) {
                    if (adminRepository.findAdminByEmail(string) == null) {
                        return false;
                    } else return true;
                } else return false;
            }
        }
    }
}
