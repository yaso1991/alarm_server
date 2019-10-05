/**
 * projectName: alarm_server
 * fileName: AlarmInfo.java
 * packageName: club.yaso91.alarm_server.entity
 * date: 2019-10-05 16:34
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.entity;

import java.sql.Timestamp;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: AlarmInfo
 * @packageName: club.yaso91.alarm_server.entity
 * @description:
 * @data: 2019-10-05 16:34
 **/
public class AlarmInfo {
    private int id;
    private String name;
    private String type;
    private int cardReaderId;
    private String state;
    private boolean alarming;
    private Timestamp alarmTime;
    private int alarmSpan;
    private String pushLevel;
    private int employeeId;
    private int masterId;
    private Employee employee;
    private Employee master;
    private CardReader cardReader;

    @Override
    public String toString() {
        return "AlarmInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", cardReaderId=" + cardReaderId +
                ", state='" + state + '\'' +
                ", alarming=" + alarming +
                ", alarmTime=" + alarmTime +
                ", alarmSpan=" + alarmSpan +
                ", pushLevel='" + pushLevel + '\'' +
                ", employeeId=" + employeeId +
                ", masterId=" + masterId +
                ", employee=" + employee +
                ", master=" + master +
                ", cardReader=" + cardReader +
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCardReaderId() {
        return cardReaderId;
    }

    public void setCardReaderId(int cardReaderId) {
        this.cardReaderId = cardReaderId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isAlarming() {
        return alarming;
    }

    public void setAlarming(boolean alarming) {
        this.alarming = alarming;
    }

    public Timestamp getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Timestamp alarmTime) {
        this.alarmTime = alarmTime;
    }

    public int getAlarmSpan() {
        return alarmSpan;
    }

    public void setAlarmSpan(int alarmSpan) {
        this.alarmSpan = alarmSpan;
    }

    public String getPushLevel() {
        return pushLevel;
    }

    public void setPushLevel(String pushLevel) {
        this.pushLevel = pushLevel;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getMaster() {
        return master;
    }

    public void setMaster(Employee master) {
        this.master = master;
    }

    public CardReader getCardReader() {
        return cardReader;
    }

    public void setCardReader(CardReader cardReader) {
        this.cardReader = cardReader;
    }
}
