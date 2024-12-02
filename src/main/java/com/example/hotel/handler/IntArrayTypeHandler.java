package com.example.hotel.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.type.TypeHandler;
import java.sql.*;

public class IntArrayTypeHandler extends BaseTypeHandler<int[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, int[] parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < parameter.length; j++) {
                sb.append(parameter[j]);
                if (j < parameter.length - 1) {
                    sb.append(",");
                }
            }
            ps.setString(i, sb.toString());
        } else {
            ps.setNull(i, Types.VARCHAR);
        }
    }

    @Override
    public int[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        return str != null ? parseStringToIntArray(str) : null;
    }

    @Override
    public int[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        return str != null ? parseStringToIntArray(str) : null;
    }

    @Override
    public int[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        return str != null ? parseStringToIntArray(str) : null;
    }

    private int[] parseStringToIntArray(String str) {
        String[] parts = str.split(",");
        int[] result = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            result[i] = Integer.parseInt(parts[i]);
        }
        return result;
    }
}
