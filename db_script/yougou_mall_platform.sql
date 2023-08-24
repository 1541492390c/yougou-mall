CREATE SCHEMA IF NOT EXISTS yougou_mall_platform DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE yougou_mall_platform;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;

CREATE TABLE `banner` (
  `banner_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` tinyint DEFAULT NULL COMMENT '轮播图类型 1-PC端 2-移动端 3-小程序端',
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  `link` varchar(255) DEFAULT NULL COMMENT '轮播图链接',
  `img` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `page` varchar(255) NOT NULL COMMENT '所属页面',
  `enabled` bit(1) NOT NULL COMMENT '是否启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`banner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='轮播图';

--
-- Table structure for table `feedback_type`
--

DROP TABLE IF EXISTS `feedback_type`;

CREATE TABLE `feedback_type` (
  `feedback_type_id` bigint DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='AT transaction mode undo table';
