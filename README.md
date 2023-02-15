# 数据库
mysql8.0:
1.自行安装好mysql8.0，用户名密码根据情况更改application.yml文件
2.建库create database huaxiaoyu charset=utf8mb4;
3.执行huaxiaoyu分支下sql：source huaxioayu.sql

redis:
1.安装redis5.0
2.开启服务

# 后端服务
1.maven打包后 java -jar方式运行即可
2.两个服务，hauxioayu 和 crushingsystem均需单独以方式1开启服务
