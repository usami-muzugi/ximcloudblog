package cn.ximcloud.blog.ximcloudblog.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Wizard
 * Date: 2018-04-05
 * Time: 20:27
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
@Entity
public class AdminInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer admin_id;

    private Long registerTime;

    private Long lastLoginTime;

    private String loginIPArrayList;

    private boolean adminAccountAvailableStatus;

    private String registerIP;

    public AdminInfo() {
    }

    @Override
    public String toString() {
        return "AdminInfo{" +
                "admin_id=" + admin_id +
                ", registerTime=" + registerTime +
                ", lastLoginTime=" + lastLoginTime +
                ", loginIPArrayList='" + loginIPArrayList + '\'' +
                ", adminAccountAvailableStatus=" + adminAccountAvailableStatus +
                ", registerIP='" + registerIP + '\'' +
                '}';
    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public Long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLoginIPArrayList() {
        return loginIPArrayList;
    }

    public void setLoginIPArrayList(String loginIPArrayList) {
        this.loginIPArrayList = loginIPArrayList;
    }

    public boolean isAdminAccountAvailableStatus() {
        return adminAccountAvailableStatus;
    }

    public void setAdminAccountAvailableStatus(boolean adminAccountAvailableStatus) {
        this.adminAccountAvailableStatus = adminAccountAvailableStatus;
    }

    public String getRegisterIP() {
        return registerIP;
    }

    public void setRegisterIP(String registerIP) {
        this.registerIP = registerIP;
    }
}
