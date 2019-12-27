#Springboot学习
##1 开始进入
### Idea创建项目   ###
###springboot的启动项

    @SpringBootApplication
    public class CommunityApplication {

    public static void main(String[] args) {

        SpringApplication.run(CommunityApplication.class, args);
    }

    }
- 在static里写js css 等文件  在template里写html文件 
-  在application.properties里写配置文件，例如`server.port=8087` 修改端口
-  在配置文件里写入配置参数例如`github.redirect.url=http://localhost:8087/callback`，然后class文件中调用注解
-    `@Value("${github.redirect.url}")  private  String redirectUrl;`就可以调用配置的参数
-  pom.xml 加入依赖


##2 bootstrap
### [bootstrap下载地址](https://v3.bootcss.com/getting-started/#download)
### 引入到项目的resource中 导入html文件
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/bootstrap-theme.css"/>
    <script src="js/bootstrap.min.js"  type="application/javascript"></script>

##3 github 登录
####[github登录 API使用文档](https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/)
- 1 访问、登录。
- 2 调用authorize接口到github，传入参数clientid（申请的githubapp里查看）、redirecturl（返回的地址）、scope（需要的字段）。
    
	`<a href="https://github.com/login/oauth/authorize?
	client_id=bc09130e5c7da6b7fa08&redirect_uri=http://localhost:8087/
	callback&scope=user&state=1">登录</a>`
- 3 github回调 redirect-url 携带code返回。
- 4 调用access_token接口 携带code去github。
：通过redirecturl接收到code，通过access_token传递，这里通过[OKHTTP](https://square.github.io/okhttp/)传递post请求，导入[Okhttp的jar包以及fastjson](https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp/4.2.2)。
- 5 github返回 access_token。
- 6 调用user 携带access_token，github返回user信息，调用okttp的get请求。
- 7 将信息存入数据库，更新登录状态 修改session。创建引入h2数据库
- 8 返回到用户

##4 页面的调用判断语句
###使用thymeleaf，首先在html标签中引用
`<html xmlns:th="http://www.thymeleaf.org">`
`<p th:text="#{home.welcome}">Welcome to our grocery store!</p>`
 说明：

     1. th:text  用来将内容输出到所在标签的body中。
     2.  #{home.welcome} 用来引入数据home对象中的 welcome属性。
	 3. 可以用th:utext 用来显示“unescaped ” 的html内容。


访问对象      

       ${param.x} 返回名为x 的 request参数。（可能有多个值）

       ${session.x} 返回名为x的Session参数。

       ${application.x} 返回名为 servlet context 的参数。

判断语句

	a标签只有在th:if的情况下显示  <a th:if="${myself=='yes'}" > </i> </a>
	unless与if正好相反           <a th:unless=${session.user != null} th:href="@{/login}" >Login</a>

[thymeleaf的使用文档](https://blog.csdn.net/weixin_44410921/article/details/88033090)


##5 存储Cookie

####Session中能够存取任何类型的数据，包括而不限于String、Integer、List、Map等。Session中也能够直接保管Java Bean乃至任何Java类，对象等，运用起来十分便当。能够把Session看做是一个Java容器类。

####内存：Session是保管在服务器端的，每个用户都会产生一个Session。假如并发访问的用户十分多，会产生十分多的Session，耗费大量的内存。 
#### 而Cookie保管在客户端，不占用服务器资源。假如并发阅读的用户十分多，Cookie是很好的选择。关于Google、Baidu、Sina来说，Cookie或许是唯一的选择。
	Cookie cookie = new Cookie("token",token);
	response.addCookie(cookie);


##6 初识h2数据库

####H2的主要特点是：
- 非常快速、的开源、JDBC API
- 嵌入式和服务器模式；内存数据库
- 占用空间小：大约2 MB的jar文件大小
- 可以直接内置到项目里，其他人下载就可以直接启动就可以使用数据库

####可以通过jar依赖的方式直接引入到项目中，就可以直接在idea中使用。
####快速开始的方法：



- 将添加h2*.jar到类路径（H2没有任何依赖性）
- 使用JDBC驱动程序类： org.h2.Driver(导入jar包时自动带入)
- 数据库URL 在您的用户主目录中 jdbc:h2:~/test打开数据库test
- 自动创建一个新的数据库
####依赖
    <dependency>
    	<groupId>com.h2database</groupId>
    	<artifactId>h2</artifactId>
    	<version>1.4.200</version>
    	<scope>test</scope>
    </dependency>

![h2在idea使用方法1](yzs-code.github.io/img/tuku/databases_h2.jpg)

![h2在idea使用方法2](yzs-code.github.io/img/tuku/databases_h2.jpg)


##7 springboot-mybatis整合
[mybatis-spring-boot官网](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)

####引入依赖
    <dependency>
      <groupId>org.mybatis.spring.boot</groupId>
      <artifactId>mybatis-spring-boot-starter</artifactId>
      <version>2.1.1</version>
    </dependency>
####DataSource配置引入依赖
	 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
     </dependency>
####在application.properties里写datasource的配置文件
    spring.datasource.url=jdbc:h2:~/test
    spring.datasource.username=sa
	spring.datasource.password=123456
	spring.datasource.driver-class-name=org.h2.Driver

####创建数据库即可使用，初始账号密码为'sa','123456'
####提供一个mapper
	@Mapper
    public interface UserMapper {
    @Insert("INSERT INTO USER(account_Id,name,token,gmt_Creat,gmt_Modified) VALUES " +
            "(#{accountId},#{name},#{token},#{gmtCreat},#{gmtModified})")
    void  insert(User user);
####在controller里自动注入
	@Autowired
    private UserMapper userMapper;
####调用userMapper的方法就可以使用
