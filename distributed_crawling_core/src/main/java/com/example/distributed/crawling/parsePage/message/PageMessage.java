package com.example.distributed.crawling.parsePage.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 页面消息
 */
public class PageMessage {
    private static final Logger log = LoggerFactory.getLogger(PageMessage.class);
    /**
     * 页面内容
     */
    private String pageContent;

    /**
     * 页面解析类
     */
    private Class clazz;

    public PageMessage(String pageContent, Class clazz) {
        this.pageContent = pageContent;
        this.clazz = clazz;
    }

    public PageMessage() {
    }

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
