package com.ej.sort;

import java.util.List;

public interface Sort {

    public default void print(List<Integer> list) {
        for (int t : list) {
            System.out.print(t + "\t");
        }
        System.out.println();
    }

    public default boolean check(List<Integer> list) {
        int size = list.size();
        int idx = 0;
        while (idx < size - 1) {
            if (list.get(idx) > list.get(idx + 1)) {

                return false;
            }
            idx++;
        }
        return true;
    }

    public List<Integer> sort(List<Integer> list);

    public String sortType();
}
