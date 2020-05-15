package com.example.distributed.crawling.requestUrl.http;

import com.example.distributed.crawling.requestUrl.message.URLMessage;
import com.example.distributed.crawling.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * 本类是get请求的方法，用在线程池中
 */
public class Get implements Callable<String> {
    private static final Logger log = LoggerFactory.getLogger(Get.class);

    private URLMessage urlMessage;

    public Get(URLMessage urlMessage) {
        this.urlMessage = urlMessage;
    }


    @Override
    public String call() {
        String result = HttpUtils.get(urlMessage.getUrl(), urlMessage.getParam(), urlMessage.getHeader(), urlMessage.getEntity());
        log.info("本次GET请求结果为：" + result);
        return result;
    }
}
