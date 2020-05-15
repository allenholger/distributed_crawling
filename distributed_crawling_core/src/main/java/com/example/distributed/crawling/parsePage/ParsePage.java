package com.example.distributed.crawling.parsePage;

import com.example.distributed.crawling.parsePage.message.PageMessage;
import com.example.distributed.crawling.util.Preconditions;

/**
 * 解析页面，主要用于线程池中
 */
public class ParsePage implements Runnable{
    //页面消息
    private PageMessage pageMessage;
    //页面解析器
    private PageParser pageParser;

    public ParsePage(PageMessage pageMessage, PageParser pageParser) {
        this.pageMessage = pageMessage;
        this.pageParser = pageParser;
    }

    @Override
    public void run() {
        Class clazz = pageMessage.getClazz();
        Preconditions.checkNotNull(clazz);
        String content = pageMessage.getPageContent();
        Preconditions.checkNotNull(content);
        pageParser.parse(clazz, content);
    }
}
