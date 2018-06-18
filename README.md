# SSM_EMALL
该项目是对网易云课堂[Java开发工程师（Web方向）微专业](http://mooc.study.163.com/smartSpec/detail/85002.htm?share=1&shareId=1378872)的[Java Web开发实践](http://mooc.study.163.com/course/1000130000?tid=2001412052#/info)大作业的实现。

***
## 运行环境

- Java 1.6
- Maven（maven的tomcat7插件）
- SSM
- MySQL
- Bootstrap
- jQuery
- 
所用IDE：IDEA

***
## 项目搭建过程

### 1.使用Maven搭建项目框架

- [使用IDEA+Maven进行Java Web开发的操作流程](https://hunter1023.github.io/2018/05/08/%E4%BD%BF%E7%94%A8IDEA-Maven%E8%BF%9B%E8%A1%8CJava-Web%E5%BC%80%E5%8F%91%E7%9A%84%E6%93%8D%E4%BD%9C%E6%B5%81%E7%A8%8B/)

- 将`src/main/webapp`文件夹替换为课程提供的`webapp.war`中的`webapp`文件夹

- 在`pom.xml`中配置项目所需依赖（也可后续补充）

### 2.根据`src/main/webapp/WEB-INF`目录下的`web.xml`配置相关xml文件

**勘误**: 
`web.xml`中以下内容重复，删除其中一个
```xml
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

- 在`src/main/resources`目录下创建Spring和mybatis的配置文件`spring-mybatis.xml`
- 在`src/main/resources`目录下创建Spring MVC的配置文件`spring-mvc.xml`
