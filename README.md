##项目说明

###1、简介

项目主要功能是开发一个团购网站的后天系统。由于项目时间比较紧，整体设计到开发时间不到6天，因此代码还未进行重构。


###2、部署说明

######1）数据库采用的是Mysql数据库，相关表结构以及填充数据已经放在了/sql文件夹下。

######2）上传文件采用的七牛，相关配置文件请提前放置在/resources下，名称为qiniu.properties。具体配置说明
	
AK=七牛提供的AK
	
SK=七牛提供的SK
	
bucket=文件所在的存储空间名称

######3）数据库连接文件相关配置文件请提前放置在/resources下，名称为db.properties。具体配置说明

mysqldb.url=数据库连接URL

mysqldb.user=用户名

mysqldb.password=密码
	
伍佳艺

StevenWu

bestwujiayi@gmail.com
