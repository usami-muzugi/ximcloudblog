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
    private Integer adminInfo_id;

    private String adminInfo_icon;

    private String adminInfo_firstName;

    private String adminInfo_lastName;

    private String adminInfo_job;

    private String adminInfo_bio;

    private String adminInfo_city;

    private Integer adminInfo_age;

    private Long adminInfo_registerTime;

    private Long adminInfo_lastLoginTime;

    private Long adminInfo_lastLoginIP;

    @Column(columnDefinition = "longtext")
    private String adminInfo_loginIPList;

    private String adminInfo_registerIP;

    public AdminInfo() {
    }

    @Override
    public String toString() {
        return "AdminInfo{" +
                "adminInfo_id=" + adminInfo_id +
                ", adminInfo_icon='" + adminInfo_icon + '\'' +
                ", adminInfo_firstName='" + adminInfo_firstName + '\'' +
                ", adminInfo_lastName='" + adminInfo_lastName + '\'' +
                ", adminInfo_job='" + adminInfo_job + '\'' +
                ", adminInfo_bio='" + adminInfo_bio + '\'' +
                ", adminInfo_city='" + adminInfo_city + '\'' +
                ", adminInfo_age=" + adminInfo_age +
                ", adminInfo_registerTime=" + adminInfo_registerTime +
                ", adminInfo_lastLoginTime=" + adminInfo_lastLoginTime +
                ", adminInfo_lastLoginIP=" + adminInfo_lastLoginIP +
                ", adminInfo_loginIPList='" + adminInfo_loginIPList + '\'' +
                ", adminInfo_registerIP='" + adminInfo_registerIP + '\'' +
                '}';
    }

    public Integer getAdminInfo_id() {
        return adminInfo_id;
    }

    public void setAdminInfo_id(Integer adminInfo_id) {
        this.adminInfo_id = adminInfo_id;
    }

    public String getAdminInfo_icon() {
        return adminInfo_icon;
    }

    public void setAdminInfo_icon(String adminInfo_icon) {
        this.adminInfo_icon = adminInfo_icon;
    }

    public String getAdminInfo_firstName() {
        return adminInfo_firstName;
    }

    public void setAdminInfo_firstName(String adminInfo_firstName) {
        this.adminInfo_firstName = adminInfo_firstName;
    }

    public String getAdminInfo_lastName() {
        return adminInfo_lastName;
    }

    public void setAdminInfo_lastName(String adminInfo_lastName) {
        this.adminInfo_lastName = adminInfo_lastName;
    }

    public String getAdminInfo_job() {
        return adminInfo_job;
    }

    public void setAdminInfo_job(String adminInfo_job) {
        this.adminInfo_job = adminInfo_job;
    }

    public String getAdminInfo_bio() {
        return adminInfo_bio;
    }

    public void setAdminInfo_bio(String adminInfo_bio) {
        this.adminInfo_bio = adminInfo_bio;
    }

    public String getAdminInfo_city() {
        return adminInfo_city;
    }

    public void setAdminInfo_city(String adminInfo_city) {
        this.adminInfo_city = adminInfo_city;
    }

    public Integer getAdminInfo_age() {
        return adminInfo_age;
    }

    public void setAdminInfo_age(Integer adminInfo_age) {
        this.adminInfo_age = adminInfo_age;
    }

    public Long getAdminInfo_registerTime() {
        return adminInfo_registerTime;
    }

    public void setAdminInfo_registerTime(Long adminInfo_registerTime) {
        this.adminInfo_registerTime = adminInfo_registerTime;
    }

    public Long getAdminInfo_lastLoginTime() {
        return adminInfo_lastLoginTime;
    }

    public void setAdminInfo_lastLoginTime(Long adminInfo_lastLoginTime) {
        this.adminInfo_lastLoginTime = adminInfo_lastLoginTime;
    }

    public Long getAdminInfo_lastLoginIP() {
        return adminInfo_lastLoginIP;
    }

    public void setAdminInfo_lastLoginIP(Long adminInfo_lastLoginIP) {
        this.adminInfo_lastLoginIP = adminInfo_lastLoginIP;
    }

    public String getAdminInfo_loginIPList() {
        return adminInfo_loginIPList;
    }

    public void setAdminInfo_loginIPList(String adminInfo_loginIPList) {
        this.adminInfo_loginIPList = adminInfo_loginIPList;
    }

    public String getAdminInfo_registerIP() {
        return adminInfo_registerIP;
    }

    public void setAdminInfo_registerIP(String adminInfo_registerIP) {
        this.adminInfo_registerIP = adminInfo_registerIP;
    }

    public AdminInfo(String adminInfo_icon, String adminInfo_firstName, String adminInfo_lastName, String adminInfo_job, String adminInfo_bio, String adminInfo_city, Integer adminInfo_age, Long adminInfo_registerTime, Long adminInfo_lastLoginTime, Long adminInfo_lastLoginIP, String adminInfo_loginIPList, String adminInfo_registerIP) {
        this.adminInfo_icon = adminInfo_icon;
        this.adminInfo_firstName = adminInfo_firstName;
        this.adminInfo_lastName = adminInfo_lastName;
        this.adminInfo_job = adminInfo_job;
        this.adminInfo_bio = adminInfo_bio;
        this.adminInfo_city = adminInfo_city;
        this.adminInfo_age = adminInfo_age;
        this.adminInfo_registerTime = adminInfo_registerTime;
        this.adminInfo_lastLoginTime = adminInfo_lastLoginTime;
        this.adminInfo_lastLoginIP = adminInfo_lastLoginIP;
        this.adminInfo_loginIPList = adminInfo_loginIPList;
        this.adminInfo_registerIP = adminInfo_registerIP;
    }
}
