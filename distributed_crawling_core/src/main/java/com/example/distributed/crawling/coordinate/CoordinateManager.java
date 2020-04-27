package com.example.distributed.crawling.coordinate;

import akka.actor.ActorRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 本类是协调管理器
 * 该类是master与master、master与slaver、slaver与salver之间的协调管理器
 * 负责管理他们之间的相互引用
 */
public class CoordinateManager {
    private static final Logger log = LoggerFactory.getLogger(CoordinateManager.class);
    /**
     * 其他master应用的列表
     */
    private List<ActorRef> otherMasterList;

    /**
     * 其他slaver引用的列表
     */
    private List<ActorRef> otherSlaverList;

    public CoordinateManager(){
        this.otherMasterList = new ArrayList<ActorRef>();
        //由于slaver比较多，而且slaver之间的增删比较多，因此在此处使用的linkedList
        this.otherSlaverList = new LinkedList<ActorRef>();
    }

    public List<ActorRef> getOtherMasterList(){
        return this.otherMasterList;
    }

    public List<ActorRef> getOtherSlaverList(){
        return this.otherSlaverList;
    }

    public void addMasterToOtherMasterList(ActorRef masterRef){
        if(!this.otherMasterList.contains(masterRef)){
            log.info("otherMasterList do not contains this masterRef, so this masterRef can add in the otherMasterList");
            this.otherMasterList.add(masterRef);
        }
    }

    public void addSlaverToOtherSlaverList(ActorRef slaverRef){
        if(!this.otherSlaverList.contains(slaverRef)){
            log.info("otherSlaverList do not contains this slaverRef, so this slaverRef can add in the otherSlaverList");
            this.otherSlaverList.add(slaverRef);
        }
    }


}
