package com.dyenigma.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    private StringUtil() {
    }

    /**
     * 判断字符串是否为空，在Guava中可以使用!Strings.isNullOrEmpty(str)来判断是否为空
     */
    public static boolean isEmpty(String s) {
        return s == null || s.equalsIgnoreCase("null") || s.trim().length() <= 0;
    }

    /**
     * 比较字符串str是否符合正则表达似乎regex的规则
     * param regex
     * param str
     * return
     */
    public static boolean compareRegex(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}