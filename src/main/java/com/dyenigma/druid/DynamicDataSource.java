package com.dyenigma.druid;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Description: 动态数据源
 * author  dyenigma
 * date 2017/7/25 10:25
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
