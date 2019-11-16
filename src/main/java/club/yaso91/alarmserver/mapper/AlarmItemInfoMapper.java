/**
 * projectName: alarmserver
 * fileName: AlarmItemInfoMapper.java
 * packageName: club.yaso91.alarmserver.mapper
 * date: 2019-10-07 15:50
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.mapper;

import club.yaso91.alarmserver.domain.AlarmItemInfo;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: AlarmItemInfoMapper
 * @packageName: club.yaso91.alarmserver.mapper
 * @description:
 * @data: 2019-10-07 15:50
 **/
@Repository
public interface AlarmItemInfoMapper {
    /**
     * 根据条件选择报警条目
     *
     * @param beginTime    开始时间
     * @param endTime      结束时间
     * @param alarmName    报警名称
     * @param employeeName 员工名称
     * @return 符合条件的报警条目.
     */
    ArrayList<AlarmItemInfo> selectSumInfos(Timestamp beginTime, Timestamp endTime, String alarmName,
                                            String employeeName);

    /**
     * 获取报警条数.
     *
     * @return 报警条数.
     */
    int selectCount();

    /**
     * 查找报警条目.
     *
     * @param alarmId   报警点ID
     * @param alarmTime 报警开始时间
     * @return 报警条目.
     */
    AlarmItemInfo find(int alarmId, Timestamp alarmTime);

    /**
     * 插入报警条目
     *
     * @param alarmItemInfo 需要插入的报警条目
     * @return 成功返回1, 失败返回0.
     */
    int insert(AlarmItemInfo alarmItemInfo);

    /**
     * 更新报警条目
     *
     * @param alarmItemInfo 报警条目
     * @return 成功返回1, 失败返回0.
     */
    int update(AlarmItemInfo alarmItemInfo);
}
