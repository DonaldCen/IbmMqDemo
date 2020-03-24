package com.xuanwu.queue;

import com.alibaba.fastjson.JSON;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueue;
import com.xuanwu.BaseJunit4Test;
import com.xuanwu.service.QueueService;
import com.xuanwu.util.Config;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description
 * @Author <a href="mailto:cenyingqiang@wxchina.com">yingqiang.Cen</a>
 * @Date 2020/3/19
 * @Version 1.0.0
 */
public class QueueTest extends BaseJunit4Test {
    @Autowired
    private QueueService queueService;

    @Test
    public void testPutMessageToQueue() throws MQException {
        queueService.putMessageToQueue("测试123123啦");
    }
    @Test
    public void testGetMessageFromQueue() throws MQException {
        String msg = queueService.getMessageFromQueue();
        System.out.println(msg);
    }

}
