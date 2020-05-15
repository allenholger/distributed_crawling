package com.example.distributed.crawling.requestUrl;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.http.javadsl.model.HttpMethods;
import akka.http.javadsl.model.Uri;
import com.example.distributed.crawling.akka.ActorCommand;
import com.example.distributed.crawling.exception.MessageTypeException;
import com.example.distributed.crawling.requestUrl.http.Get;
import com.example.distributed.crawling.requestUrl.http.Post;
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
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 本类是URL的请求类
 * 该类主要是处理URL请求的
 * 本类是采用Actor的模式
 * 本类采用的线程池的方式
 */
public class RequestUrl extends UntypedAbstractActor {
    private static final Logger log = LoggerFactory.getLogger(RequestUrl.class);

    //创建线程池
    ThreadPoolExecutor threadPool = new ThreadPoolExecutor(50, 50, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());


    @Override
    public void onReceive(Object message) throws Throwable {
        if(message instanceof URLMessage){
            URLMessage urlMessage = (URLMessage) message;
            String method = urlMessage.getMethod();
            if(method.equalsIgnoreCase(HttpMethods.GET.value())){
                log.info("该请求是GET请求");
                Future<String> future = threadPool.submit(new Get(urlMessage));
                String result = future.get();
                System.out.println(result);
            }else if(method.equalsIgnoreCase(HttpMethods.POST.value())){
                log.info("该请求是POST请求");
                Future<String> future = threadPool.submit(new Post(urlMessage));
                String result = future.get();
                System.out.println(result);
            }
        }else if(message instanceof String){
            String msg = (String) message;
            if(msg.equalsIgnoreCase(ActorCommand.CLOSE)|| msg.equalsIgnoreCase(ActorCommand.STOP)
                    || msg.equalsIgnoreCase(ActorCommand.KILL) || msg.equalsIgnoreCase(ActorCommand.TERMINATE)){
                log.info(getSender().getClass().getSimpleName() + "要关闭");
                //首先要关闭线程池
                if(threadPool.isTerminated()){
                    threadPool.shutdown();
                }
                //其次关闭actor以及actorSystem
                if(!getSelf().isTerminated()){
                   getContext().stop(getSelf());
                }
            }
        } else{
            throw new MessageTypeException("当前消息类新为：" + message.getClass().getSimpleName() + ", 不是URLMessage类型");
        }
    }


    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("sys");
        ActorRef actorRef = actorSystem.actorOf(Props.create(RequestUrl.class), "requestUrl");
        Map<String, String> param = new HashMap<>();
        param.put("spm", "search");
        URLMessage message = new URLMessage("http://fund.eastmoney.com/320007.html?", param, null, null, "GET");
        actorRef.tell(message, ActorRef.noSender());
        actorRef.tell("terminate", ActorRef.noSender());
    }
}
