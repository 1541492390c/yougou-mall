CREATE SCHEMA IF NOT EXISTS yougou_mall_user DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE yougou_mall_user;

--
-- Table structure for table `addr`
--

DROP TABLE IF EXISTS `addr`;

CREATE TABLE `addr` (
  `addr_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `consignee` varchar(50) NOT NULL COMMENT '收货人',
  `telephone` varchar(11) NOT NULL COMMENT '联系电话',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `district` varchar(50) DEFAULT NULL COMMENT '区、县',
  `detailed_addr` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `is_default` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否默认地址',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`addr_id`),
  KEY `addr_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收货地址';

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `comment_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `product_id` bigint DEFAULT NULL COMMENT '商品ID',
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `order_item_id` bigint NOT NULL COMMENT '订单项ID',
  `rate` decimal(10,2) DEFAULT NULL COMMENT '评分',
  `img_list` varchar(255) DEFAULT NULL COMMENT '图片列表',
  `content` text COMMENT '评价内容',
  `comment_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评价时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`comment_id`),
  KEY `comment_index` (`user_id`,`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评价';

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feedback_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `feedback_type_id` bigint NOT NULL COMMENT '反馈类型ID',
  `feedback_type_name` varchar(255) DEFAULT NULL COMMENT '反馈类型名称',
  `content` text NOT NULL COMMENT '反馈内容',
  `contact_way` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `img_list` varchar(255) DEFAULT NULL COMMENT '图片列表',
  `feedback_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '反馈时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`feedback_id`),
  KEY `feedback_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户反馈';

--
-- Table structure for table `undo_log`
--

DROP TABLE IF EXISTS `undo_log`;

CREATE TABLE `undo_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `branch_id` bigint NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(100) NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COMMENT='AT transaction mode undo table';

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` bigint NOT NULL COMMENT '主键ID',
  `gender` tinyint NOT NULL DEFAULT '0' COMMENT '性别 0-未填写 1-男 2-女',
  `user_type` tinyint NOT NULL DEFAULT '2' COMMENT '用户类型 1-管理员 2-普通用户',
  `state` int DEFAULT '1' COMMENT '用户状态 0-禁用 1-正常',
  `nickname` varchar(255) NOT NULL COMMENT '昵称',
  `avatar` varchar(255) COMMENT '头像',
  `birthday` timestamp NULL DEFAULT NULL COMMENT '生日',
  `enabled` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户';

LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES (1,0,1,1,'admin',NULL,NULL,b'1',CURRENT_TIMESTAMP,NULL);
UNLOCK TABLES;
