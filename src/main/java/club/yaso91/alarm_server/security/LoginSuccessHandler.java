/**
 * projectName: alarm_server
 * fileName: LoginSuccessHandle.java
 * packageName: club.yaso91.alarm_server.security
 * date: 2019-10-16 14:42
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
 * @className: LoginSuccessHandle
 * @packageName: club.yaso91.alarm_server.security
 * @description:登录成功的处理函数.
 * @data: 2019-10-16 14:42
 **/
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication auth) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(200);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("manager", auth.getPrincipal());
        map.put("authenticated", true);
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(map));
        out.flush();
        out.close();
    }
}
