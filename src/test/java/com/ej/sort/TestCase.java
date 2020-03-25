package com.ej.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestCase {

    int times = 10;
    int size = 80000;

    List<Integer> list = null;

    {
        list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
    }

    private List<Integer> copyList() {
        List<Integer> result = new ArrayList<>(size);
        result.addAll(list);
        return result;
    }

    private void sort(Sort sort) {
        long begin = System.currentTimeMillis();
        List<Integer> list = null;
        for (int i = 0; i < times; i++) {
            list = copyList();
            list = sort.sort(list);
        }
        long end = System.currentTimeMillis();
        System.out.println(String.format("[%s]对[%s]个数据进行[%s]次排序锁耗费的时间为[%sms],排序验证结果:[%s]", sort.sortType(), list.size(), times, end - begin, sort.check(list)));
    }

    private void n2(){
        System.out.println("\n以下排序时间复杂度为O(n^2)");
        sort(new Sort01Bubble());
        sort(new Sort02Select());
        sort(new Sort03Insert());
    }

    private void nlogn(){
        System.out.println("\n以下排序时间复杂度为O(nlogn)");
        sort(new Sort04Quick());
        sort(new Sort06Merge());
        sort(new Sort07Heap());
        sort(new Sort08Bucket());
    }

    private void n13(){
        System.out.println("\n以下排序时间复杂度为O(n^1.3)");
        sort(new Sort05Shell());
    }

    private void nxk(){
        System.out.println("\n以下排序时间复杂度为O(n*k)");
        sort(new Sort09Radix());
    }

    @org.junit.Test
    public void todo(){
        //n2();
        //n2();
        nlogn();
        nlogn();
        n13();
        nxk();

    }
}
