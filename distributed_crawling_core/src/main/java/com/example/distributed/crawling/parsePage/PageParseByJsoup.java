package com.example.distributed.crawling.parsePage;

/**
 * 采用Jsoup解析页面的父类，其子类只需要实现具体的doParse方法即可
 */
public abstract class PageParseByJsoup extends PageParser implements Jsoup{

    //子类真正需要实现的方法， 页面的解析均是在这个方法中执行的
    abstract void doParse(Object message);
}
