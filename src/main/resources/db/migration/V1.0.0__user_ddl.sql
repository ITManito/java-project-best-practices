/*
 Navicat Premium Data Transfer

 Source Server         : team3
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 02/12/2019 17:49:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `userEvaluation` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `deleted` tinyint(2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `other_info` varchar(3000) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (2, '张三', 1, 'demoData', 'demoData', 'demoData', 1, NULL, NULL, NULL);
INSERT INTO `user` VALUES (3, '张三丰', 45, 'demoData', 'demoData', 'demoData', 0, NULL, NULL, NULL);
INSERT INTO `user` VALUES (4, '李四', 23, 'demoData', 'demoData', 'demoData', 0, NULL, NULL, NULL);
INSERT INTO `user` VALUES (5, '王五', 78, 'demoData', 'demoData', 'demoData', 0, NULL, NULL, NULL);
INSERT INTO `user` VALUES (6, '李四五', 445, 'string', 'string', 'string', 0, NULL, NULL, NULL);
INSERT INTO `user` VALUES (7, '王欢', 23, 'demoData', 'demoData', 'demoData', 1, '2019-11-01 02:28:09', '2019-11-01 02:28:09', NULL);
INSERT INTO `user` VALUES (8, 'sunshine', 8, 'sunshine', 'sunshine', 'sunshine', 1, '2019-11-01 02:28:38', '2019-11-01 02:28:38', NULL);
INSERT INTO `user` VALUES (9, 'string', 0, 'string', 'string', 'string', 1, '2019-11-01 07:58:48', '2019-11-01 07:58:48', NULL);
INSERT INTO `user` VALUES (10, 'string', 0, 'string', 'string', 'string', 0, '2019-11-01 08:09:45', '2019-11-01 08:09:45', NULL);
INSERT INTO `user` VALUES (11, 'string', 0, 'string', 'string', 'string', 0, '2019-11-01 08:37:28', '2019-11-01 08:37:28', NULL);
INSERT INTO `user` VALUES (12, 'string', 0, 'string', 'string', 'string', 0, '2019-11-01 20:38:36', '2019-11-01 20:38:36', '{\"city\":\"string\",\"sex\":\"string\"}');
INSERT INTO `user` VALUES (13, 'string', 0, 'string', 'string', 'string', 0, '2019-11-29 01:06:19', '2019-11-29 01:06:19', '{\"city\":\"string\",\"sex\":\"string\"}');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
