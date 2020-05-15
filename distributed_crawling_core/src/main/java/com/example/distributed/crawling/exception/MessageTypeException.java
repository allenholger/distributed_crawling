package com.example.distributed.crawling.exception;

/**
 * 消息类型异常类
 */
public class MessageTypeException extends Exception{
    public MessageTypeException() {
    }

    public MessageTypeException(String message) {
        super(message);
    }

    public MessageTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageTypeException(Throwable cause) {
        super(cause);
    }

    public MessageTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
