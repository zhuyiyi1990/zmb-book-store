/*
 Navicat Premium Data Transfer

 Source Server         : 本地Linux mysql
 Source Server Type    : MySQL
 Source Server Version : 50740
 Source Host           : 192.168.239.128:3306
 Source Schema         : zmb_book_store

 Target Server Type    : MySQL
 Target Server Version : 50740
 File Encoding         : 65001

 Date: 24/02/2023 13:52:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书名',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '借阅单价',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '哈利波特', 'JK', 12.34, '魔幻');
INSERT INTO `book` VALUES (2, '三国演义', '罗贯中', 100.00, '小说');
INSERT INTO `book` VALUES (3, '罗贯中', '吴承恩', 100.01, '小说');

-- ----------------------------
-- Table structure for book_stock
-- ----------------------------
DROP TABLE IF EXISTS `book_stock`;
CREATE TABLE `book_stock`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NULL DEFAULT NULL COMMENT '图书id',
  `total` int(11) NULL DEFAULT NULL COMMENT '图书总数',
  `borrowed_num` int(11) NULL DEFAULT NULL COMMENT '已外借数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_stock
-- ----------------------------
INSERT INTO `book_stock` VALUES (1, 1, 3, 1);
INSERT INTO `book_stock` VALUES (2, 2, 5, 1);
INSERT INTO `book_stock` VALUES (3, 3, 4, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别(0-默认未知,1-男,2-女)',
  `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_sys_user_phone`(`phone`) USING BTREE,
  INDEX `idx_su_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '朱一一', 1, '15021042401', 0);
INSERT INTO `user` VALUES (2, '周茂兵', 1, NULL, 0);
INSERT INTO `user` VALUES (3, '尤旭', 1, NULL, 0);

-- ----------------------------
-- Table structure for user_borrow_detail
-- ----------------------------
DROP TABLE IF EXISTS `user_borrow_detail`;
CREATE TABLE `user_borrow_detail`  (
  `borrow_id` int(11) NOT NULL COMMENT 'borrow summary id',
  `seq` int(11) NOT NULL COMMENT '序列号',
  `book_id` int(11) NULL DEFAULT NULL COMMENT '图书id',
  `num` int(11) NULL DEFAULT NULL COMMENT '所借数量',
  `returned_num` int(11) NULL DEFAULT NULL COMMENT '已还数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '借阅当时的单价',
  PRIMARY KEY (`borrow_id`, `seq`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_borrow_detail
-- ----------------------------
INSERT INTO `user_borrow_detail` VALUES (4, 1, 1, 1, 0, 12.34);
INSERT INTO `user_borrow_detail` VALUES (4, 2, 2, 1, 0, 100.00);
INSERT INTO `user_borrow_detail` VALUES (4, 3, 3, 1, 0, 100.01);

-- ----------------------------
-- Table structure for user_borrow_summary
-- ----------------------------
DROP TABLE IF EXISTS `user_borrow_summary`;
CREATE TABLE `user_borrow_summary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '0-正常，1-所有书已还',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_borrow_summary
-- ----------------------------
INSERT INTO `user_borrow_summary` VALUES (4, 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
