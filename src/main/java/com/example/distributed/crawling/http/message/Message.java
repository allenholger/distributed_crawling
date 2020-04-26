package com.example.distributed.crawling.http.message;


import akka.actor.typed.ActorRef;
import com.example.distributed.crawling.http.message.type.MessageTag;

import java.util.logging.Logger;

public class Message<T> {
    private static final Logger log = Logger.getLogger(Message.class.getSimpleName());
    //消息名
    private String messageName;
    //消息内容
    private String messageContext;

    private ActorRef<T> replyTo;


    public Message(String messageName, String messageContext, ActorRef<T> replyTo) {
        this.messageName = messageName;
        //将消息名放入消息标签列表中
        MessageTag.getInstance().addMessageTypeToList(messageName);
        this.messageContext = messageContext;
        this.replyTo = replyTo;
    }

    public Message() {
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getMessageContext() {
        return messageContext;
    }

    public void setMessageContext(String messageContext) {
        this.messageContext = messageContext;
    }

    public ActorRef<T> getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(ActorRef<T> replyTo) {
        this.replyTo = replyTo;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageName='" + messageName + '\'' +
                ", messageContext='" + messageContext + '\'' +
                ", replyTo=" + replyTo +
                '}';
    }
}
