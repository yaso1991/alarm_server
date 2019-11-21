/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:RedisTest.java
 *    Date:2019/11/18 上午10:53
 *    Author:Yaso
 */
package club.yaso91.statictest;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

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

// 使用lambda表达式
        List costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax.stream().forEach(System.out::println);
    }
}
