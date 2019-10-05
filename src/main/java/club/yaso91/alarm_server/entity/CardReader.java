/**
 * projectName: alarm_server
 * fileName: CardReader.java
 * packageName: club.yaso91.alarm_server.entity
 * date: 2019-10-05 17:11
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.entity;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: CardReader
 * @packageName: club.yaso91.alarm_server.entity
 * @description:
 * @data: 2019-10-05 17:11
 **/
public class CardReader {
    private int id;
    private String name;
    private String value;

    @Override
    public String toString() {
        return "CardReader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
