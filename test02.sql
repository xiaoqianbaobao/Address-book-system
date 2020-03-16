/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : test02

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-01-23 14:39:19
*/
CREATE DATABASE test02;
USE test02
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_communication_info`
-- ----------------------------
DROP TABLE IF EXISTS `tb_communication_info`;
CREATE TABLE `tb_communication_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `office_phone` varchar(20) DEFAULT NULL,
  `mobile_phone` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `qq` varchar(15) DEFAULT NULL,
  `available` char(1) NOT NULL DEFAULT 'Y',
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  `operator` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_communication_info
-- ----------------------------
INSERT INTO `tb_communication_info` VALUES ('1', '1', '123456', '239665', 'zhangsan@126.com', '98765432', 'Y', '2015-11-11 17:52:23', '2017-01-23 14:32:53', 'admin');
INSERT INTO `tb_communication_info` VALUES ('3', '4', '123123', '345345', 'sdfsdf@123.com', '234234234', 'Y', '2015-11-11 17:52:23', '2016-08-25 16:18:24', 'admin');
INSERT INTO `tb_communication_info` VALUES ('4', '5', '152245226', '1264254166', 'mmmm@mmasd.com', '211543254', 'Y', '2015-11-11 17:52:23', '2016-08-25 16:37:08', 'admin');
INSERT INTO `tb_communication_info` VALUES ('5', '5', '9546215233', '2112423431', 'oooo@adsd.com', '6984125451', 'Y', '2015-11-11 17:52:23', '2016-08-25 16:37:08', 'admin');
INSERT INTO `tb_communication_info` VALUES ('10', '5', '123123123123', '00000000000', 'aaa@aaa.com', '111111', 'N', '2015-11-11 17:52:23', '2016-08-25 16:37:08', 'admin');
INSERT INTO `tb_communication_info` VALUES ('12', '10', '23432423423', '234234234', '213@123.com', '123123', 'N', '2015-11-11 17:52:23', '2016-07-27 08:44:38', 'admin');
INSERT INTO `tb_communication_info` VALUES ('13', '7', '123', '123', 'ASD@ASD.COM', '123123', 'N', '2015-11-11 19:29:26', '2016-07-27 08:43:48', 'admin');
INSERT INTO `tb_communication_info` VALUES ('24', '16', '123456789', '18611', '', '', 'Y', '2016-07-27 08:45:31', '2016-07-27 08:45:31', 'admin');
INSERT INTO `tb_communication_info` VALUES ('26', '1', '123', '123123123', 'zhangsan@126.com', '', 'Y', '2016-08-16 16:46:31', '2017-01-23 14:32:53', 'admin');
INSERT INTO `tb_communication_info` VALUES ('31', '6', '010010101', '0202020202', 'sunwukong@xitian.com', '', 'Y', '2016-08-25 16:26:17', '2017-01-23 13:55:14', 'admin');
INSERT INTO `tb_communication_info` VALUES ('32', '8', '123456', '654321', '', '', 'Y', '2016-08-26 09:22:20', '2016-08-26 09:22:30', 'admin');
INSERT INTO `tb_communication_info` VALUES ('33', '6', '999999999', '0303030303', 'sunwukong@xitian.com', '', 'N', '2017-01-23 13:54:30', '2017-01-23 13:55:14', 'admin');
INSERT INTO `tb_communication_info` VALUES ('34', '14', '1122333', '5566789', 'lisi@163.com', '', 'Y', '2017-01-23 14:04:19', '2017-01-23 14:17:08', 'admin');
INSERT INTO `tb_communication_info` VALUES ('36', '14', '0000001', '7777551', 'lisi@126.com', '', 'N', '2017-01-23 14:08:08', '2017-01-23 14:09:05', 'admin');
INSERT INTO `tb_communication_info` VALUES ('39', '1', '9999887', '1010111', 'zhangsan@163.com', '', 'N', '2017-01-23 14:32:27', '2017-01-23 14:32:53', 'admin');

