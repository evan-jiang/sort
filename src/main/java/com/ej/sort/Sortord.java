package com.ej.sort;

import java.util.List;

public interface Sortord {

    default <T> void print(List<T> list){
        for (T t : list) {
            System.out.print(t.toString() + "\t");
        }
        System.out.println();
    }

    public  <T extends Comparable> List<T> sort(List<T> list);
}
