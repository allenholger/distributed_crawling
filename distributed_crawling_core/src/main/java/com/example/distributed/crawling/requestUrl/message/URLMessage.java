package com.example.distributed.crawling.requestUrl.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;

/**
 * 本类是URL的消息类
 */
public class URLMessage implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(URLMessage.class);

    private static final Long serialVersionUID = 1L;

    /**
     * 请求的URL
     */
    private String url;

    /**
     * 请求参数
     */
    private Map<String, String> param;

    /**
     * 请求头
     */
    private Map<String, Object> header;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 请求体
     */
    private Map<String, Object> entity;

    public URLMessage(String url, Map<String, String> param, Map<String, Object> header, String method, Map<String, Object> entity) {
        this.url = url;
        this.param = param;
        this.header = header;
        this.method = method;
        this.entity = entity;
    }

    public URLMessage() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    public Map<String, Object> getHeader() {
        return header;
    }

    public void setHeader(Map<String, Object> header) {
        this.header = header;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, Object> getEntity() {
        return entity;
    }

    public void setEntity(Map<String, Object> entity) {
        this.entity = entity;
    }

    public void addParam(String key, String value){
        this.param.put(key, value);
    }

    public void addHead(String key, Object value){
        this.header.put(key, value);
    }

    public void addEntity(String key, Object value){
        this.entity.put(key, value);
    }
}
