package com.example.distributed.crawling.parsePage;

import akka.actor.UntypedAbstractActor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 本接口是页面解析器，用来标识页面的不同解析类型
 */
public abstract class PageParser extends UntypedAbstractActor implements Parser {

    @Override
    public void parse(Class clazz, Object message) {
        try {
            //首先获取子类对象
            Object subClass = clazz.newInstance();
            //获取子类中的doParse方法
            Method doParseMethod = clazz.getMethod("doParse", Object.class);
            doParseMethod.invoke(subClass, message);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
