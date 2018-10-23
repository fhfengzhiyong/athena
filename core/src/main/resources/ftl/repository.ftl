package com.lcworld.robot.dao;

import com.chejiyang.api.orm.CrudRepository;
import ${package}.modules.${module}.model.${className};
import org.apache.ibatis.annotations.Mapper;
/**
* 编写访问数据库的接口
* ${className}
 * @author ${author}
*/

@Mapper
public interface ${className}Repository extends CrudRepository<${className}>
{
 
}