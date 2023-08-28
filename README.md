# 优购商城
<div align='center'>
    <img src='https://gitee.com/chi-congmin/yougou-mall/raw/master/readme/img/yougou.png' alt='logo'>
</div>

## 项目简介

毕业设计，一个基于 SpringCloud Alibaba、Nacos、Seata、MyBatis-Plus、RabbitMQ、Elasticsearch、MySql、Redis、Minio  的分布式微服务商城系统，此外还引用了阿里云 SMS 短信服务，以及支付宝支付, 系统包括了商城前端和后台管理平台，商城前端包含了完整的购物流程，后台管理平台包括了商品管理、订单管理、用户管理、轮播图管理、活动管理、优惠券管理、规格管理。

## 目录结构

```
yougou-mall
├─yougou-mall-auth --认证授权服务
├─yougou-mall-common --公共模块
│  ├─yougou-mall-common-basic --基础模块
│  ├─yougou-mall-common-cache --缓存模块
│  ├─yougou-mall-common-database --数据库模块
│  ├─yougou-mall-common-rabbitmq --RabbitMQ模块
│  └─yougou-mall-common-security --公共安全模块
├─yougou-mall-extra --扩展服务（包含短信、验证码、文件资源等服务）
├─yougou-mall-feign --内部接口
│  ├─yougou-mall-feign-auth --认证授权服务内部接口
│  ├─yougou-mall-feign-extra --扩展服务内部接口
│  ├─yougou-mall-feign-order --订单服务内部接口
│  ├─yougou-mall-feign-payment --支付服务内部接口
│  ├─yougou-mall-feign-product --商品服务内部接口
│  └─yougou-mall-feign-user --用户服务内部接口
├─yougou-mall-gateway --网关
├─yougou-mall-order --订单服务
├─yougou-mall-payment --支付服务
├─yougou-mall-platform --平台服务
├─yougou-mall-product --商品服务
├─yougou-mall-search --搜索服务
└─yougou-mall-user --用户服务
```

## 技术选型

|        技术         |      说明       |  版本号  |
| :-----------------: | :-------------: | :------: |
|     SpringCloud     |   微服务框架    | 2020.0.0 |
| SpringCloud Alibaba |   微服务框架    |  2021.1  |
|       Gateway       |      网关       |  3.0.4   |
|    LoadBalancer     |    负载均衡     |  3.0.4   |
|      OpenFeign      |    服务调用     |  3.0.4   |
|        Minio        |  本地对象存储   |    X     |
|     SpringBoot      |    核心框架     |  2.4.6   |
|   SpringSecurity    |    安全框架     |  2.4.6   |
|    MyBaits-Plus     |     ORM框架     |  3.5.3   |
|        Druid        |  数据库连接池   |  1.2.6   |
|        Seata        |   分布式事务    |  1.6.1   |
|       Knife4j       | Api文档生成工具 |  4.1.0   |
|    Elasticsearch    |    搜索引擎     |  7.17.2  |
|      Logstash       | 数据库同步工具  |  7.17.2  |

## 环境搭建

### 开发环境

|   工具   |            说明            | 版本号 |
| :------: | :------------------------: | :----: |
|  JDK11   |            JDK             |   11   |
|  Maven   |           MAVEN            | 3.8.6  |
|  Nacos   | 服务注册发现中心和配置中心 | 2.0.3  |
| RabbitMQ |          消息队列          | 3.9.11 |
|  MySQL   |        关系型数据库        | 8.0.26 |
|  Redis   |         缓存数据库         | 6.2.6  |

### 开发工具

| 工具          | 说明          | 下载地址                                                     |
| ------------- | ------------- | ------------------------------------------------------------ |
| IntelliJ IDEA | JAVA开发IDE   | [https://www.jetbrains.com/idea/download](https://gitee.com/link?target=https%3A%2F%2Fwww.jetbrains.com%2Fidea%2Fdownload) |
| DataGrip      | 数据库开发IDE | https://www.jetbrains.com/datagrip/download/                 |

## 运行截图

- ### 管理平台端运行截图



- ### 商城PC端运行截图