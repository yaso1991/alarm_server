/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:RedisTest.java
 *    Date:2019/11/18 上午10:53
 *    Author:Yaso
 */
package club.yaso91.statictest;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: RedisTest
 * @packageName: club.yaso91.statictest
 * @description:
 * @data: 2019-11-18 10:53
 **/
public class RedisTest {
    public static void main(String[] args) {
        int result;

        result = foo();
        System.out.println(result);     /////////2

        result = bar();
        System.out.println(result);    /////////2
    }

    @SuppressWarnings("finally")
    public static int foo() {
        try {
            int a = 5 / 0;
        } catch (Exception e) {
            return 1;
        } finally {
            return 2;
        }

    }

    @SuppressWarnings("finally")
    public static int bar() {
        try {
            return 1;
        } finally {
            return 2;
        }
    }
}
