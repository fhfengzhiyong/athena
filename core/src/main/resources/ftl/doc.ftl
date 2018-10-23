<#assign classNameLower=classNameLower(className)>
 #查询
**历史修改：** 

|修改人|修改内容|修改时间|备注|
|:----    |:---|:----- |-----   |
|${author}|增加|${date}|初次增加|

**简要描述：** 

- ${classNameLower}分页

**请求URL：** 
- ` ${classNameLower}Front/findPage `
  
**请求方式：**
- POST/GET 

**参数：** 

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
|pageNum|N|int|页数(从1开始)|
<#list columns as column>
    |${column.columnNameLower!""} |N|${column.simpleJavaType!""} |${column.remarks!""}  |
</#list>
 **返回示例**

``` 
    {
        "message": "操作成功！",
        "result": true,
        "errorCode": 0,
        "object":
        [
           "page":{
            			"pageNum": 0,
            			"pageSize": 15,
            			"pageCount": 0,
           				 "recordCount": 0,
           				 "recordList":
            			 [
              					{
<#list columns as column>
					                   "${column.columnNameLower} ":xxx,
</#list>
  			    				}
            			],
           				 "startIndex": 1,
           				 "endIndex": 0
        }
     ]
   }


```

 **返回参数说明** 
 

|一级参数|二级参数|三级参数|类型|说明|
|:----- |:-----  |:-----|:-----|:-----|
|message|-|-|string |提示信息  |
 |result|-|-|boolean | 执行结果 |
 |errorCode|-|-|string |错误代码  |
 |** object**|-|-|page |业务对象  |
 |>|pageNum|-|int |第x页  |
 |>|pageSize|-|int |每页显示  |
 |>|recordCount|-|int |总页数  |
 |>|**recordList**|-|array |数据对象数组  |
<#list columns as column>
    |>|>>|${column.columnNameLower!""}|${column.simpleJavaType!""} |${column.remarks!""}  |
</#list>
 **备注** 

- 更多返回错误代码请看首页的错误代码描述
 #详细
**历史修改：** 

|修改人|修改内容|修改时间|备注|
|:----    |:---|:----- |-----   |
|fengzy|增加|${date}|初次增加|

**简要描述：** 

- ${classNameLower}详细

**请求URL：** 
- ` ${classNameLower}Front/detail `
  
**请求方式：**
- POST/GET 

**参数：** 

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
 |${pk}|Y|long|id|
 **返回示例**

``` 
    {
        "message": "操作成功！",
        "result": true,
        "errorCode": 0,
        "object":
        {
<#list columns as column>
                "${column.columnNameLower} ":xxx,
</#list>
        }
    }


```

 **返回参数说明** 
 
 |一级参数|二级参数|类型|说明|
|:----- |:-----  |:-----|:-----|:-----|
|message|-|string |提示信息  |
 |result|-|boolean | 执行结果 |
 |errorCode|-|string |错误代码  |
 |** object**|-|object |业务对象  |
<#list columns as column>
    |>|${column.columnNameLower!""}|${column.simpleJavaType!""} |${column.remarks!""}  |
</#list>
 **备注** 

- 更多返回错误代码请看首页的错误代码描述
 #增加
**历史修改：** 

|修改人|修改内容|修改时间|备注|
|:----    |:---|:----- |-----   |
|fengzy|增加|${date}|初次增加|

**简要描述：** 

- ${classNameLower}增加接口

**请求URL：** 
- ` ${classNameLower}Front/add `
  
**请求方式：**
- POST

**参数：** 

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
<#list columns as column>
    |${column.columnNameLower!""} |Y|${column.simpleJavaType!""} |${column.remarks!""}  |
</#list>
 **返回示例**

``` 
    {
        "message": "操作成功！",
        "result": true,
        "errorCode": 0,
        "object":null
    }


```

 **返回参数说明** 

|参数名|类型|说明|
|:-----  |:-----|-----                           |
|message|string|提示信息|
|result|boolean|执行结果|
|errorCode|int|错误代码|
|object|object|业务数据|
 **备注** 

- 更多返回错误代码请看首页的错误代码描述
 #修改
**历史修改：** 

|修改人|修改内容|修改时间|备注|
|:----    |:---|:----- |-----   |
|fengzy|增加|${date}|初次增加|

**简要描述：** 

- ${classNameLower}增加接口

**请求URL：** 
- ` ${classNameLower}Front/update `
  
**请求方式：**
- POST

**参数：** 

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
<#list columns as column>
    |${column.columnNameLower!""} |Y|${column.simpleJavaType!""} |${column.remarks!""}  |
</#list>
 **返回示例**

``` 
    {
        "message": "操作成功！",
        "result": true,
        "errorCode": 0,
        "object":null
    }


```

 **返回参数说明** 

|参数名|类型|说明|
|:-----  |:-----|-----                           |
|message|string|提示信息|
|result|boolean|执行结果|
|errorCode|int|错误代码|
|object|object|业务数据|
 **备注** 

- 更多返回错误代码请看首页的错误代码描述
 #删除
**历史修改：** 

|修改人|修改内容|修改时间|备注|
|:----    |:---|:----- |-----   |
|fengzy|增加|${date}|初次增加|

**简要描述：** 

- ${classNameLower}删除接口

**请求URL：** 
- ` ${classNameLower}Front/delete `
  
**请求方式：**
- GET

**参数：** 

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
 |${pk}|Y|long|id|
 **返回示例**

``` 
    {
        "message": "操作成功！",
        "result": true,
        "errorCode": 0,
        "object":null
    }


```

 **返回参数说明** 

|参数名|类型|说明|
|:-----  |:-----|-----                           |
|message|string|提示信息|
|result|boolean|执行结果|
|errorCode|int|错误代码|
|object|object|业务数据|
 **备注** 

- 更多返回错误代码请看首页的错误代码描述


#查询全部

**历史修改：**

|修改人|修改内容|修改时间|备注|
|:----    |:---|:----- |-----   |
|fengzy|增加|${date}|初次增加|

**简要描述：**

- ${classNameLower}查询全部

**请求URL：**

- ` ${classNameLower}Front/findAll `

**请求方式：**
- POST/GET

**参数：**

- 无参

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
<#list columns as column>
|${column.columnNameLower!""} |N|${column.simpleJavaType!""} |${column.remarks!""}  |
</#list>
**返回示例**
    ```
    {
    "message": "操作成功", 
    "result": true, 
    "object": {
        "page": [
            {
<#list columns as column>
              "${column.columnNameLower} ":xxx,
</#list>
            }
        ]
    }, 
    "errorCode": 0
    }

    **返回参数说明**

|一级参数|二级参数|三级参数|类型|说明|
    |:----- |:-----  |:-----|:-----|:-----|
    |message|-|-|string |提示信息  |
    |result|-|-|boolean | 执行结果 |
    |errorCode|-|-|string |错误代码  |
    |** object**|-|-|page |业务对象  |
    |>|**page**|-|array |${classNameLower}对象数组  |
<#list columns as column>
    |>|>>|${column.columnNameLower!""}|${column.simpleJavaType!""} |${column.remarks!""}  |
</#list>

**备注**

- 更多返回错误代码请看首页的错误代码描述