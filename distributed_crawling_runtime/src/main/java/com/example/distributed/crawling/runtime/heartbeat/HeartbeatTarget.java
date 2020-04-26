package com.example.distributed.crawling.runtime.heartbeat;


import jdk.management.resource.ResourceId;

/**
 * 本接口是心跳的目标接口
 */
public interface HeartbeatTarget<T> {

    /*
     *  接受心跳数据
     *  目标机器接受其他机器发送的心跳数据， 其他机器向目标机器发送心跳数据
     * @param heartbeatOrigin   心跳发送者机器的ID
     * @param payload           心跳的信息
     */
    void reveiveHeartbeat(ResourceID heartbeatOrigin, T payload);


    /**
     * 心跳的接收者向心跳的接收者请求心跳
     * @param heartbeatTarget
     * @param payload
     */
    void requestHeartbeat(ResourceID heartbeatTarget, T payload);
}
