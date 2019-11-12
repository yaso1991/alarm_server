/**
 * projectName: alarm_server
 * fileName: Msg.java
 * packageName: club.yaso91.alarm_server.entity
 * date: 2019-11-12 15:08
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.entity;

import lombok.Data;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: Msg
 * @packageName: club.yaso91.alarm_server.entity
 * @description:
 * @data: 2019-11-12 15:08
 **/
@Data
public class Message {
    private String name;
    private String content;
}
