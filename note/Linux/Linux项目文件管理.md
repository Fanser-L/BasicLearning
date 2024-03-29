# Linux 项目文件下的文件管理目录

## 在linux世界里，一切皆为文件

## 具体的目录文件
>
>- /bin (/usr/bin  ,/usr/local/bin)
    Binary 的缩写，一般存放一些命令
>- /sbin (/usr/sbin  ,/usr/local/sbin)
    Super Binary 的缩写，这里存放的是系统管理员使用的系统管理程序
>- /home
    存放普通用户的主目录，在linux中每个用户都有着自己的目录，一般该目录的命名是用该用户的用户的账号名命名的。
>- /root
    该目录为系统管理员，也称作超级权限者的用户主目录
>- /lib
    动态库，系统开机所需要的最基本的动态连接共享库，其作用类似于Windows中的dll文件。几乎所有文件都需要用到这些共享库。
>- /lost+found
    这个目录一般情况下是空的，在非法关机的情况下可能会多出一些文件。
>- /etc
    所有系统所需要的的配置文件和子目录。
>- /usr
    这是一个非常重要的文件夹，类似于windows中的Program Files文件夹，用户的很多文件和应用都放在这个文件夹下。
>- /boot
    存放的是linux启动时使用的一些核心文件，包括一些连接文件和镜像文件

**注意：** 以下三个文件不建议改动，都是跟系统相关的文件，如果瞎改容易造成崩溃

>- /proc
    这个目录是虚拟的目录，是系统内存的一个映射，访问这个目录来获取系统信息。
>- /srv
    service的缩写，该目录存放一些服务启动之后需要提取的数据
>- /sys
    这是linux 2.6 内核的一个很大的变化，该目录中安装了一个2.6内核中心的文件夹sysfs
>- /tmp
    这个目录是用来存储一些临时文件。
>- /dev
    类似windows的设备管理器，把所有的硬件以文件的形式存储。
>- /media
    linux系统会自动识别一些设备，例如U盘、光驱等等，当识别后，linux会将这些设备挂载到这个文件夹中
>- /mnt
    系统提供该目录是为了让用户临时挂载别的文件系统的，我们可以将外部的存储挂载在/mnt/上，然后进入目录就能查看其中的内容了。  比如说共享文件夹中的内容就存放在这个文件夹中。
>- /opt
    这是给主机额外安装软件所摆放的目录，如安装ORACLE数据库就可以放在该目录底下。默认为空。
>- /usr/local
    这是另一个给主机额外安装软件所安装的目录。一般是通过编译源码方式安装的程序。
>- /var
    这个目录中存放着不断扩充着的东西，习惯将常被修改的文件放在这个目录下。包括各种日志文件。
>- /selinux
    SElinux是一种子安全系统，他能控制程序只能访问特定文件。类似于360。
