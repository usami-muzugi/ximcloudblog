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

    private String admin_icon;

    private String admin_job;

    private Long registerTime;

    private Long lastLoginTime;

    @Column(columnDefinition = "longtext")
    private String loginIPList;

    private boolean adminAccountAvailableStatus;

    private String registerIP;

    public AdminInfo() {
    }

    public AdminInfo(String admin_icon, String admin_job, Long registerTime, Long lastLoginTime, String loginIPList, boolean adminAccountAvailableStatus, String registerIP) {
        this.admin_icon = admin_icon;
        this.admin_job = admin_job;
        this.registerTime = registerTime;
        this.lastLoginTime = lastLoginTime;
        this.loginIPList = loginIPList;
        this.adminAccountAvailableStatus = adminAccountAvailableStatus;
        this.registerIP = registerIP;
    }

    @Override
    public String toString() {
        return "AdminInfo{" +
                "admin_id=" + admin_id +
                ", admin_icon='" + admin_icon + '\'' +
                ", admin_job='" + admin_job + '\'' +
                ", registerTime=" + registerTime +
                ", lastLoginTime=" + lastLoginTime +
                ", loginIPList='" + loginIPList + '\'' +
                ", adminAccountAvailableStatus=" + adminAccountAvailableStatus +
                ", registerIP='" + registerIP + '\'' +
                '}';
    }

    public String getAdmin_job() {
        return admin_job;
    }

    public void setAdmin_job(String admin_job) {
        this.admin_job = admin_job;
    }

    public String getAdmin_icon() {
        return admin_icon;
    }

    public void setAdmin_icon(String admin_icon) {
        this.admin_icon = admin_icon;
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

    public String getLoginIPList() {
        return loginIPList;
    }

    public void setLoginIPList(String loginIPList) {
        this.loginIPList = loginIPList;
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
