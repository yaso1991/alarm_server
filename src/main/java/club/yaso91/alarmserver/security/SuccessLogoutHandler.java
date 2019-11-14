/**
 * projectName: alarmserver
 * fileName: LogoutSuccessHandler.java
 * packageName: club.yaso91.alarmserver.security
 * date: 2019-10-16 20:02
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
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
 * @className: LogoutSuccessHandler
 * @packageName: club.yaso91.alarmserver.security
 * @description:
 * @data: 2019-10-16 20:02
 **/
public class SuccessLogoutHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication auth) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(200);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("authenticated", false);
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(map));
        out.flush();
        out.close();
    }
}
