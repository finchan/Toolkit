package com.xavier.pandora.fastjson.data;

import java.util.List;
import java.util.Map;

public class Result {
    private String id;
    private String key;
    private String tableName;
    private String className;
    private Map<String, List<Column>> column ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Map<String, List<Column>> getColumn() {
        return column;
    }

    public void setColumn(Map<String, List<Column>> column) {
        this.column = column;
    }
}
