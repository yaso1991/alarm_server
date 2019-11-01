/**
 * projectName: alarm_server
 * fileName: ManagerService.java
 * packageName: club.yaso91.alarm_server.service
 * date: 2019-10-05 11:35
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.service;

import club.yaso91.alarm_server.entity.Manager;
import club.yaso91.alarm_server.entity.SystemConfig;
import club.yaso91.alarm_server.mapper.ManagerInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: ManagerService
 * @packageName: club.yaso91.alarm_server.service
 * @description:
 * @data: 2019-10-05 11:35
 **/
@Service
public class ManagerService implements UserDetailsService {
    @Autowired
    ManagerInfoMapper managerInfoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Manager manager =  managerInfoMapper.selectUserByUsername(username);
        if(manager == null) {
            throw new UsernameNotFoundException("账户不存在.");
        }
        return  manager;
    }
}
