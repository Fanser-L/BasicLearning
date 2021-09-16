# HTML文档结构

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

| 属性名 | 描述 | 属性值 |
| :-: | ---- | ----|
|class|为HTML元素定义一个或多个类名(classname)|一个或多个类名|
|id|为HTML元素定义唯一的id|唯一的id，不能重复|
|style|为HTML元素定义行内样式|多个样式键值对组成|
|title|描述了HTML元素的额外信息（作为工具条使用）|文本|

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

#### meta属性

|属性|描述|属性值|
|:-:|----|---|
|charset|HTML5新属性，定义文档的字符编码。|character_set|
|content|定义与http-equiv或name属性相关的元信息|text|
|http-equiv|将content属性关联到HTTP头部。<br>例如```<meta http-equiv="refresh" content="30">```<br>表示每30s刷新页面|
|name|将content属性关联到一个名称。<br> 例如```<meta name = "author" content = "willa">```<br> 表示content中的作者名字是willa|



