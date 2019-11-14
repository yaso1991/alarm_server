/**
 * projectName: alarmserver
 * fileName: WebSecurityConfig.java
 * packageName: club.yaso91.alarmserver.config
 * date: 2019-10-13 11:14
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.config;

import club.yaso91.alarmserver.security.LoginFailureHandler;
import club.yaso91.alarmserver.security.LoginSuccessHandler;
import club.yaso91.alarmserver.security.NotLoginDeniedHandler;
import club.yaso91.alarmserver.security.SuccessLogoutHandler;
import club.yaso91.alarmserver.service.ManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: WebSecurityConfig
 * @packageName: club.yaso91.alarmserver.config
 * @description:
 * @data: 2019-10-13 11:14
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ManagerServiceImpl managerServiceImpl;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(managerServiceImpl);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/", "/alarmInfo", "/employeeInfo", "/getSumInfos");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁止csrf验证.
        http.csrf().disable();
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(new LoginSuccessHandler())
                .failureHandler(new LoginFailureHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new SuccessLogoutHandler())
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .exceptionHandling()
                // 当登录后访问无权资源时（默认跳转到报错页面）
//                .accessDeniedHandler(new LoginedDeniedHandler())
                // 当未登录用户访问未授权资源是的处理动作.
                .authenticationEntryPoint(new NotLoginDeniedHandler())
        ;
    }
}
