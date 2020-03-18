package com.ej.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sort07Heap implements Sort {
    @Override
    public List<Integer> sort(List<Integer> list) {
        int size = 0;
        if (list == null || (size = list.size()) <= 1) {
            return list;
        }
        for (int idx = size / 2 - 1; idx >= 0; idx--) {
            //构建大顶堆
            list = buildHeap(list, idx, size - 1);
        }
        //大顶堆以构造完成
        for (int idx = size - 1; idx > 0; idx--) {
            //将大顶堆的定点换至最后面(先换到最后面的说明较大的数已确认)
            Integer temp = list.get(0);
            list.set(0, list.get(idx));
            list.set(idx, temp);
            //大顶堆的定点被置换，需要重新构造大顶堆，同时较大的数已经确认，不应该参与重构大顶堆
            list = buildHeap(list, 0, idx - 1);
        }
        return list;
    }

    private List<Integer> buildHeap(List<Integer> list, int begin, int end) {
        //以此为顶点
        Integer point = list.get(begin);
        int idx = begin * 2 + 1;
        while (idx <= end) {
            if (idx + 1 <= end && list.get(idx) < list.get(idx + 1)) {
                //选出顶点的左右子节点中的较大者
                idx++;
            }
            //较大者和顶点比较
            if (list.get(idx) > point) {
                //交换保证顶点最大
                list.set(begin, list.get(idx));
                list.set(idx, point);
                //以原来的顶点下移后所在的位置重新为顶点继续构造大顶堆子集
                begin = idx;
                idx = begin * 2 + 1;
            } else {
                break;
            }
        }
        return list;
    }

    @Override
    public String sortType() {
        return "堆排序";
    }

}
