/**
 * projectName: alarmserver
 * fileName: YasoThreadFactory.java
 * packageName: club.yaso91.alarmserver.component
 * date: 2019-11-14 10:35
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.component;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: YasoThreadFactory
 * @packageName: club.yaso91.alarmserver.component
 * @description:
 * @data: 2019-11-14 10:35
 **/
public class YasoThreadFactory implements ThreadFactory {
    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(1);

    /**
     * 定义线程组名称，在问题排查时，非常有帮助.
     *
     * @param threadGroupName 线程组名
     */
    YasoThreadFactory(String threadGroupName) {
        namePrefix = "From YasoThreadFactory's " + threadGroupName + "-Worker-";
    }

    @Override
    public Thread newThread(Runnable task) {
        String name = namePrefix + nextId.getAndIncrement();
        Thread thread = new Thread(null, task, name, 0, false);
        System.out.println(thread.getName());
        return thread;
    }
}
