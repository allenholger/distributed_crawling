package com.example.distributed.crawling.requestUrl;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.http.javadsl.model.HttpMethods;
import akka.http.javadsl.model.Uri;
import com.example.distributed.crawling.exception.MessageTypeException;
import com.example.distributed.crawling.requestUrl.message.URLMessage;
import com.example.distributed.crawling.util.HttpUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 本类是URL的请求类
 * 该类主要是处理URL请求的
 * 本类是采用Actor的模式
 */
public class RequestUrl extends UntypedAbstractActor {
    private static final Logger log = LoggerFactory.getLogger(RequestUrl.class);


    @Override
    public void onReceive(Object message) throws Throwable {
        if(message instanceof URLMessage){
            URLMessage urlMessage = (URLMessage) message;
            String method = urlMessage.getMethod();
            if(method.equalsIgnoreCase(HttpMethods.GET.value())){
                log.info("该请求是GET请求");
                String result = HttpUtils.get(urlMessage.getUrl(), urlMessage.getParam(), urlMessage.getHeader(), urlMessage.getEntity());
                System.out.println(result);
            }else if(method.equalsIgnoreCase(HttpMethods.POST.value())){
                log.info("该请求是POST请求");
                String result = HttpUtils.post(urlMessage.getUrl(), urlMessage.getParam(), urlMessage.getHeader(), urlMessage.getEntity());
                System.out.println(result);
            }
        }else{
            throw new MessageTypeException("当前消息类新为：" + message.getClass().getSimpleName() + ", 不是URLMessage类型");
        }
    }
}
