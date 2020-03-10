package com.java.study.datastructuresalgorithms.basisdatastructure.linear.array;

/**
 * 数据结构-数组学习
 *
 * @author Mr.Xu
 * @date 2020/3/7 16:28
 */
public class Array {

    /**
     * 数组（Array）是一种线性表数据结构。它用一组连续的内存空间，来存储一组具有相同类型的数据。
     * 因为是连续的内存空间和相同的数据类型,所以就有了所及访问的特性,
     * 随机访问寻址公式: a[i]_address = base_address + i * data_type_size
     * 数组的劣势:  低效的“插入”和“删除”
     * 数组为了保持内存数据的连续性，会导致插入、删除这两个操作比较低效。
     * <p>
     * ---现在我们就来详细说一下，究竟为什么会导致低效？又有哪些改进方法呢？
     * 一个长度为n的数组,如果在第k个位置插入一条数据,那么第k+1...n的位置的数据就需要向后移动一位,给k位置腾出空间,
     * 所以会出现大量的数据搬移操作,从而见你降低了效率,反之删除也是类似的.
     * <p>
     * ---又有哪些改进方法呢???
     * 在特定的情况下,如不需要保证数据的原始顺序时.我们在第k个位置添加一个数据,就可以直接把第k个位置的数据移动到数组末尾
     * 然后在第k个位置添加数据,这样就不需要大量的数据搬移,很高效的添加数据.反之亦然.
     * <p>
     * 为什么数组的下标是从 0 开始,如果假设是从 1 开始的,那么寻址公式就变成了 a[i]_address = base_address + (i-1) * data_type_size
     * 那么就多了一次数学运算,这是我个人认为比较合理的解释.
     * <p>
     * 二维数组的寻址公式:
     * 先有一个二维数组  a[m][n]
     * [(0,0),(0,1),(0,2) ...(0,j),(0,n-2),(0,n-1) ]
     * ...
     * [(i,0),(i,1),(i,2) ...(i,j),(i,n-2), (i,n-1)]
     * ...
     * [(m,0),(m,1),(m,2) ...(m,j),(m,n-2), (m-1,n-1)]
     *
     * <p>
     * 那么在内存中的位置可能为先排列每一行:    [(0,0),..(0,n-1)]  ...[(i,0)(i,j)(i,n-1)] ... [(m-1,0)...(m-1,n-1)]
     * <p>
     * 那么寻址公式为: a[i][j]_address = base_address + (i*n+j)* data_type_size
     * <p>
     * 那么在内存中的位置可能为先排列每一列:    [(0,0),.(i,0).(m-1,0)]  ...[0,j)(i,j)(m-1,j)] ... [(0,n-1)...(i,n-1)..(m-1,n-1)]
     * <p>
     * 那么寻址公式为: a[i][j]_address = base_address + (j*m+i)* data_type_size
     */

    public static void main(String[] args) {
        String[][] erwei = new String[][]{{"00", "01", "02"}, {"10", "11", "12"}, {"20", "21", "22",}, {"30", "31", "32"}};
        for (int i = 0; i < erwei.length; i++) {
            System.out.println();
            for (int j = 0; j < erwei[i].length; j++) {
                System.out.print(erwei[i][j] + "\t");
            }

        }

        String[][] real = new String[4][3];
        for (int i = 0; i < real.length; i++) {
            System.out.println();
            for (int j = 0; j < real[i].length; j++) {
                System.out.print("\t" + i + j);
            }

        }
    }

}
