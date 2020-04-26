给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。


![alt 属性文本](https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif "鼠标悬停时显示的文字")  
在杨辉三角中，每个数是它左上方和右上方的数的和。


解题思路 : 下一行的数据总是由上一行数据计算出来,很明显为动态规划 

编码思路 : 每一行的第一个和最后一个元素的值一直都是 1 ,所以直接塞值即可
         中间的数据都是由上一行下标相同的数据和前一个数据的和 
