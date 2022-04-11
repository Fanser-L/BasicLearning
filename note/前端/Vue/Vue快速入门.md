# Vue快速入门

## 简介

1. JavaScript框架
2. 简化Dom操作
3. 响应式的数据驱动

## 第一个Vue程序

官网：https://cn.vuejs.org/

使用vscode开发，首先先安装liveserver插件

![image-20220401181236641](https://s2.loli.net/2022/04/01/SPVJNAWpbdkuocZ.png)

这个插件的作用就是，所见即所得，保存前端页面后，在浏览器那边会自动进行一个刷新的操作，总之就是展示页面效果上会方便很多。

el: 挂载点

思考：

1. Vue实例的作用范围是什么
    Vue会管理el选项命中的元素以及器内部的后代元素
2. 是否可以使用其他的选择器
    基本上的所有选择器都可以使用，用于获取不同的元素，但是还是建议使用id选择器，因为id选择器是唯一的。
3. 是否可以设置其他的dom元素
    可以使用双标签，不能使用HTML和BODY标签

data：数据对象

- Vue中使用的数据定义在data中
- data中可以书写复杂类型的数据
- 绚烂复杂数据类型，遵守js的语法即可

# Vue的本地应用

## 1、内容绑定，事件绑定 

- 通过Vue实现常见的网页效果
- 学习Vue指令，以案例巩固知识点

- v-text指令：
  - v-text指令的作用是：设置标签的内容（textcontent）
  - 默认写法会替换全部内容，使用**差值表达式{{}}**可以替换指定内容

- v-html指令：
  - v-html指令作用是将元素设置innerHTML
  - 实际上与上面的text指令相比，也就是html和text之间的差别，他能够读取一些html标签

- v-on指令：
  - 指令的作用就是：为元素绑定事件
  - 事件名不需要写on
  - 指令可以简写为@
  - 绑定的方法定义在methods属性中

## 2、显示切换，属性绑定

- v-show指令:
  - v-show指令的作用是：根据表达式的真假状态切换元素的显示状态
  - 本质是对display的操作来切换显示状态

- v-if指令：
  - v-if指令的作用是：根据表达式的真假状态来切换元素的显示状态
  - 本质是通过操纵dom元素来切换显示状态
  - 表达式的值为true，元素存在于dom树中，值为false，从dom树中移除

- v-bind指令：
  - v-bind指令的作用是为元素绑定属性
  - 完整写法是v-bind：属性名
  - 简写的话可以直接省略v-bind，只保留 `：属性名`

----
有以上知识就能实现一个简单的轮播图了！



## 3、列表循环，表单元素绑定

以上几个指令就可以实现轮播图了！

- v-for指令：
  - v-for指令的作用是：根据数据生成列表结构
  - 数组经常和v-for结合使用
  - 语法是`（item，index） in 数据`
  - item好idnex可以结合其他指令一起使用
  - 数组长度的更新会同步到页面上，是响应式的

- v-on指令补充：
  - 事件绑定的方法写成函数调用的形式，可以自定义传入参数
  - 定义方法是需要定义形参来接收传入的实参
  - 事件的后面跟上`.修饰符`可以对事件进行限制
  - `.enter`可以限制触发的按键为回车

- v-model指令：
  - v-model指令的作用是便捷的设置和获取表单元素的值
  - 绑定的数据会和表单元素的值相关联
  - 绑定的数据与表单元素的值是双向更新的

---

结合这么多指令，可以完成一个网页记事本的操作

# 网络应用：axios基本使用

基本使用：

文档地址：http://axios-js.com/zh-cn/docs/

## 案例

执行 `GET` 请求

```
// 为给定 ID 的 user 创建请求
axios.get('/user?ID=12345')
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });

// 上面的请求也可以这样做
axios.get('/user', {
    params: {
      ID: 12345
    }
  })
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });
```

执行 `POST` 请求

```
axios.post('/user', {
    firstName: 'Fred',
    lastName: 'Flintstone'
  })
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });
```

执行多个并发请求

```
function getUserAccount() {
  return axios.get('/user/12345');
}

function getUserPermissions() {
  return axios.get('/user/12345/permissions');
}

axios.all([getUserAccount(), getUserPermissions()])
  .then(axios.spread(function (acct, perms) {
    // 两个请求现在都执行完成
  }));
```

## axios API

可以通过向 `axios` 传递相关配置来创建请求

##### axios(config)

```
// 发送 POST 请求
axios({
  method: 'post',
  url: '/user/12345',
  data: {
    firstName: 'Fred',
    lastName: 'Flintstone'
  }
});
// 获取远端图片
axios({
  method:'get',
  url:'http://bit.ly/2mTM3nY',
  responseType:'stream'
})
  .then(function(response) {
  response.data.pipe(fs.createWriteStream('ada_lovelace.jpg'))
});
```

##### axios(url[, config])

```
// 发送 GET 请求（默认的方法）
axios('/user/12345');
```

