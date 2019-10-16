/**
 * projectName: alarm_server
 * fileName: WebSecurityConfig.java
 * packageName: club.yaso91.alarm_server.config
 * date: 2019-10-13 11:14
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.config;

import club.yaso91.alarm_server.security.LoginFailureHandler;
import club.yaso91.alarm_server.security.LoginSuccessHandle;
import club.yaso91.alarm_server.security.NotLoginDeniedHandler;
import club.yaso91.alarm_server.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: WebSecurityConfig
 * @packageName: club.yaso91.alarm_server.config
 * @description:
 * @data: 2019-10-13 11:14
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ManagerService managerService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(managerService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/", "/alarmInfo","/employeeInfo","/getSumInfos");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();//禁止csrf验证.
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(new LoginSuccessHandle())
                .failureHandler(new LoginFailureHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .exceptionHandling()
//                .accessDeniedHandler(new LoginedDeniedHandler()) // 当登录后访问无权资源时（默认跳转到报错页面）
                .authenticationEntryPoint(new NotLoginDeniedHandler()) // 当未登录用户访问未授权资源是的处理动作.
        ;

    }
}
