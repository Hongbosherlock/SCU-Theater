/*
Navicat MySQL Data Transfer

Source Server         : root_localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : testdb001

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2018-10-06 12:38:34
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `Account` varchar(32) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id` (`Id`),
  UNIQUE KEY `Account` (`Account`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'qiaoger', 'nishengqiao', '1');
INSERT INTO `user` VALUES ('2', 'zhang', 'zhangsan', '1');
INSERT INTO `user` VALUES ('3', 'li', 'lisi', '0');
INSERT INTO `user` VALUES ('4', 'wang', 'wangba', '0');
INSERT INTO `user` VALUES ('5', 'nini', '妮妮', '1');
