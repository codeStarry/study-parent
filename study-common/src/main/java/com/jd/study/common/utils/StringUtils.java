package com.jd.study.common.utils;

import java.util.Random;

/**
 * 字符串工具类
 * @author lsy
 * @date 2020-7-16
 */
public class StringUtils {

    public final static String EMPTY = "";

    /**
     * 是否为空， 不包括空白字符判断
     * @param sequence
     * @return
     */
    public static Boolean isEmpty(final CharSequence sequence) {
        return null == sequence || sequence.length() <= 0;
    }

    /**
     * 是否非空
     * @param sequence
     * @return
     */
    public static Boolean isNotEmpty(final CharSequence sequence) {
        return !isEmpty(sequence);
    }

    /**
     * 驼峰转下划线
     * @param str
     * @return
     */
    public static String humpToUnderscore(String str) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        int len = str.length();
        StringBuilder builder = new StringBuilder(len);
        for (int i = 0; i < len; ++i) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                builder.append("_");
            }
            builder.append(Character.toLowerCase(c));
        }
        return builder.toString();
    }

    /**
     * 下划线转驼峰
     * @param str
     * @return
     */
    public static String underscoreToHump(String str) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        int len = str.length();
        StringBuilder builder = new StringBuilder(len);
        for (int i = 0; i < len; ++i) {
            char c = str.charAt(i);
            if (c == '_') {
                ++i;
                builder.append(Character.toUpperCase(str.charAt(i)));
            }else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String str = "createTimeDateGG";
        System.out.println(humpToUnderscore(str));
    }

    /**
     * 获取随机数，如果未指定随机数的数量；将默认为 4
     * @param quantity  数量
     * @return          String
     */
    public static String getRandomNumber(Integer quantity) {
        String str = "qwertyuioplkjhgfdsazxcvbnm0123456789POIUYTREWQASDFGHJKLMNBVCXZ";
        int len = 4;
        if (null != quantity && quantity > 0) {
            len = quantity;
        }
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= len; i++) {
            int index = random.nextInt(str.length());
            builder.append(str.charAt(index));
        }
        return builder.toString();
    }
}
