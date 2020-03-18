# 排序

### 冒泡排序

#### 排序过程

>1、已知数组的长度为n，假定排序的总趟数为t，则t=n-1
>
>2、第i趟(i取值范围[1,n-1])从第0个元素开始直到第n-i个元素，逐一向后每两个相邻的元素相互比较交换，保证最大(或者最小)值在后面
>
>3、i++之后重复第二步

#### 时间复杂度

- 最坏：n(n^2)
- 最好：n(n)

#### 空间复杂度

>一次基础临时变量赋值，所以空间复杂度为O(1)

#### 稳定性

>相邻数据之间的交换，所以是【稳定排序】

#### 代码实现

```java
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
```

### 选择排序

#### 排序过程

>1、已知数组的长度为n，假定排序的总趟数为t，则t=n-1
>
>2、第i趟(i取值范围[1,n-1])从第i个元素开始直到第n-1个元素，选出其中最小(或者最大)的值的位置(假定为midx)，如果midx位置的值比第i-1位置的值还要小(或者大)，这将midx和i-1的值交换
>
>3、i++之后重复第二步

#### 时间复杂度

- 最坏：n(n^2)
- 最好：n(n^2)没有提前跳出

#### 空间复杂度

> 一次基础临时变量赋值，所以空间复杂度为O(1)

#### 稳定性

> 第i趟时早到的最值需要和第i-1位置数据交换，也就是说第i-1位置的数据需要跨越置换，在置换的过程中可能跨越了与i-1相同的数据，稳定性遭到破坏，所以该排序是【不稳定排序】

#### 代码实现

```java
    public List<Integer> sort(List<Integer> list) {
        int size = 0;
        if(list == null || (size = list.size()) <= 1){
            return list;
        }
        //趟数
        int t = size - 1;
        for (int i = 1; i <= t; i++) {
            int minIdx = i - 1;
            for (int j = i; j < size; j++) {
                if (list.get(minIdx) > list.get(j)) {
                    //选择较小值的下标
                    minIdx = j;
                }
            }
            if (i - 1 != minIdx) {
                //如果较小值的下标不是i-1则需要交换
                Integer point = list.get(i - 1);
                list.set(i - 1, list.get(minIdx));
                list.set(minIdx, point);
            }
        }
        return list;
    }
```
### 插入排序

#### 排序过程

> 【注意：插入排序的特点，在选定每一个参考元素时，在这个元素之前的所有元素都是有序的，所以基本有序的集合用插入排序效率高】
>
> 1、已知数组的长度为n，假定排序的总趟数为t，则t=n-1
>
> 2、第i趟(i取值范围[1,n-1])从第i-1个元素开始直到第0个元素，从后往前，逐一和第i个参考元素(假定该元素为v)比较，比v大(或者小)的元素向后移一个位置，直到出现不大于(或者不小于)v为止，并记这个位置为j(显然j的范围为[0,i-1])，然后将v设置到j+1的位置
>
> 3、i++之后重复第二步

#### 时间复杂度

- 最好：O(n)(原本有序)
- 最坏：O(n2)(原本逆序)

#### 空间复杂度

> 一次基础临时变量赋值，所以空间复杂度为O(1)

#### 稳定性

> 后移的跨度都是1，参考元素向前置换是虽然跨度可能大于1，但是所跨过的元素都是比参考元素大(或者小)的元素，稳定性没有破坏，所以是【稳定排序】

#### 代码实现

```java
    public List<Integer> sort(List<Integer> list) {
        int size = 0;
        if(list == null || (size = list.size()) <= 1){
            return list;
        }
        if(size < 2){return list;}
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
```

### 快速排序

#### 排序过程

> 这是一个递归排序
>
> 1、每次选定一个参考数据，然后将需要排序的数据分配在这个参考数据的左右两边，左边数据都比参考数据小(或者大)，右边都比参考数据大(或者小)
>
> 2、上一步得到的左右两批数据如果数据量大于2个，则再分别对对其执行上一步操作

#### 时间复杂度

- 最好：O(nlogn)
- 最坏：n^2(点儿背，每次参考数据是最大的，退化成冒泡)

#### 空间复杂度

> 一次基础临时变量赋值，所以空间复杂度为O(1)，但是该排序是递归的，递归的次数至少为(logn) + 1次，所以空间复杂度为logn*O(1)，即为O(logn)

#### 稳定性

> 构造右边集合时，置换都是参考元素后面的一个数和最右边未和参考元素比较的元素跨越置换的，破坏了稳定性，所以是【不稳定排序】

#### 代码实现

```java
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
```

### 希尔排序

#### 排序过程

