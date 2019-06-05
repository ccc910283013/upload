package com.ewell.upload.config.properties;

import com.ewell.upload.config.handler.DatabaseContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContextHolder.getDataSourceType();
    }

}
