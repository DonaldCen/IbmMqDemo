package com.xuanwu.service.impl;

import com.alibaba.fastjson.JSON;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.CMQC;
import com.xuanwu.service.QueueService;
import com.xuanwu.util.Config;
import com.xuanwu.util.IbmMqFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author <a href="mailto:cenyingqiang@wxchina.com">yingqiang.Cen</a>
 * @Date 2020/3/19
 * @Version 1.0.0
 */
@Component
public class QueueServiceImpl implements QueueService {
    private static final Logger logger = LoggerFactory.getLogger(QueueServiceImpl.class);
    @Autowired
    private IbmMqFactory factory;
    @Autowired
    private Config config;


    @Override
    public MQQueue findQueueByName(String queueName) {
        MQQueueManager manager = factory.getMqQueueManager();
        MQQueue queue = null;
        try {
            queue = manager.accessQueue(queueName, CMQC.MQOO_OUTPUT);
        } catch (MQException e) {
            logger.error("create queue error..",e);
            try {
                manager.close();
            } catch (MQException ex) {
                logger.error("MQQueueManager close error..",ex);
            }
            try {
                manager.disconnect();
            } catch (Exception eq) {
                logger.error("MQQueueManager disconnect error..",eq);
            }
        }
        return queue;
    }

    @Override
    public MQQueue addQueue(String queueName) {
        MQQueueManager manager = factory.getMqQueueManager();
        try {
            MQQueue queue = new MQQueue(manager,queueName,CMQC.MQOO_OUTPUT,config.getQueueManagerName(),"123","123");
            return queue;
        } catch (MQException e) {
            logger.error("create queue error..",e);
            return null;
        }finally {
            try {
                manager.disconnect();
            } catch (MQException ex) {
                ex.printStackTrace();
            }
        }
    }
}
