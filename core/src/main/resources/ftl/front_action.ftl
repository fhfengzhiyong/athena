<#assign classname=classNameLower(className)>
package ${package}.action;

import com.chejiyang.api.auth.UserTokenUtil;
import com.chejiyang.api.cache.RedisUtils;
import com.chejiyang.api.http.MessageInfo;
import ${package}.model.${className};
import ${package}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * ${className}  
 * @author ${author}
*/

@RestController
@RequestMapping("v1/${className}")
public class ${className}Controller {

	private static Log logger = LogFactory.getLog(${className}FrontAction.class);

	@Autowired
	${className}Service ${classname}Service;

}
