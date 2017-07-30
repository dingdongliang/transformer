package com.dyenigma.druid;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:动态数据源生成
 * author  dyenigma
 * date 2017/7/25 10:29
 */
public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
    public static List<String> dataSourceIds = new ArrayList<>();

    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return contextHolder.get();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    /**
     * 判断指定DataSrouce当前是否存在
     * param dataSourceId
     * return
     */
    public static boolean containsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }
}
