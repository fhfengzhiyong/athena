package com.straw.maker.run;

import java.util.List;

/**
 * @author straw(fengzy)
 * @description 配置
 * @date 2018/10/9
 */
public class BaseConfig implements Cloneable{
    List<String> listTables;
    /**
     * 扩展名称
     */
    private String suffix;
    /**
     * 实体类
     */
    private String entityPath;

    public String getSuffix() {
        return suffix;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getEntityPath() {
        return entityPath;
    }

    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }

    public List<String> getListTables() {
        return listTables;
    }

    public void setListTables(List<String> listTables) {
        this.listTables = listTables;
    }

}
