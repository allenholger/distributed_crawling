package com.example.distributed.crawling.akka;

/**
 * 本类是Actor的命令
 */
public class ActorCommand {
//---------------------
    //关闭命令
// --------------------
    /**
     * 关闭命令
     */
    public static final String CLOSE = "close";

    /**
     * 杀死命令
     */
    public static final String KILL = "kill";

    /**
     * 终结
     */
    public static final String TERMINATE = "terminate";

    /**
     * 停止
     */
    public static final String STOP = "stop";
}
