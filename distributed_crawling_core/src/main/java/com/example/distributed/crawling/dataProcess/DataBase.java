package com.example.distributed.crawling.dataProcess;

import akka.actor.UntypedAbstractActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 本类是将数据存储到数据库中
 */
public class DataBase extends UntypedAbstractActor {
    private static final Logger log = LoggerFactory.getLogger(DataBase.class);

    @Override
    public void onReceive(Object message) {


    }
}
