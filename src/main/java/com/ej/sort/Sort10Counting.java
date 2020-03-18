package com.ej.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Sort10Counting implements Sort {

    private static final int MAX_STEP = 1024;

    @Override
    public List<Integer> sort(List<Integer> list) {
        int size = 0;
        if (list == null || (size = list.size()) <= 1) {
            return list;
        }
        //获取最大值
        Integer[] minAndMax = getMinAndMax(list);
        Integer min = minAndMax[0], max = minAndMax[1];
        //最大值与最小值相同就不要排了
        if (min == max) {
            return list;
        }
        return sort(list, min, max, true);
    }

    private Integer[] getMinAndMax(List<Integer> list) {
        Integer[] array = new Integer[2];
        Integer min = list.get(0), max = min;
        for (int idx = 1; idx < list.size(); idx++) {
            if (list.get(idx) < min) {
                min = list.get(idx);
            } else if (list.get(idx) > max) {
                max = list.get(idx);
            }
        }
        array[0] = min;
        array[1] = max;
        return array;
    }

    private List<Integer> sort(List<Integer> list, Integer min, Integer max, boolean stability) {
        int countLength = max - min + 1;
        if (countLength > MAX_STEP) {
            System.err.println("数据取值范围太大不建议计数排序");
            return list;
        }
        int[] count = new int[countLength];
        for (Integer value : list) {
            //记录每个相同的元素出现的次数
            ++count[value - min];
        }
        if (stability) {
            Integer [] sorted = new Integer[list.size()];
            for (int idx = 1; idx < countLength; idx++) {
                //在记录出现次数的基础上再记录该元素在排好序的数组中的最大位置
                count[idx] = count[idx] + count[idx - 1];
            }
            //这里必须是倒序
            for (int idx = list.size() - 1; idx >= 0; idx--) {
                Integer temp = list.get(idx);
                sorted[--count[temp - min]] = temp;
            }
            return Arrays.asList(sorted);
        } else {
            //如果不考虑稳定性
            int k = 0;
            for (int idx = 0; idx < countLength; idx++) {
                for (int j = 0; j < count[idx]; j++) {
                    list.set(k++, idx + min);
                }
            }
            return list;
        }
    }

    @Override
    public String sortType() {
        return "计数排序";
    }

    public static void main(String[] args) {
        int total = 100;
        List<Integer> list = new ArrayList<>(total);
        for (int idx = 0; idx < total; idx++) {
            list.add(new Random().nextInt(100));
        }
        new Sort10Counting().print(list);
        list = new Sort10Counting().sort(list);
        new Sort10Counting().print(list);
        System.out.println(new Sort10Counting().check(list));
    }
}
