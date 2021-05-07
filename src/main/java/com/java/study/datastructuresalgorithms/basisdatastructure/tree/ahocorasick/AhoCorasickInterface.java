package com.java.study.datastructuresalgorithms.basisdatastructure.tree.ahocorasick;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 接口
 *
 * @author Mr.Xu
 * @date 2021/5/7 21:11
 */
public interface AhoCorasickInterface {


    /**
     * 获取匹配的结果
     *
     * @param strText 被匹配的主串
     * @return 结果
     */
    List<Result> match(String strText);

    /**
     * 获取匹配的结果的字符串
     *
     * @param strText 被匹配的主串
     * @return 结果
     */
    default List<String> queryStrings(String strText) {
        List<Result> match = match(strText);
        if (Objects.isNull(match)) {
            return null;
        }
        return match.stream().map(m -> strText.substring(m.getPos(), m.getPos() + m.getLength())).collect(Collectors.toList());
    }


    /**
     * 获取匹配的结果的数量
     *
     * @param strText 被匹配的主串
     * @return 结果
     */
    default Integer count(String strText) {
        List<Result> match = match(strText);
        if (Objects.isNull(match)) {
            return 0;
        }
        return match.size();
    }

    @Data
    @Accessors(chain = true)
    @RequiredArgsConstructor
    class Result {
        final private int pos;
        final private int length;
        private String str;
    }

}
