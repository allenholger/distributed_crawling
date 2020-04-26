package com.example.distributed.crawling.http.message.type;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 定义消息标签
 * 消息类型应该设置为单例模式
 */
public class MessageTag<T> {
    private static final Logger log = Logger.getLogger(MessageTag.class.getSimpleName());

    private static MessageTag instance;
    /**
     * 私有的构造方法
     */
    private MessageTag(){
        this.messageTypeList = new ArrayList<T>();
    }
    /**
     * 消息标签列表
     */
    private List<T> messageTypeList;

    public static MessageTag getInstance(){
        if(instance == null){
            instance = new MessageTag();
        }
        return instance;
    }

    /**
     * 将用户定义的消息标签放入消息标签列表中
     * @param messageType
     */
    public void addMessageTypeToList(T messageType){
        if(!messageTypeList.contains(messageType)){
            synchronized (MessageTag.class){
                messageTypeList.add(messageType);
            }
        }
    }

    /**
     * 判断是否包含指定的消息标签
     * @param messageType
     * @return
     */
    public boolean containsMessageType(T messageType){
        return messageTypeList.contains(messageType);
    }
}
