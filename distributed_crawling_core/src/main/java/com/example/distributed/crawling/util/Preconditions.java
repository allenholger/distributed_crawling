package com.example.distributed.crawling.util;


/**
 * 本类是条件的预判断类
 */
public final class Preconditions {

    public static <T> T checkNotNull(T reference){
        if(reference == null){
            throw new NullPointerException();
        }
        return reference;
    }
/*
    public static <T> T checkNotNull(T reference, @NotNull Object message){
        if(reference == null){
            throw new NullPointerException(String.valueOf(message));
        }
        return reference;
    }
*/

}
