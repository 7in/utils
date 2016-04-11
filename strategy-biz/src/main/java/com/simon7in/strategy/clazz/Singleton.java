package com.simon7in.strategy.clazz;

/**
 * Created by jiayu.shenjy on 2016/3/18.
 */
public class Singleton {
    private static Singleton instance = null;
    private Singleton() { }

    public static synchronized Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
