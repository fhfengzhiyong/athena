package ${package}.modules.${module}.model;

import java.io.Serializable;
<#list columns as column>
    <#if  column.simpleJavaType == 'Date'>
import java.util.Date;
        <#break>
    </#if>
</#list>

/**
 * ${className}
 *
 * @author ${author}
 */

public class ${className} implements Serializable
{
    private static final long serialVersionUID = 1L;
<#list columns as column>
    <#if  column.columnNameLower =='flag'>
     /**
     *${column.remarks}
     */
    private ${column.simpleJavaType} ${column.columnNameLower} = "N";
    <#else>
    /**
     *${column.remarks}
     */
    private ${column.simpleJavaType} ${column.columnNameLower};
    </#if>
</#list>
<#list columns as column>
    /**
     *${column.remarks}
     */
    public void set${column.columnName}(${column.simpleJavaType} ${column.columnNameLower}) 
    {
        this.${column.columnNameLower} = ${column.columnNameLower};
    }
    /**
     *${column.remarks}
     */
    public ${column.simpleJavaType} get${column.columnName}() 
    {
        return this.${column.columnNameLower};
    }
</#list>
    public void clear(){
<#list columns as column>
    ${column.columnNameLower}=null;
</#list>
    }
    @Override
    public String toString()
    {
        return "${className} [<#list columns as column>${column.columnNameLower}=" + ${column.columnNameLower} + ",</#list>]";
    }
}
