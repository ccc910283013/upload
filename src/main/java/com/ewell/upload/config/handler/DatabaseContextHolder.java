package com.ewell.upload.config.handler;


public class DatabaseContextHolder {


    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    /**
     * @param dataSourceType 数据库类型
     * @Description: 设置数据源类型
     */
    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    /**
     * @Description: 获取数据源类型
     */
    public static String getDataSourceType() {
        return contextHolder.get();
    }

    /**
     * @Description: 清除数据源类型
     */
    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}
