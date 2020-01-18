/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : bookshop

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 08/01/2020 17:00:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (92, '文字的张力', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/6c6ed76c-fc92-470a-ae5e-5823724f70d9.jpg');
INSERT INTO `category` VALUES (93, '艺术的边界', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/a72ad51a-743e-4b17-9437-ed75114c01f6.jpg');
INSERT INTO `category` VALUES (94, '孩子的书架', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/927b2347-3558-4926-94ef-29dd407ef343.jpg');
INSERT INTO `category` VALUES (95, '认知的维度', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/7dc8e688-2cd2-4e7a-9b30-48a1d6e94aba.jpg');
INSERT INTO `category` VALUES (96, '生活的况趣', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/2223dfb0-12dc-4ac3-9382-8ed7fc60da01.jpg');

-- ----------------------------
-- Table structure for orderItem
-- ----------------------------
DROP TABLE IF EXISTS `orderItem`;
CREATE TABLE `orderItem`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL,
  `oid` int(11) NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  `number` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_orderitem_user`(`uid`) USING BTREE,
  INDEX `fk_orderitem_product`(`pid`) USING BTREE,
  INDEX `fk_orderitem_order`(`oid`) USING BTREE,
  CONSTRAINT `fk_orderitem_order` FOREIGN KEY (`oid`) REFERENCES `order_` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_orderitem_product` FOREIGN KEY (`pid`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_orderitem_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderItem
-- ----------------------------
INSERT INTO `orderItem` VALUES (4, 24, NULL, NULL, 11);
INSERT INTO `orderItem` VALUES (5, 10, NULL, NULL, 2);
INSERT INTO `orderItem` VALUES (6, 11, NULL, NULL, 4);
INSERT INTO `orderItem` VALUES (7, 19, NULL, NULL, 1);
INSERT INTO `orderItem` VALUES (8, 22, NULL, NULL, 1);
INSERT INTO `orderItem` VALUES (9, 17, NULL, NULL, 2);
INSERT INTO `orderItem` VALUES (10, 18, NULL, NULL, 7);
INSERT INTO `orderItem` VALUES (11, 18, 3, 54, 1);
INSERT INTO `orderItem` VALUES (12, 20, 13, 52, 1);
INSERT INTO `orderItem` VALUES (13, 13, NULL, NULL, 1);
INSERT INTO `orderItem` VALUES (14, 15, 14, 55, 1);
INSERT INTO `orderItem` VALUES (15, 16, NULL, NULL, 11);
INSERT INTO `orderItem` VALUES (16, 10, 15, 55, 3);
INSERT INTO `orderItem` VALUES (17, 14, NULL, 55, 3);

-- ----------------------------
-- Table structure for order_
-- ----------------------------
DROP TABLE IF EXISTS `order_`;
CREATE TABLE `order_`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `recipient` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  `payTime` datetime(0) NULL DEFAULT NULL,
  `deliverTime` datetime(0) NULL DEFAULT NULL,
  `dealTime` datetime(0) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_order_user`(`uid`) USING BTREE,
  CONSTRAINT `fk_order_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_
-- ----------------------------
INSERT INTO `order_` VALUES (3, '20200107170945129878', '', '', '', '2020-01-07 03:09:45', NULL, NULL, NULL, 'waitPay', 54);
INSERT INTO `order_` VALUES (4, '202001072016578569086', '远洋太古里', 'x教授', '12345678901', '2020-01-07 06:16:58', NULL, NULL, NULL, 'waitPay', 52);
INSERT INTO `order_` VALUES (5, '202001072020517284471', '远洋太古里', 'x教授', '12345678901', '2020-01-07 06:20:52', NULL, NULL, NULL, 'waitPay', 52);
INSERT INTO `order_` VALUES (6, '202001072021550481099', '木叶村', '漩涡鸣人', '1008611', '2020-01-07 06:21:55', NULL, NULL, NULL, 'waitPay', 52);
INSERT INTO `order_` VALUES (7, '202001072024094913344', '拙政园', '恭亲王', '963258741', '2020-01-07 06:24:09', NULL, NULL, NULL, 'waitPay', 52);
INSERT INTO `order_` VALUES (8, '202001072036159949591', '拙政园', '恭亲王', '963258741', '2020-01-07 06:36:16', NULL, NULL, NULL, 'waitPay', 52);
INSERT INTO `order_` VALUES (9, '202001072037239809982', '', '', '', '2020-01-07 06:37:24', NULL, NULL, NULL, 'waitPay', 52);
INSERT INTO `order_` VALUES (10, '202001072038413428256', '', '', '', '2020-01-07 06:38:41', NULL, NULL, NULL, 'waitPay', 52);
INSERT INTO `order_` VALUES (11, '202001072039001913764', '', '', '', '2020-01-07 06:39:00', NULL, NULL, NULL, 'waitPay', 52);
INSERT INTO `order_` VALUES (12, '202001072048009875747', '毁诺城', '息大娘', '1008611', '2020-01-07 06:48:01', NULL, NULL, NULL, 'waitDelivery', 52);
INSERT INTO `order_` VALUES (13, '202001072058541985851', '春熙路', '燕小六', '18957789651', '2020-01-07 06:58:54', NULL, NULL, NULL, 'waitPay', 52);
INSERT INTO `order_` VALUES (14, '202001081342056547954', '象牙山', '赵四', '123456', '2020-01-07 23:42:06', '2020-01-07 23:42:20', NULL, NULL, 'waitDelivery', 55);
INSERT INTO `order_` VALUES (15, '202001081620355292002', 'ftydfgt', 'xtsd', 'dty df', '2020-01-08 02:20:36', '2020-01-08 02:20:38', NULL, NULL, 'waitDelivery', 55);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `publisher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `discount` float NULL DEFAULT NULL,
  `stock` int(11) NULL DEFAULT NULL,
  `cid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_product_category`(`cid`) USING BTREE,
  CONSTRAINT `fk_product_category` FOREIGN KEY (`cid`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (10, '算法导论', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/f661b1da-913c-4dcf-a39d-34705294e5ac.jpg', 60.2, 'Thomas H.Corme', '机械工业出版社', 4.7, 99, 95);
INSERT INTO `product` VALUES (11, 'C程序设计语言', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/d27d9404-cbfc-4201-be11-5db1d3ed6e23.jpg', 7.4, 'Brian W Kernighan', '机械工业出版社', 2.5, 45, 95);
INSERT INTO `product` VALUES (12, '呐喊', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/8e49fce8-83aa-47ba-97f7-f725e35b794e.jpg', 10.5, '鲁迅', '人民文学出版社', 7, 1949, 92);
INSERT INTO `product` VALUES (13, '红楼梦', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/35ce3083-1fc1-4761-8eaf-6f43325db190.jpg', 26.4, '曹雪芹', '人民文学出版社', 2.4, 11, 92);
INSERT INTO `product` VALUES (14, '了不起的盖茨比', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/655dd447-8753-4056-9748-b5b9ae704369.jpg', 7.8, '菲茨杰拉德', '天津人民出版社', 5.6, 17, 92);
INSERT INTO `product` VALUES (15, '百年孤独', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/f3e5177f-81eb-41e0-b639-5e9684693a14.jpg', 26.4, '加西亚·马尔克斯', '南海出版公司', 3.7, 58, 92);
INSERT INTO `product` VALUES (16, '西西弗神话', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/61040bff-125e-4490-8b24-f7e6bc9b792f.jpg', 14.4, '加缪', '译林出版社', 4.8, 17, 92);
INSERT INTO `product` VALUES (17, '计算机程序的构造与解释', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/161b12eb-7ef5-4c7d-9f1e-3e24a18fe7ee.jpg', 31, 'Harold Abelson', '机械工业出版社', 6.5, 36, 95);
INSERT INTO `product` VALUES (18, '人月神话', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/9b420335-95d5-4dcf-b436-4911c8dc11a8.jpg', 34.7, '弗雷德里克.布鲁克斯', '清华大学出版社', 2.1, 35, 95);
INSERT INTO `product` VALUES (19, '代码大全', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/35d9e0fb-3875-4458-9b94-34ca99579f53.jpg', 64, '史蒂夫·迈克康奈尔', '电子工业出版社', 4.2, 25, 95);
INSERT INTO `product` VALUES (20, '音乐即自由', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/88ad5205-d0bb-49da-8cd8-832e73e9f31d.jpg', 15.6, '坂本龙一', '中信出版集团', 3.6, 12, 93);
INSERT INTO `product` VALUES (21, '故事', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/fa60844a-6ea9-41e0-a330-ce84e1f8480b.jpg', 36, '罗伯特·麦基', '天津人民出版社', 4.8, 35, 93);
INSERT INTO `product` VALUES (22, '认识电影', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/38110cda-4d56-4d07-9656-50af1410cfe2.jpg', 37, '路易斯·贾内梯', '世界图书出版社', 3.9, 64, 93);
INSERT INTO `product` VALUES (23, '写给大家的西方美术史', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/b44555d0-fbb6-4257-a4df-6d8544f880bc.jpg', 31.4, '蒋勋', '湖南美术出版社', 8.1, 98, 93);
INSERT INTO `product` VALUES (24, '鱼翅与花椒', 'https://bookshop-bucket.oss-cn-qingdao.aliyuncs.com/s29767895.jpg', 15.3, '扶霞·邓洛普', '上海译文出版社', 4.9, 17, 96);

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  `pid` int(11) NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_review_product`(`pid`) USING BTREE,
  INDEX `fk_review_user`(`uid`) USING BTREE,
  CONSTRAINT `fk_review_product` FOREIGN KEY (`pid`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_review_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of review
-- ----------------------------
INSERT INTO `review` VALUES (1, '一本非常好的书', 25, 10, '2020-01-04 00:00:00');
INSERT INTO `review` VALUES (2, '程序 = 数据结构 + 算法，和 SICP 一样，程序员必读。学习算法就是学习卓越的解决问题的思想发明。但是千万别被很多人的推崇吓到，实际上算法导论是一本大而全的算法入门书，当然证明和习题都让人望而生畏。', 27, 10, '2020-01-05 00:00:00');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '测试用户1', '123456', '123');
INSERT INTO `user` VALUES (2, '测试用户2', '123456', '123');
INSERT INTO `user` VALUES (3, '测试用户3', '123456', '123');
INSERT INTO `user` VALUES (4, '测试用户4', '123456', '123');
INSERT INTO `user` VALUES (5, '测试用户5', '123456', '123');
INSERT INTO `user` VALUES (6, '测试用户6', '123456', '123');
INSERT INTO `user` VALUES (7, '测试用户7', '123456', '123');
INSERT INTO `user` VALUES (8, '测试用户8', '123456', '123');
INSERT INTO `user` VALUES (9, '测试用户9', '123456', '123');
INSERT INTO `user` VALUES (10, '测试用户10', '123456', '123');
INSERT INTO `user` VALUES (11, '测试用户11', '123456', '123');
INSERT INTO `user` VALUES (12, '测试用户12', '123456', '123');
INSERT INTO `user` VALUES (13, '测试用户13', '123456', '123');
INSERT INTO `user` VALUES (14, '测试用户14', '123456', '123');
INSERT INTO `user` VALUES (15, '测试用户15', '123456', '123');
INSERT INTO `user` VALUES (16, '测试用户16', '123456', '123');
INSERT INTO `user` VALUES (17, '测试用户17', '123456', '123');
INSERT INTO `user` VALUES (18, '测试用户18', '123456', '123');
INSERT INTO `user` VALUES (19, '测试用户19', '123456', '123');
INSERT INTO `user` VALUES (20, '测试用户20', '123456', '123');
INSERT INTO `user` VALUES (21, '测试用户21', '123456', '123');
INSERT INTO `user` VALUES (22, '测试用户22', '123456', '123');
INSERT INTO `user` VALUES (23, '测试用户23', '123456', '123');
INSERT INTO `user` VALUES (24, '测试用户24', '123456', '123');
INSERT INTO `user` VALUES (25, '测试用户25', '123456', '123');
INSERT INTO `user` VALUES (26, '测试用户26', '123456', '123');
INSERT INTO `user` VALUES (27, '测试用户27', '123456', '123');
INSERT INTO `user` VALUES (28, '测试用户28', '123456', '123');
INSERT INTO `user` VALUES (29, '测试用户29', '123456', '123');
INSERT INTO `user` VALUES (30, '测试用户30', '123456', '123');
INSERT INTO `user` VALUES (31, '测试用户31', '123456', '123');
INSERT INTO `user` VALUES (32, '测试用户32', '123456', '123');
INSERT INTO `user` VALUES (33, '测试用户33', '123456', '123');
INSERT INTO `user` VALUES (34, '测试用户34', '123456', '123');
INSERT INTO `user` VALUES (35, '测试用户35', '123456', '123');
INSERT INTO `user` VALUES (36, '测试用户36', '123456', '123');
INSERT INTO `user` VALUES (37, '测试用户37', '123456', '123');
INSERT INTO `user` VALUES (38, '测试用户38', '123456', '123');
INSERT INTO `user` VALUES (39, '测试用户39', '123456', '123');
INSERT INTO `user` VALUES (40, '测试用户40', '123456', '123');
INSERT INTO `user` VALUES (41, '测试用户41', '123456', '123');
INSERT INTO `user` VALUES (42, '测试用户42', '123456', '123');
INSERT INTO `user` VALUES (43, '测试用户43', '123456', '123');
INSERT INTO `user` VALUES (44, '测试用户44', '123456', '123');
INSERT INTO `user` VALUES (45, '测试用户45', '123456', '123');
INSERT INTO `user` VALUES (46, '测试用户46', '123456', '123');
INSERT INTO `user` VALUES (47, '测试用户47', '123456', '123');
INSERT INTO `user` VALUES (48, '测试用户48', '123456', '123');
INSERT INTO `user` VALUES (49, '测试用户49', '123456', '123');
INSERT INTO `user` VALUES (50, '测试用户50', '123456', '123');
INSERT INTO `user` VALUES (51, '007', '123456', NULL);
INSERT INTO `user` VALUES (52, '张小花', '123456', NULL);
INSERT INTO `user` VALUES (53, '赵大宝', '123456', NULL);
INSERT INTO `user` VALUES (54, '唐伯虎', '123456', NULL);
INSERT INTO `user` VALUES (55, '赵四', '71fb6b2b0d8485d3c44992bb2fdda50c', 'p4PZ0nOJW6ULEplLznHgfQ==');

SET FOREIGN_KEY_CHECKS = 1;
