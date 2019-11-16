/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:CardReaderMapper.java
 *    Date:2019/11/14 下午3:27
 *    Author:Yaso
 */

package club.yaso91.alarmserver.mapper;

import club.yaso91.alarmserver.domain.CardReader;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
/**
 * @title: CardReaderMapper
 * @package club.yaso91.alarmserver.mapper
 * @description: card_reader表Mapper
 * @author: Yaso
 * @date: 2019-1128-14 15:28
 * @version: V1.0
*/

@Repository
public interface CardReaderMapper {
    /**
     * ..
     * @return
     */
    ArrayList<CardReader> selectAll();
}
