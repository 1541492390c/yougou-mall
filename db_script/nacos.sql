CREATE SCHEMA IF NOT EXISTS nacos DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE nacos;

--
-- Table structure for table `config_info`
--

DROP TABLE IF EXISTS `config_info`;

CREATE TABLE `config_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='config_info';

--
-- Dumping data for table `config_info`
--

LOCK TABLES `config_info` WRITE;
INSERT INTO `config_info`
VALUES (1, 'seataServer.properties', 'DEV_GROUP',
        '# 存储模式\nstore.mode=db\n \nstore.db.datasource=druid\nstore.db.dbType=mysql\n# 需要根据mysql的版本调整driverClassName\n# mysql8及以上版本对应的driver：com.mysql.cj.jdbc.Driver\n# mysql8以下版本的driver：com.mysql.jdbc.Driver\nstore.db.driverClassName=com.mysql.cj.jdbc.Driver\n# 注意根据生产实际情况调整参数host和port\nstore.db.url=jdbc:mysql://127.0.0.1:3306/seata?characterEncoding=utf-8&serverTimezone=GMT\n# 数据库用户名\nstore.db.user=#数据库用户名\n# 用户名密码\nstore.db.password=#数据库密码\nservice.vgroupMapping.auth_tx_group=defalut\nservice.vgroupMapping.user_tx_group=defalut\nservice.vgroupMapping.product_tx_group=default\nservice.vgroupMapping.order_tx_group=default\nservice.vgroupMapping.platform_tx_group=default\nservice.vgroupMapping.payment_tx_group=default',
        '6e0910a5f78aa87a1ee3219079a8c210', '2023-08-24 03:47:53', '2023-08-28 05:04:38', 'nacos', '127.0.0.1', '',
        '5d93097c-43ec-4f77-866c-9ad4ecd9df28', '', '', '', 'properties', ''),
       (36, 'yougou-mall-auth-dev.yaml', 'DEV_GROUP',
        'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/yougou_mall_auth?characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: #数据库用户名\n    password: #数据库密码',
        'ed92c0f5b6b289a7db2e890b3920a239', '2023-08-25 04:31:20', '2023-08-28 05:00:01', 'nacos', '127.0.0.1', '',
        'b261b20d-67b4-4e33-83ef-17ebae6d68b8', '', '', '', 'yaml', ''),
       (39, 'yougou-mall-gateway-dev.yaml', 'DEV_GROUP',
        'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n      globalcors:\n        cors-configurations:\n          \"[/**]\":\n            allowCredentials: true\n            allowedMethods: \"*\"\n            allowedHeaders: \"*\"\n            allowedOriginPatterns: \"*\"\n      routes:\n        # 认证授权服务\n        - id: yougou_mall_auth\n          uri: lb://yougou-mall-auth\n          predicates:\n            - Path=/auth/**, /admin/auth/**\n          filters:\n            - RewritePath=/auth-service/(?<segment>.*),/$\\{segment}\n\n        # 用户服务\n        - id: yougou_mall_user\n          uri: lb://yougou-mall-user\n          predicates:\n            - Path=/user/**, /admin/user/**\n          filters:\n            - RewritePath=/user-service/(?<segment>.*),/$\\{segment}\n\n        # 商品服务\n        - id: yougou_mall_product\n          uri: lb://yougou-mall-product\n          predicates:\n            - Path=/product/**, /admin/product/**\n          filters:\n            - RewritePath=/product-service/(?<segment>.*),/$\\{segment}\n\n        # 订单服务\n        - id: yougou_mall_order\n          uri: lb://yougou-mall-order\n          predicates:\n            - Path=/order/**, /admin/order/**\n          filters:\n           - RewritePath=/biz-service/(?<segment>.*),/$\\{segment}\n\n        # 平台服务\n        - id: yougou_mall_platform\n          uri: lb://yougou-mall-platform\n          predicates:\n            - Path=/platform/**, /admin/platform/**\n          filters:\n            - RewritePath=/platform-service/(?<segment>.*),/$\\{segment}\n\n        # 扩展服务\n        - id: yougou_mall_extra\n          uri: lb://yougou-mall-extra\n          predicates:\n            - Path=/extra/**\n          filters:\n           - RewritePath=/extra-service/(?<segment>.*),/$\\{segment}\n           \n        # 支付服务\n        - id: yougou_mall_payment\n          uri: lb://yougou-mall-payment\n          predicates:\n            - Path=/payment/**, /admin/payment/**\n          filters:\n           - RewritePath=/payment-service/(?<segment>.*),/$\\{segment}\n\n        # 搜索服务\n        - id: yougou_mall_search\n          uri: lb://yougou-mall-search\n          predicates:\n            - Path=/search/**\n          filters:\n           - RewritePath=/search-service/(?<segment>.*),/$\\{segment}\n\nknife4j:\n  enable: true\n  gateway:\n    version: openapi3\n    strategy: discover\n    enabled: true\n    discover:\n      enabled: true\n      service-config:\n        yougou-mall-auth:\n          group-name: \'认证授权服务\'\n        yougou-mall-user:\n          group-name: \'用户服务\'\n        yougou-mall-product:\n          group-name: \'商品服务\'\n        yougou-mall-order:\n          group-name: \'订单服务\'\n        yougou-mall-platform:\n          group-name: \'平台服务\'\n        yougou-mall-extra:\n          group-name: \'扩展服务\'\n        yougou-mall-payment:\n          group-name: \'支付服务\'\n        yougou-mall-search:\n          group-name: \'搜索服务\'\n      excluded-services:\n        - yougou-mall-gateway',
        'ed33cb3a7a8e3a576b6c76a3b38e1f12', '2023-08-25 04:31:20', '2023-08-25 04:42:44', 'nacos', '127.0.0.1', '',
        'b261b20d-67b4-4e33-83ef-17ebae6d68b8', '', '', '', 'yaml', ''),
       (40, 'yougou-mall-order-dev.yaml', 'DEV_GROUP',
        'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/yougou_mall_order?characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: #数据库用户名\n    password: #数据库密码\n\nseata:\n  tx-service-group: order_tx_group\n  service:\n    vgroup-mapping:\n      order_tx_group: default',
        '904a666c4029f5cfbd437ae63cd47b34', '2023-08-25 04:31:20', '2023-08-28 05:00:33', 'nacos', '127.0.0.1', '',
        'b261b20d-67b4-4e33-83ef-17ebae6d68b8', '', '', '', 'yaml', ''),
       (41, 'yougou-mall-payment-dev.yaml', 'DEV_GROUP',
        'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/yougou_mall_payment?characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: #数据库用户名\n    password: #数据库密码\n\nalipay:\n  #支付宝appId\n  app-id: #支付宝appId\n  #支付宝公钥\n  alipay-public-key: #支付宝公钥\n  #应用私钥\n  private-key: #应用私钥\n  #支付宝网关(沙盒模式)\n  server-url: https://openapi-sandbox.dl.alipaydev.com/gateway.do\n  #签名类型\n  sign-type: RSA2\n  #返回地址(前端支付成功地址)\n  return-url: #http://localhost:3000/payment_success\n  #异步通知地址(需要外网能访问的域名,没有可以使用内网穿透)\n  notify-url: #异步通知地址\n\nseata:\n  tx-service-group: payment_tx_group\n  service:\n    vgroup-mapping:\n      payment_tx_group: default',
        '9ef5af2841ff1401dc3f09c17a5ab39f', '2023-08-25 04:31:20', '2023-08-28 05:01:42', 'nacos', '127.0.0.1', '',
        'b261b20d-67b4-4e33-83ef-17ebae6d68b8', '', '', '', 'yaml', ''),
       (42, 'yougou-mall-platform-dev.yaml', 'DEV_GROUP',
        'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/yougou_mall_platform?characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: #数据库用户名\n    password: #数据库密码\n\nseata:\n  tx-service-group: platform_tx_group\n  service:\n    vgroup-mapping:\n      platform_tx_group: default',
        '74545396e755c0b64213916afe7305f9', '2023-08-25 04:31:20', '2023-08-28 05:02:00', 'nacos', '127.0.0.1', '',
        'b261b20d-67b4-4e33-83ef-17ebae6d68b8', '', '', '', 'yaml', ''),
       (43, 'yougou-mall-product-dev.yaml', 'DEV_GROUP',
        'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/yougou_mall_product?characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: #数据库用户名\n    password: #数据库密码\n\nseata:\n  tx-service-group: product_tx_group\n  service:\n    vgroup-mapping:\n      product_tx_group: default',
        'fa04a33904412107888d7bb218aea7ff', '2023-08-25 04:31:20', '2023-08-28 05:02:16', 'nacos', '127.0.0.1', '',
        'b261b20d-67b4-4e33-83ef-17ebae6d68b8', '', '', '', 'yaml', ''),
       (45, 'yougou-mall-user-dev.yaml', 'DEV_GROUP',
        'spring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/yougou_mall_user?characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: #数据库用户名\n    password: #数据库密码\n\nseata:\n  tx-service-group: user_tx_group\n  service:\n    vgroup-mapping:\n      user_tx_group: default',
        'e5a90b315564c85565123700eca6fc99', '2023-08-25 04:31:20', '2023-08-28 05:02:38', 'nacos', '127.0.0.1', '',
        'b261b20d-67b4-4e33-83ef-17ebae6d68b8', '', '', '', 'yaml', ''),
       (46, 'yougou-mall-extra-dev.yaml', 'DEV_GROUP',
        '# minio\nminio:\n    endpoint: http://127.0.0.1:9000\n    bucket: yougou-mall-resource\n    access-key: #access-key\n    secret-key: #secret-key\n\n# 阿里云\naliyun:\n    endpoint: dysmsapi.aliyuncs.com\n    access-key-id: #access-key-id\n    access-key-secret: #access-key-secret',
        '75867653ef3de6344e80a8be66b35aa4', '2023-08-25 04:31:56', '2023-08-28 05:03:12', 'nacos', '127.0.0.1', '',
        'b261b20d-67b4-4e33-83ef-17ebae6d68b8', '', '', '', 'yaml', ''),
       (47, 'yougou-mall-search-dev.yaml', 'DEV_GROUP', 'elasticsearch:\n    host: 127.0.0.1\n    port: 9500',
        'ae5378eca6fd289c741c48a905bbd9d5', '2023-08-25 04:31:56', '2023-08-25 04:31:56', NULL, '127.0.0.1', '',
        'b261b20d-67b4-4e33-83ef-17ebae6d68b8', NULL, NULL, NULL, 'yaml', NULL),
       (49, 'yougou-mall-common-cache-dev.yaml', 'DEV_GROUP',
        'spring:\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    password: 123456\n    database: 0\n  main:\n    allow-bean-definition-overriding: true\n\nr-lock-key: 9FD9A289-6FA0-CF19-1154-11EFFE7317D5',
        '6c72f8352a7c8c8e8b04384bc028c85e', '2023-08-25 04:37:40', '2023-08-28 05:03:28', 'nacos', '127.0.0.1', '',
        'b261b20d-67b4-4e33-83ef-17ebae6d68b8', '', '', '', 'yaml', ''),
       (50, 'yougou-mall-common-database-dev.yaml', 'DEV_GROUP',
        'mybatis-plus:\n  configuration:\n    map-underscore-to-camel-case: true\n  global-config:\n    banner: false\n    db-config:\n      #逻辑删除\n      logic-delete-field: enabled\n      logic-delete-value: 0\n      logic-not-delete-value: 1\n  mapper-locations: classpath:mapper/*.xml\n    \n\nseata:\n  config:\n    nacos:\n      namespace: 5d93097c-43ec-4f77-866c-9ad4ecd9df28\n      server-addr: 127.0.0.1:8848\n      data-id: seataServer.properties\n  registry:\n    nacos:\n      namespace: 5d93097c-43ec-4f77-866c-9ad4ecd9df28\n      server-addr: 127.0.0.1:8848',
        'fd9ca4bf76784cb92789d9dd89212b29', '2023-08-25 04:38:37', '2023-08-28 05:04:01', 'nacos', '127.0.0.1', '',
        'b261b20d-67b4-4e33-83ef-17ebae6d68b8', '', '', '', 'yaml', ''),
       (51, 'yougou-mall-common-rabbitmq-dev.yaml', 'DEV_GROUP',
        'spring:\n  rabbitmq:\n    host: 127.0.0.1\n    port: 5672\n    username: admin\n    password: admin',
        'c93e8bec247ada3f7ba5f73691fc15b7', '2023-08-25 04:39:19', '2023-08-28 05:04:10', 'nacos', '127.0.0.1', '',
        'b261b20d-67b4-4e33-83ef-17ebae6d68b8', '', '', '', 'yaml', '');
UNLOCK TABLES;

--
-- Table structure for table `config_info_aggr`
--

DROP TABLE IF EXISTS `config_info_aggr`;

CREATE TABLE `config_info_aggr` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='增加租户字段';

--
-- Dumping data for table `config_info_aggr`
--

LOCK TABLES `config_info_aggr` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `config_info_beta`
--

DROP TABLE IF EXISTS `config_info_beta`;

CREATE TABLE `config_info_beta` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='config_info_beta';

--
-- Dumping data for table `config_info_beta`
--

LOCK TABLES `config_info_beta` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `config_info_tag`
--

DROP TABLE IF EXISTS `config_info_tag`;

CREATE TABLE `config_info_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='config_info_tag';

