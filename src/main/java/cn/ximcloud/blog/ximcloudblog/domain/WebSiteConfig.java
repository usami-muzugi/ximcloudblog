package cn.ximcloud.blog.ximcloudblog.domain;

import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: Wizard
 * Date: 2018-04-05
 * Time: 22:58
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
public class WebSiteConfig {
    @Id
    @GeneratedValue
    private Integer webSiteId;
    private String webSiteName;
    private String webSiteDomain;
    private String webSiteInco;
    private Boolean webSiteSSL;

    public WebSiteConfig() {
    }

    public WebSiteConfig(String webSiteName, String webSiteDomain, String webSiteInco, Boolean webSiteSSL) {
        this.webSiteName = webSiteName;
        this.webSiteDomain = webSiteDomain;
        this.webSiteInco = webSiteInco;
        this.webSiteSSL = webSiteSSL;
    }

    @Override
    public String toString() {
        return "WebSiteConfig{" +
                "webSiteId=" + webSiteId +
                ", webSiteName='" + webSiteName + '\'' +
                ", webSiteDomain='" + webSiteDomain + '\'' +
                ", webSiteInco='" + webSiteInco + '\'' +
                ", webSiteSSL=" + webSiteSSL +
                '}';
    }

    public Integer getWebSiteId() {
        return webSiteId;
    }

    public void setWebSiteId(Integer webSiteId) {
        this.webSiteId = webSiteId;
    }

    public String getWebSiteName() {
        return webSiteName;
    }

    public void setWebSiteName(String webSiteName) {
        this.webSiteName = webSiteName;
    }

    public String getWebSiteDomain() {
        return webSiteDomain;
    }

    public void setWebSiteDomain(String webSiteDomain) {
        this.webSiteDomain = webSiteDomain;
    }

    public String getWebSiteInco() {
        return webSiteInco;
    }

    public void setWebSiteInco(String webSiteInco) {
        this.webSiteInco = webSiteInco;
    }

    public Boolean getWebSiteSSL() {
        return webSiteSSL;
    }

    public void setWebSiteSSL(Boolean webSiteSSL) {
        this.webSiteSSL = webSiteSSL;
    }
}
