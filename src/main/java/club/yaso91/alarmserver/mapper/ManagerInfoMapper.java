/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:ManagerInfoMapper.java
 *    Date:2019/11/14 下午3:38
 *    Author:Yaso
 */

package club.yaso91.alarmserver.mapper;

import club.yaso91.alarmserver.entity.Manager;
import org.springframework.stereotype.Repository;

/**
 * @title: ManagerInfoMapper
 * @package club.yaso91.alarmserver.mapper
 * @description: manager_info mapper.
 * @author: Yaso
 * @date: 2019-11-14 15:53
 * @version: V1.0
*/

@Repository
public interface ManagerInfoMapper {
    /**
     * 丢弃.
     * @param username
     * @param password
     * @return
     */
    @Deprecated
    Manager selectManager(String username, String password);

    /**
     * 配合spring security框架控制访问权限.
     * @param username 用户名.
     * @return 管理员信息.
     */
    Manager selectUserByUsername(String username);
}
