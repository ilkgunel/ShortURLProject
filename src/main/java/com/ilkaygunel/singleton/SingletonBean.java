package com.ilkaygunel.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SingletonBean {
    private SingletonBean() {

    }

    public static SingletonBean singletonBean;
    public ConcurrentMap<Long, Map<String, String>> shortUrls = new ConcurrentHashMap<>();

    public static SingletonBean getInstance() {
        if (singletonBean == null) {
            singletonBean = new SingletonBean();
        }
        return singletonBean;
    }
}
