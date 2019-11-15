/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:HomeController.java
 *    Date:2019/11/14 下午3:50
 *    Author:Yaso
 */

package club.yaso91.alarmserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @title: HomeController
 * @package club.yaso91.alarmserver.controller
 * @description:
 * @author: Yaso
 * @date: 2019-11-14 15:51
 * @version: V1.0
*/

@RestController
@RequestMapping("/")
public class HomeController {
    @RequestMapping
    public String index() {
        return "index";
    }

}
