### HTML

- 1.概念：最基础的网络开发语言
     * Haper Text Markup Language   超文本标记语言
        * 超文本
        * 标记语言
        * 标记语言不是编程语言（没有逻辑上的结构）
- 2.快速入门：
  - 语法：
    * 文档后缀名为 .html 或者 .htm
  - 标签：
    * 围堵标签：如`<html></html>`，有开始和结尾标签
    * 自闭和标签：开始和结束标签在一起 如`<br/>`
    * 标签可以嵌套，但是要求合法（具体怎么合法  稍微用脑子想想也知道）
    * 在开始标签中可以定义属性。属性是由键值构成的，值需要用引号引起来。
    * html 的标签不区分大小写，但是建议使用小写
- 3.标签：

    * 文件标签：构成 html 的最基本的标签
        `<html>` 标签：html 的根标签
        `<head>` 标签：头标签，用于指定html 文档的一些属性。引入外部的资源
        `<title>` 标签：标题标签，用于显示网页名
        `<body>` 标签：主体标签，书写的内容都在body标签内
    * 文字标签：和文本有关的标签`<p></p>`
      * 文本样式标记`<font 属性="属性值">文本内容</font>` 
    * 图片标签：和图片有关的标签`<img src="图像URL"/>`
    * 表格标签：和表格相关的标签
        ```
        <table>
          <tr>
            <td>单元格内的文字</td>
          </tr>
        </table>
        ```
       * colspan rowspan 跨列跨行 属于th hd特有的属性。
       * 一些属性：
          *  width 宽度
          *  height 高度
          *  background 背景 可以设置纯色也可以引入图片
          *  align 标签属性
          *  cellspacing 设定单元格之间的间距
          *  cellpadding 单元格边缘与单元格边缘之间的间距
          *  bgcolor 表格颜色
       * 表单控件`<input type= "控件类型">` 具体控件有：
         1. 文本输入控件text
         2. 密码输入控件password
         3. 单选输入控件radio 无法输入值，所以得先设置好value
         4. 复选框控件checkbox 一般也需要设置value
         5. 文件上传控件file
         6. 提交按钮控件submit
         7. 重置按钮控件reset
      * textarea 多行文本框`<textarea></textarea>`
    * 列表标签：和列表有关的标签`<li>` 无序列表
        ```
          <ul>
            <li>列表项1</li>
            <li>列表项2</li>
          </ul>
        ```
      * 常用type属性来指定项目符号
        * disc  显示效果分别为实心圆
        * square  实心方框
        * circle  圆
    * a标签 超链接标记`<a href="跳转目标" target="目标窗口的弹出方式"></a>`
      * target属性值
        * _self 默认值，表示在原窗口打开
        * _blank 在新窗口打开
        * _parent 在父框架集中打开被链接文档
        * _top 在整个窗口中打开被链接文档
    * div标记 盒子容器


## 1.1基本的文档结构

最简单是html文档结构如下

``` html
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>

</body>
</html>
```

&nbsp; 样例：aboutUs页面

``` html

<!DOCTYPE html>
<html>
<head>
 <title>关于我们</title>
</head>
<body bgcolor="blue" background="img/bg.jpg">

 <!-- 以下是siteNav部分 -->
 <div id="siteNav">siteNav部分</div>
 <!-- 以下是页头部分 -->
 <div id="header" style="background: #ccc;">页头部分</div>
 <!-- 以下是content内容部分代码 -->
 <div id="content" style="background: #fff;"></div>
 <!-- 以下是footer页脚部分的代码 -->
 <div id="footer" style="background: #ccc;">页脚部分</div>

</body>
</html>

```

#### 核心属性

| 属性名| 描述                                | 属性值        |
| :---:| ----------------------------------- | -------------|
|class|为HTML元素定义一个或多个类名(classname)  |一个或多个类名|
|id   |为HTML元素定义唯一的id                  |唯一的id，不能重复|
|style|为HTML元素定义行内样式                  |多个样式键值对组成|
|title|描述了HTML元素的额外信息（作为工具条使用）|文本             |

#### 重要属性

|属性|描述|属性值|
|:-:|----|---|
|accesskey|设置访问HTML元素的键盘快捷键|字符|
|tabindex|设置可交互的HTML元素的Tab键控制次序|数字|
|dir|为HTML元素定义行内样式|多个键值对组成|
|lang|规定元素内容的语言|language_code<br/>例如：```<html lang="en">```中en表示英文|
|contenteditable|指定元素内容是否可编译|true(可拖动)、false(不可拖动)、auto(依照浏览器默认情况)|
|data-*|存储私有页面后应用的自定义数据，可用于所有HTML元素|任何字符串|
|draggable|规定HTML元素是否可拖动|指定元素内容是否可拖动|true(可拖动)、false(不可拖动)、auto(依照浏览器默认情况)|
|hidden|hidden属性规定对HTML元素进行隐藏。|例如<br/>```<p hidden>隐藏该段落</p>```
|spellcheck|specheck属性规定是否对元素内容进行拼写检查|ture、false|

#### meta属性从后备队列中，按照一定的算法选择选出

|属性|描述|属性值|
|:-:|----|---|
|charset|HTML5新属性，定义文档的字符编码。|character_set|
|content|定义与http-equiv或name属性相关的元信息|text|
|http-equiv|将content属性关联到HTTP头部。<br>例如```<meta http-equiv="refresh" content="30">```<br>表示每30s刷新页面|
|name|将content属性关联到一个名称。<br> 例如```<meta name = "author" content = "willa">```<br> 表示content中的作者名字是willa|




