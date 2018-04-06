package cn.ximcloud.blog.ximcloudblog.utils.passwordutil;

/**
 * Created by IntelliJ IDEA.
 * User: Wizard
 * Date: 2018-04-07
 * Time: 01:46
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
public class PasswordUtil {
    private PasswordUtil(){}

    /**
     * 密码相同
     * @param password_1 password_1
     * @param password_2 password_2
     * @return
     */
    public static boolean compare(String password_1, String password_2) {
        if (password_1.equals(password_2)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean nulled(String password) {
        if (password==null||password.equals("")) {
            return false;
        } else return true;
    }


    /**
     *  密码大于6位
     * @param password password
     * @return
     */
    public static boolean exceed(String password) {
        if (password.length() >= 6) {
            return true;
        } else return false;
    }


    /**
     *
     * @param password_1 password_1
     * @param password_2 password_2
     * @return
     */
    public static String passwordTest(String password_1, String password_2) {
        if (nulled(password_1)) {
            if (exceed(password_1)) {
                if (compare(password_1, password_2)) {
                    return null;
                }
                return "两次密码不相同！";
            }
            return "密码需为6位字符以上!";
        }
        return "密码不能为空字符！";
    }








    /**
     * 密码强度
     */
}
