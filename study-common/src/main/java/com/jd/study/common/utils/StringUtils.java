package com.jd.study.common.utils;

import java.util.Random;

/**
 * 字符串工具类
 * @author lsy
 * @date 2020-7-16
 */
public class StringUtils {

    public static final String EMPTY = "";
    public static final char[] CHARS = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9'
    };

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
        String str = "create_time";
        System.out.println(underscoreToHump(str));
    }


    /**
     * 获取随机数，如果未指定随机数的数量；将默认为 4
     * @param quantity  数量
     * @return          String
     */
    public static String getRandomString(Integer quantity) {
        int len = 4;
        if (null != quantity && quantity > 0) {
            len = quantity;
        }
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= len; i++) {
            int index = random.nextInt(CHARS.length);
            builder.append(CHARS[index]);
        }
        return builder.toString();
    }
}
