/**
 * projectName: alarm_server
 * fileName: LoginFailureHandler.java
 * packageName: club.yaso91.alarm_server.security
 * date: 2019-10-16 14:45
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.devtools.restart.FailureHandler;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

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
 * @className: LoginFailureHandler
 * @packageName: club.yaso91.alarm_server.security
 * @description:登录失败的处理.
 ** @data: 2019-10-16 14:45
 **/
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(401);
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
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
