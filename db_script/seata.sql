CREATE SCHEMA IF NOT EXISTS seata DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE seata;

--
-- Table structure for table `branch_table`
--

DROP TABLE IF EXISTS `branch_table`;

CREATE TABLE `branch_table` (
  `branch_id` bigint NOT NULL,
  `xid` varchar(128) NOT NULL,
  `transaction_id` bigint DEFAULT NULL,
  `resource_group_id` varchar(32) DEFAULT NULL,
  `resource_id` varchar(256) DEFAULT NULL,
  `branch_type` varchar(8) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `client_id` varchar(64) DEFAULT NULL,
  `application_data` varchar(2000) DEFAULT NULL,
  `gmt_create` datetime(6) DEFAULT NULL,
  `gmt_modified` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`branch_id`),
  KEY `idx_xid` (`xid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `branch_table`
--

LOCK TABLES `branch_table` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `distributed_lock`
--

DROP TABLE IF EXISTS `distributed_lock`;

CREATE TABLE `distributed_lock` (
  `lock_key` char(20) NOT NULL,
  `lock_value` varchar(20) NOT NULL,
  `expire` bigint DEFAULT NULL,
  PRIMARY KEY (`lock_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `distributed_lock`
--

LOCK TABLES `distributed_lock` WRITE;
INSERT INTO `distributed_lock` VALUES ('AsyncCommitting',' ',0),('RetryCommitting',' ',0),('RetryRollbacking',' ',0),('TxTimeoutCheck',' ',0),('UndologDelete',' ',0);
UNLOCK TABLES;

--
-- Table structure for table `global_table`
--

DROP TABLE IF EXISTS `global_table`;

CREATE TABLE `global_table` (
  `xid` varchar(128) NOT NULL,
  `transaction_id` bigint DEFAULT NULL,
  `status` tinyint NOT NULL,
  `application_id` varchar(32) DEFAULT NULL,
  `transaction_service_group` varchar(32) DEFAULT NULL,
  `transaction_name` varchar(128) DEFAULT NULL,
  `timeout` int DEFAULT NULL,
  `begin_time` bigint DEFAULT NULL,
  `application_data` varchar(2000) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`xid`),
  KEY `idx_status_gmt_modified` (`status`,`gmt_modified`),
  KEY `idx_transaction_id` (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `global_table`
--

LOCK TABLES `global_table` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `lock_table`
--

DROP TABLE IF EXISTS `lock_table`;

CREATE TABLE `lock_table` (
  `row_key` varchar(128) NOT NULL,
  `xid` varchar(128) DEFAULT NULL,
  `transaction_id` bigint DEFAULT NULL,
  `branch_id` bigint NOT NULL,
  `resource_id` varchar(256) DEFAULT NULL,
  `table_name` varchar(32) DEFAULT NULL,
  `pk` varchar(36) DEFAULT NULL,
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '0:locked ,1:rollbacking',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`row_key`),
  KEY `idx_status` (`status`),
  KEY `idx_branch_id` (`branch_id`),
  KEY `idx_xid` (`xid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `lock_table`
--

LOCK TABLES `lock_table` WRITE;
UNLOCK TABLES;
