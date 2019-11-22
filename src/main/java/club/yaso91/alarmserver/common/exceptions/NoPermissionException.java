/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:NoPermissionException.java
 *    Date:2019/11/22 下午3:33
 *    Author:Yaso
 */

package club.yaso91.alarmserver.common.exceptions;

/**
 * 无权限异常.
 *
 * @author 晓风轻 (Yaso改)
 * @date: 2019-11-22 14:48
 */

public class NoPermissionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoPermissionException() {
	}

	public NoPermissionException(String message) {
		super(message);
	}

	public NoPermissionException(Throwable cause) {
		super(cause);
	}

	public NoPermissionException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoPermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
