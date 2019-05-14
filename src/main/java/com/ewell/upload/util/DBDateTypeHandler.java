package com.ewell.upload.util;


import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;

/**
 * CLOB类型转换工具
 */
@MappedJdbcTypes({JdbcType.DATE})
public class DBDateTypeHandler implements TypeHandler<Object> {
    public  Object valueOf(String param){
        return null;
    }
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        Date date = new Date((long)o);
        preparedStatement.setDate(i,date);
    }
    @Override
    public Object getResult(ResultSet resultSet, String s) throws SQLException {
        Date date = resultSet.getDate(s);
        return (date == null) ? null : DateUtil.timeStampToDate(date.getTime());
    }
    @Override
    public Object getResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
    @Override
    public Object getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
