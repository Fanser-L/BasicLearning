# Mysql安装使用

Mysql是隶属于MySqlAB公司，后来被Oracle公司收购（08年被SUN公司收购，09年sun公司被Oracle公司收购）

## MySql优点

- 成本低，开源性代码，一般可以免费试用
- 性能高，执行速度比较快，可移植性比较强
- 简单，容易安装和使用

## Mysql服务的开启和关闭

net start [mysql的名字]
net stop [mysql的名字]

## Mysql服务的登陆和退出

- 登陆
`mysql -h localhost -P 3306 -u root -proot`
`mysql -u root -proot` 本地端的服务器可以直接省略部分命令
  - -h host指连接服务器 后面localhost可以不接空格
  - -P Port指连接的服务器端口 默认端口是3306 端口号可以在 .ini的配置文件中查看
  - -u user指用户名
  - -p password指密码，这里注意可以直接在p后回车，这样输入密码时不显示密码，在p后接密码时不能有空格。

- 退出
`exit`

## Mysql常用命令

- 1.显示全部数据库
  - show databases; //注意每条语句后要加上;或者/g   这里犯了个很笨的错误，datebases 敲半天都是错误命令，真蠢。
- 2.打开指定的库
  - use 数据库名; //进入到某个数据库
- 3.查看当前库的表
  - show tables;
  - show tables from 数据库名;  //在其他库里查看某个数据库内的表
- 4.select database();  //查看当前所在的数据库
- 5.创建一个表
  - creat table 表名(
    列名 列类型,
    列名 列类型,
    ......
    );
- 6.查看表结构
  - desc 表名;
- 7.查看服务器版本号
  - 已登录的情况
    - select version();
  - 未登录到mysql服务端的情况
    - mysql -V
    - mysql -version

## mysql的语法规范

1. 不区分大小写，但是建议关键字大写，表名列名小写
2. 每条命令用分号结尾或者/g结尾，推荐是使用分号结尾
3. 每条命令可以根据需要进行缩进或者换行
4. 注释：
4.1 单行注释：`#注释`
4.2 单行注释：`-- 注释`
4.3 多行注释：`/* 注释 */`
