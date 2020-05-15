package com.example.distributed.crawling.parsePage;

public interface Parser {
    /**
     * 解析方法
     */
    void parse(Class clazz, Object message);
}
