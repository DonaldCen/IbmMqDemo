package com.xuanwu.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author <a href="mailto:cenyingqiang@wxchina.com">yingqiang.Cen</a>
 * @Date 2020/3/19
 * @Version 1.0.0
 */
@Component
public class Config {
    @Value("${ibm.mq.queueManagerName}")
    private String queueManagerName;
    @Value("${ibm.mq.host}")
    private String host;
    @Value("${ibm.mq.port}")
    private int port;
    @Value("${ibm.mq.channel}")
    private String channel;
    @Value("${ibm.mq.userName}")
    private String userName;
    @Value("${ibm.mq.password}")
    private String password;
    @Value("${ibm.mq.ccsid}")
    private int ccsid;

    @Value("${ibm.mq.maximumSize}")
    private int maximumSize;
    @Value("${ibm.mq.clntRcvBuffSize}")
    private int clntRcvBuffSize;
    @Value("${ibm.mq.clntSndBuffSize}")
    private int clntSndBuffSize;
    @Value("${ibm.mq.connectTimeout}")
    private int connectTimeout;
    @Value("${ibm.mq.keepAlive}")
    private String keepAlive;
    @Value("${ibm.mq.managerPool.timeout}")
    private int managerPoolTimeout;
    @Value("${ibm.mq.managerPool.maxConnections}")
    private int managerPoolMaxConnections;
    @Value("${ibm.mq.managerPool.unusedConnections}")
    private int managerPoolUnusedConnections;



    public String getQueueManagerName() {
        return queueManagerName;
    }

    public void setQueueManagerName(String queueManagerName) {
        this.queueManagerName = queueManagerName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCcsid() {
        return ccsid;
    }

    public void setCcsid(int ccsid) {
        this.ccsid = ccsid;
    }

    public int getMaximumSize() {
        return maximumSize;
    }

    public void setMaximumSize(int maximumSize) {
        this.maximumSize = maximumSize;
    }

    public int getClntRcvBuffSize() {
        return clntRcvBuffSize;
    }

    public void setClntRcvBuffSize(int clntRcvBuffSize) {
        this.clntRcvBuffSize = clntRcvBuffSize;
    }

    public int getClntSndBuffSize() {
        return clntSndBuffSize;
    }

    public void setClntSndBuffSize(int clntSndBuffSize) {
        this.clntSndBuffSize = clntSndBuffSize;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public String getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(String keepAlive) {
        this.keepAlive = keepAlive;
    }

    public int getManagerPoolTimeout() {
        return managerPoolTimeout;
    }

    public void setManagerPoolTimeout(int managerPoolTimeout) {
        this.managerPoolTimeout = managerPoolTimeout;
    }

    public int getManagerPoolMaxConnections() {
        return managerPoolMaxConnections;
    }

    public void setManagerPoolMaxConnections(int managerPoolMaxConnections) {
        this.managerPoolMaxConnections = managerPoolMaxConnections;
    }

    public int getManagerPoolUnusedConnections() {
        return managerPoolUnusedConnections;
    }

    public void setManagerPoolUnusedConnections(int managerPoolUnusedConnections) {
        this.managerPoolUnusedConnections = managerPoolUnusedConnections;
    }
}
