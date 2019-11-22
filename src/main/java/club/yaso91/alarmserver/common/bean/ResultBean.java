/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:ResultBean.java
 *    Date:2019/11/22 下午3:33
 *    Author:Yaso
 */

package club.yaso91.alarmserver.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * controller统一返回到前台的对象,泛型.
 *
 * @author: 晓风轻 (Yaso改)
 * @date: 2019-11-22 14:26
 */
@Data
public class ResultBean<T> implements Serializable {

    public static final int NO_LOGIN = -1;
    public static final int SUCCESS = 0;
    public static final int CHECK_FAIL = 1;
    public static final int NO_PERMISSION = 2;
    public static final int UNKNOWN_EXCEPTION = -99;
    private static final long serialVersionUID = 1L;
    /**
     * 返回的信息(主要出错的时候使用)
     */
    private String msg = "success";

    /**
     * 接口返回码
     * 0   : 成功
     * >0 : 表示已知的异常(例如提示错误等, 需要调用地方单独处理)
     * <0 : 表示未知的异常(不需要单独处理, 调用方统一处理)
     */
    private int code = SUCCESS;

    /**
     * 返回的数据
     */
    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = UNKNOWN_EXCEPTION;
    }
}
