package com.ej.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sort06Merge implements Sort {
    @Override
    public List<Integer> sort(List<Integer> list) {
        int size = 0;
        if (list == null || (size = list.size()) <= 1) {
            return list;
        }
        return sort(list, 0, size - 1);
    }

    private List<Integer> sort(List<Integer> list, int begin, int end) {
        if (begin == end) {
            return Arrays.asList(list.get(begin));
        }
        //将需要排序的集合拆分成两部分，拆到左右两部分都只有一个元素为止
        int midd = begin + (end - begin) / 2;
        //左边部分，已经排好序的
        List<Integer> leftList = sort(list, begin, midd);
        //右边部分，已经排好序的
        List<Integer> rightList = sort(list, midd + 1, end);

        //将两个有序集合合并在一起，
        List<Integer> mergeList = new ArrayList<>(end - begin + 1);
        int leftIdx = 0, rightIdx = 0;
        while (leftIdx < leftList.size() && rightIdx < rightList.size()) {
            mergeList.add(leftList.get(leftIdx) <= rightList.get(rightIdx) ? leftList.get(leftIdx++) : rightList.get(rightIdx++));
        }
        while (leftIdx < leftList.size()) {
            mergeList.add(leftList.get(leftIdx++));
        }
        while (rightIdx < rightList.size()) {
            mergeList.add(rightList.get(rightIdx++));
        }
        return mergeList;
    }

    @Override
    public String sortType() {
        return "归并排序";
    }

    public static void main(String[] args) {
        int total = 10;
        List<Integer> list = new ArrayList<>(total);
        for (int idx = 0; idx < total; idx++) {
            list.add(idx);
        }
        Collections.shuffle(list);
        new Sort06Merge().print(new Sort06Merge().sort(list));
        System.out.println(new Sort07Heap().check(list));
    }
}
