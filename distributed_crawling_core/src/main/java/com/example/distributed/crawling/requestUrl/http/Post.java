package com.example.distributed.crawling.requestUrl.http;

import com.example.distributed.crawling.requestUrl.message.URLMessage;
import com.example.distributed.crawling.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * 本类是Post请求，用于线程池中
 */
public class Post implements Callable<String> {
    private static final Logger log = LoggerFactory.getLogger(Post.class);

    private URLMessage urlMessage;

    public Post(URLMessage urlMessage) {
        this.urlMessage = urlMessage;
    }


    @Override
    public String call() {
        String result = HttpUtils.post(urlMessage.getUrl(), urlMessage.getParam(), urlMessage.getHeader(), urlMessage.getEntity());
        log.info("本次POST请求结果为：" + result);
        return result;
    }
}
