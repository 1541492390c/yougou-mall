CREATE SCHEMA IF NOT EXISTS yougou_mall_product DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE yougou_mall_product;

--
-- Table structure for table `attr`
--

DROP TABLE IF EXISTS `attr`;

CREATE TABLE `attr` (
  `attr_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `product_id` bigint NOT NULL COMMENT '商品ID',
  `name` varchar(255) DEFAULT NULL COMMENT '属性名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`attr_id`),
  KEY `attr_product_product_id_fk` (`product_id`),
  CONSTRAINT `attr_product_product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品属性值';

--
-- Table structure for table `attr_value`
--

DROP TABLE IF EXISTS `attr_value`;

CREATE TABLE `attr_value` (
  `attr_value_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `attr_id` bigint NOT NULL COMMENT '属性ID',
  `name` varchar(255) DEFAULT NULL COMMENT '商品属性值名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`attr_value_id`),
  KEY `attr_value_product_attr_attr_id_fk` (`attr_id`),
  CONSTRAINT `attr_value_product_attr_attr_id_fk` FOREIGN KEY (`attr_id`) REFERENCES `attr` (`attr_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品属性值';

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;

CREATE TABLE `brand` (
  `brand_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) NOT NULL COMMENT '品牌名称',
  `category_node` varchar(255) NOT NULL COMMENT '分类节点',
  `img` varchar(255) DEFAULT NULL COMMENT '品牌logo图片',
  `description` varchar(255) DEFAULT NULL COMMENT '品牌简介',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='品牌';

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `category_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '上一级分类id(顶级分类为0)',
  `level` tinyint DEFAULT NULL COMMENT '分类层级 1-一级分类 2-二级分类',
  `name` varchar(50) NOT NULL COMMENT '分类名称',
  `node` varchar(255) DEFAULT NULL COMMENT '分类节点',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品分类';

--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;

CREATE TABLE `favorite` (
  `favorite_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `product_id` bigint NOT NULL COMMENT '商品ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`favorite_id`),
  KEY `favorite_product_product_id_fk` (`product_id`),
  CONSTRAINT `favorite_product_product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品收藏';

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `product_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `brand_id` bigint DEFAULT NULL COMMENT '品牌id',
  `category_node` varchar(10) NOT NULL COMMENT '分类节点 格式: 一级分类-二级分类-三级分类 如: (0-1-2)',
  `state` tinyint NOT NULL DEFAULT '1' COMMENT '商品状态 0-已下架 1-上架',
  `discount` int DEFAULT NULL COMMENT '折扣',
  `name` varchar(255) NOT NULL COMMENT '商品名称',
  `cover` varchar(255) DEFAULT NULL COMMENT '商品封面',
  `img_list` text COMMENT '图片列表',
  `is_discount` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否折扣',
  `recommended` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否推荐',
  `enabled` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品';

--
-- Table structure for table `sec_kill`
--

DROP TABLE IF EXISTS `sec_kill`;

CREATE TABLE `sec_kill` (
  `sec_kill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `start_time` varchar(255) NOT NULL COMMENT '秒杀活动开始时间',
  `end_time` varchar(255) NOT NULL COMMENT '秒杀活动结束时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`sec_kill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='秒杀活动场次';

--
-- Table structure for table `sku`
--

DROP TABLE IF EXISTS `sku`;

CREATE TABLE `sku` (
  `sku_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `product_id` bigint NOT NULL COMMENT '商品 ID',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `discount_price` decimal(10,2) DEFAULT NULL COMMENT '折扣价格',
  `description` varchar(255) DEFAULT NULL COMMENT 'SKU简介',
  `specs` varchar(255) NOT NULL COMMENT 'sku规格',
  `sku_stock` int NOT NULL DEFAULT '0' COMMENT '库存',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`sku_id`),
  KEY `sku_product_product_id_fk` (`product_id`),
  CONSTRAINT `sku_product_product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品SKU';

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
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb3 COMMENT='AT transaction mode undo table';
