package com.milkman.core;

import clojure.lang.Keyword;
import com.milkman.core.entities.Driver;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Utils {

    public static String concat(String a, String b) {
        return a + b;
    }

    public static String getType(Object o) {
        return o.getClass().getName();
    }

    /* trasform a clojure map into a java HashMap */
    public static Map persistentArrayMapToHashMap(clojure.lang.PersistentArrayMap persistentArrayMap) {
       Map<Object, Object> hashMap = new HashMap<>();
       hashMap.putAll(persistentArrayMap);
       return hashMap;
    }

    /* this method accepts a generic Map with the keys specified as clojure.lang.Keyword and returns
    * the keyword of the first element as a String*/
    public static String getMapKeywordAsString(Map<clojure.lang.Keyword, Object> persistentArrayMap) {
       Map.Entry<clojure.lang.Keyword,Object> entry = persistentArrayMap.entrySet().iterator().next();
        return entry.getKey().getName();
    }


    public static Map<String, Object> unkeywordizeMap(Map<Keyword, Object> clojureMap) {
        Map<String, Object> map = new HashMap<>();
        clojureMap.forEach((k,v) -> map.put(k.getName(), v));
        return map;
    }
}
