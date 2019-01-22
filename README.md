练习任务

# 1      定义

Web端UI系统、微信公众号、微信小程序、Android手机端，在本文中统一称为客户端(Client)。

# 2      任务描述

实现一套简单的客户端+接口平台程序。客户端开发组的同学只完成客户端任务部分，接口平台组的同学只完成接口平台任务部分即可。

最终的效果为同学A开发的接口平台可以为多位同学开发的客户端提供服务。同学B开发的客户端可以连接到不同的接口平台。

# 3      任务目的

理解客户端与接口平台的工作方式。

学习项目中需要的各项技术。

# 4      场景描述

![img](file:///C:/Users/Warren/AppData/Local/Temp/msohtmlclip1/01/clip_image002.png)

 

# 5      接口平台组任务

## 5.1     建立数据库

使用MS SqlServer数据库。版本：2008 R2或以上。

![img](file:///C:/Users/Warren/AppData/Local/Temp/msohtmlclip1/01/clip_image004.png)

![img](file:///C:/Users/Warren/AppData/Local/Temp/msohtmlclip1/01/clip_image006.png)

这2个表手工填写一些测试数据即可。

## 5.2     开发接口

### 5.2.1  获取访问令牌接口

GET /access_token

**参数说明**

| **参数**      | **数据类型** | **说明**                                                     |
| ------------- | ------------ | ------------------------------------------------------------ |
| client_id     | string       | 客户端标识。*注意这里说的client_id对应数据库master_client表的client_open_id字段，并不是主键 id。因为当客户端的信息被泄露后，未经授权的第三方程序可能会冒用client_id向接口平台发送请求。* |
| client_secret | string       | 客户端密匙。                                                 |

 

**为了简单，当接口内部发生错误或验证不通过时，统一返回 HTTP 400**

**调用成功返回以下格式的JSON**

{"access_token":"XXX","expires_in":7200}

| **参数**     | **数据类型** | **说明**             |
| ------------ | ------------ | -------------------- |
| access_token | string       | 客户端的访问令牌。   |
| expires_in   | int          | 令牌过期时间。单位秒 |

 

**实现逻辑**

1)       获取到HTTP请求的参数*client_id, client_secret*

2)       查询数据库表master_client，条件为：client_open_id = *client_id* and client_secret= *client_secret*

3)       如果能查询到结果，则从配置文件中获取配置的过期时间，然后生成一个GUID做为access_token，把这个access_token存到缓存，并设定该缓存的过期时间。最后将access_token与过期时间组装成JSON返回给客户端。

### 5.2.2  获取用户信息接口

GET /user

**参数说明**

| **参数**     | **数据类型** | **说明**   |
| ------------ | ------------ | ---------- |
| id           | string       | 用户主键ID |
| access_token | String       | 访问令牌   |

 

**为了简单，当接口内部发生错误或验证不通过时，统一返回 HTTP 400，注意：当在验证access_token失败（缓存中不存在）时，返回 HTTP 401，以方便客户端重新发送获取令牌的请求。**

**调用成功返回以下格式的JSON**

{"id":"XXX",”display_name”:”XXX”}

| **参数**     | **数据类型** | **说明**     |
| ------------ | ------------ | ------------ |
| id           | string       | 用户主键ID   |
| display_name | string       | 用户显示名称 |

 

**实现逻辑**

1)       获取到HTTP请求的参数*id, access_token*

2)       如果缓存中存在access_token则标识请求有效。

3)       查询数据库表master_user, 条件 id=*id*

4)       组装JSON，返回。

## 5.3     用户登录授权页面

1)       当客户端检测到用户未登录后，会重定向到该页面，并且会在URI中附带redirect_uri

参数。

2)       用户在该页面输入用户名，密码，点登录按钮。

3)       系统检查用户名密码正确后再重定向到redirect_uri参数的地址，并且附带参数code（为了简单，这里code的值就传递master_user表的id）

如：<https://www.client.com/home?code=>1 

4)       客户端页面接收到code参数后，会调用 /user 接口获取用户信息。（*这一步是客户端的事情）*

# 6      客户端组任务

## 6.1     AdminLTE模板

![img](file:///C:/Users/Warren/AppData/Local/Temp/msohtmlclip1/01/clip_image008.jpg)

<https://adminlte.io/>

1)       将这个HTML静态模板改为java web（只改造index2.html页面即可）

2)       当用户访问时检查access_token是否有效，当前用户是否登录。

3)       access_token失效时，发送HTTP GET请求，调用 /access_token接口获取令牌。

格式：/access_token?client_id=xxx&client_secret=xxx

client_id和client_secret写在配置文件中。

获取到access_token后将其存入缓存并设置过期时间。

4)       用户未登录时，重定向到预定义的授权登录页面（地址写在配置文件中，该页面由接口平台提供），注意要附带redirect_uri参数，参数值为当前页面的uri

5)       在授权登录页面填写用户名，密码后，如果验证通过会重定向回redirect_uri的地址，并且会附带code参数。

6)       调用 /user接口，附带access_token和code参数获取用户信息

7)       将接口返回值的display_name值显示在模板右上角的用户名处。（已用红笔圈出）

8)       将左侧的导航菜单改为加载动态数据。（可以将菜单数据写在JSON文件中）

# 7      调试

接口平台组的任务可以借助浏览器，Postman等进行调试。

客户端组的任务需要另外写一个辅助的web程序来模拟接口平台进行HTTP响应。