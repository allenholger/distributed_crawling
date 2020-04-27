package com.example.distributed.crawling.akka;

import akka.actor.UntypedAbstractActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * client接口
 */
public class ClientActor extends UntypedAbstractActor implements Client {

    private static final Logger logger = LoggerFactory.getLogger(ClientActor.class);

    @Override
    public void onReceive(Object message) throws Throwable, Throwable {

    }

    @Override
    public void registerClientToClient() {

    }

    @Override
    public void registerClientToServer() {

    }

    @Override
    public void sendHeartbeat() {

    }

    @Override
    public void synchronousData() {

    }
}
