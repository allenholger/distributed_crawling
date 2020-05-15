package com.example.distributed.crawling.requestUrl.http;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

/**
 * 本类是自定义的HttpGet方法， 该Get方法具备entity
 */
public class CustomHttpGet extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "GET";

    public CustomHttpGet() {
    }

    public CustomHttpGet(URI uri) {
        this.setURI(uri);
    }

    public CustomHttpGet(String uri) {
        this.setURI(URI.create(uri));
    }

    public String getMethod() {
        return METHOD_NAME;
    }
}
