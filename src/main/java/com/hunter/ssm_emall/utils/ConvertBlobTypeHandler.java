package com.hunter.ssm_emall.utils;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.sql.*;

/**
 * 实现Blob类型和String的转化
 */
public class ConvertBlobTypeHandler extends BaseTypeHandler<String> {
    public static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        byte[] bytes = parameter.getBytes(UTF8);
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ps.setBinaryStream(i, bis, bytes.length);
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Blob blob = rs.getBlob(columnName);
        String returnValue = null;
        if (null != blob) {
            returnValue = new String(blob.getBytes(1, (int) blob.length()), UTF8);
        }
        return returnValue;
    }


    @Override
    public String getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        Blob blob = rs.getBlob(columnIndex);
        String returnValue = null;
        if (null != blob) {
            returnValue = new String(blob.getBytes(1, (int) blob.length()), UTF8);
        }
        return returnValue;
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        Blob blob = cs.getBlob(columnIndex);
        String returnValue = null;
        if (null != blob) {
            returnValue = new String(blob.getBytes(1, (int) blob.length()), UTF8);
        }
        return returnValue;
    }
}
