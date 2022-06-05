# Linux常用命令

> 1.1命令格式与目录处理命令
> 1.2文件处理命令

**命令格式**
命令格式：命令[-选项][参数]
例：ls -la /etc
说明：
1、个别命令使用不遵循此格式
2、当有多个选项时，可以写在一起
3、简化选项与完整选项 如：-a等于--all

## 1.1命令格式与目录处理命令

### 目录处理命令：ls    //list

|ls|use|
|:-:|--|
|ls -a：| all 显示目录下全部文件|
|ls -l：| long 长格式显示|
|ls -lh：| 人性化选项，把文件大小的字节显示成人性化的M而不是字节|
|ls -ld：|direct 显示当前目录下的信息，而不看目录以下的信息|
例：
-rw-------. 1 root root 2.8K Jun 17 02:00 anaconda-ks.cfg|
> -rw-r--r--

- 文件类型和权限

### 目录处理命令：dir

mkdir：创建目录
与windows不同的地方在于可以一次性创建多个文件
mkdir /tmp/ljr/a /tmp/ljr/b
类似这种操作

### 目录处理命令：cd

与window大体一致，但是不同点在于cd后一个空格是必须的，linux中不允许省略这一个空格。

### 目录处理命令：rmdir

remove 删除目录
只能删除一个空目录，要删除目录需要先得把底下的内容全删除

### 目录处理命令：cp

copy

``` txt
cp -rp [源文件或目录][目标目录]
   -r 复制目录
   -p 保留文件属性
```

-p 可以保存文件的更改时间。（保存属性）

### 目录处理命令：mv

剪切
mv /tmp/ljr/a  /root
mv /tmp/ljr/a  /root/c  剪切并改名

### 目录处理命令：rm

rm-rf [文件或目录]
-r 删除目录
-f 强制执行

rm-rf/* 删库跑路？

linux中是没有回收站这个概念的，误删除后就不能找回了。 再次提及备份文件的重要性

## 1.2文件处理命令

vi  命令

i 插入

o 操作

：q 退出

:wq 保存修改退出

:q! 退出不修改

![image-20220518234129773](https://s2.loli.net/2022/05/18/EJF5AagLCTSbIUd.png)

![image-20220519123350245](https://s2.loli.net/2022/05/19/iceYJNdqLwXZrbx.png)

![image-20220519130034972](https://s2.loli.net/2022/05/19/l8muMdOx1KTcoBy.png)

![image-20220529194544617](https://s2.loli.net/2022/05/29/leCcwtiMxhSGL9Z.png)

![image-20220519164450783](https://s2.loli.net/2022/05/19/UvMkBGKNl7mxAsn.png)

![image-20220519164651036](https://s2.loli.net/2022/05/19/t8SrPJyimMfcHvA.png)

![image-20220519165601709](https://s2.loli.net/2022/05/19/16vWDZahNedgyrm.png)

## 1.3用户管理命令

查询用户：

```bash
id： 【user】
```

用户组的分配：

![image-20220529173759306](https://s2.loli.net/2022/05/29/LhFDQPsvj9g7HmY.png)

## 1.4运行级别管理命令

![image-20220529185755402](https://s2.loli.net/2022/05/29/IZU7dNLr9nAyfqR.png)

![image-20220529190153946](https://s2.loli.net/2022/05/29/tHZV3W2EAdsgj98.png)

## 1.5帮助指令

![image-20220529190601854](https://s2.loli.net/2022/05/29/gIVakR5ufOYZtvL.png)

## 防火墙控制

查看防火墙状态

systemctl status firewalld

开启/关闭防火墙

systemctl start/stop firewalld



