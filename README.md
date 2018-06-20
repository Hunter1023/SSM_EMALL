# SSM_EMALL
该项目是对网易云课堂[Java开发工程师（Web方向）微专业](http://mooc.study.163.com/smartSpec/detail/85002.htm?share=1&shareId=1378872)的[Java Web开发实践](http://mooc.study.163.com/course/1000130000?tid=2001412052#/info)大作业的实现。

吐槽：课程提供的**前端代码、数据库设计文档质！量！差！与大作业要求无法匹配，需要自行修改完善**，否则就只能做出很低劣的项目。

- 根据前端代码，项目默认商品的数量为1时，才能说得通（已购买页面，显示的总消费金额只是 累加 商品的单价，无关数量）

***
## 运行环境

- Java 1.6
- Maven（maven的tomcat7插件）
- SSM
- MySQL

所用IDE：IDEA

***
## 项目搭建过程

### 1.创建数据库
首先，课件提供的**数据库设计文档有诸多槽点**：
1. 文档中**没有商品数量的相关信息**，但是前端账务页面却**需要展示商品的购买数量**
2. 内容表中price的类型是bigint，交易记录表的price类型却为int，**最后的评分标准要求商品价格可以为小数，因此默认 存储的价格单位为分**
3. 在person表的id是自增主键的情况下，**不能提供标明id的用户数据**用于插入
4. 交易记录表中的**price的含义应该为总价**

提供的数据库设计文档修改：
- person表(将username和nickname都改为小写)
```
create table person(
id int auto_increment primary key comment "主键", 
username varchar(100) comment "用户名", 
password varchar(100) comment "密码md5加密",
nickname varchar(50) comment "用户昵称",
userType tinyint(3) comment "类型，买家0，卖家1") 
ENGINE=InnoDB  DEFAULT CHARSET=utf8;
```
- content表(添加商品库存数)
```
create table content(
id int auto_increment primary key comment "主键",  
price bigint  comment "当前价格",
title varchar(100) comment "标题",
num int not null comment "库存",
icon blob comment "图片",
abstract varchar(200) comment "摘要",
text blob comment "正文"  )
ENGINE=InnoDB  DEFAULT CHARSET=utf8;
```
- trx表(修改购买价格的类型和commit；添加购买数量）
```
create table trx(
id int auto_increment primary key comment "主键",  
contentId int  comment "内容ID",
personId int comment "用户ID",
num int default null commit "购买数量",
price bigint comment "购买总价",
time bigint comment "购买时间")
ENGINE=InnoDB  DEFAULT CHARSET=utf8;
```
- 用户数据(去除id数据的传入)
```
insert into `person` (`id`, `userName`, `password`, `nickName`, `userType`) values('0','buyer','37254660e226ea65ce6f1efd54233424','buyer','0');
insert into `person` (`id`, `userName`, `password`, `nickName`, `userType`) values('1','seller','981c57a5cfb0f868e064904b8745766f','seller','1');
```

### 2.使用Maven搭建项目框架

- [使用IDEA+Maven进行Java Web开发的操作流程](https://hunter1023.github.io/2018/05/08/%E4%BD%BF%E7%94%A8IDEA-Maven%E8%BF%9B%E8%A1%8CJava-Web%E5%BC%80%E5%8F%91%E7%9A%84%E6%93%8D%E4%BD%9C%E6%B5%81%E7%A8%8B/)

- 将`src/main/webapp`文件夹替换为课程提供的`webapp.war`中的`webapp`文件夹

- 在`pom.xml`中配置项目所需依赖（也可后续补充）

### 3.根据`src/main/webapp/WEB-INF`目录下的`web.xml`配置相关xml文件

**勘误**: 
`web.xml`中以下内容重复，删除其中一个
```xml
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

- 在`src/main/resources`目录下创建Spring和mybatis的配置文件`spring-mybatis.xml`
- 在`src/main/resources`目录下创建Spring MVC的配置文件`spring-mvc.xml`


### 4.根据课程提供的文档设计Controller