-- ----------------------------
-- Table structure for `tb_customer_info`
-- ----------------------------
DROP TABLE IF EXISTS `tb_customer_info`;
CREATE TABLE `tb_customer_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `work_unit` varchar(300) DEFAULT NULL,
  `work_addr` varchar(300) DEFAULT NULL,
  `home_addr` varchar(300) DEFAULT NULL,
  `role` varchar(100) DEFAULT NULL,
  `available` char(1) NOT NULL DEFAULT 'Y',
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  `operator` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_customer_info
-- ----------------------------
INSERT INTO `tb_customer_info` VALUES ('1', '张三', '女', '1985-11-10', '中国电信', '北京市', '北京市', '经理', 'Y', '2015-11-11 17:52:17', '2017-01-23 14:32:53', 'admin');
INSERT INTO `tb_customer_info` VALUES ('3', '马化腾', '男', '1971-10-29', '腾讯', '不详', '不详', 'CEO', 'Y', '2015-11-11 17:52:17', '2017-01-23 09:29:10', 'admin');
INSERT INTO `tb_customer_info` VALUES ('4', '乔布斯', '男', '1955-02-24', '苹果公司', '美国', '美国', 'CEO', 'Y', '2015-11-11 17:52:17', '2017-01-23 09:29:10', 'admin');
INSERT INTO `tb_customer_info` VALUES ('5', '麦当娜', '女', '1958-08-16', '娱乐公司', '美国', '美国', '歌手', 'Y', '2015-11-11 17:52:17', '2017-01-23 09:29:10', 'admin');
INSERT INTO `tb_customer_info` VALUES ('6', '孙悟空', '男', '1900-01-01', '天宫', '天宫', '花果山', '神仙', 'Y', '2015-11-11 17:52:17', '2017-01-23 13:55:14', 'admin');
INSERT INTO `tb_customer_info` VALUES ('7', '麦当娜', '女', '1958-08-16', '娱乐公司', '美国', '美国', '职员', 'Y', '2015-11-11 17:52:17', '2017-01-23 09:29:10', 'admin');
INSERT INTO `tb_customer_info` VALUES ('8', '王富贵', '男', '2000-08-02', '富贵院', '富贵村', '富贵村有钱屯', '董事长', 'Y', '2015-11-11 17:52:17', '2016-08-26 09:22:30', 'admin');
INSERT INTO `tb_customer_info` VALUES ('10', '超人', '男', '1970-01-12', '美国', '美国', '美国', '英雄', 'Y', '2015-11-11 17:52:17', '2015-11-11 17:52:17', 'admin');
INSERT INTO `tb_customer_info` VALUES ('14', '李四', '男', '1996-08-03', '务农', '李家屯郊外', '李家屯', '农民', 'Y', '2016-07-26 17:04:08', '2017-01-23 14:27:35', 'admin');
INSERT INTO `tb_customer_info` VALUES ('16', '马云', '男', '1964-09-10', '阿里巴巴', '浙江', '浙江', 'CEO', 'Y', '2016-07-27 08:45:31', '2017-01-23 09:29:10', 'admin');

-- ----------------------------
-- Table structure for `tb_users`
-- ----------------------------
DROP TABLE IF EXISTS `tb_users`;
CREATE TABLE `tb_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_name` varchar(30) NOT NULL,
  `password` varchar(200) NOT NULL,
  `status` varchar(5) NOT NULL DEFAULT 'guest',
  `available` char(1) NOT NULL DEFAULT 'Y',
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  `operator` varchar(30) NOT NULL,
  PRIMARY KEY (`id`,`account_name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_users
-- ----------------------------
INSERT INTO `tb_users` VALUES ('1', 'admin', 'admin', 'admin', 'Y', '2015-11-11 17:50:38', '2015-11-11 17:50:38', 'admin');
INSERT INTO `tb_users` VALUES ('2', 'guest', 'guest', 'guest', 'Y', '2015-11-11 17:50:38', '2015-11-11 19:32:48', 'guest');
INSERT INTO `tb_users` VALUES ('3', 'hib', 'asd', 'guest', 'Y', '2015-11-11 17:50:38', '2015-11-11 17:50:38', 'admin');
INSERT INTO `tb_users` VALUES ('4', 'zhangsan', '123', 'guest', 'Y', '2015-11-11 17:50:38', '2015-11-11 17:50:38', 'admin');
INSERT INTO `tb_users` VALUES ('5', 'lisi', '123456', 'guest', 'Y', '2015-11-11 17:50:38', '2015-11-11 17:50:38', 'admin');
INSERT INTO `tb_users` VALUES ('6', 'wangwu', '111', 'guest', 'Y', '2016-07-27 09:50:25', '2016-07-27 09:50:25', 'admin');
INSERT INTO `tb_users` VALUES ('7', 'mr', 'mrsoft', 'admin', 'Y', '2016-08-25 16:24:09', '2016-08-25 16:24:09', 'admin');
