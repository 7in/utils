/**
 * Tmall.com Inc.
 * Copyright (c) All Rights Reserved.
 */
package com.simon7in.strategy.sortutil;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 比较器
 * 
 * @author  jiayu.shenjy
 * @version
 */
public class IntegerComparator implements Comparator<Integer> {

    /** 
     * @see Comparator#compare(Object, Object)
     */
    @Override
    public int compare(Integer arg0, Integer arg1) {
        return (arg0 > arg1 ? 1 : (arg0 < arg1 ? -1 : 0));
    }

    public static void main(String[] args) {

        List<Integer> intList = new ArrayList<Integer>();

        intList.add(3);
        intList.add(2);
        intList.add(1);

        Collections.sort(intList, new IntegerComparator());
        System.out.println(intList);

    }
}
