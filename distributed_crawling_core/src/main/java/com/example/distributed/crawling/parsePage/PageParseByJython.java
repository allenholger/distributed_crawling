package com.example.distributed.crawling.parsePage;

/**
 * 本类是通过Jython调用python脚本来执行页面解析类，子类只需要实现doParse方法即可
 */
public abstract class PageParseByJython extends PageParser implements Jython{
    //子类真正需要实现的方法， 页面的解析均是在这个方法中执行的
    abstract void doParse(Object message);
}
