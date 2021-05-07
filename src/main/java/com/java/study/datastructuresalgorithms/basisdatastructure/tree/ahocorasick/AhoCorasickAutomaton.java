package com.java.study.datastructuresalgorithms.basisdatastructure.tree.ahocorasick;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.*;

/**
 * AC自动机
 *
 * @author Mr.Xu
 * @date 2021/5/7 21:08
 */
public class AhoCorasickAutomaton implements AhoCorasickInterface {

    public static void main(String[] args) {

        List<String> patterns = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            /*  生成100个模式串 */
            patterns.add(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(3, 10)));
        }
        patterns.addAll(Arrays.asList("his", "hers", "he", "she", "self"));
        String[] array = patterns.toArray(new String[]{});

        /*  构建AC自动机 */
        AhoCorasickAutomaton root = AhoCorasickAutomaton.build(array);

        for (int i = 0; i < 1000; i++) {
            /*  生成随机的字符串去匹配 */
            String text = RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(40, 100));
            List<Result> match = root.match(text);

            if (match.size() < 1) {
            /*  如果匹配结果为空，用Java的indexOf方法校验下 */
                patterns.forEach(p->{
                    int i1 = text.indexOf(p);
                    if (i1 >= 0) {
                        System.out.println("错误了 P: "+p+" text: "+text);
                    }
                });
            }
        }
        System.out.println("结束 ");

    }

    /**
     * 数据
     */
    private final Character data;
    /**
     * 子节点
     */
    private Map<Character, AhoCorasickAutomaton> children;
    /**
     * 失败指针
     */
    private AhoCorasickAutomaton fail;
    /**
     * 是否为字符结束节点
     */
    private Set<Integer> exist;

    @Override
    public List<Result> match(String strText) {
        char[] text = strText.toCharArray();
        int n = text.length;
        AhoCorasickAutomaton root = this;
        AhoCorasickAutomaton p = root;
        List<Result> results = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            AhoCorasickAutomaton pc = null;
            while ((p.children == null || (pc = p.children.get(text[i])) == null) && p != root) {
                /* 匹配失败 且 当前节点父节点不是根节点（即当前节点深度大于 2 ）  */
                p = p.fail;
            }
            p = pc;
            if (p == null) {
                /*  匹配失败 且 当前节点父节点是根节点（即当前节点深度为 2 ） */
                p = root;
                continue;
            }
            AhoCorasickAutomaton tmp = p;
            while (tmp != root) {
                if (tmp.exist != null) {
                    /*  exist不为空，说明这个节点是模式串字符的最后一个节点 */
                    for (Integer len : tmp.exist) {
                        int pos = i - len + 1;
                        results.add(new Result(pos, len).setStr(strText.substring(pos, pos + len)));
                        /*  System.out.println(" 匹配起始下标 " + pos + "; 长度 " + len + "; 字符 " + substring); */
                    }
                }
                    /* tmp = tmp.fail;
                    如果用这个就会出现重复结果，因为在构建失败指针时
                    已经把失败指针指向的结点exist添加到此节点中了
                    这样就可以在匹配成功时避免了查询失败指针的过程 */
                tmp = root;
            }
        }
        return results;
    }

    public AhoCorasickAutomaton(Character data) {
        this.data = data;
    }

    /**
     * 构建Tree树
     */
    public static AhoCorasickAutomaton build(String... strings) {
        AhoCorasickAutomaton root = new AhoCorasickAutomaton(null);
        for (String str : strings) {
            insertStr(str, root);
        }
        buildFailurePointer(root);
        return root;
    }

    /**
     * 递归插入节点
     */
    private static void insertStr(String str, AhoCorasickAutomaton root) {
        if (str == null || "".equals(str.trim())) {
            /*  字符串不能为空 */
            return;
        }
        AhoCorasickAutomaton ahoCorasickAutomaton = root;
        for (Character c : str.toCharArray()) {
            if (ahoCorasickAutomaton.children == null) {
                ahoCorasickAutomaton.children = new HashMap<>(4);
            }
            if (!ahoCorasickAutomaton.children.containsKey(c)) {
                ahoCorasickAutomaton.children.put(c, new AhoCorasickAutomaton(c));
            }
            ahoCorasickAutomaton = ahoCorasickAutomaton.children.get(c);
        }
        /* 整个字符串的最后一个节点，添加Ending标识，值为这个字符串的长度 */
        if (Objects.isNull(ahoCorasickAutomaton.exist)) {
            ahoCorasickAutomaton.exist = new HashSet<>(4);
        }
        ahoCorasickAutomaton.exist.add(str.length());
    }

    /**
     * 构建失败指针
     */
    private static void buildFailurePointer(AhoCorasickAutomaton root) {
        Queue<AhoCorasickAutomaton> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            AhoCorasickAutomaton p = queue.remove();
            if (p.children == null) {
                continue;
            }
            p.children.forEach((k, pc) -> {
                if (p == root) {
                    pc.fail = root;
                } else {
                    AhoCorasickAutomaton q = p.fail;
                    while (q != null) {
                        AhoCorasickAutomaton qc = q.children.get(k);
                        if (qc != null) {
                            pc.fail = qc;
                            if (!Objects.isNull(pc.fail.exist)) {
                                /*  把失败指针的End节点也加入进来 */
                                if (!Objects.isNull(pc.exist)) {
                                    pc.exist.addAll(pc.fail.exist);
                                } else {
                                    (pc.exist = new HashSet<>(4)).addAll(pc.fail.exist);
                                }
                            }
                            break;
                        }
                        q = q.fail;
                    }
                    if (q == null) {
                        pc.fail = root;
                    }
                }
                /*  子节点加入队列 */
                queue.add(pc);
            });
        }
    }
}
