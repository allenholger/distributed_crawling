package com.example.distributed.crawling.http;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Receive;
import akka.actor.typed.javadsl.ReceiveBuilder;
import com.example.distributed.crawling.http.message.Message;
import com.example.distributed.crawling.http.message.type.MessageTag;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *本类是akka的服务端
 * server 应该被设置成单例的模式
 */
public class Server<T extends Message> extends AbstractBehavior<T> {

    private static final Logger log = Logger.getLogger(Server.class.getSimpleName());

    /**
     * 服务器的名字
     */
    private String serverName;
    /**
     * 客户端的actorRef列表
     */
    private List<ActorRef<T>> clientActorRefList;
    /**
     * 服务端的actorRef列表
     */
    private List<ActorRef<T>> severActorRefList;
    private Server instance;
    private Server(String serverName, ActorContext<T> context){
        super(context);
        this.serverName = serverName;
        this.clientActorRefList = new ArrayList<ActorRef<T>>();
        this.severActorRefList = new ArrayList<ActorRef<T>>();
    }

    /**
     * 提供获取实例的公有方法
     * @param serverName
     * @param context
     * @return
     */
    public Server getInstance(String serverName, ActorContext<T> context){
        if(instance == null){
            instance = new Server(serverName, context);
        }
        return instance;
    }

    /**
     * 获取服务端的名字
     * @return
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * 获取客户端引用列表
     * @return
     */
    public List<ActorRef<T>> getClientActorRefList() {
        return clientActorRefList;
    }

    /**
     * 获取服务端的引用列表
     * @return
     */
    public List<ActorRef<T>> getSeverActorRefList() {
        return severActorRefList;
    }

    /**
     * 将客户端添加进clientActorRefList中
     * @param clients
     */
    public void addClientToClientActorRefList(ActorRef<T>... clients){
        for(ActorRef<T> client: clients){
            this.clientActorRefList.add(client);
        }
    }

    /**
     * 将服务端添加进serverActorRefList中
     * @param servers
     */
    public void addServerToServerActorRefList(ActorRef<T>... servers){
        for(ActorRef<T> server: servers){
            this.severActorRefList.add(server);
        }
    }


    public Receive<T> createReceive() {
        //ReceiveBuilder<T> receiveBuilder = newReceiveBuilder().onMessageEquals();
        return null;
    }


    //---------------以下部分定义的是各种数据类型  ------------------------------
    private Behavior<T> behavior(T t){
        if(t.getClass().getName().contains("Server")){
            //说明是向server端发送消息
            if(MessageTag.getInstance().containsMessageType(t.getMessageName())){

            }
            getContext().getLog().info("向server进行注册：");
            this.addServerToServerActorRefList(t.getReplyTo());
        }else{
            //说明是向client端发送消息

        }
        return null;
    }
}
