/**
 * projectName: alarm_server
 * fileName: Employee.java
 * packageName: club.yaso91.alarm_server.entity
 * date: 2019-10-05 17:10
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.entity;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: Employee
 * @packageName: club.yaso91.alarm_server.entity
 * @description:
 * @data: 2019-10-05 17:10
 **/
public class Employee {
    private int id;
    private String name;
    private String position;
    private String workId;
    private String email;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", workId='" + workId + '\'' +
                ", email='" + email + '\'' +
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
