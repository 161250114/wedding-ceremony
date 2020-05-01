/*
Navicat MySQL Data Transfer

Source Server         : practice
Source Server Version : 50160
Source Host           : localhost:3306
Source Database       : wedding

Target Server Type    : MYSQL
Target Server Version : 50160
File Encoding         : 65001

Date: 2020-05-01 11:17:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `album`
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `current_number` int(11) NOT NULL DEFAULT '0',
  `max_number` int(11) NOT NULL DEFAULT '5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of album
-- ----------------------------

-- ----------------------------
-- Table structure for `album_photo`
-- ----------------------------
DROP TABLE IF EXISTS `album_photo`;
CREATE TABLE `album_photo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `albumid` int(11) NOT NULL,
  `address` varchar(100) NOT NULL DEFAULT '',
  `upload_time` datetime NOT NULL,
  `order_number` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of album_photo
-- ----------------------------

-- ----------------------------
-- Table structure for `bill`
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL DEFAULT '',
  `money` double NOT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `time` datetime NOT NULL,
  `userid` int(11) NOT NULL,
  `balance` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bill
-- ----------------------------

-- ----------------------------
-- Table structure for `chat_history`
-- ----------------------------
DROP TABLE IF EXISTS `chat_history`;
CREATE TABLE `chat_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '聊天记录编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `chatted_user_id` int(11) DEFAULT NULL COMMENT '聊天对象编号',
  `begin_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chat_history
-- ----------------------------
INSERT INTO `chat_history` VALUES ('1', '111', '321', '2020-04-08 17:54:23', '2020-04-08 21:54:35');

-- ----------------------------
-- Table structure for `check_history`
-- ----------------------------
DROP TABLE IF EXISTS `check_history`;
CREATE TABLE `check_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '查看记录编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `checked_user_id` int(11) DEFAULT NULL COMMENT '查看对象编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of check_history
-- ----------------------------
INSERT INTO `check_history` VALUES ('1', '111', '321');
INSERT INTO `check_history` VALUES ('2', '321', '114');
INSERT INTO `check_history` VALUES ('3', '114', '178');
INSERT INTO `check_history` VALUES ('4', '114', '321');
INSERT INTO `check_history` VALUES ('5', '111', '178');
INSERT INTO `check_history` VALUES ('6', '111', '114');
INSERT INTO `check_history` VALUES ('7', '111', '178');
INSERT INTO `check_history` VALUES ('8', '111', '321');
INSERT INTO `check_history` VALUES ('9', '111', '321');
INSERT INTO `check_history` VALUES ('10', '111', '178');
INSERT INTO `check_history` VALUES ('11', '111', '666');
INSERT INTO `check_history` VALUES ('12', '111', '666');
INSERT INTO `check_history` VALUES ('13', '111', '666');
INSERT INTO `check_history` VALUES ('14', '111', '321');
INSERT INTO `check_history` VALUES ('15', '111', '321');
INSERT INTO `check_history` VALUES ('16', '111', '666');
INSERT INTO `check_history` VALUES ('17', '111', '666');
INSERT INTO `check_history` VALUES ('18', '111', '321');
INSERT INTO `check_history` VALUES ('19', '111', '321');
INSERT INTO `check_history` VALUES ('20', '111', '321');
INSERT INTO `check_history` VALUES ('21', '111', '321');
INSERT INTO `check_history` VALUES ('22', '111', '321');
INSERT INTO `check_history` VALUES ('23', '111', '321');
INSERT INTO `check_history` VALUES ('24', '111', '321');
INSERT INTO `check_history` VALUES ('25', '111', '321');
INSERT INTO `check_history` VALUES ('26', '111', '321');
INSERT INTO `check_history` VALUES ('27', '111', '321');
INSERT INTO `check_history` VALUES ('28', '111', '321');
INSERT INTO `check_history` VALUES ('29', '111', '321');
INSERT INTO `check_history` VALUES ('30', '111', '321');
INSERT INTO `check_history` VALUES ('31', '111', '321');
INSERT INTO `check_history` VALUES ('32', '111', '321');
INSERT INTO `check_history` VALUES ('33', '111', '321');
INSERT INTO `check_history` VALUES ('34', '111', '321');
INSERT INTO `check_history` VALUES ('35', '111', '321');
INSERT INTO `check_history` VALUES ('36', '111', '321');
INSERT INTO `check_history` VALUES ('37', '111', '321');
INSERT INTO `check_history` VALUES ('38', '111', '321');
INSERT INTO `check_history` VALUES ('39', '111', '111');
INSERT INTO `check_history` VALUES ('40', '111', '111');
INSERT INTO `check_history` VALUES ('41', '111', '111');
INSERT INTO `check_history` VALUES ('42', '111', '111');
INSERT INTO `check_history` VALUES ('43', '111', '111');
INSERT INTO `check_history` VALUES ('44', '111', '178');
INSERT INTO `check_history` VALUES ('45', '111', '178');
INSERT INTO `check_history` VALUES ('46', '111', '178');
INSERT INTO `check_history` VALUES ('47', '111', '178');
INSERT INTO `check_history` VALUES ('48', '111', '666');
INSERT INTO `check_history` VALUES ('49', '111', '321');
INSERT INTO `check_history` VALUES ('50', '111', '321');
INSERT INTO `check_history` VALUES ('51', '111', '321');
INSERT INTO `check_history` VALUES ('52', '111', '321');
INSERT INTO `check_history` VALUES ('53', '111', '111');
INSERT INTO `check_history` VALUES ('54', '111', '111');
INSERT INTO `check_history` VALUES ('55', '111', '111');
INSERT INTO `check_history` VALUES ('56', '111', '321');
INSERT INTO `check_history` VALUES ('57', '111', '321');
INSERT INTO `check_history` VALUES ('58', '111', '321');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `happiness_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `content` varchar(500) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for `date_apply`
-- ----------------------------
DROP TABLE IF EXISTS `date_apply`;
CREATE TABLE `date_apply` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid1` int(11) NOT NULL,
  `userid2` int(11) NOT NULL,
  `applyinfo` varchar(50) DEFAULT NULL,
  `replyinfo` varchar(50) DEFAULT '',
  `result` varchar(10) NOT NULL DEFAULT '',
  `applydate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of date_apply
-- ----------------------------

-- ----------------------------
-- Table structure for `date_record`
-- ----------------------------
DROP TABLE IF EXISTS `date_record`;
CREATE TABLE `date_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid2` int(11) NOT NULL,
  `userid1` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of date_record
-- ----------------------------

-- ----------------------------
-- Table structure for `date_standard`
-- ----------------------------
DROP TABLE IF EXISTS `date_standard`;
CREATE TABLE `date_standard` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `agemin` int(11) NOT NULL,
  `agemax` int(11) NOT NULL,
  `heightmin` int(11) NOT NULL,
  `heightmax` int(11) NOT NULL,
  `salary` varchar(30) NOT NULL DEFAULT '',
  `education` varchar(30) NOT NULL DEFAULT '',
  `address` varchar(100) NOT NULL DEFAULT '',
  `marrige` tinyint(4) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of date_standard
-- ----------------------------

-- ----------------------------
-- Table structure for `friend_apply`
-- ----------------------------
DROP TABLE IF EXISTS `friend_apply`;
CREATE TABLE `friend_apply` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid1` int(11) NOT NULL,
  `userid2` int(11) NOT NULL,
  `applyInfo` varchar(50) DEFAULT '',
  `result` varchar(10) NOT NULL DEFAULT '',
  `replyInfo` varchar(50) NOT NULL DEFAULT '',
  `applydate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend_apply
-- ----------------------------

-- ----------------------------
-- Table structure for `happiness`
-- ----------------------------
DROP TABLE IF EXISTS `happiness`;
CREATE TABLE `happiness` (
  `id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `time` date NOT NULL,
  `content` varchar(500) NOT NULL,
  `likes` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of happiness
-- ----------------------------

-- ----------------------------
-- Table structure for `happiness_likes`
-- ----------------------------
DROP TABLE IF EXISTS `happiness_likes`;
CREATE TABLE `happiness_likes` (
  `id` int(11) NOT NULL,
  `happiness_id` int(11) NOT NULL,
  `like_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of happiness_likes
-- ----------------------------

-- ----------------------------
-- Table structure for `happiness_photo`
-- ----------------------------
DROP TABLE IF EXISTS `happiness_photo`;
CREATE TABLE `happiness_photo` (
  `id` int(11) NOT NULL,
  `happiness_id` int(11) NOT NULL,
  `photo` mediumblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of happiness_photo
-- ----------------------------

-- ----------------------------
-- Table structure for `label_heat`
-- ----------------------------
DROP TABLE IF EXISTS `label_heat`;
CREATE TABLE `label_heat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(50) DEFAULT NULL COMMENT '标签',
  `heat` int(11) NOT NULL COMMENT '热度（次数）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of label_heat
-- ----------------------------
INSERT INTO `label_heat` VALUES ('1', 'sexy', '52');
INSERT INTO `label_heat` VALUES ('2', 'teacher', '151');
INSERT INTO `label_heat` VALUES ('3', 'beautiful', '43');
INSERT INTO `label_heat` VALUES ('4', 'pretty', '109');
INSERT INTO `label_heat` VALUES ('5', 'rich', '136');
INSERT INTO `label_heat` VALUES ('6', 'funny', '28');
INSERT INTO `label_heat` VALUES ('7', 'outgoing', '16');
INSERT INTO `label_heat` VALUES ('8', 'handsome', '4');

-- ----------------------------
-- Table structure for `search`
-- ----------------------------
DROP TABLE IF EXISTS `search`;
CREATE TABLE `search` (
  `id` int(11) NOT NULL,
  `sex` int(11) NOT NULL,
  `youngest` int(11) NOT NULL,
  `oldest` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `shortest` int(11) NOT NULL,
  `tallest` int(11) NOT NULL,
  `lowest` int(11) NOT NULL,
  `highest` int(11) NOT NULL,
  `education` varchar(255) NOT NULL,
  `profession` varchar(255) NOT NULL,
  `marrige` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of search
-- ----------------------------

-- ----------------------------
-- Table structure for `standard`
-- ----------------------------
DROP TABLE IF EXISTS `standard`;
CREATE TABLE `standard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `youngest` int(11) NOT NULL,
  `oldest` int(11) NOT NULL,
  `shortest` int(11) NOT NULL,
  `tallest` int(11) NOT NULL,
  `nation` varchar(50) NOT NULL,
  `education` varchar(50) NOT NULL,
  `marriage` int(11) NOT NULL,
  `address` varchar(50) NOT NULL,
  `sex` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of standard
-- ----------------------------
INSERT INTO `standard` VALUES ('1', '111', '25', '30', '170', '185', '汉族', '本科', '0', '北京', '0');
INSERT INTO `standard` VALUES ('2', '114', '31', '40', '175', '190', '汉族', '本科', '0', '辽宁', '0');
INSERT INTO `standard` VALUES ('3', '178', '28', '36', '185', '195', '汉族', '博士', '0', '美国', '0');
INSERT INTO `standard` VALUES ('4', '321', '26', '30', '175', '180', '汉族', '本科', '0', '江苏', '0');
INSERT INTO `standard` VALUES ('5', '666', '24', '29', '155', '175', '汉族', '本科', '0', '北京', '1');

-- ----------------------------
-- Table structure for `system_message`
-- ----------------------------
DROP TABLE IF EXISTS `system_message`;
CREATE TABLE `system_message` (
  `id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `content` varchar(500) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_message
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL DEFAULT '',
  `password` varchar(50) NOT NULL DEFAULT '',
  `phone` varchar(11) NOT NULL DEFAULT '',
  `usertype` tinyint(4) NOT NULL DEFAULT '0',
  `fullname` varchar(20) NOT NULL DEFAULT '',
  `sex` tinyint(4) NOT NULL,
  `birthday` date NOT NULL,
  `height` int(11) NOT NULL,
  `introduction` varchar(1000) DEFAULT NULL,
  `address` varchar(100) NOT NULL DEFAULT '',
  `education` varchar(50) NOT NULL DEFAULT '',
  `marrige` tinyint(4) NOT NULL,
  `salary` varchar(50) NOT NULL DEFAULT '',
  `balance` int(11) NOT NULL DEFAULT '0',
  `trueness` int(11) NOT NULL DEFAULT '60',
  `profession` varchar(30) NOT NULL DEFAULT '',
  `albumid` int(11) unsigned NOT NULL,
  `standardid` int(11) NOT NULL,
  `vip_enddate` date NOT NULL,
  `date_status` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user2album` (`albumid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `user_label`
-- ----------------------------
DROP TABLE IF EXISTS `user_label`;
CREATE TABLE `user_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `label_1` varchar(50) DEFAULT NULL,
  `label_2` varchar(50) DEFAULT NULL,
  `label_3` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_label
-- ----------------------------
INSERT INTO `user_label` VALUES ('1', '111', 'sexy', 'teacher', 'beautiful');
INSERT INTO `user_label` VALUES ('2', '321', 'teacher', 'pretty', 'rich');
INSERT INTO `user_label` VALUES ('3', '114', 'rich', 'sexy', 'funny');
INSERT INTO `user_label` VALUES ('4', '178', 'beautiful', 'teacher', 'outgoing');
INSERT INTO `user_label` VALUES ('5', '666', 'handsome', 'rich', 'funny');

-- ----------------------------
-- Table structure for `user_limit`
-- ----------------------------
DROP TABLE IF EXISTS `user_limit`;
CREATE TABLE `user_limit` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `limit_browse` tinyint(4) NOT NULL,
  `limit_message` tinyint(4) NOT NULL,
  `limit_fapply` tinyint(4) NOT NULL,
  `limit_dapply` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_limit
-- ----------------------------

-- ----------------------------
-- Table structure for `user_relation`
-- ----------------------------
DROP TABLE IF EXISTS `user_relation`;
CREATE TABLE `user_relation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid1` int(11) NOT NULL,
  `userid2` int(11) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `type` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_relation
-- ----------------------------

-- ----------------------------
-- Table structure for `wedding`
-- ----------------------------
DROP TABLE IF EXISTS `wedding`;
CREATE TABLE `wedding` (
  `id` int(11) NOT NULL,
  `applicant_id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `total` int(11) NOT NULL,
  `start` date NOT NULL DEFAULT '2000-01-01',
  `end` date NOT NULL DEFAULT '2000-01-01',
  `location` varchar(500) NOT NULL,
  `detail` varchar(500) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wedding
-- ----------------------------

-- ----------------------------
-- Table structure for `wedding_record`
-- ----------------------------
DROP TABLE IF EXISTS `wedding_record`;
CREATE TABLE `wedding_record` (
  `id` int(11) NOT NULL,
  `wedding_id` int(11) NOT NULL,
  `approver_id` int(11) NOT NULL,
  `time` date NOT NULL,
  `result` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wedding_record
-- ----------------------------
