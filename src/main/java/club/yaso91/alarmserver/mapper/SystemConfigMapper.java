/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:SystemConfigMapper.java
 *    Date:2019/11/14 下午3:43
 *    Author:Yaso
 */

package club.yaso91.alarmserver.mapper;

import club.yaso91.alarmserver.entity.SystemConfig;
import org.springframework.stereotype.Repository;

/**
 * @title: SystemConfigMapper
 * @package club.yaso91.alarmserver.mapper
 * @description: system_config mapper.
 * @author: Yaso
 * @date: 2019-11-14 15:48
 * @version: V1.0
*/

@Repository
public interface SystemConfigMapper {
    /**
     * 全选
     * @return
     */
    SystemConfig selectAll();

    /**
     * 更新系统设置参数
     * @param systemConfig  系统设置参数
     * @return 成功返回1,失败返回0.
     */
    int update(SystemConfig systemConfig);
}
