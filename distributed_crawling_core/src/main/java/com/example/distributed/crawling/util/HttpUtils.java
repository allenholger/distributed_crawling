package com.example.distributed.crawling.util;

import com.example.distributed.crawling.requestUrl.http.CustomHttpGet;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 本类是Http的工具类
 */
public class HttpUtils {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * GET请求
     * @param url       请求地址
     * @param params    请求参数
     * @param headers   请求头
     * @param entity    请求体
     * @return
     */
    public static String get(String url, Map<String, String> params, Map<String, Object> headers, Map<String, Object> entity){
        CustomHttpGet getRequest = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            if(params != null && !params.isEmpty()){
                for (Map.Entry<String, String> param: params.entrySet()) {
                    builder.addParameter(param.getKey(), param.getValue());
                }
            }
            URI uri = builder.build();
            getRequest = new CustomHttpGet(uri);
            //添加请求头
            if(headers != null && !headers.isEmpty()){
                for (Map.Entry<String, Object> head: headers.entrySet()) {
                    getRequest.addHeader(head.getKey(), String.valueOf(head.getValue()));
                }
            }
            //添加请求体
            if(entity != null && !entity.isEmpty()){
                for(Map.Entry<String, Object> entry: entity.entrySet()){
                    getRequest.setEntity(new StringEntity(entry.getKey(), String.valueOf(entry.getValue())));
                }
            }
        } catch (URISyntaxException e) {
            log.info("URL转换成URI时语法异常！" + e.getMessage());
        }
        return execute(getRequest);
    }


    /**
     * POST请求
     * @param url       请求地址
     * @param params    请求参数
     * @param headers   请求头
     * @param entity    请求体
     * @return
     */
    public static String post(String url, Map<String, String> params, Map<String, Object> headers, Map<String, Object> entity){
        HttpPost postRequest = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            if(params != null && !params.isEmpty()){
                for (Map.Entry<String, String> param: params.entrySet()) {
                    builder.addParameter(param.getKey(), param.getValue());
                }
            }
            URI uri = builder.build();
            postRequest = new HttpPost(uri);
            if(headers != null && !headers.isEmpty()){
                for (Map.Entry<String, Object> head: headers.entrySet()) {
                    postRequest.addHeader(head.getKey(), String.valueOf(head.getValue()));
                }
            }
            if(entity != null && !entity.isEmpty()){
                for (Map.Entry<String, Object> entry: entity.entrySet()) {
                    postRequest.setEntity(new StringEntity(entry.getKey(), String.valueOf(entry.getValue())));
                }
            }
        } catch (URISyntaxException e) {
            log.info("URL转换成URI时语法异常！" + e.getMessage());
        }
       return execute(postRequest);
    }

    private static String execute(HttpUriRequest request){
        try(CloseableHttpClient client = HttpClientBuilder.create().build();
            CloseableHttpResponse response = client.execute(request);){
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String contentType = response.getEntity().getContentType().getValue();
                String charSet;
                if(contentType != null && contentType.contains(";")){
                    charSet = contentType.substring(contentType.lastIndexOf("=") + 1);
                }else{
                    charSet = StandardCharsets.UTF_8.name();
                }
                return EntityUtils.toString(response.getEntity(), charSet);
            }
        } catch (IOException e) {
            log.info(e.getClass().getSimpleName() + "类型异常！" + e.getMessage());
        }
        return null;
    }
}
