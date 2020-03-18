package com.ej.sort;

import java.util.List;

public class Sort04Quick implements Sort {
    @Override
    public List<Integer> sort(List<Integer> list) {
        int size = 0;
        if(list == null || (size = list.size()) <= 1){
            return list;
        }
        return sort(list,0,size - 1);
    }

    private List<Integer> sort(List<Integer> list,final int begin,final int end){
        //参考数据随意，这里选择需要排序的第一个数据
        Integer point = list.get(begin);
        int left = begin;
        int right = end;
        //因为选择第一个数据为参考数据，所以从第二个数据开始遍历
        int idx = begin + 1;
        while (idx <= right){
            int temp = list.get(idx);
            if(temp < point){
                //比参考数据小，需要移到参考数据的左边
                list.set(idx - 1,temp);
                list.set(idx,point);
                idx++;
            }else if(temp > point){
                //比参考数据大，需要移到参考数据的右边，事实上temp本身就在参考数据的右边
                //如果此次temp的位置不变下次遇到比参考数据小的数据时交换数据后这一次的temp就会出现在参考数据的左边，这是不对的
                //所以此次需要将temp移到最右边同时缩小排序长度
                //注意：temp移到最后面的过程就破坏了稳定性
                list.set(idx,list.get(right));
                list.set(right,temp);
                right--;
            }else{
                //和参考数据相同
                idx++;
                //没有这行代码也行，这行代码的用处就是尽量的不破坏文档性，但是上面的代码已经破坏了
                point = temp;
            }
        }
        //至此begin到end的数据已经分好了
        //此时的idx-1表示参考数据的位置
        int pointIdx = idx - 1;
        if(pointIdx - begin > 1){
            //参考数据左边数据量大于1，则需要将左边再次拆分
            list = sort(list,begin,pointIdx - 1);
        }
        if(end - pointIdx > 1){
            //参考数据右边数据量大于1，则需要将右边再次拆分
            list = sort(list,pointIdx + 1,end);
        }
        return list;
    }

    @Override
    public String sortType() {
        return "快速排序";
    }
}
