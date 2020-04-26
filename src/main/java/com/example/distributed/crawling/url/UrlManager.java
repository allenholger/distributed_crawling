package com.example.distributed.crawling.url;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;


/**
 * 本类是url管理器类
 */

public class UrlManager {
    private static final Logger log = Logger.getLogger("UrlManager");
    /**
     * 未使用的url池，该池是并发的链表队列，它是线程安全的
     */
    private ConcurrentLinkedQueue<String> unusedUrlPool;

    /**
     * 正在使用的url池，该池也是线程安全的
     */
    private Vector<String> usingUrlPool;

    /**
     * 已经使用的Url池，该池是线程不安全的
     */
    private Set<String> usedUrlPool;

    public UrlManager(){
        unusedUrlPool = new ConcurrentLinkedQueue<String>();
        usingUrlPool = new Vector<String>();
        usedUrlPool = new HashSet<String>();
    }

    /**
     * 从未使用的URL池中获取一个URL，由于是一个队列，总是获取第一条URL
     * @return  如果队列为空，则返回null， 如果队列部位空，则返回队列的头
     */
    public String getFromUnusedUrlPool(){
        if(unusedUrlPool.isEmpty()){
            log.info("未使用的URL池为空，不存在未被使用的URL");
            return null;
        }
        return unusedUrlPool.poll();
    }

    /**
     * 判断指定的url是否在是否在指定的集合中
     * @param t     指定的集合
     * @param url  指定的url
     * @param <T>  集合的类型
     * @return
     */
   private <T extends Collection> boolean containsUrl(T t, String url){
        return t.contains(url);
   }
    /**
     * 向未使用的url池中添加url
     * @param urls
     */
    public void addToUnusedUrlPool(String... urls){
        for (String url: urls) {
            //判断url是否在未使用的url池中、正在使用的url池中、已经使用过的url池中是否存在该url
            if(!containsUrl(unusedUrlPool, url) || !containsUrl(usingUrlPool, url) || !containsUrl(usedUrlPool, url)){
                unusedUrlPool.add(url);
            }
        }
    }

    /**
     * 移除掉指定的url
     * @param url
     */
    public void removeFromUsingUrlPool(String url){
        //首先找到该url的索引位置
        int index = usingUrlPool.indexOf(url);
        if(index == -1){
            log.info("正在执行的url池中没有指定的url：" + url);
        }else{
            //说明找到了，就将url冲正在运行的url池中移除掉
            usingUrlPool.remove(index);
        }
    }

    /**
     * 将url添加进正在使用的url池中
     * @param url
     */
    public void addToUsingUrlPool(String url){
        if(!containsUrl(usingUrlPool, url) || !containsUrl(usedUrlPool, url)){
            usingUrlPool.add(url);
        }
    }

    /**
     * 向已经使用过的url池中添加url
     * @param url
     */
    public void addToUsedUrlPool(String url){
        synchronized (this){
            if(!containsUrl(usedUrlPool, url)){
                usedUrlPool.add(url);
            }
        }
    }

}
