## DOM相关知识

document object model 文档对象模型，是处理可拓展标志语言的标准编程接口，他可以以一种独立于平台和语言的方式访问和修改一个文档的内容和结构。

1.DOM（Document Object Model）：文档对象模型

文档：HTML文档
文档对象：页面中的元素
文档对象模型：对象被组织在一个树形结构中，用来表示文档中对象的标准模型就叫DOM，目的为了让程序（js）去操作元素

DOM树结构：
![DOM树结构](https://img-blog.csdnimg.cn/20190803152814283.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxOTkzNTAz,size_16,color_FFFFFF,t_70)
DOM分为两种：元素树和节点树

- 元素树：把文档中所有的标签看成是一棵树
- 节点树：把文档中所有的内容看成是一棵树

节点的访问：
在DOM中，HTML文档中的各个节点被视为各种类型的Node对象。如果想要通过某个节点的子节点来找到该元素，语法如下：
``` 父节点对象  = 子节点对象.parentNode```
|属性       |类型   |描述                                |
|:-:       |----    |--                                 |
|parentNode|Node    |返回节点的父节点，没有父节点时为null  |
|childNode|NodeList|返回节点到子节点的节点列表（注意是列表）|
|firstChild|Node    |返回首个子节点，没有则为null         |
|lastChild|Node     |返回节点的最后一个子节点，没有则为null|

获取文档中的指定元素
1. 通过元素的id属性获取元素
document的getElementById（"userid"）
2. 通过元素的name属性获取元素
document的getElementByName("username")[0];
由于多个元素可能有相同的name，所以方法返回的值为一个数组，而不是元素


## JavaScript

JavaScript是Web中一种功能强大的脚本语言，常用来为网页添加各种各样的动态功能

JavaScript的引入：

1. 内嵌式
`<script type ="text/javascript"> document.write("hello")</script>`
2. 外链式
`<script type ="text/javascript" src="JS文件路径"></script>`

数据类型：

|类型     |含义      |说明                                                 |
|:-:      |----     |---                                                  |
|Number   |数值型    |数值型数据不区分整型和浮点型，数值型数据不需要用引号括起来|
|String   |字符串类型|字符串是用单个引号或者多个引号括起来的一个或多个字符      |
|Boolean  |布尔类型  |true/false                                           |
|Object   |对象类型  |一组数据的功能和键值对集合                             |
|Null     |空类型    |没有任何值                                            |
|Undefined|未定义类型|指变量被创建，但未赋值时具有的值                        |

事件处理

发生时间->启动事件处理程序->对事件处理程序做出反应

onclick 点击当前元素时触发事件
onblur  当前元素失去焦点时触发事件
onchange当前元素失去焦点且发生变化时触发事件
onfocus 当前元素获得焦点时触发此事件
onreset 当表单被重置时触发此事件
onsubmit当表单被提交时触发此事件
onload  当页面加载完成时触发此事件

常用对象：

 1. windows对象

 | 属性、方法|说明|
 |  :-:   |---|
 |document、history、location、navigator、screen|返回相应对象的引用。|
 |parent、self、top|分别返回父窗口、当前窗口、最顶层窗口的对象引用|
 |innerWidth、innerHeight|分别返回窗口文档显示区域的宽度和高度|
 |outerWidth、outerHeight|分别返回窗口的外部宽度和高度|
 |open()、close()|打开或者关闭浏览器窗口|、
 |alert()、confirm()、prompt|分别弹出警告框、确认框、用户输入框|
 |setTimeout()、clearTimeout()|设置或清除普通定时器|

 2. Data对象
 3. String对象


