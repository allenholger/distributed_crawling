package com.example.distributed.crawling.parsePage;

import akka.actor.UntypedAbstractActor;
import com.example.distributed.crawling.akka.ActorCommand;
import com.example.distributed.crawling.exception.MessageTypeException;
import com.example.distributed.crawling.parsePage.message.PageMessage;
import com.example.distributed.crawling.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 本接口是页面解析器，用来标识页面的不同解析类型
 */
public abstract class PageParser extends UntypedAbstractActor implements Parser{
    private static final Logger log = LoggerFactory.getLogger(PageParser.class);

    //创建线程池
    ThreadPoolExecutor threadPool = new ThreadPoolExecutor(50, 50, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    @Override
    public void onReceive(Object message) throws Throwable {
        Preconditions.checkNotNull(message);
        if(message instanceof PageMessage){
            PageMessage pageMessage = (PageMessage) message;
            threadPool.submit(new ParsePage(pageMessage, this));
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
        }else{
            throw new MessageTypeException("当前消息类型为：" + message.getClass().getName() + ", 非PageMessage类型");
        }
    }

    @Override
    public void parse(Class clazz, Object message) {
        try {
            //首先获取子类对象
            Object subClass = clazz.newInstance();
            //获取子类中的doParse方法
            Method doParseMethod = clazz.getMethod("doParse", Object.class);
            doParseMethod.invoke(subClass, message);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
