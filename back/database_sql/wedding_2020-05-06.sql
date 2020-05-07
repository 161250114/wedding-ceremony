/*
Navicat MySQL Data Transfer

Source Server         : practice
Source Server Version : 50160
Source Host           : localhost:3306
Source Database       : wedding

Target Server Type    : MYSQL
Target Server Version : 50160
File Encoding         : 65001

Date: 2020-05-06 09:13:54
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of album
-- ----------------------------
INSERT INTO `album` VALUES ('1', '0', '5');
INSERT INTO `album` VALUES ('2', '0', '5');
INSERT INTO `album` VALUES ('3', '0', '5');
INSERT INTO `album` VALUES ('4', '0', '5');
INSERT INTO `album` VALUES ('5', '0', '5');
INSERT INTO `album` VALUES ('6', '0', '5');
INSERT INTO `album` VALUES ('7', '0', '5');
INSERT INTO `album` VALUES ('8', '0', '5');

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
) ENGINE=InnoDB AUTO_INCREMENT=232 DEFAULT CHARSET=utf8;

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
INSERT INTO `check_history` VALUES ('59', '111', '111');
INSERT INTO `check_history` VALUES ('60', '111', '114');
INSERT INTO `check_history` VALUES ('61', '111', '114');
INSERT INTO `check_history` VALUES ('62', '111', '114');
INSERT INTO `check_history` VALUES ('63', '111', '114');
INSERT INTO `check_history` VALUES ('64', '111', '114');
INSERT INTO `check_history` VALUES ('65', '111', '114');
INSERT INTO `check_history` VALUES ('66', '111', '114');
INSERT INTO `check_history` VALUES ('67', '111', '114');
INSERT INTO `check_history` VALUES ('68', '111', '114');
INSERT INTO `check_history` VALUES ('69', '111', '114');
INSERT INTO `check_history` VALUES ('70', '111', '114');
INSERT INTO `check_history` VALUES ('71', '111', '114');
INSERT INTO `check_history` VALUES ('72', '111', '114');
INSERT INTO `check_history` VALUES ('73', '111', '114');
INSERT INTO `check_history` VALUES ('74', '111', '114');
INSERT INTO `check_history` VALUES ('75', '111', '114');
INSERT INTO `check_history` VALUES ('76', '111', '114');
INSERT INTO `check_history` VALUES ('77', '111', '114');
INSERT INTO `check_history` VALUES ('78', '111', '114');
INSERT INTO `check_history` VALUES ('79', '111', '114');
INSERT INTO `check_history` VALUES ('80', '111', '114');
INSERT INTO `check_history` VALUES ('81', '111', '114');
INSERT INTO `check_history` VALUES ('82', '111', '114');
INSERT INTO `check_history` VALUES ('83', '111', '114');
INSERT INTO `check_history` VALUES ('84', '111', '114');
INSERT INTO `check_history` VALUES ('85', '111', '114');
INSERT INTO `check_history` VALUES ('86', '111', '114');
INSERT INTO `check_history` VALUES ('87', '111', '114');
INSERT INTO `check_history` VALUES ('88', '111', '114');
INSERT INTO `check_history` VALUES ('89', '111', '114');
INSERT INTO `check_history` VALUES ('90', '111', '114');
INSERT INTO `check_history` VALUES ('91', '111', '114');
INSERT INTO `check_history` VALUES ('92', '111', '114');
INSERT INTO `check_history` VALUES ('93', '111', '114');
INSERT INTO `check_history` VALUES ('94', '111', '114');
INSERT INTO `check_history` VALUES ('95', '111', '114');
INSERT INTO `check_history` VALUES ('96', '111', '114');
INSERT INTO `check_history` VALUES ('97', '111', '114');
INSERT INTO `check_history` VALUES ('98', '111', '114');
INSERT INTO `check_history` VALUES ('99', '111', '114');
INSERT INTO `check_history` VALUES ('100', '111', '114');
INSERT INTO `check_history` VALUES ('101', '111', '114');
INSERT INTO `check_history` VALUES ('102', '111', '114');
INSERT INTO `check_history` VALUES ('103', '111', '114');
INSERT INTO `check_history` VALUES ('104', '111', '114');
INSERT INTO `check_history` VALUES ('105', '111', '114');
INSERT INTO `check_history` VALUES ('106', '111', '114');
INSERT INTO `check_history` VALUES ('107', '111', '114');
INSERT INTO `check_history` VALUES ('108', '111', '114');
INSERT INTO `check_history` VALUES ('109', '111', '114');
INSERT INTO `check_history` VALUES ('110', '111', '114');
INSERT INTO `check_history` VALUES ('111', '111', '114');
INSERT INTO `check_history` VALUES ('112', '111', '114');
INSERT INTO `check_history` VALUES ('113', '111', '114');
INSERT INTO `check_history` VALUES ('114', '111', '114');
INSERT INTO `check_history` VALUES ('115', '111', '114');
INSERT INTO `check_history` VALUES ('116', '111', '114');
INSERT INTO `check_history` VALUES ('117', '111', '114');
INSERT INTO `check_history` VALUES ('118', '111', '114');
INSERT INTO `check_history` VALUES ('119', '111', '114');
INSERT INTO `check_history` VALUES ('120', '111', '114');
INSERT INTO `check_history` VALUES ('121', '111', '114');
INSERT INTO `check_history` VALUES ('122', '111', '114');
INSERT INTO `check_history` VALUES ('123', '111', '114');
INSERT INTO `check_history` VALUES ('124', '111', '114');
INSERT INTO `check_history` VALUES ('125', '111', '114');
INSERT INTO `check_history` VALUES ('126', '111', '114');
INSERT INTO `check_history` VALUES ('127', '111', '168');
INSERT INTO `check_history` VALUES ('128', '111', '168');
INSERT INTO `check_history` VALUES ('129', '111', '168');
INSERT INTO `check_history` VALUES ('130', '111', '168');
INSERT INTO `check_history` VALUES ('131', '111', '114');
INSERT INTO `check_history` VALUES ('132', '111', '114');
INSERT INTO `check_history` VALUES ('133', '111', '114');
INSERT INTO `check_history` VALUES ('134', '111', '114');
INSERT INTO `check_history` VALUES ('135', '111', '114');
INSERT INTO `check_history` VALUES ('136', '111', '114');
INSERT INTO `check_history` VALUES ('137', '111', '114');
INSERT INTO `check_history` VALUES ('138', '111', '114');
INSERT INTO `check_history` VALUES ('139', '111', '111');
INSERT INTO `check_history` VALUES ('140', '111', '114');
INSERT INTO `check_history` VALUES ('141', '111', '114');
INSERT INTO `check_history` VALUES ('142', '111', '114');
INSERT INTO `check_history` VALUES ('143', '111', '114');
INSERT INTO `check_history` VALUES ('144', '111', '114');
INSERT INTO `check_history` VALUES ('145', '111', '114');
INSERT INTO `check_history` VALUES ('146', '111', '114');
INSERT INTO `check_history` VALUES ('147', '111', '114');
INSERT INTO `check_history` VALUES ('148', '111', '114');
INSERT INTO `check_history` VALUES ('149', '111', '114');
INSERT INTO `check_history` VALUES ('150', '111', '114');
INSERT INTO `check_history` VALUES ('151', '111', '114');
INSERT INTO `check_history` VALUES ('152', '111', '114');
INSERT INTO `check_history` VALUES ('153', '111', '114');
INSERT INTO `check_history` VALUES ('154', '111', '114');
INSERT INTO `check_history` VALUES ('155', '111', '114');
INSERT INTO `check_history` VALUES ('156', '111', '114');
INSERT INTO `check_history` VALUES ('157', '111', '114');
INSERT INTO `check_history` VALUES ('158', '111', '114');
INSERT INTO `check_history` VALUES ('159', '111', '114');
INSERT INTO `check_history` VALUES ('160', '111', '114');
INSERT INTO `check_history` VALUES ('161', '111', '114');
INSERT INTO `check_history` VALUES ('162', '111', '114');
INSERT INTO `check_history` VALUES ('163', '111', '114');
INSERT INTO `check_history` VALUES ('164', '111', '114');
INSERT INTO `check_history` VALUES ('165', '111', '114');
INSERT INTO `check_history` VALUES ('166', '111', '114');
INSERT INTO `check_history` VALUES ('167', '111', '168');
INSERT INTO `check_history` VALUES ('168', '111', '114');
INSERT INTO `check_history` VALUES ('169', '111', '114');
INSERT INTO `check_history` VALUES ('170', '111', '114');
INSERT INTO `check_history` VALUES ('171', '111', '111');
INSERT INTO `check_history` VALUES ('172', '111', '114');
INSERT INTO `check_history` VALUES ('173', '111', '114');
INSERT INTO `check_history` VALUES ('174', '111', '114');
INSERT INTO `check_history` VALUES ('175', '111', '114');
INSERT INTO `check_history` VALUES ('176', '111', '114');
INSERT INTO `check_history` VALUES ('177', '111', '114');
INSERT INTO `check_history` VALUES ('178', '111', '114');
INSERT INTO `check_history` VALUES ('179', '111', '114');
INSERT INTO `check_history` VALUES ('180', '111', '114');
INSERT INTO `check_history` VALUES ('181', '111', '114');
INSERT INTO `check_history` VALUES ('182', '111', '114');
INSERT INTO `check_history` VALUES ('183', '111', '114');
INSERT INTO `check_history` VALUES ('184', '111', '114');
INSERT INTO `check_history` VALUES ('185', '111', '114');
INSERT INTO `check_history` VALUES ('186', '111', '114');
INSERT INTO `check_history` VALUES ('187', '111', '114');
INSERT INTO `check_history` VALUES ('188', '111', '114');
INSERT INTO `check_history` VALUES ('189', '111', '114');
INSERT INTO `check_history` VALUES ('190', '111', '114');
INSERT INTO `check_history` VALUES ('191', '111', '114');
INSERT INTO `check_history` VALUES ('192', '111', '114');
INSERT INTO `check_history` VALUES ('193', '111', '114');
INSERT INTO `check_history` VALUES ('194', '111', '114');
INSERT INTO `check_history` VALUES ('195', '111', '114');
INSERT INTO `check_history` VALUES ('196', '111', '114');
INSERT INTO `check_history` VALUES ('197', '111', '114');
INSERT INTO `check_history` VALUES ('198', '111', '114');
INSERT INTO `check_history` VALUES ('199', '111', '114');
INSERT INTO `check_history` VALUES ('200', '111', '114');
INSERT INTO `check_history` VALUES ('201', '111', '114');
INSERT INTO `check_history` VALUES ('202', '111', '114');
INSERT INTO `check_history` VALUES ('203', '111', '114');
INSERT INTO `check_history` VALUES ('204', '111', '114');
INSERT INTO `check_history` VALUES ('205', '111', '114');
INSERT INTO `check_history` VALUES ('206', '111', '114');
INSERT INTO `check_history` VALUES ('207', '111', '114');
INSERT INTO `check_history` VALUES ('208', '111', '114');
INSERT INTO `check_history` VALUES ('209', '111', '114');
INSERT INTO `check_history` VALUES ('210', '111', '114');
INSERT INTO `check_history` VALUES ('211', '111', '168');
INSERT INTO `check_history` VALUES ('212', '111', '168');
INSERT INTO `check_history` VALUES ('213', '111', '111');
INSERT INTO `check_history` VALUES ('214', '111', '111');
INSERT INTO `check_history` VALUES ('215', '111', '111');
INSERT INTO `check_history` VALUES ('216', '111', '111');
INSERT INTO `check_history` VALUES ('217', '111', '111');
INSERT INTO `check_history` VALUES ('218', '111', '114');
INSERT INTO `check_history` VALUES ('219', '111', '114');
INSERT INTO `check_history` VALUES ('220', '111', '114');
INSERT INTO `check_history` VALUES ('221', '111', '111');
INSERT INTO `check_history` VALUES ('222', '111', '168');
INSERT INTO `check_history` VALUES ('223', '111', '114');
INSERT INTO `check_history` VALUES ('224', '111', '114');
INSERT INTO `check_history` VALUES ('225', '111', '168');
INSERT INTO `check_history` VALUES ('226', '111', '168');
INSERT INTO `check_history` VALUES ('227', '111', '114');
INSERT INTO `check_history` VALUES ('228', '111', '168');
INSERT INTO `check_history` VALUES ('229', '111', '168');
INSERT INTO `check_history` VALUES ('230', '111', '168');
INSERT INTO `check_history` VALUES ('231', '111', '114');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of date_standard
-- ----------------------------
INSERT INTO `date_standard` VALUES ('1', '25', '30', '170', '185', '10000-20000元', '本科', '北京市/市辖区', '0', '111');
INSERT INTO `date_standard` VALUES ('2', '31', '40', '175', '190', '10000-20000元', '本科', '辽宁省', '3', '114');
INSERT INTO `date_standard` VALUES ('3', '22', '28', '175', '180', '10000-20000元', '本科', '北京市', '0', '168');

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
  `state` int(11) NOT NULL DEFAULT '0',
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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of label_heat
-- ----------------------------
INSERT INTO `label_heat` VALUES ('1', '开朗', '122');
INSERT INTO `label_heat` VALUES ('2', '大方', '120');
INSERT INTO `label_heat` VALUES ('3', '主动', '113');
INSERT INTO `label_heat` VALUES ('4', '外向', '122');
INSERT INTO `label_heat` VALUES ('5', '俏皮', '21');
INSERT INTO `label_heat` VALUES ('6', '敏捷', '12');
INSERT INTO `label_heat` VALUES ('7', '乐观', '25');
INSERT INTO `label_heat` VALUES ('8', '调皮', '5');
INSERT INTO `label_heat` VALUES ('10', '豪爽', '1');
INSERT INTO `label_heat` VALUES ('11', '爽朗', '105');
INSERT INTO `label_heat` VALUES ('12', '正直', '1');
INSERT INTO `label_heat` VALUES ('13', '直率', '1');
INSERT INTO `label_heat` VALUES ('14', '直爽', '1');
INSERT INTO `label_heat` VALUES ('21', '耿直', '1');
INSERT INTO `label_heat` VALUES ('22', '公正', '1');
INSERT INTO `label_heat` VALUES ('26', '正派', '1');
INSERT INTO `label_heat` VALUES ('27', '爽快', '1');
INSERT INTO `label_heat` VALUES ('28', '率真', '1');
INSERT INTO `label_heat` VALUES ('29', '真诚', '1');
INSERT INTO `label_heat` VALUES ('30', '热诚', '1');
INSERT INTO `label_heat` VALUES ('31', '纯真', '1');
INSERT INTO `label_heat` VALUES ('32', '坦率', '1');
INSERT INTO `label_heat` VALUES ('33', '笃实', '1');
INSERT INTO `label_heat` VALUES ('34', '热忱', '1');
INSERT INTO `label_heat` VALUES ('35', '热心', '1');
INSERT INTO `label_heat` VALUES ('36', '好客', '1');
INSERT INTO `label_heat` VALUES ('37', '和气', '1');
INSERT INTO `label_heat` VALUES ('38', '和蔼', '1');
INSERT INTO `label_heat` VALUES ('39', '和善', '1');
INSERT INTO `label_heat` VALUES ('40', '亲切', '1');
INSERT INTO `label_heat` VALUES ('41', '谦让', '1');
INSERT INTO `label_heat` VALUES ('42', '谦虚', '1');
INSERT INTO `label_heat` VALUES ('43', '谦逊', '1');
INSERT INTO `label_heat` VALUES ('44', '虚心', '1');
INSERT INTO `label_heat` VALUES ('45', '自谦', '1');
INSERT INTO `label_heat` VALUES ('46', '强硬', '1');
INSERT INTO `label_heat` VALUES ('47', '坚强', '1');
INSERT INTO `label_heat` VALUES ('48', '毅力', '1');
INSERT INTO `label_heat` VALUES ('49', '勇敢', '1');
INSERT INTO `label_heat` VALUES ('50', '果敢', '1');

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
  `vip_enddate` date NOT NULL,
  `date_status` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user2album` (`albumid`)
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('111', 'mark', '111', '0002', '0', '马克', '0', '1992-12-12', '175', 'sss', '北京市/市辖区/朝阳区', '本科', '0', '10000-20000元', '0', '80', '教育培训', '135', '2020-03-10', '0');
INSERT INTO `user` VALUES ('114', 'sara', '123', '0001', '0', '李琳', '1', '1991-09-13', '162', 'llk', '辽宁省/沈阳市/和平区', '本科', '0', '10000-20000元', '0', '60', '教育培训', '12', '2020-04-15', '0');
INSERT INTO `user` VALUES ('168', 'tryna', '145', '0003', '0', '李想', '1', '1994-12-09', '172', 'ffff', '北京市/市辖区/朝阳区', '本科', '0', '10000-20000元', '0', '70', '教育培训', '111', '2020-04-22', '0');

-- ----------------------------
-- Table structure for `user_label`
-- ----------------------------
DROP TABLE IF EXISTS `user_label`;
CREATE TABLE `user_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `label` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_label
-- ----------------------------
INSERT INTO `user_label` VALUES ('1', '111', '开朗');
INSERT INTO `user_label` VALUES ('2', '111', '大方');
INSERT INTO `user_label` VALUES ('3', '111', '敏捷');
INSERT INTO `user_label` VALUES ('4', '111', '乐观');
INSERT INTO `user_label` VALUES ('5', '114', '开朗');
INSERT INTO `user_label` VALUES ('6', '114', '调皮');
INSERT INTO `user_label` VALUES ('7', '114', '外向');
INSERT INTO `user_label` VALUES ('8', '114', '大方');
INSERT INTO `user_label` VALUES ('12', '114', '乐观');
INSERT INTO `user_label` VALUES ('14', '111', '正直');
INSERT INTO `user_label` VALUES ('24', '168', '大方');
INSERT INTO `user_label` VALUES ('26', '168', '直爽');
INSERT INTO `user_label` VALUES ('27', '168', '调皮');
INSERT INTO `user_label` VALUES ('28', '168', '主动');
INSERT INTO `user_label` VALUES ('29', '168', '热心');
INSERT INTO `user_label` VALUES ('30', '114', '主动');

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
-- Table structure for `user_question`
-- ----------------------------
DROP TABLE IF EXISTS `user_question`;
CREATE TABLE `user_question` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `questionid` int(11) NOT NULL,
  `answer` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_question
-- ----------------------------
INSERT INTO `user_question` VALUES ('1', '114', '2', 'justin');
INSERT INTO `user_question` VALUES ('2', '114', '0', 'jump');

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
