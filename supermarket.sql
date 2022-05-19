/*
 Navicat Premium Data Transfer

 Source Server         : 我的
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : supermarket

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 20/02/2022 00:06:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cashier_configure
-- ----------------------------
DROP TABLE IF EXISTS `cashier_configure`;
CREATE TABLE `cashier_configure`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `json` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `updateTime` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cashier_configure
-- ----------------------------
INSERT INTO `cashier_configure` VALUES (1, '1', '{\"title\":\"超市小票\",\"machine\":\"1\",\"number\":\"1\",\"address\":\"长沙基地总部C10栋\",\"tel\":\"15111490636\",\"ramerk\":\"1231231\",\"preview\":1,\"auto\":1}', 1, '2022-02-19 23:56:14');

-- ----------------------------
-- Table structure for cashier_goods
-- ----------------------------
DROP TABLE IF EXISTS `cashier_goods`;
CREATE TABLE `cashier_goods`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `goodsName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goodsCode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `salesPrice` decimal(5, 2) NULL DEFAULT NULL,
  `purchasePrice` decimal(5, 2) NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `inventory` int(0) NULL DEFAULT NULL COMMENT '库存',
  `pinyin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '拼音码',
  `supplier` int(0) NULL DEFAULT NULL COMMENT '供货商',
  `productionPeriod` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生产期',
  `shelfLife` int(0) NULL DEFAULT NULL COMMENT '保质期/天',
  `status` int(0) NULL DEFAULT NULL COMMENT '商品状态 1-启用，2-禁用',
  `discount` decimal(5, 2) NULL DEFAULT NULL COMMENT '优惠金额',
  `ramerk` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `createTime` timestamp(0) NULL DEFAULT NULL,
  `updateTime` timestamp(0) NULL DEFAULT NULL,
  `uid` bigint(0) NULL DEFAULT NULL,
  `uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `parentId` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cashier_goods
-- ----------------------------
INSERT INTO `cashier_goods` VALUES (1, '苹果', 'ZDY1644545950057', 1.00, 1.00, 1, 1085, '', 2, '2022-02-11', 11, 1, 0.00, '', '2022-02-11 10:19:45', '2022-02-19 23:46:24', 2, '00001', 2);
INSERT INTO `cashier_goods` VALUES (2, '梨', 'ZDY1644550775884', 2.00, 1.00, 1, 12, '', NULL, '2022-02-11', 11, NULL, 0.00, '', '2022-02-11 11:39:59', '2022-02-11 11:39:59', 2, '00001', 1);
INSERT INTO `cashier_goods` VALUES (3, '橘子', 'ZDY1644568609024', 3.00, 2.00, 1, 1111, 'juzhi', 1, '2022-02-11', 3, NULL, 0.00, '', '2022-02-11 16:37:22', '2022-02-11 16:37:22', 1, 'admin', 1);
INSERT INTO `cashier_goods` VALUES (4, '全聚德', '1', 490.00, 32.00, 1, 3, NULL, NULL, NULL, NULL, NULL, 0.00, NULL, '2022-02-19 23:56:52', '2022-02-19 23:56:52', 2, '00001', 2);

-- ----------------------------
-- Table structure for cashier_goods_history
-- ----------------------------
DROP TABLE IF EXISTS `cashier_goods_history`;
CREATE TABLE `cashier_goods_history`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `goodsCode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goodsName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `inventory` int(0) NULL DEFAULT NULL COMMENT '库存',
  `salesPrice` decimal(5, 2) NULL DEFAULT NULL COMMENT '销售价格',
  `purchasePrice` decimal(5, 2) NULL DEFAULT NULL COMMENT '实际价格',
  `profit` decimal(5, 2) NULL DEFAULT NULL COMMENT '买价',
  `sum` decimal(5, 2) NULL DEFAULT NULL COMMENT '个数',
  `rate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `uid` bigint(0) NULL DEFAULT NULL,
  `parentId` bigint(0) NULL DEFAULT NULL,
  `createTime` timestamp(0) NULL DEFAULT NULL,
  `orderTime` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cashier_in_goods
-- ----------------------------
DROP TABLE IF EXISTS `cashier_in_goods`;
CREATE TABLE `cashier_in_goods`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `goodsName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goodsCode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `salesPrice` decimal(5, 2) NULL DEFAULT NULL,
  `purchasePrice` decimal(5, 2) NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `inventory` int(0) NULL DEFAULT NULL,
  `supplier` int(0) NULL DEFAULT NULL,
  `productionPeriod` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `shelfLife` int(0) NULL DEFAULT NULL,
  `batch` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `createTime` timestamp(0) NULL DEFAULT NULL,
  `uid` bigint(0) NULL DEFAULT NULL,
  `uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `parentId` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cashier_in_goods
-- ----------------------------
INSERT INTO `cashier_in_goods` VALUES (1, '苹果', 'ZDY1644545950057', 1.00, 1.00, 1, 1, 1, '2022-02-11', 11, '1644566729517', 1, '2022-02-11 16:05:50', 1, 'admin', 1);
INSERT INTO `cashier_in_goods` VALUES (2, '苹果', 'ZDY1644545950057', 1.00, 1.00, 1, 1, 1, '2022-02-11', 11, '1644568454874', 1, '2022-02-11 16:34:25', 1, 'admin', 1);
INSERT INTO `cashier_in_goods` VALUES (3, '苹果', 'ZDY1644545950057', 1.00, 1.00, 1, 1, 1, '2022-02-11', 11, '1645192611868', 1, '2022-02-18 21:57:00', 1, 'admin', 1);
INSERT INTO `cashier_in_goods` VALUES (4, '苹果', 'ZDY1644545950057', 1.00, 1.00, 1, 10, 2, '2022-02-11', 11, '1645282226863', 1, '2022-02-19 22:51:10', 2, '00001', 2);
INSERT INTO `cashier_in_goods` VALUES (5, '苹果', 'ZDY1644545950057', 1.00, 1.00, 1, 1, 1, '2022-02-11', 11, '1645282226863', 0, '2022-02-19 22:51:17', 2, '00001', 2);

-- ----------------------------
-- Table structure for cashier_inventory
-- ----------------------------
DROP TABLE IF EXISTS `cashier_inventory`;
CREATE TABLE `cashier_inventory`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `inventoryNo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `inventoryTime` date NULL DEFAULT NULL,
  `beforeInventory` int(0) NULL DEFAULT NULL,
  `afterInventory` int(0) NULL DEFAULT NULL,
  `difference` int(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `ramerk` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createTime` timestamp(0) NULL DEFAULT NULL,
  `uid` bigint(0) NULL DEFAULT NULL,
  `uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `parentId` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cashier_inventory
-- ----------------------------
INSERT INTO `cashier_inventory` VALUES (11, 'PD1645199551553', '2022-02-18', 2234, 1234, 1, 1, NULL, '2022-02-18 23:52:32', 1, 'admin', 1);
INSERT INTO `cashier_inventory` VALUES (12, 'PD1645199849712', '2022-02-18', 2234, 2234, 0, 1, NULL, '2022-02-18 23:57:30', 1, 'admin', 1);
INSERT INTO `cashier_inventory` VALUES (13, 'PD1645284886383', '2022-02-19', 1099, 1099, 0, 1, NULL, '2022-02-19 23:34:46', 2, '00001', 2);

-- ----------------------------
-- Table structure for cashier_inventory_day
-- ----------------------------
DROP TABLE IF EXISTS `cashier_inventory_day`;
CREATE TABLE `cashier_inventory_day`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `inventoryTime` date NULL DEFAULT NULL,
  `goodsCode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goodsName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `purchasePrice` decimal(5, 2) NULL DEFAULT NULL,
  `beforeInventory` int(0) NULL DEFAULT NULL,
  `afterInventory` int(0) NULL DEFAULT NULL,
  `createTime` timestamp(0) NULL DEFAULT NULL,
  `parentId` bigint(0) NULL DEFAULT NULL,
  `inInventory` int(0) NULL DEFAULT NULL,
  `payNum` int(0) NULL DEFAULT NULL,
  `reportLoss` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 123 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cashier_inventory_day
-- ----------------------------
INSERT INTO `cashier_inventory_day` VALUES (106, '2022-02-18', 'ZDY1644545950057', '苹果', 1.00, 1111, 1111, '2022-02-18 23:52:18', 1, 1, 1, 2);
INSERT INTO `cashier_inventory_day` VALUES (107, '2022-02-18', 'ZDY1644550775884', '梨', 1.00, 12, 12, '2022-02-18 23:52:18', 1, 0, 0, 0);
INSERT INTO `cashier_inventory_day` VALUES (108, '2022-02-18', 'ZDY1644568609024', '橘子', 2.00, 1111, 1111, '2022-02-18 23:52:18', 1, 0, 0, 0);
INSERT INTO `cashier_inventory_day` VALUES (116, '2022-02-19', 'ZDY1644545950057', '苹果', 1.00, 111, 1111, '2022-02-19 00:05:08', 1, 0, 1, 2);
INSERT INTO `cashier_inventory_day` VALUES (117, '2022-02-19', 'ZDY1644550775884', '梨', 1.00, 12, 12, '2022-02-19 00:05:08', 1, 0, 0, 0);
INSERT INTO `cashier_inventory_day` VALUES (118, '2022-02-19', 'ZDY1644568609024', '橘子', 2.00, 1111, 1111, '2022-02-19 00:05:08', 1, 0, 0, 0);
INSERT INTO `cashier_inventory_day` VALUES (119, '2022-02-19', 'ZDY1644545950057', '苹果', 1.00, 1111, 1111, '2022-02-19 00:05:08', 1, 0, 1, 2);
INSERT INTO `cashier_inventory_day` VALUES (120, '2022-02-19', 'ZDY1644550775884', '梨', 1.00, 12, 12, '2022-02-19 00:05:08', 1, 0, 0, 0);
INSERT INTO `cashier_inventory_day` VALUES (121, '2022-02-19', 'ZDY1644568609024', '橘子', 2.00, 1111, 1111, '2022-02-19 00:05:08', 1, 0, 0, 0);
INSERT INTO `cashier_inventory_day` VALUES (123, '2022-02-19', 'ZDY1644545950057', '苹果', 1.00, 1083, 1083, '2022-02-19 16:12:14', 1, 0, 1, 2);
INSERT INTO `cashier_inventory_day` VALUES (124, '2022-02-19', 'ZDY1644550775884', '梨', 1.00, 12, 12, '2022-02-19 16:12:14', 1, 0, 0, 0);
INSERT INTO `cashier_inventory_day` VALUES (125, '2022-02-19', 'ZDY1644568609024', '橘子', 2.00, 1111, 1111, '2022-02-19 16:12:14', 1, 0, 0, 0);
INSERT INTO `cashier_inventory_day` VALUES (126, '2022-02-19', 'ZDY1644545950057', '苹果', 1.00, 1099, 1099, '2022-02-19 23:34:36', 2, 10, 1, 2);
INSERT INTO `cashier_inventory_day` VALUES (127, '2022-02-19', 'ZDY1644550775884', '梨', 1.00, 12, 12, '2022-02-19 23:34:36', 1, 0, 0, 0);
INSERT INTO `cashier_inventory_day` VALUES (128, '2022-02-19', 'ZDY1644568609024', '橘子', 2.00, 1111, 1111, '2022-02-19 23:34:36', 1, 0, 0, 0);

-- ----------------------------
-- Table structure for cashier_inventory_goods
-- ----------------------------
DROP TABLE IF EXISTS `cashier_inventory_goods`;
CREATE TABLE `cashier_inventory_goods`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `inventoryNo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goodsName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goodsCode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `purchasePrice` decimal(5, 2) NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `beforeInventory` int(0) NULL DEFAULT NULL,
  `afterInventory` int(0) NULL DEFAULT NULL,
  `difference` int(0) NULL DEFAULT NULL,
  `createTime` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 89 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cashier_inventory_goods
-- ----------------------------
INSERT INTO `cashier_inventory_goods` VALUES (83, 'PD1645199551553', '苹果', 'ZDY1644545950057', 1.00, 1, 1111, 111, 1, '2022-02-18 23:52:31');
INSERT INTO `cashier_inventory_goods` VALUES (84, 'PD1645199551553', '梨', 'ZDY1644550775884', 1.00, 1, 12, 12, 0, '2022-02-18 23:52:31');
INSERT INTO `cashier_inventory_goods` VALUES (85, 'PD1645199551553', '橘子', 'ZDY1644568609024', 2.00, 1, 1111, 1111, 0, '2022-02-18 23:52:31');
INSERT INTO `cashier_inventory_goods` VALUES (86, 'PD1645199849712', '苹果', 'ZDY1644545950057', 1.00, 1, 1111, 1111, 1, '2022-02-18 23:57:29');
INSERT INTO `cashier_inventory_goods` VALUES (87, 'PD1645199849712', '梨', 'ZDY1644550775884', 1.00, 1, 12, 12, 0, '2022-02-18 23:57:29');
INSERT INTO `cashier_inventory_goods` VALUES (88, 'PD1645199849712', '橘子', 'ZDY1644568609024', 2.00, 1, 1111, 1111, 0, '2022-02-18 23:57:29');
INSERT INTO `cashier_inventory_goods` VALUES (89, 'PD1645284886383', '苹果', 'ZDY1644545950057', 1.00, 1, 1099, 1099, 7, '2022-02-19 23:34:46');

-- ----------------------------
-- Table structure for cashier_loss_goods
-- ----------------------------
DROP TABLE IF EXISTS `cashier_loss_goods`;
CREATE TABLE `cashier_loss_goods`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `goodsName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goodsCode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `purchasePrice` decimal(5, 2) NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `reportLoss` int(0) NULL DEFAULT NULL,
  `ramerk` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createTime` timestamp(0) NULL DEFAULT NULL,
  `uid` bigint(0) NULL DEFAULT NULL,
  `uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `parentId` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cashier_loss_goods
-- ----------------------------
INSERT INTO `cashier_loss_goods` VALUES (1, '苹果', 'ZDY1644545950057', 1.00, 1, 1, '损坏', '2022-02-11 16:11:38', 1, 'admin', 1);
INSERT INTO `cashier_loss_goods` VALUES (2, '苹果', 'ZDY1644545950057', 1.00, 1, 1, '破了', '2022-02-19 22:38:05', 3, '00002', 2);
INSERT INTO `cashier_loss_goods` VALUES (3, '苹果', 'ZDY1644545950057', 1.00, 1, 1, '坏了', '2022-02-19 22:42:56', 2, '00001', 2);

-- ----------------------------
-- Table structure for cashier_menu
-- ----------------------------
DROP TABLE IF EXISTS `cashier_menu`;
CREATE TABLE `cashier_menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `menuUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `menuType` int(0) NULL DEFAULT NULL COMMENT '管理员权限',
  `uiType` int(0) NULL DEFAULT NULL COMMENT '店长权限',
  `type` int(0) NULL DEFAULT NULL COMMENT '店员权限',
  `sort` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cashier_menu
-- ----------------------------
INSERT INTO `cashier_menu` VALUES (1, '收银', 'images/on_21_03.jpg', 'index.html', 1, 2, 3, 1);
INSERT INTO `cashier_menu` VALUES (2, '商品', 'images/on_10_03.jpg', 'shangpinbianji.html', 1, 2, NULL, 2);
INSERT INTO `cashier_menu` VALUES (3, '进货', 'images/on_8_03.jpg', 'jinhuo.html', 1, 2, NULL, 3);
INSERT INTO `cashier_menu` VALUES (4, '退货', 'images/on_4_03.png', 'duihuo.html', 1, 2, 3, 4);
INSERT INTO `cashier_menu` VALUES (5, '盘点', 'images/on_5_03.jpg', 'pandian.html', 1, 2, NULL, 5);
INSERT INTO `cashier_menu` VALUES (6, '报损', 'images/on_13_03.jpg', 'shangpinbaocun.html', 1, 2, 3, 6);
INSERT INTO `cashier_menu` VALUES (7, '销售单据', 'images/on_3_03.jpg', 'xiaoshoudanju.html', 1, 2, NULL, 7);
INSERT INTO `cashier_menu` VALUES (8, '系统设置', 'images/on_11_03.jpg', 'xitongshezhi.html', 1, NULL, NULL, 8);
INSERT INTO `cashier_menu` VALUES (9, '报表', 'images/on_22_03.png', 'shangpinbaobiao.html', 1, 2, NULL, 9);

-- ----------------------------
-- Table structure for cashier_order
-- ----------------------------
DROP TABLE IF EXISTS `cashier_order`;
CREATE TABLE `cashier_order`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `orderId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `amount` decimal(5, 2) NULL DEFAULT NULL,
  `fictitious` decimal(5, 2) NULL DEFAULT NULL,
  `actualPay` decimal(5, 2) NULL DEFAULT NULL,
  `num` int(0) NULL DEFAULT NULL,
  `singleNum` int(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `unfinished` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `orderTime` timestamp(0) NULL DEFAULT NULL,
  `updateTime` timestamp(0) NULL DEFAULT NULL,
  `ramerk` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `uid` bigint(0) NULL DEFAULT NULL,
  `uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `parentId` bigint(0) NULL DEFAULT NULL,
  `giveChange` decimal(5, 2) NULL DEFAULT NULL,
  `payType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cashier_order
-- ----------------------------
INSERT INTO `cashier_order` VALUES (11, 'SY1644562269887', 1.00, 0.00, 1.00, 1, 1, 0, '苹果*1', '2022-02-11 14:51:10', '2022-02-11 14:51:10', NULL, 1, 'admin', 1, NULL, NULL);
INSERT INTO `cashier_order` VALUES (12, 'SY1644562934308', 1.00, 0.00, 1.00, 1, 1, 1, '苹果*1', '2022-02-11 15:02:14', '2022-02-11 15:03:39', NULL, 1, 'admin', 1, 0.00, 'XJ');
INSERT INTO `cashier_order` VALUES (13, 'SY1645200349505', 1.00, 0.00, 1.00, 1, 1, 0, '苹果*1', '2022-02-19 00:05:50', '2022-02-19 00:05:50', NULL, 1, 'admin', 1, NULL, NULL);
INSERT INTO `cashier_order` VALUES (14, 'SY1645200753730', 1.00, 0.00, 2.00, 1, 1, 1, '苹果*1', '2022-02-19 00:12:34', '2022-02-19 00:20:00', NULL, 1, 'admin', 1, 0.00, 'XJ');
INSERT INTO `cashier_order` VALUES (15, 'SY1645201723766', 1.00, 0.00, 1.00, 1, 1, 0, '苹果*1', '2022-02-19 00:28:44', '2022-02-19 00:28:44', NULL, 1, 'admin', 1, NULL, NULL);
INSERT INTO `cashier_order` VALUES (16, 'SY1645202044940', 1.00, 0.25, 2.00, 1, 1, 1, '苹果*1', '2022-02-19 00:34:05', '2022-02-19 00:36:28', NULL, 1, 'admin', 1, 0.00, 'YHK');
INSERT INTO `cashier_order` VALUES (17, 'SY1645202322769', 1.00, 0.00, 1.00, 1, 1, 0, '苹果*1', '2022-02-19 00:38:43', '2022-02-19 00:38:43', NULL, 1, 'admin', 1, NULL, NULL);
INSERT INTO `cashier_order` VALUES (18, 'SY1645202502843', 1.00, 0.00, 1.00, 1, 1, 0, '苹果*1', '2022-02-19 00:41:43', '2022-02-19 00:41:43', NULL, 1, 'admin', 1, NULL, NULL);
INSERT INTO `cashier_order` VALUES (19, 'SY1645202742659', 1.00, 0.00, 1.00, 1, 1, 0, '苹果*1', '2022-02-19 00:45:43', '2022-02-19 00:45:43', NULL, 1, 'admin', 1, NULL, NULL);
INSERT INTO `cashier_order` VALUES (20, 'SY1645203156816', 1.00, 0.00, 1.00, 1, 1, 0, '苹果*1', '2022-02-19 00:52:37', '2022-02-19 00:52:37', NULL, 1, 'admin', 1, NULL, NULL);
INSERT INTO `cashier_order` VALUES (21, 'SY1645203287090', 1.00, 0.00, 3.00, 1, 1, 1, '苹果*1', '2022-02-19 00:54:47', '2022-02-19 00:55:14', NULL, 1, 'admin', 1, 2.00, 'XJ');
INSERT INTO `cashier_order` VALUES (22, 'SY1645254912446', 3.00, 0.60, 2.40, 3, 1, 1, '苹果*3', '2022-02-19 15:15:12', '2022-02-19 15:15:25', NULL, 1, 'admin', 1, 0.00, 'XJ');
INSERT INTO `cashier_order` VALUES (23, 'SY1645255183717', 1.00, 0.20, 0.80, 1, 1, 1, '苹果*1', '2022-02-19 15:19:44', '2022-02-19 15:20:21', NULL, 1, 'admin', 1, 0.00, 'XJ');
INSERT INTO `cashier_order` VALUES (24, 'SY1645255395181', 4.00, 0.00, 4.00, 4, 1, 0, '苹果*4', '2022-02-19 15:23:25', '2022-02-19 15:23:25', NULL, 1, 'admin', 1, NULL, NULL);
INSERT INTO `cashier_order` VALUES (25, 'SY1645256218844', 1.00, 0.20, 0.80, 1, 1, 1, '苹果*1', '2022-02-19 15:37:01', '2022-02-19 15:37:06', NULL, 1, 'admin', 1, 0.00, 'XJ');
INSERT INTO `cashier_order` VALUES (26, 'SY1645256628151', 1.00, 0.10, 0.90, 1, 1, 1, '苹果*1', '2022-02-19 15:43:51', '2022-02-19 15:43:56', NULL, 1, 'admin', 1, 0.00, 'XJ');
INSERT INTO `cashier_order` VALUES (27, 'SY1645256724560', 1.00, 0.30, 0.70, 1, 1, 1, '苹果*1', '2022-02-19 15:45:25', '2022-02-19 15:45:30', NULL, 1, 'admin', 1, 0.00, 'XJ');
INSERT INTO `cashier_order` VALUES (28, 'SY1645257190368', 9.00, 1.80, 7.20, 9, 1, 4, '苹果*9', '2022-02-19 15:53:10', '2022-02-19 15:53:17', NULL, 1, 'admin', 1, 0.00, 'XJ');
INSERT INTO `cashier_order` VALUES (29, 'SY1645281277225', 1.00, 0.00, 1.00, 1, 1, 0, '苹果*1', '2022-02-19 22:34:37', '2022-02-19 22:34:37', NULL, 3, '00002', 2, NULL, NULL);
INSERT INTO `cashier_order` VALUES (30, 'SY1645281295879', 1.00, 0.50, 0.50, 1, 1, 1, '苹果*1', '2022-02-19 22:34:56', '2022-02-19 22:35:02', NULL, 3, '00002', 2, 0.00, 'XJ');
INSERT INTO `cashier_order` VALUES (31, 'SY1645285112195', 4.00, 0.00, 4.00, 4, 1, 1, '苹果*4', '2022-02-19 23:38:32', '2022-02-19 23:38:34', NULL, 3, '00002', 2, 0.00, 'XJ');
INSERT INTO `cashier_order` VALUES (32, 'SY1645285328174', 9.00, 0.00, 9.00, 9, 1, 1, '苹果*9', '2022-02-19 23:42:08', '2022-02-19 23:42:14', NULL, 2, '00001', 2, 0.00, 'XJ');
INSERT INTO `cashier_order` VALUES (33, 'SY1645285581964', 1.00, 0.00, 1.00, 1, 1, 1, '苹果*1', '2022-02-19 23:46:22', '2022-02-19 23:46:24', NULL, 2, '00001', 2, 0.00, 'XJ');

-- ----------------------------
-- Table structure for cashier_order_goods
-- ----------------------------
DROP TABLE IF EXISTS `cashier_order_goods`;
CREATE TABLE `cashier_order_goods`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `orderId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goodsCode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goodsName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `num` int(0) NULL DEFAULT NULL,
  `costPrice` decimal(5, 2) NULL DEFAULT NULL,
  `discount` decimal(5, 2) NULL DEFAULT NULL,
  `currentPrice` decimal(5, 2) NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `subtotal` decimal(5, 2) NULL DEFAULT NULL,
  `createTime` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cashier_order_goods
-- ----------------------------
INSERT INTO `cashier_order_goods` VALUES (11, 'SY1644562269887', 'ZDY1644545950057', '苹果', 1, 1.00, 0.00, 1.00, 1, 1.00, '2022-02-11 14:51:13');
INSERT INTO `cashier_order_goods` VALUES (12, 'SY1644562934308', 'ZDY1644545950057', '苹果', 1, 1.00, 0.00, 1.00, 1, 1.00, '2022-02-11 15:02:19');
INSERT INTO `cashier_order_goods` VALUES (13, 'SY1645200349505', 'ZDY1644545950057', '苹果', 1, 1.00, 0.00, 1.00, 1, 1.00, '2022-02-19 00:06:03');
INSERT INTO `cashier_order_goods` VALUES (14, 'SY1645200753730', 'ZDY1644545950057', '苹果', 1, 1.00, 0.00, 1.00, 1, 1.00, '2022-02-19 00:12:34');
INSERT INTO `cashier_order_goods` VALUES (15, 'SY1645201723766', 'ZDY1644545950057', '苹果', 1, 1.00, 0.00, 1.00, 1, 1.00, '2022-02-19 00:28:44');
INSERT INTO `cashier_order_goods` VALUES (16, 'SY1645202044940', 'ZDY1644545950057', '苹果', 1, 1.00, 0.00, 1.00, 1, 1.00, '2022-02-19 00:34:05');
INSERT INTO `cashier_order_goods` VALUES (17, 'SY1645202322769', 'ZDY1644545950057', '苹果', 1, 1.00, 0.00, 1.00, 1, 1.00, '2022-02-19 00:38:43');
INSERT INTO `cashier_order_goods` VALUES (18, 'SY1645202502843', 'ZDY1644545950057', '苹果', 1, 1.00, 0.00, 1.00, 1, 1.00, '2022-02-19 00:41:43');
INSERT INTO `cashier_order_goods` VALUES (19, 'SY1645202742659', 'ZDY1644545950057', '苹果', 1, 1.00, 0.00, 1.00, 1, 1.00, '2022-02-19 00:45:43');
INSERT INTO `cashier_order_goods` VALUES (20, 'SY1645203156816', 'ZDY1644545950057', '苹果', 1, 1.00, 0.00, 1.00, 1, 1.00, '2022-02-19 00:52:37');
INSERT INTO `cashier_order_goods` VALUES (21, 'SY1645203287090', 'ZDY1644545950057', '苹果', 1, 1.00, 0.00, 1.00, 1, 1.00, '2022-02-19 00:54:47');
INSERT INTO `cashier_order_goods` VALUES (22, 'SY1645254912446', 'ZDY1644545950057', '苹果', 3, 1.00, 0.00, 1.00, 1, 3.00, '2022-02-19 15:15:12');
INSERT INTO `cashier_order_goods` VALUES (23, 'SY1645255183717', 'ZDY1644545950057', '苹果', 1, 1.00, 0.00, 1.00, 1, 1.00, '2022-02-19 15:19:44');
INSERT INTO `cashier_order_goods` VALUES (24, 'SY1645255395181', 'ZDY1644545950057', '苹果', 4, 1.00, 0.00, 1.00, 1, 4.00, '2022-02-19 15:24:05');
INSERT INTO `cashier_order_goods` VALUES (25, 'SY1645256218844', 'ZDY1644545950057', '苹果', 1, 1.00, 0.00, 1.00, 1, 1.00, '2022-02-19 15:37:01');
INSERT INTO `cashier_order_goods` VALUES (26, 'SY1645256628151', 'ZDY1644545950057', '苹果', 1, 1.00, 0.00, 1.00, 1, 1.00, '2022-02-19 15:43:51');
INSERT INTO `cashier_order_goods` VALUES (27, 'SY1645256724560', 'ZDY1644545950057', '苹果', 1, 1.00, 0.00, 1.00, 1, 1.00, '2022-02-19 15:45:25');
INSERT INTO `cashier_order_goods` VALUES (28, 'SY1645257190368', 'ZDY1644545950057', '苹果', 9, 1.00, 1.80, 1.00, 1, 7.20, '2022-02-19 15:53:10');
INSERT INTO `cashier_order_goods` VALUES (29, 'SY1645281277225', 'ZDY1644545950057', '苹果', 1, 1.00, 0.00, 1.00, 1, 1.00, '2022-02-19 22:34:37');
INSERT INTO `cashier_order_goods` VALUES (30, 'SY1645281295879', 'ZDY1644545950057', '苹果', 1, 1.00, 0.50, 1.00, 1, 0.50, '2022-02-19 22:34:56');
INSERT INTO `cashier_order_goods` VALUES (31, 'SY1645285112195', 'ZDY1644545950057', '苹果', 4, 1.00, 0.00, 1.00, 1, 4.00, '2022-02-19 23:38:32');
INSERT INTO `cashier_order_goods` VALUES (32, 'SY1645285328174', 'ZDY1644545950057', '苹果', 9, 1.00, 0.00, 1.00, 1, 9.00, '2022-02-19 23:42:08');
INSERT INTO `cashier_order_goods` VALUES (33, 'SY1645285581964', 'ZDY1644545950057', '苹果', 1, 1.00, 0.00, 1.00, 1, 1.00, '2022-02-19 23:46:22');

-- ----------------------------
-- Table structure for cashier_pay_type
-- ----------------------------
DROP TABLE IF EXISTS `cashier_pay_type`;
CREATE TABLE `cashier_pay_type`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `type` int(0) NULL DEFAULT NULL,
  `acct` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `updateTime` timestamp(0) NULL DEFAULT NULL,
  `uid` bigint(0) NULL DEFAULT NULL,
  `uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `parentId` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cashier_user
-- ----------------------------
DROP TABLE IF EXISTS `cashier_user`;
CREATE TABLE `cashier_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL COMMENT '1-超级管理员，2-店长，3-店员',
  `createTime` timestamp(0) NULL DEFAULT NULL,
  `updateTime` timestamp(0) NULL DEFAULT NULL,
  `loginTime` timestamp(0) NULL DEFAULT NULL,
  `parentId` bigint(0) NULL DEFAULT NULL,
  `createId` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cashier_user
-- ----------------------------
INSERT INTO `cashier_user` VALUES (1, 'admin', '123', 1, '2022-02-11 10:56:40', NULL, '2022-02-19 23:59:59', 1, 1);
INSERT INTO `cashier_user` VALUES (2, '00001', '123', 2, '2022-02-11 10:56:40', NULL, '2022-02-19 23:38:41', 2, 1);
INSERT INTO `cashier_user` VALUES (3, '00002', '123', 3, '2022-02-19 14:54:52', NULL, '2022-02-19 23:38:21', 2, 2);

SET FOREIGN_KEY_CHECKS = 1;
