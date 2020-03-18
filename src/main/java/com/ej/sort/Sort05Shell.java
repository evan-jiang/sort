package com.ej.sort;

import java.util.List;

public class Sort05Shell implements Sort {
    @Override
    public List<Integer> sort(List<Integer> list) {
        int size = 0;
        if (list == null || (size = list.size()) <= 1) {
            return list;
        }
        int step = size / 2;
        //int step = 1;//当step等于1时就是插入排序
        do {
            list = sort(list, size, step);
            step /= 2;
        } while (step > 0);
        return list;
    }

    private List<Integer> sort(List<Integer> list, int size, int step) {
        for (int s = 0; s < step; s++) {
            //以下参照插入排序
            for (int i = s + step; i < size; i += step) {
                Integer point = list.get(i);
                int idx = i;
                for (int j = i - step; j >= 0; j -= step) {
                    if(point < list.get(j)){
                        list.set(idx,list.get(j));
                        idx = j;
                    }else {
                        break;
                    }
                }
                if(idx != i){
                    list.set(idx,point);
                }
            }
        }
        return list;
    }

    @Override
    public String sortType() {
        return "希尔排序";
    }
}
