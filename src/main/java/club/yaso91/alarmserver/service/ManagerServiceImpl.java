/**
 * projectName: alarmserver
 * fileName: ManagerService.java
 * packageName: club.yaso91.alarmserver.service
 * date: 2019-10-05 11:35
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.service;

import club.yaso91.alarmserver.entity.Manager;
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