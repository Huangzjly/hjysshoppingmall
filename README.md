# 项目介绍
    本项目是一个网上商城系统，旨在提供一个完整的购物体验，包括商品浏览、用户登录、注册、购物车管理、订单处理等功能。
    
    系统采用Java Web技术栈开发，包括JSP、Servlet、JavaBeans等技术，以及MySQL数据库进行数据存储。

# 作者

黄佳怡 202230441145 22计科1班
 
# 环境依赖

Java Development Kit (JDK) 22.0.2 或更高版本
Apache Tomcat 8.x 或更高版本
MySQL 8.0.39 或更高版本
Eclipse IDE for Java EE Developers (4.32.0 或更高版本)
Navicat 或其他数据库管理工具
 
# 目录结构描述
    ├── ReadMe.md           // 帮助文档

    ├── src/main             // DDS核心文件库，包含各版本的include、src、lib文件夹，方便合并
    
       ├   hjy.control/
       ├      ├── HandleExit.java          // 处理退出操作
       ├      ├── HandleguanliExit.java    // 处理管理员退出操作
       ├      ├── HandleguanliLogin.java   // 处理管理员登录操作
       ├      ├── HandleLogin.java         // 处理用户登录操作
       ├      ├── HandleRegister.java      // 处理用户注册操作

       ├   hjy.dao/
       ├      ├── GoodsDao.java           // 商品数据访问对象
       ├      ├── Logs.java               // 系统日志记录

       ├   hjy.db/
       ├      ├── DbClose.java            // 数据库连接关闭
       ├      ├── DbConn.java             // 数据库连接

       ├   hjy.entity/
       ├      ├── Goods.java              // 商品实体类
       ├      ├── guanliLogin.java        // 管理员登录实体类
       ├      ├── Login.java              // 用户登录实体类
       ├      ├── OrderForm.java          // 订单表单实体类
       ├      ├── Register.java           // 用户注册实体类

       ├   hjy.goods/
       ├      ├── BuyGoods.java           // 购买商品操作
       ├      ├── DataByPage.java         // 分页显示数据
       ├      ├── DeleteGoodsFromCar.java  // 从购物车删除商品
       ├      ├── PutGoodsToCar.java       // 添加商品到购物车

       ├── webapp  
       ├      ├── browse/                 // 浏览商品相关页面
       ├      │   ├── lookClassify.jsp    // 查看商品分类
       ├      │   ├── searchByKeyWord.jsp // 按关键词搜索商品
       ├      │   ├── showDetail.jsp      // 显示商品详情
       ├      │   ├── showGoods.jsp       // 显示商品列表
       ├      ├── join/                   // 用户登录注册相关页面
       ├      │   ├── guanlilogin.jsp     // 管理员登录页面
       ├      │   ├── isLogin.jsp         // 检查是否登录
       ├      │   ├── landing.jsp         // 登录页面
       ├      │   ├── login.jsp           // 用户登录页面
       ├      │   ├── register.jsp        // 用户注册页面
       ├      │   ├── registerSuccess.jsp // 注册成功页面
       ├      ├── order/                  // 订单相关页面
       ├      │   ├── lookOrderForm.jsp   // 查看订单表单
       ├      └── shoppingCar/            // 购物车相关页面
       ├          ├── lookShoppingCar.jsp  // 查看购物车
       ├          ├── searchCategory.jsp  // 搜索商品分类
 
# 使用说明
 配置数据库连接：在 hjy.db.DbConn.java 中设置数据库连接参数。
 
部署项目：将项目导入Eclipse，配置Tomcat服务器，然后部署项目。

访问页面：在浏览器中输入 http://localhost:8080/hjy 访问项目首页。

测试功能：注册新用户，登录，浏览商品，添加商品到购物车，下订单等。
 
 
# 版本内容更新
###### v1.0.0: 
   1.实现了商品浏览、搜索、分类查看功能。
   
   2.实现了用户注册、登录、退出功能。
   
   3.实现了购物车管理，包括添加、删除商品。
   
   4.实现了订单处理，包括查看订单表单。
 
 
