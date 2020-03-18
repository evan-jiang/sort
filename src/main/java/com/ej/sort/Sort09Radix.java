package com.ej.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Sort09Radix implements Sort {
    @Override
    public List<Integer> sort(List<Integer> list) {
        int size = 0;
        if (list == null || (size = list.size()) <= 1) {
            return list;
        }
        //先定义一个名词——基数：这里仅限于整数，所谓基数就是整数value从后往前的第i个数字且带符号，这里的i表示第i次读到value值
        //比如整数-109，那第一次读到这个数的基数为-9，第二次为-0也就是0，第三次为-1
        //找出绝对值最大的数，为了计算最长数据
        int max = 0;
        for (Integer value : list) {
            if(Math.abs(value) > max){
                max = Math.abs(value);
            }
        }
        return sort(list, (int) Math.log10(max) + 1);
    }

    private List<Integer> sort(List<Integer> list, int maxLength) {
        //为什么是19？要考虑负数的情况
        List<List<Integer>> lists = new ArrayList<>(19);
        //初始化临时数组
        for (int idx = 0; idx < 19; idx++) {
            //这里为什么不用ArrayList
            lists.add(new LinkedList<>());
        }
        for (int idx = 1, radix = 1; idx <= maxLength; idx++, radix *= 10) {
            for (Integer value : list) {
                //基数加9的原因是基数的顺序是-9到9
                lists.get(((value / radix) % 10) + 9 ).add(value);
            }
            int i = 0;
            for (List<Integer> tempList : lists) {
                if(tempList.isEmpty()){
                    continue;
                }
                //每一轮按基数分组之后会写到元素数组
                for (Integer value : tempList) {
                    list.set(i++,value);
                }
                //对于tempList的写入都是add()，所以需要清空之后次轮再写入
                tempList.clear();
            }
        }
        return list;
    }

    @Override
    public String sortType() {
        return "基数排序";
    }

}
