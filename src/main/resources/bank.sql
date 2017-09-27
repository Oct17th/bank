/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : bank

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2017-09-27 15:44:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `name` varchar(255) NOT NULL,
  `balance` float DEFAULT '0',
  PRIMARY KEY (`name`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`name`) REFERENCES `user` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('a', '10.2');
INSERT INTO `account` VALUES ('spring', '94.9');
INSERT INTO `account` VALUES ('u', '12');
INSERT INTO `account` VALUES ('yj', '123');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `u_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('a', 'p');
INSERT INTO `user` VALUES ('spring', 'test');
INSERT INTO `user` VALUES ('u', 'p');
INSERT INTO `user` VALUES ('yj', '1017');
