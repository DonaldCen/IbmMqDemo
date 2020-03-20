package com.xuanwu.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description
 * @Author <a href="mailto:cenyingqiang@wxchina.com">yingqiang.Cen</a>
 * @Date 2020/3/19
 * @Version 1.0.0
 */
public class IbmMqServer {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("applicationcontext.xml");
        System.out.println("IbmMqServer has started.");
    }
}
