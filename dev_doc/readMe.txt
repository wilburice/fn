一、开发环境
a)GitHub客户端
b)MyEclipse 2013
c)MySQL 5.1.37-community 数据库
4)Apache Maven 3.2.1

二、环境安装
2.1.下载源码
https://github.com/springside/springside4.git
在springside根目录下下载fn项目：
https://github.com/wilburice/fn.git

2.2 执行SpringSide4启动程序，下载依赖
./quick-start.bat

2.3 导入数据库
./fn/bin/refresh-db.bat

2.4 试启动服务器
./fn/bin/jetty.bat

2.5 创建Eclipse工程
到fn目录
mvn eclipse:eclipse

2.6 在MyEclipse中调试
在MyEclipse中导入fn项目；
修改 src/test/java 目录下的 fn.QuickStartServer.java 中的端口号；
debug这个文件。

