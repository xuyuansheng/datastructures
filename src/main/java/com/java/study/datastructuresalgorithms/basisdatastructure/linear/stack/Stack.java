package com.java.study.datastructuresalgorithms.basisdatastructure.linear.stack;

import org.junit.Test;

/**
 * 数据结构-栈
 *
 * @author Mr.Xu
 * @date 2020/3/7 16:35
 */
public class Stack {


    /**
     * 实现浏览器一样的前进后退功能
     * 思路 : 使用两个链式栈,back 和 forward 每新进一个页面就把当前页面push到forward中
     * 没后退一个页面,就把当前页面push到back中,并从forward中取出前一个页面
     * 演示请见 fab.html
     */
    @Test
    public void forwardAndBack() {


        LinkedStack back = new LinkedStack();
        LinkedStack forward = new LinkedStack();
        String currentPage = "page1";
        forward.push(currentPage);
        currentPage = "page2";
        forward.push(currentPage);
        currentPage = "page3";
        forward.push(currentPage);
        currentPage = "page4";
        forward.push(currentPage);
        currentPage = "page5";
        /*  前进了四个页面,开始后退 */
        back.push(currentPage);
        currentPage = forward.pop();
        back.push(currentPage);
        currentPage = forward.pop();
        back.push(currentPage);
        currentPage = forward.pop();

    }

}
