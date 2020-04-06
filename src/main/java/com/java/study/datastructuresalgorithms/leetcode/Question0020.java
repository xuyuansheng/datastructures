package com.java.study.datastructuresalgorithms.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0020 {


    public static void main(String[] args) {
        System.out.println((int) '[');
        System.out.println((int) ']');
        System.out.println((int) '(');
        System.out.println((int) ')');
        System.out.println((int) '{');
        System.out.println((int) '}');
        Question0020 test = new Question0020();
        System.out.println(test.isValid("[[[]]]{}]"));

    }

    public boolean isValid(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>(3);
        map.put(']', '[');
        map.put('}', '{');
        map.put(')', '(');
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack();
        for (int i = 0; i < chars.length; i++) {
            Character character = map.get(chars[i]);
            if (stack.isEmpty() || character == null) {
                /*  stack为空或字符为左括号,直接入栈 */
                stack.push(chars[i]);
            } else if (character.equals(stack.peek())) {
                /*  字符为右括号,且在栈顶找到了对应的左括号 */
                stack.pop();
            } else {
                /*  字符为右括号,且在栈顶没找到了对应的左括号 */
                return false;
            }
        }
        return stack.isEmpty();
    }

}
