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

 Date: 14/02/2023 16:56:53
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
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for book_stock
-- ----------------------------
DROP TABLE IF EXISTS `book_stock`;
CREATE TABLE `book_stock`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NULL DEFAULT NULL COMMENT '书本id',
  `total` int(11) NULL DEFAULT NULL COMMENT '书本总数',
  `borrowed_num` int(11) NULL DEFAULT NULL COMMENT '已外借数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_borrow_detail
-- ----------------------------
DROP TABLE IF EXISTS `user_borrow_detail`;
CREATE TABLE `user_borrow_detail`  (
  `borrow_id` int(11) NOT NULL COMMENT 'borrow summary id',
  `seq` int(11) NOT NULL COMMENT '序列号',
  `book_id` int(11) NULL DEFAULT NULL COMMENT '书本id',
  `num` int(11) NULL DEFAULT NULL COMMENT '所借数量',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '0-正常，1-所有书已还',
  `returned_num` int(11) NULL DEFAULT NULL COMMENT '已还数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '借阅时的单价',
  PRIMARY KEY (`borrow_id`, `seq`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_borrow_summary
-- ----------------------------
DROP TABLE IF EXISTS `user_borrow_summary`;
CREATE TABLE `user_borrow_summary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总借阅金额',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '0-正常，1-所有书已还',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
