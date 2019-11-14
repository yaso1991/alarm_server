/**
 * projectName: alarmserver
 * fileName: CardReader.java
 * packageName: club.yaso91.alarmserver.entity
 * date: 2019-10-05 17:11
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.entity;

import lombok.Data;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: CardReader
 * @packageName: club.yaso91.alarmserver.entity
 * @description:
 * @data: 2019-10-05 17:11
 **/
@Data
public class CardReader {
    private int id;
    private String name;
    private String value;
}