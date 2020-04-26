package com.example.distributed.crawling.akka;


import akka.actor.UntypedAbstractActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * server 接口
 */
public class ServerActor<T> extends UntypedAbstractActor implements Server{
    private static final Logger log = LoggerFactory.getLogger(ServerActor.class);

    @Override
    public void onReceive(Object message) throws Throwable {

    }

    @Override
    public void registerServer() {

    }

    @Override
    public void requestHeartbeat() {

    }

    @Override
    public void synchronousData() {

    }
}
