package club.yaso91.alarm_server.controller;

import club.yaso91.alarm_server.entity.Manager;
import club.yaso91.alarm_server.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @title: LoginController
 * @package club.yaso91.alarm_server.controller
 * @description: controller handle login page.
 * @author: Yaso
 * @date: 2019-1055-05 10:55
 * @version: V1.0
 */

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private ManagerService managerService;

    /**
     * @title: login
     * @author: Yaso
     * @date: 2019-10-05 11:25
     * @description: TODO
     * @param: username
     * @param: password
     * @return: club.yaso91.alarm_server.entity.Manager
     * @throws:
     */
    @RequestMapping(method = RequestMethod.POST)
    public Manager login(String username, String password) {
        return managerService.login(username, password);
    }
}
