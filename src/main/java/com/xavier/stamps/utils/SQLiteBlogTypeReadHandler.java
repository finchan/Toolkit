package com.xavier.stamps.utils;

import org.apache.ibatis.type.BlobTypeHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteBlogTypeReadHandler extends BlobTypeHandler {
    @Override
    public byte[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        byte[] returnValue = rs.getBytes(columnName);
        return returnValue;
    }
}
