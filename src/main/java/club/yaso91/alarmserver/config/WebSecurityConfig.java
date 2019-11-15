/**
 * projectName: alarmserver
 * fileName: WebSecurityConfig.java
 * packageName: club.yaso91.alarmserver.config
 * date: 2019-10-13 11:14
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.config;

import club.yaso91.alarmserver.service.ManagerServiceImpl;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: WebSecurityConfig
 * @packageName: club.yaso91.alarmserver.config
 * @description: 系统安全权限配置.
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

    /**
     * @version: V1.0
     * @author: Yaso
     * @className: LoginFailureHandler
     * @packageName: club.yaso91.alarmserver.security
     * @description:登录失败的处理. * @data: 2019-10-16 14:45
     **/
    private class LoginFailureHandler implements AuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                            AuthenticationException e) throws IOException, ServletException {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(401);
            ObjectMapper om = new ObjectMapper();
            Map<String, Object> map = new HashMap<String, Object>(16);
            map.put("status", 401);
            if (e instanceof BadCredentialsException ||
                    e instanceof UsernameNotFoundException) {
                map.put("msg", "账户名或者密码输入错误!");
            } else if (e instanceof LockedException) {
                map.put("msg", "账户被锁定，请联系管理员!");
            } else if (e instanceof CredentialsExpiredException) {
                map.put("msg", "密码过期，请联系管理员!");
            } else if (e instanceof AccountExpiredException) {
                map.put("msg", "账户过期，请联系管理员!");
            } else if (e instanceof DisabledException) {
                map.put("msg", "账户被禁用，请联系管理员!");
            } else {
                map.put("msg", "登录失败!");
            }
            PrintWriter out = response.getWriter();
            out.write(om.writeValueAsString(map));
            out.flush();
            out.close();
        }
    }

    /**
     * @version: V1.0
     * @author: Yaso
     * @className: LoginSuccessHandle
     * @packageName: club.yaso91.alarmserver.security
     * @description:登录成功的处理函数.
     * @data: 2019-10-16 14:42
     **/
    private class LoginSuccessHandler implements AuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Authentication auth) throws IOException, ServletException {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(200);
            Map<String, Object> map = new HashMap<String, Object>(16);
            map.put("manager", auth.getPrincipal());
            map.put("authenticated", true);
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(map));
            out.flush();
            out.close();
        }
    }

    /**
     * @version: V1.0
     * @author: Yaso
     * @className: NotLoginDeniedHandler
     * @packageName: club.yaso91.alarmserver.security
     * @description: 未登录用户访问未授权资源的处理.
     * @data: 2019-10-16 14:32
     **/
    private class NotLoginDeniedHandler implements AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                             AuthenticationException exception) throws IOException, ServletException {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    /**
     * @version: V1.0
     * @author: Yaso
     * @className: LogoutSuccessHandler
     * @packageName: club.yaso91.alarmserver.security
     * @description:
     * @data: 2019-10-16 20:02
     **/
    private class SuccessLogoutHandler implements LogoutSuccessHandler {
        @Override
        public void onLogoutSuccess(HttpServletRequest request,
                                    HttpServletResponse response,
                                    Authentication auth) throws IOException, ServletException {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(200);
            Map<String, Object> map = new HashMap<String, Object>(16);
            map.put("authenticated", false);
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(map));
            out.flush();
            out.close();
        }
    }
}
