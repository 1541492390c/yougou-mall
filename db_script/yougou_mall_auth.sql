CREATE SCHEMA IF NOT EXISTS yougou_mall_auth DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE yougou_mall_auth;

--
-- Table structure for table `auth_account`
--

DROP TABLE IF EXISTS `auth_account`;

CREATE TABLE `auth_account` (
  `auth_account_id` bigint NOT NULL COMMENT '主键',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `state` int DEFAULT '1' COMMENT '状态 0-禁用  1-正常',
  `user_type` tinyint DEFAULT '2' COMMENT '用户类型 1-管理员 2-普通用户',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `role` varchar(255) NOT NULL DEFAULT 'USER' COMMENT '角色',
  `enabled` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`auth_account_id`),
  UNIQUE KEY `auth_account_username_pk` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='认证授权账号';

LOCK TABLES `auth_account` WRITE;
INSERT INTO `auth_account` VALUES (1,1,1,1,'admin','$2a$10$Q6Zmp8ed1G02EUJAhQC8heDgH9FGQmk4uP0Uy7trdSk50iWFPlkXm',NULL,NULL,'SUPER_ADMIN',b'1',CURRENT_TIMESTAMP,NULL);
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COMMENT='AT transaction mode undo table';
