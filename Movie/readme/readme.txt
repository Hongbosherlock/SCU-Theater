整理人 qiaoger@126.com
2018年10月06日

特别提醒：学习了之前的ssm示例，这里开始完整的ssm示例

版本说明：
20180423  完成第一版ssm_demo、实现按编号查询用户信息、修改用户昵称；
20181006  进行整理
***************************************************************************************
ssm 学习示例

前言：
SSM （SSM 框架集）
SSM（Spring+SpringMVC+MyBatis）框架集由Spring、SpringMVC、MyBatis三个开源框架整合而成，
    常作为数据源较简单的web项目的框架。
其中spring是一个轻量级的控制反转（IoC）和面向切面（AOP）的容器框架。
SpringMVC分离了控制器、模型对象、分派器以及处理程序对象的角色，这种分离让它们更容易进行定制。
MyBatis是一个支持普通SQL查询，存储过程和高级映射的优秀持久层框架。

一、知识点：
1、mysql安装和配置（参考readme/mysqlsetup.htm）
2、mybatis 操作数据库方法
3、ssm 开发 (参考readme 目下的两篇学习笔记)
4、bootstrap.css
5、web.xml => spring/applicationContext-*.xml => springmvc.xml
   看懂这几个配置文件就能搞懂ssm的基本架构了
6、springframework  的各种注解要理解哦，@Controller、@Service、@Autowired……
7、js 、 ajax ，见user.html

二、开发环境
1、jdk1.8
2、intelliJ IDEA
3、mysql-5.6.17-winx64
4、Navicat For MySQL V8.2.1 简体中文绿色特别
5、tomcat-8.0.29

三、数据库的具体配置（使用Navicat For MySQL图形界面软件更简单）
根据resources/properties/db.properties文件 创建相应的数据库及用户
jdbc.url=jdbc:mysql://localhost:3306/testdb001?characterEncoding=utf-8
jdbc.username=test001
jdbc.password=test001password
即：
数据库名字：testdb001
用户名：test001
密  码：test001password
testdb001.sql 是创建表格  user  的sql文件；
以上操作都是可以在Navicat For MySQL中进行操作的（对初学者，推荐用该方法）。

*************************************************
以下是命令模式操作描述）
# UPDATE user SET Password = password ( 'abc123' ) WHERE User = 'root' ;

# 使用 root 账号
# 第1步 创建可远程访问的账户,localhost也必须创建
CREATE USER 'test001'@'%' IDENTIFIED BY 'test001password';
CREATE USER 'test001'@'localhost' IDENTIFIED BY 'test001password';

# 第2步 创建数据库 testdb001
CREATE DATABASE testdb001;

# 第3步 给普通账号test001 赋予数据库testdb001的权限
grant all on testdb001.* to test001@localhost identified by 'test001password' ;

# 第4步 在数据库testdb001 中创建表格user
use testdb001;
create table user
(
	Id				serial			not null,
	Account			        varchar(32)		not null unique,
	Name			        varchar(20)		not null,
	Active		         	boolean			not null default true,
	primary key (Id)
);

# 第5步 表格中插入一些记录
INSERT INTO user(id, account, name, active) VALUES (1, 'qiaoger', 'nishengqiao', true);
INSERT INTO user(id, account, name, active) VALUES (2, 'zhang', 'zhangsan', true);
INSERT INTO user(id, account, name, active) VALUES (3, 'li', 'lisi', false);
INSERT INTO user(account, name, active) VALUES ( 'wang', 'wangba', false);
