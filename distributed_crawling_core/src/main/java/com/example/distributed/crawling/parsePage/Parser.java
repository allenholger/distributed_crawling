package com.example.distributed.crawling.parsePage;

public interface Parser {
    /**
     * 解析方法
     */
    void parse(Class clazz, Object message);

    /**
     * 子类中真正要执行的方法
     * @param message
     */
    void doParse(Object message);
}