> 希尔排序基于【基本有序的插入排序高效】的理论都优化排序，想方设法构造【基本有序】
>
> 1、已知数组的长度为n，选定一个步长step=n/2，将数组分组，所有下标idx%step相同的分为一组，这样原始数组将分为step个组，然后对每个组进行插入排序，第一轮过后下标相差n/2的元素时有序的
>
> 2、将上一步的step缩短，使得step=step/2=(n/2)/2，这一轮过后下标相差(n/2)/2的元素时有序的
>
> 3、继续缩短step知道step=1，最后下标相差1的元素时有序的，完成排序
>
> 【注意：步长为1的希尔排序就等价于希尔排序】

#### 时间复杂度

- 最好：O(n)(本来有序)
- 最坏：O(n^2)(每一次以step分组后都是逆序(n^2)/2 + (n^2)/4 + (n^2) /8 + (n^2) /16 + ... + n)
- 平均：O(n^1.3)(很复杂)

#### 空间复杂度

> 一次基础临时变量赋值，所以空间复杂度为O(1)

#### 稳定性

> 当步长大于1时，存在跨越置换，稳定性遭到破坏，所以是【不稳定排序】

#### 代码实现

```java
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
```

### 归并排序

#### 排序过程

> 归并排序包含两个操作：拆分、合并
>
> 1、已知数组的长度为n，将数组拆分成[0,(n-1)/2]和[((n-1)/2) + 1,n-1]两个兄弟数组
>
> 2、继续将上一步的两兄弟数组拆分成4个部分，这4个部分有两对兄弟构成
>
> 3、继续拆，拆到不能再拆为止
>
> 4,、合并，从下往上，将兄弟数据按序合并，合并得到的数组就是对这对兄弟的父数组的排序
>
> 5、将上一步合并得到的数组与其兄弟数组按序合并
>
> 6、继续合，合到没有兄弟为止

#### 时间复杂度

- 最好：O(nlogn)(拆分的次数为logn，所以一个元素合并的次数就为logn，一个有n个元素)
- 最坏：O(nlogn)

#### 空间复杂度

> 第一次迭代需要开辟n/2个空间临时存储元素(左边没有完成右边不会开始)，第二次是n/4(左边没有完成右边不会开始)。。。右边开始时左边已排完，所以需要空间n/2+n/4+n/8+...=n，即空间复杂度为O(n)

#### 稳定性

> 元素的的置换体现在合并时，而合并是逐一赋值的，没有破坏稳定性，所以是【稳定排序】

#### 代码实现

```java
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
        int leftIdx = 0, rightIdx = 0, mergeIdx = 0;
        while (leftIdx < leftList.size() && rightIdx < rightList.size()) {
            mergeList.add(leftList.get(leftIdx) <= rightList.get(rightIdx) ? leftList.get(leftIdx++) : rightList.get(rightIdx++));
            mergeIdx++;
        }
        while (leftIdx < leftList.size()) {
            mergeList.add(leftList.get(leftIdx++));
        }
        while (rightIdx < rightList.size()) {
            mergeList.add(rightList.get(rightIdx++));
        }
        return mergeList;
    }
```

### 堆排序

#### 排序过程

> 首先了解大(或者小)顶堆：一颗完全二叉树，同时每一个节点都比自己的左右子节点大(或者小)
>
> 顶堆与数组之间的关系：一颗顶堆从上到下，从左到右的下标表示数组的小标
>
> 构造顶堆的顺序：从最后一个节点的父节点开始往数组的前面开始构造，这样是为了保证最小的子树都是顶堆
>
> 1、已知数组的长度为n，最后一个元素的父节点下标为n/2-1，构造顶堆
>
> 2、然后是n/2-2开始构造顶堆
>
> 3、。。。如果数据够长，第一步中的顶点的父节点为(n/2-1)/2-1假设这个序号为p，如过在构造以p为顶点的顶堆时，p的左右子树都不是顶推子树，那么是不是没法构造？做就是为什么要先从最小都顶堆开始构造的原因
>
> 4、数组完全转化成顶堆之后，将下标为0的元素和最后一个【未固定】元素交换，这样最后一个元素就是最大(或者最小)，同时将这个元素【固定】一直到排序完成。注意这个时候除开已固定的数之外的数组并不是一个顶堆，原因是顶点被交换了，需要重新构造顶堆，这次比较容易，因为顶点的子树都是顶堆
>
> 5、重复上一步，知道固定下标为1的元素，也就是第二个元素为止，排序结束

#### 时间复杂度

- 最好：O(nlogn)
- 最坏：O(nlogn)

#### 空间复杂度

> 一次基础临时变量赋值，所以空间复杂度为O(1)

#### 稳定性

>构造顶堆时存在跨越置换，被跨越元素与置换元素没有必然的大小关系，稳定性被破坏，同时在固定最值的时候设置都顶点的过程也是跨越置换，也会破坏稳定性，综上所述为【不稳定排序】

#### 代码实现

```java
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
```

### 桶排序

#### 排序过程

>桶排序是是一种分而治之的思想
>
>1、确定桶的数量b，找出最大值和最小值，将最小值到最大值的范围分为b个区间
>
>2、遍历整个集合，将所有元素分别放入对应的区间(桶)内
>
>3、分别对每个桶进行排序，这里的排序方式可以任意
>
>4、从首个桶开始合并得到结果排序完成

