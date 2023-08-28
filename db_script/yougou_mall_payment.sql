CREATE SCHEMA IF NOT EXISTS yougou_mall_payment DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE yougou_mall_payment;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;

CREATE TABLE `coupon` (
  `coupon_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `quota` int DEFAULT NULL COMMENT '优惠券配额',
  `take_count` int DEFAULT NULL COMMENT '已领取数量',
  `expired` int DEFAULT NULL COMMENT '过期天数',
  `category_node` varchar(255) NOT NULL DEFAULT '0' COMMENT '可用分类节点, 为0则为通用优惠券',
  `description` varchar(50) DEFAULT NULL COMMENT '描述',
  `used_amount` decimal(10,2) NOT NULL COMMENT '使用金额',
  `discount_amount` decimal(10,2) NOT NULL COMMENT '抵扣金额',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='优惠券';

--
-- Table structure for table `coupon_user`
--

DROP TABLE IF EXISTS `coupon_user`;

CREATE TABLE `coupon_user` (
  `coupon_user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `coupon_id` bigint NOT NULL COMMENT '优惠券ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `state` tinyint NOT NULL DEFAULT '1' COMMENT '优惠券状态 0-已使用 1-待使用 2-已过期',
  `receive_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '领取时间',
  `expired_time` datetime DEFAULT NULL COMMENT '过期时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`coupon_user_id`),
  KEY `coupon_user_index` (`coupon_id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `coupon_user_log`
--

DROP TABLE IF EXISTS `coupon_user_log`;

CREATE TABLE `coupon_user_log` (
  `coupon_user_log_id` bigint NOT NULL COMMENT '主键ID',
  `coupon_user_id` bigint NOT NULL COMMENT '用户优惠券ID',
  `coupon_id` bigint NOT NULL COMMENT '优惠券ID',
  `discount_amount` decimal(10,2) DEFAULT NULL COMMENT '折扣金额',
  `order_no` varchar(255) NOT NULL COMMENT '订单号',
  `is_pay` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否实际支付',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`coupon_user_log_id`),
  KEY `coupon_user_log_index` (`coupon_user_id`,`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户优惠券使用记录';

--
-- Table structure for table `payment_log`
--

DROP TABLE IF EXISTS `payment_log`;

CREATE TABLE `payment_log` (
  `payment_log_id` bigint NOT NULL COMMENT '主键ID',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '支付总金额',
  `state` tinyint DEFAULT NULL COMMENT '支付状态 0-支付失败 1-支付成功',
  `order_no` varchar(255) DEFAULT NULL COMMENT '订单号',
  `message` varchar(255) DEFAULT NULL COMMENT '支付信息',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`payment_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='支付记录';

--
-- Table structure for table `undo_log`
--

DROP TABLE IF EXISTS `undo_log`;

CREATE TABLE `undo_log` (
  `branch_id` bigint NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(100) NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
