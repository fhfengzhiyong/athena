package com.straw.maker.utils;

import java.io.Serializable;

public class ColumnProperties implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String simpleJavaType;
    private String columnNameLower;
    private String columnName;
    private String remarks;
    private String colType;
    private String originalColumnName;

    public String getOriginalColumnName() {
        return originalColumnName;
    }

    public void setOriginalColumnName(String originalColumnName) {
        this.originalColumnName = originalColumnName;
    }

    public String getColType() {
        return colType;
    }

    public void setColType(String colType) {
        this.colType = colType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getSimpleJavaType() {
        return simpleJavaType;
    }

    public void setSimpleJavaType(String simpleJavaType) {
        this.simpleJavaType = simpleJavaType;
    }

    public String getColumnNameLower() {
        return columnNameLower;
    }

    public void setColumnNameLower(String columnNameLower) {
        this.columnNameLower = columnNameLower;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