--
-- Dumping data for table `config_info_tag`
--

LOCK TABLES `config_info_tag` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `config_tags_relation`
--

DROP TABLE IF EXISTS `config_tags_relation`;

CREATE TABLE `config_tags_relation` (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='config_tag_relation';

--
-- Dumping data for table `config_tags_relation`
--

LOCK TABLES `config_tags_relation` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `group_capacity`
--

DROP TABLE IF EXISTS `group_capacity`;

CREATE TABLE `group_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';

--
-- Dumping data for table `group_capacity`
--

LOCK TABLES `group_capacity` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `his_config_info`
--

DROP TABLE IF EXISTS `his_config_info`;

CREATE TABLE `his_config_info` (
  `id` bigint unsigned NOT NULL,
  `nid` bigint unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  `src_user` text COLLATE utf8_bin,
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='多租户改造';

--
-- Dumping data for table `his_config_info`
--

LOCK TABLES `his_config_info` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;

CREATE TABLE `permissions` (
  `role` varchar(50) NOT NULL,
  `resource` varchar(512) NOT NULL,
  `action` varchar(8) NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `uk_username_role` (`username`,`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
INSERT INTO `roles` VALUES ('nacos','ROLE_ADMIN');
UNLOCK TABLES;

--
-- Table structure for table `tenant_capacity`
--

DROP TABLE IF EXISTS `tenant_capacity`;

CREATE TABLE `tenant_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='租户容量信息表';

--
-- Dumping data for table `tenant_capacity`
--

LOCK TABLES `tenant_capacity` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `tenant_info`
--

DROP TABLE IF EXISTS `tenant_info`;

CREATE TABLE `tenant_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='tenant_info';

--
-- Dumping data for table `tenant_info`
--

LOCK TABLES `tenant_info` WRITE;
INSERT INTO `tenant_info` VALUES (1,'1','5d93097c-43ec-4f77-866c-9ad4ecd9df28','seata','seata','nacos',1692848724628,1692848724628),(2,'1','b261b20d-67b4-4e33-83ef-17ebae6d68b8','yougou-mall','优购商城','nacos',1692848906778,1692848906778);
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES ('nacos','$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu',1);
UNLOCK TABLES;
