package com.example.distributed.crawling.akka;

/**
 * 该接口是客户端接口
 */
public interface Client {

    /**
     * 向其他客户端注册该客户端
     */
    void registerClientToClient();

    /**
     * 向server注册该客户端
     */
    void registerClientToServer();

    /**
     * 向其他server发送心跳
     */
    void sendHeartbeat();

    /**
     * 同步数据
     * 该接口是用来向其他的client进行同步数据使用的
     */
    void synchronousData();
}
