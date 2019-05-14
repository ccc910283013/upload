package com.ewell.upload.util;


import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;

/**
 * CLOB类型转换工具
 */
@MappedJdbcTypes({JdbcType.CLOB})
public class DBClobTypeHandler implements TypeHandler<Object> {
    public  Object valueOf(String param){
        return null;
    }
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        Clob clob = preparedStatement.getConnection().createClob();
        clob.setString(1,(String) o);
        preparedStatement.setClob(i,clob);
    }
    @Override
    public Object getResult(ResultSet resultSet, String s) throws SQLException {
        Clob clob = resultSet.getClob(s);
        return (clob == null || clob.length() == 0) ? null : clob.getSubString((long) 1, (int) clob.length());
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
