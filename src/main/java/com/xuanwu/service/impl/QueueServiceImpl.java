package com.xuanwu.service.impl;

import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.CMQC;
import com.xuanwu.service.QueueService;
import com.xuanwu.util.Config;
import com.xuanwu.util.IbmMqFactory;
import com.xuanwu.util.MQExceptionCode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

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

    private MQQueue queue;

    private MQQueueManager manager;


    public void openQueue() throws MQException {
        manager = factory.getMqQueueManager();
        int openOptions = CMQC.MQOO_INPUT_AS_Q_DEF | CMQC.MQOO_OUTPUT;
        try {
            queue = manager.accessQueue(config.getQueueName(), openOptions);
        } catch (MQException e) {
            logger.error("create queue error..", e);
            try {
                manager.close();
            } catch (MQException ex) {
                if (ex.reasonCode == MQExceptionCode.MQRC_OPTION_NOT_VALID_FOR_TYPE) {
                    openOptions = CMQC.MQOO_OUTPUT;
                    queue = manager.accessQueue(config.getQueueName(), openOptions);
                }
                logger.error("MQQueueManager close error..", ex);
            }
            try {
                manager.disconnect();
            } catch (Exception eq) {
                logger.error("MQQueueManager disconnect error..", eq);
            }
        }
    }

    @Override
    public void putMessageToQueue(String msg) throws MQException {
        if (queue == null) {
            openQueue();
        }
        MQPutMessageOptions pmo = new MQPutMessageOptions();
        MQMessage mqMessage = new MQMessage();
        try {
            mqMessage.characterSet = config.getCcsid();
            mqMessage.write(msg.getBytes());

            queue.put(mqMessage, pmo);
            manager.commit();
        } catch (IOException e) {
            logger.error("put message to queue failed..", e);
        }
    }

    @Override
    public String getMessageFromQueue() throws MQException {
        if (queue == null) {
            openQueue();
        }
        int len = 0;
        try {
            MQMessage mqMsg = new MQMessage();
            MQGetMessageOptions gmo = new MQGetMessageOptions();
            gmo.options = gmo.options + CMQC.MQGMO_SYNCPOINT;
            gmo.options = gmo.options + CMQC.MQGMO_WAIT;
            gmo.options = gmo.options + CMQC.MQGMO_FAIL_IF_QUIESCING;
            gmo.waitInterval = config.getConnectTimeout();
            mqMsg.characterSet = config.getCcsid();

            queue.get(mqMsg, gmo);
            len = mqMsg.getDataLength();
            byte[] message = new byte[len];
            mqMsg.readFully(message, 0, len);
            String msgStr = new String(message);
            logger.info("get from mq message is:" + msgStr);
            manager.commit();
            return msgStr;
        } catch (IOException e) {
            logger.error("mq msg get dataLength failed..", e);
        } catch (MQException mqe) {
            int reason = mqe.reasonCode;
            if (reason == MQExceptionCode.MQRC_NO_MSG_AVAILABLE) {// no messages
                logger.warn("mq already no message!");
                return null;
            } else {
                logger.error("mq msg get dataLength failed..", mqe);
            }
        }
        return null;
    }
}
