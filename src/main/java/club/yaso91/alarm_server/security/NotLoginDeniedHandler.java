/**
 * projectName: alarm_server
 * fileName: NotLoginDeniedHandler.java
 * packageName: club.yaso91.alarm_server.security
 * date: 2019-10-16 14:32
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.security;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: NotLoginDeniedHandler
 * @packageName: club.yaso91.alarm_server.security
 * @description: 未登录用户访问未授权资源的处理.
 * @data: 2019-10-16 14:32
 **/
public class NotLoginDeniedHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