#### 时间复杂度

- 最好：O(N)【O(n)最值分桶 + 桶排序O(b(n/b)log(n/b)) = O(n) + O(n(logn-logb)) 均分且桶多效率高，但空间消耗大】
- 最坏：O(n)最值分桶 + O(n^2)

#### 空间复杂度

> O(n+b)，需要重新存储n个元素，分别有b个桶存储

#### 稳定性

> 分桶的过程是稳定的，对每个桶排序的稳定性决定了整体稳定性

#### 代码实现

```java
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
```

### 基数排序

#### 排序过程

> 是桶排序的一种具体实现
>
> 先定义一个名词——基数：这里仅限于整数，所谓基数就是整数value从后往前的第i个数字且带符号，这里的i表示第i次读到value值，长度不够的基数为0。比如整数-109：第一次读到这个数的基数为-9，第二次为-0也就是0，第三次为-1，第四次为0。
>
> 那基数的范围[-9,9]，申明19个桶，分别用于存储[-9,9]这19个基数对应的元素
>
> 1、找出数组中最长的数据，将最大长度length定义为执行分桶的次数
>
> 2、第一轮将所有元素以基数(第一轮是倒数第一个数带符号)为顺序分别放入对应的19个桶中，然后将所有桶数据依次写入到原数组中，并清空所有桶
>
> 3、第二轮将所有元素以基数(第二轮是倒数第二个数带符号)为顺序分别放入对应的19个桶中，然后将所有桶数据依次写入到原数组中，并清空所有桶
>
> 4、。。。
>
> 5、直到处理完第length轮排序完成

#### 时间复杂度

- 最好：O(max(length) * n * 2 ) = O(n*k)
- 最坏：O(max(length) * n * 2 ) = O(n*k)

#### 空间复杂度

> n个数据加上r个桶，O(n+r)

#### 稳定性

> 相同的元素每次都会分到同一个桶里面，而且先后顺序不会打乱，所有是【稳定排序】

#### 代码实现

```java
	public List<Integer> sort(List<Integer> list) {
        int size = 0;
        if (list == null || (size = list.size()) <= 1) {
            return list;
        }
        //先定义一个名词——基数：这里仅限于整数，所谓基数就是整数value从后往前的第i个数字且带符号，这里的i表示第i次读到value值
        //比如整数-109，那第一次读到这个数的基数为-9，第二次为-0也就是0，第三次为-1
        //找出绝对值最大的数，为了计算最长数据
        int max = 0;
        for (Integer value : list) {
            if(Math.abs(value) > max){
                max = Math.abs(value);
            }
        }
        return sort(list, (int) Math.log10(max) + 1);
    }

    private List<Integer> sort(List<Integer> list, int maxLength) {
        //为什么是19？要考虑负数的情况
        List<List<Integer>> lists = new ArrayList<>(19);
        //初始化临时数组
        for (int idx = 0; idx < 19; idx++) {
            //这里为什么不用ArrayList
            lists.add(new LinkedList<>());
        }
        for (int idx = 1, radix = 1; idx <= maxLength; idx++, radix *= 10) {
            for (Integer value : list) {
                //基数加9的原因是基数的顺序是-9到9
                lists.get(((value / radix) % 10) + 9 ).add(value);
            }
            int i = 0;
            for (List<Integer> tempList : lists) {
                if(tempList.isEmpty()){
                    continue;
                }
                //每一轮按基数分组之后会写到元素数组
                for (Integer value : tempList) {
                    list.set(i++,value);
                }
                //对于tempList的写入都是add()，所以需要清空之后次轮再写入
                tempList.clear();
            }
        }
        return list;
    }
```

### 计数排序

#### 排序过程

> 分桶原理
>
> 首先强调适用性，适用于数据量大但是元素取值范围小的排序，比如100W考生的分数排名，全国人口年龄排名
>
> 1、先找出最小于最大值，确认取值范文[min,max]，定义计数数组count长度为max-min+1
>
> 2、遍历待排数组统计每个元素出现的次数，将次数记录在计数数组的相应位置(元素值减去min即为count下标)
>
> 3、如果不考虑稳定性，只需要遍历count数组，用下标idx加上min的值依次写入新数组中count[idx]次，排序完成
>
> 4、如果考虑稳定性，则需要记录每个出现过的数据在排序结果数组中的最大位置
>
> 5、倒序遍历原数组元素value，通过value减去min得到count中的下标idx，此时count[idx] - 1就是value在排序结果数组中的位置

#### 时间复杂度

- 最好：O(n+k)(k为count的长度)
- 最坏：O(n+k)

#### 空间复杂度

> n个元素+k个计数=O(n+k)

#### 稳定性

>在记录出现次数和应该写入的最大位置的情况下可以实现【稳定排序】

#### 代码实现

```java
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
```