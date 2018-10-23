<#assign classname=classNameLower(className)>
package ${package}.service.impl;

import org.springframework.stereotype.Service;
import ${package}.modules.brevity.model.BrevityArticle;
import com.chejiyang.api.orm.CrudServiceImpl;
import ${package}.modules.${module}.repository.BrevityArticleRepository;
import ${package}.modules.${module}.service.BrevityArticleService;

/**
 * ${className}  
 *  @author ${author}
*/
@Service(value="${classname}Service")
public class ${className}ServiceImpl  extends CrudServiceImpl<${className}, ${className}Repository> implements ${className}Service{

}
