package com.ej.sort;

import java.util.List;

public class Sort02Select implements Sort {
    @Override
    public List<Integer> sort(List<Integer> list) {
        int size = 0;
        if(list == null || (size = list.size()) <= 1){
            return list;
        }
        //趟数
        int t = size - 1;
        for (int i = 1; i <= t; i++) {
            int minIdx = i - 1;
            for (int j = i; j < size; j++) {
                if (list.get(minIdx) > list.get(j)) {
                    //选择较小值的下标
                    minIdx = j;
                }
            }
            if (i - 1 != minIdx) {
                //如果较小值的下标不是i-1则需要交换
                Integer point = list.get(i - 1);
                list.set(i - 1, list.get(minIdx));
                list.set(minIdx, point);
            }
        }
        return list;
    }

    @Override
    public String sortType() {
        return "选择排序";
    }
}
