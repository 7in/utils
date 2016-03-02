package com.simon7in.strategy.util;

import java.util.UUID;

/**
 * Created by jiayu.shenjy on 2016/1/18.
 */
public class UnionIdUtil {

    public static String getUuid(){
        UUID uuid =  UUID.randomUUID();
        return uuid.toString();
    }

    public static void main(String[] args) {
        UUID uuid =  UUID.randomUUID();
        System.out.print(uuid.toString());
    }
}
