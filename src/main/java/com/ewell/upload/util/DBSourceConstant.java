package com.ewell.upload.util;

/**
 * 多数据源的枚举
 */
public interface DBSourceConstant {

    String DATA_SOURCE_MASTER= "masterDataSource";        //核心数据源

    String DATA_SOURCE_CLUSTER = "clusterDataSource";            //其他业务的数据源
}
