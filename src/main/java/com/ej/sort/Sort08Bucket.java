package com.ej.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sort08Bucket implements Sort {

    private static final int DEF_BUCKET_TOTAL = 10;

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
        //分桶-思想
        Integer bucketTotal = getBucketTotal(list);
        List<List<Integer>> lists = splitBucket(list, min, max, bucketTotal);
        //对每个桶排序-这里我们用归并排序对每个桶进行排序
        Sort sort = new Sort06Merge();
        list.clear();
        for (List<Integer> temp : lists) {
            temp = sort.sort(temp);
            list.addAll(temp);
        }
        return list;
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

    private Integer getBucketTotal(List<Integer> list) {
        return list.size() < DEF_BUCKET_TOTAL ? list.size() / 2 : DEF_BUCKET_TOTAL;
    }

    private List<List<Integer>> splitBucket(List<Integer> list, Integer min, Integer max, int bucketTotal) {
        List<List<Integer>> lists = new ArrayList<>(bucketTotal);
        for (int idx = 0; idx < bucketTotal; idx++) {
            lists.add(new ArrayList<>());
        }
        Integer step = (max - min + 1) / bucketTotal;
        for (Integer curr : list) {
            int bucketIdx = (curr - min) / step;
            lists.get(bucketIdx).add(curr);
        }
        return lists;
    }

    @Override
    public String sortType() {
        return "桶排序";
    }

    public static void main(String[] args) {
        int total = 100;
        List<Integer> list = new ArrayList<>(total);
        for (int idx = 0; idx < total; idx++) {
            list.add(idx);
        }
        Collections.shuffle(list);
        new Sort08Bucket().sort(list);
        System.out.println(new Sort08Bucket().check(list));
    }
}
