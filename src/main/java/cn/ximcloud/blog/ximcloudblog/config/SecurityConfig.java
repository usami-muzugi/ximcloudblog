//package cn.ximcloud.blog.ximcloudblog.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * Created by IntelliJ IDEA.
// * User: Wizard
// * Date: 2018-04-15
// * Time: 21:37
// * ProjectName: ximcloudblog
// * To change this template use File | Settings | File Templates.
// * <p>
// * ////////////////////////////////////////////////////////////////////
// * //                          _ooOoo_                               //
// * //                         o8888888o                              //
// * //                         88" . "88                              //
// * //                         (| ^_^ |)                              //
// * //                         O\  =  /O                              //
// * //                      ____/`---'\____                           //
// * //                    .'  \\|     |//  `.                         //
// * //                   /  \\|||  :  |||//  \                        //
// * //                  /  _||||| -:- |||||-  \                       //
// * //                  |   | \\\  -  /// |   |                       //
// * //                  | \_|  ''\---/''  |   |                       //
// * //                  \  .-\__  `-`  ___/-. /                       //
// * //                ___`. .'  /--.--\  `. . ___                     //
// * //              ."" '<  `.___\_<|>_/___.'  >'"".                  //
// * //            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
// * //            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
// * //      ========`-.____`-.___\_____/___.-`____.-'========         //
// * //                           `=---='                              //
// * //      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
// * //         佛祖保佑          永无BUG     永不修改                  //
// * ////////////////////////////////////////////////////////////////////
// **/
//
///**
// *  安全配置类
// */
//
//@EnableWebSecurity   //启用Web安全注解
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    /**
//     * 自定义配置
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/css/**", "/js/**", "/font/**", "/index.php","/","/login","/register").permitAll()    //都可以访问
//                .antMatchers("/admin/**").hasRole("ADMIN")    //需要相应的角色才能访问
//                .and().formLogin()    //基于 Form 表单验证
//                .loginPage("/login").failureUrl("/login-error");
//    }
//
//    /**
//     * 认证信息管理
//     * @param authenticationManagerBuilder
//     * @throws Exception
//     */
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder
//                .inMemoryAuthentication()    //认证信息管理存储于内存中
//                .withUser("usamimizugi").password("ourinsama").roles("ADMIN");
//    }
//}
