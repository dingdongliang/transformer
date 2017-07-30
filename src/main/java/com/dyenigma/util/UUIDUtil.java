package com.dyenigma.util;

import java.util.UUID;

/**
 * topic 生成数据库主键的方法
 * author: dyenigma
 * create: 2016/4/21 8:22
 */
public class UUIDUtil {
    /**
     * 获得一个UUID
     * <p>
     * return String UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    //获得指定数量的UUID
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }
}
