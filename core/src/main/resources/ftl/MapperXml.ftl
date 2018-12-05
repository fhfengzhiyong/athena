<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "htpp://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${package}.modules.${module}.repository.${className}Repository">
    <resultMap id="BaseResultMap" type="${package}.modules.${module}.model.${className}">
      <#list columns as column>
      <#if  column.columnName==primaryKey>
      <id column="${column.originalColumnName}" property="${column.columnNameLower}" jdbcType="${column.colType}"/>
      <#else>
      <result column="${column.originalColumnName}" property="${column.columnNameLower}" jdbcType="${column.colType}"/>
      </#if>
      </#list>
    </resultMap>

    <insert id="insertSelective" parameterType="${package}.modules.${module}.model.${className}">
        insert into ${table}
        <trim prefix="(" suffix=")" suffixOverrides=",">
      <#list columns as column>
          <if test="${column.columnNameLower} !=null and ${column.columnNameLower} !=''">${column.originalColumnName},</if>
      </#list>
        </trim>
        <trim prefix=" values (" suffix=")" suffixOverrides=",">
      <#list columns as column>
          <if test="${column.columnNameLower} !=null and ${column.columnNameLower} !=''">${jh}{${column.columnNameLower},jdbcType=${column.colType}}, </if>
      </#list>
        </trim>
    </insert>

    <insert id="insert" parameterType="${package}.modules.${module}.model.${className}">
        insert into ${table}  <trim prefix="(" suffix=")" suffixOverrides=","> <#list columns as column>${column.originalColumnName},</#list></trim>
        <trim prefix=" values (" suffix=")" suffixOverrides=","><#list columns as column>${jh}{${column.columnNameLower},jdbcType=${column.colType}},</#list></trim>
    </insert>

    <update id="updateByPrimaryKeySelective" parameterType="${package}.modules.${module}.model.${className}">
        update ${table}
        <set>
       <#list columns as column>
           <if test="${column.columnNameLower} !=null and ${column.columnNameLower} !=''">
           ${column.originalColumnName} = ${jh}{${column.columnNameLower},jdbcType=${column.colType}},
           </if>
       </#list>
        </set>
        where ${primaryKey} = ${jh}{${primaryKey},jdbcType=VARCHAR}
    </update>

    <update id="updateByPrimaryKey" parameterType="${package}.modules.${module}.model.${className}">
        update ${table}
        <set>
       <#list columns as column>
           ${column.originalColumnName} = ${jh}{${column.columnNameLower},jdbcType=${column.colType}},
       </#list>
            where ${primaryKey} = ${jh}{${primaryKey},jdbcType=VARCHAR}
        </set>

    </update>

    <select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="java.lang.String">
        select * from ${table} where ${primaryKey} = ${jh}{${primaryKey},jdbcType=VARCHAR}
    </select>

    <delete id="deleteByPrimaryKey" parameterType="java.lang.String">
        delete from ${table} where  ${primaryKey} =  ${jh}{${primaryKey},jdbcType=VARCHAR}
    </delete>


</mapper>
