package com.example.distributed.crawling.akka;

/**
 * 该接口是server接口
 */
public interface Server {

    /**
     * 注册server接口
     * 该接口是用来将该server向其他server进行注册
     */
    void registerServer();


    /**
     * 请求心跳接口
     * 该接口是用来向其他的server或client请求心跳，其他的server或者client接到该请求后，会发送心跳
     */
    void requestHeartbeat();

    /**
     * 同步数据
     * 该接口是用来向其他的server进行同步数据使用的
     */
    void synchronousData();
}
