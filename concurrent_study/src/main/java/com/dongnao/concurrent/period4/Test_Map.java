package com.dongnao.concurrent.period4;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Test_Map {
    public static void main(String args[]){
        HashMap<String, String> map = new HashMap<>();

        for (int i=0; i< 10000;i++){
            map.put("james" + i, "james" + i);
        }


        map.get("James");




        ConcurrentHashMap<String, String> map1 = new ConcurrentHashMap<>();
        map1.put("","");

        new ConcurrentHashMap<>(1);


    }
}
