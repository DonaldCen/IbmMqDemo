package com.xuanwu.util;

import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.MQSimpleConnectionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

/**
 * @Description
 * @Author <a href="mailto:cenyingqiang@wxchina.com">yingqiang.Cen</a>
 * @Date 2020/3/19
 * @Version 1.0.0
 */
@Component
public class IbmMqFactory {
    private static final Logger logger = LoggerFactory.getLogger(IbmMqFactory.class);

    @Autowired
    private Config config;

    @PostConstruct
    private void init(){
        setEnvironment();
        setDefaultMQSimpleConnectionManager();
    }


    private void setEnvironment(){
        MQEnvironment.hostname = config.getHost();
        MQEnvironment.port = config.getPort();
        MQEnvironment.userID = config.getUserName();
        MQEnvironment.password = config.getPassword();
        MQEnvironment.channel = config.getChannel();
        MQEnvironment.CCSID = config.getCcsid();

        setProperties();
    }

    private void setProperties(){
        MQEnvironment.properties.put(Constants.MAXIMUM_SIZE,config.getMaximumSize());
        MQEnvironment.properties.put(Constants.CLNT_RCV_BUF_SIZE,config.getClntRcvBuffSize());
        MQEnvironment.properties.put(Constants.CLNT_SND_BUF_SIZE,config.getClntSndBuffSize());
        MQEnvironment.properties.put(Constants.CONNECT_TIMEOUT,config.getConnectTimeout());
        MQEnvironment.properties.put(Constants.KEEP_ALIVE,config.getKeepAlive());
    }


    public MQQueueManager getMqQueueManager() {
        MQQueueManager mqQueueManager = null;
        try {
            mqQueueManager = new MQQueueManager(config.getQueueManagerName());
            logger.info("初始化队列管理器完成!");
        } catch (MQException e) {
            logger.error("初始化队列管理器出现异常", e);
        }
        return mqQueueManager;
    }

    /**
     *
     */
    private void setDefaultMQSimpleConnectionManager(){
        MQSimpleConnectionManager connectionManager = new MQSimpleConnectionManager();
        connectionManager.setActive(MQSimpleConnectionManager.MODE_AUTO);
        connectionManager.setTimeout(TimeUnit.SECONDS.toMillis(config.getManagerPoolTimeout()));
        connectionManager.setMaxConnections(config.getManagerPoolMaxConnections());
        connectionManager.setMaxUnusedConnections(config.getManagerPoolUnusedConnections());
        MQEnvironment.setDefaultConnectionManager(connectionManager);
    }
}
