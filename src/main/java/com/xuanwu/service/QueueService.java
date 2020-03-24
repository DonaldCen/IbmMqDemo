package com.xuanwu.service;

import com.ibm.mq.MQException;
import com.ibm.mq.MQQueue;

/**
 * @Description 队列相关Service
 * @Author <a href="mailto:cenyingqiang@wxchina.com">yingqiang.Cen</a>
 * @Date 2020/3/19
 * @Version 1.0.0
 */
public interface QueueService {

    void putMessageToQueue(String msg) throws MQException;

    String getMessageFromQueue() throws MQException;

}
