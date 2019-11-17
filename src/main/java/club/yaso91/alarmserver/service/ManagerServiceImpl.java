/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:ManagerServiceImpl.java
 *    Date:2019/11/17 上午11:13
 *    Author:Yaso
 */
package club.yaso91.alarmserver.service;

import club.yaso91.alarmserver.domain.Manager;
import club.yaso91.alarmserver.mapper.ManagerInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: ManagerService
 * @packageName: club.yaso91.alarmserver.service
 * @description:
 * @data: 2019-10-05 11:35
 **/
@Service
public class ManagerServiceImpl implements UserDetailsService {

    @Autowired
     private ManagerInfoMapper managerInfoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Manager manager = managerInfoMapper.selectUserByUsername(username);
        if (manager == null) {
            throw new UsernameNotFoundException("账户不存在.");
        }
        return manager;
    }
}
