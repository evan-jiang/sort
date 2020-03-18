package com.ej.sort;

import java.util.List;

public class Sort01Bubble implements Sort {
    @Override
    public List<Integer> sort(List<Integer> list) {
        int size = 0;
        if(list == null || (size = list.size()) <= 1){
            return list;
        }
        //总趟数
        int t = size - 1;
        for (int i = 1; i <= t; i++) {
            boolean sorted = true;
            for (int j = 0; j < size - i; j++) {
                if (list.get(j) <= list.get(j + 1)) {
                    continue;
                }
                //前面的比后面的大，需要交换
                Integer temp = list.get(j);
                list.set(j, list.get(j + 1));
                list.set(j + 1, temp);
                sorted = false;
            }
            if(sorted){
                //【优化点】如果一趟下来没有发生过任何交换就意味着已经排完了
                break;
            }
        }
        return list;
    }

    @Override
    public String sortType() {
        return "冒泡排序";
    }
}
