package com.ej.sort;

import java.util.List;

public class Sort03Insert implements Sort {
    @Override
    public List<Integer> sort(List<Integer> list) {
        int size = 0;
        if(list == null || (size = list.size()) <= 1){
            return list;
        }
        //从数据的第二个元素开始
        for (int i = 1; i < size; i++) {
            //参考元素
            Integer point = list.get(i);
            int idx = i;
            //排在参考元素前的元素逐一和参考元素比较
            //每一趟插入排序之前i之前的数据都是有序的
            for (int j = i - 1; j >= 0; j--) {
                if(point < list.get(j)){
                    //比参考元素大则需要往后移
                    list.set(idx,list.get(j));
                    //标记往后移之前的位置
                    idx = j;
                }else{
                    //基于【每一趟插入排序之前i之前的数据都是有序的】这个特点
                    //所以再前面的数据就不用比了
                    break;
                }
            }
            if(idx != i){
                //比i位置大的数据都后移了，那么idx位置的数据也是后移到idx+1的位置了
                //那么idx位置的数据就需要用参考数据置换
                list.set(idx,point);
            }
        }
        return list;
    }

    @Override
    public String sortType() {
        return "插入排序";
    }
}
