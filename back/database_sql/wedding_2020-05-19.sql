# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.29)
# Database: wedding
# Generation Time: 2020-05-19 07:49:09 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table album
# ------------------------------------------------------------

DROP TABLE IF EXISTS `album`;

CREATE TABLE `album` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `current_number` int(11) NOT NULL DEFAULT '0',
  `max_number` int(11) NOT NULL DEFAULT '5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;

INSERT INTO `album` (`id`, `current_number`, `max_number`)
VALUES
	(1,0,5),
	(2,0,5),
	(3,0,5),
	(4,0,5),
	(5,0,5),
	(6,0,5),
	(7,0,5),
	(8,0,5),
	(12,3,5),
	(77,2,5),
	(111,4,5),
	(135,2,5),
	(136,0,5),
	(137,3,10),
	(138,0,5),
	(139,0,5),
	(140,2,5),
	(141,1,5),
	(142,5,5),
	(143,1,5),
	(144,2,5),
	(145,3,15),
	(146,0,5),
	(147,0,5);

/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table album_photo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `album_photo`;

CREATE TABLE `album_photo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `albumid` int(11) NOT NULL,
  `address` varchar(100) NOT NULL DEFAULT '',
  `upload_time` datetime NOT NULL,
  `order_number` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `album_photo` WRITE;
/*!40000 ALTER TABLE `album_photo` DISABLE KEYS */;

INSERT INTO `album_photo` (`id`, `albumid`, `address`, `upload_time`, `order_number`)
VALUES
	(1,135,'../../../static/photo1.jpg','2020-05-05 18:10:34',1),
	(2,135,'../../../static/photo2.jpg','2020-05-08 19:00:41',2),
	(3,12,'../../../static/girl2.jpg','2020-05-09 19:53:59',1),
	(4,12,'../../../static/girl3.jpg','2020-05-20 19:54:16',2),
	(5,12,'../../../static/girl.png','2020-05-09 19:54:45',3),
	(6,111,'../../../static/girl3.jpg','2020-05-06 19:57:06',1),
	(7,111,'../../../static/girl2.jpg','2020-05-04 19:57:26',2),
	(8,111,'../../../static/girl.png','2020-05-01 19:57:48',3),
	(9,111,'../../../static/girl4.jpg','2020-05-06 19:58:19',4),
	(10,77,'../../../static/photo1.jpg','2020-04-15 08:45:07',1),
	(11,77,'../../../static/girl3.jpg','2019-11-29 08:45:25',2),
	(57,137,'/album/169/57.jpeg','2020-05-13 16:55:47',1),
	(58,137,'/album/169/58.jpg','2020-05-13 17:03:13',2),
	(59,137,'/album/169/59.jpeg','2020-05-13 17:03:13',3),
	(60,140,'/album/172/60.jpg','2020-05-13 17:21:59',1),
	(61,140,'/album/172/61.jpg','2020-05-13 17:21:59',2),
	(77,141,'/album/173/77.jpg','2020-05-13 17:43:34',1),
	(78,142,'/album/174/78.jpg','2020-05-13 17:46:56',2),
	(79,142,'/album/174/79.jpg','2020-05-13 17:46:56',1),
	(80,142,'/album/174/80.jpeg','2020-05-13 17:46:56',3),
	(81,142,'/album/174/81.jpeg','2020-05-13 17:46:56',4),
	(82,142,'/album/174/82.jpeg','2020-05-13 17:46:56',5),
	(95,143,'/album/175/95.jpeg','2020-05-13 18:16:35',1),
	(99,144,'/album/176/99.jpeg','2020-05-13 18:19:43',1),
	(100,144,'/album/176/100.jpeg','2020-05-13 18:19:43',2),
	(101,145,'/album/177/101.jpg','2020-05-13 18:26:17',1),
	(104,145,'/album/177/104.jpeg','2020-05-13 18:26:18',2),
	(105,145,'/album/177/105.jpeg','2020-05-13 18:26:18',3);

/*!40000 ALTER TABLE `album_photo` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bill
# ------------------------------------------------------------

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

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;

INSERT INTO `bill` (`id`, `type`, `money`, `remark`, `time`, `userid`, `balance`)
VALUES
	(1,'充值',50,'充值了50.0元','2020-05-12 12:47:41',169,160),
	(2,'购买',10,'购买30天会员，消费了10.0元','2020-05-12 13:32:58',169,150),
	(3,'购买',108,'购买365天会员，消费了108.0元','2020-05-12 13:38:20',169,42),
	(4,'购买',5,'购买相册最大容量5张，消费了5.0元','2020-05-12 13:54:04',169,37),
	(5,'充值',50,'充值了50.0元','2020-05-13 17:12:37',169,87),
	(6,'充值',50,'充值了50.0元','2020-05-13 17:44:13',173,50),
	(7,'购买',10,'购买30天会员，消费了10.0元','2020-05-13 17:44:15',173,40),
	(8,'购买',30,'购买90天会员，消费了30.0元','2020-05-13 17:44:21',173,10),
	(9,'充值',50,'充值了50.0元','2020-05-13 18:28:32',177,50),
	(10,'购买',10,'购买30天会员，消费了10.0元','2020-05-13 18:28:38',177,40),
	(11,'购买',10,'购买相册最大容量10张，消费了10.0元','2020-05-13 18:28:46',177,30);

/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table chat_history
# ------------------------------------------------------------

DROP TABLE IF EXISTS `chat_history`;

CREATE TABLE `chat_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '聊天记录编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `chatted_user_id` int(11) NOT NULL COMMENT '聊天对象编号',
  `isAgree` tinyint(4) NOT NULL,
  `user_aisle` varchar(50) DEFAULT NULL,
  `chatted_user_aisle` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `chat_history` WRITE;
/*!40000 ALTER TABLE `chat_history` DISABLE KEYS */;

INSERT INTO `chat_history` (`id`, `user_id`, `chatted_user_id`, `isAgree`, `user_aisle`, `chatted_user_aisle`)
VALUES
	(1,111,321,2,'0','1'),
	(2,111,114,2,'0','2'),
	(3,168,111,2,'1','7'),
	(4,111,168,2,'6',''),
	(5,123,111,2,'0','1'),
	(6,111,123,2,'0','1'),
	(7,111,123,2,'0','1'),
	(8,111,123,2,'2','3'),
	(9,111,123,2,'4','5'),
	(10,123,111,2,'6','7'),
	(11,123,111,2,'8','9'),
	(12,111,123,2,'d',''),
	(13,111,123,2,'',''),
	(14,111,123,2,'',''),
	(15,111,123,2,'',''),
	(16,111,123,2,'',''),
	(17,111,123,2,'',''),
	(18,111,123,2,'',''),
	(19,111,123,0,'',''),
	(20,111,168,0,'',''),
	(21,111,168,0,'','');

/*!40000 ALTER TABLE `chat_history` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table check_history
# ------------------------------------------------------------

DROP TABLE IF EXISTS `check_history`;

CREATE TABLE `check_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '查看记录编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `checked_user_id` int(11) DEFAULT NULL COMMENT '查看对象编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `check_history` WRITE;
/*!40000 ALTER TABLE `check_history` DISABLE KEYS */;

INSERT INTO `check_history` (`id`, `user_id`, `checked_user_id`)
VALUES
	(1,111,321),
	(2,321,114),
	(3,114,178),
	(4,114,321),
	(5,111,178),
	(6,111,114),
	(7,111,178),
	(8,111,321),
	(9,111,321),
	(10,111,178),
	(11,111,666),
	(12,111,666),
	(13,111,666),
	(14,111,321),
	(15,111,321),
	(16,111,666),
	(17,111,666),
	(18,111,321),
	(19,111,321),
	(20,111,321),
	(21,111,321),
	(22,111,321),
	(23,111,321),
	(24,111,321),
	(25,111,321),
	(26,111,321),
	(27,111,321),
	(28,111,321),
	(29,111,321),
	(30,111,321),
	(31,111,321),
	(32,111,321),
	(33,111,321),
	(34,111,321),
	(35,111,321),
	(36,111,321),
	(37,111,321),
	(38,111,321),
	(39,111,111),
	(40,111,111),
	(41,111,111),
	(42,111,111),
	(43,111,111),
	(44,111,178),
	(45,111,178),
	(46,111,178),
	(47,111,178),
	(48,111,666),
	(49,111,321),
	(50,111,321),
	(51,111,321),
	(52,111,321),
	(53,111,111),
	(54,111,111),
	(55,111,111),
	(56,111,321),
	(57,111,321),
	(58,111,321),
	(59,111,111),
	(60,111,114),
	(61,111,114),
	(62,111,114),
	(63,111,114),
	(64,111,114),
	(65,111,114),
	(66,111,114),
	(67,111,114),
	(68,111,114),
	(69,111,114),
	(70,111,114),
	(71,111,114),
	(72,111,114),
	(73,111,114),
	(74,111,114),
	(75,111,114),
	(76,111,114),
	(77,111,114),
	(78,111,114),
	(79,111,114),
	(80,111,114),
	(81,111,114),
	(82,111,114),
	(83,111,114),
	(84,111,114),
	(85,111,114),
	(86,111,114),
	(87,111,114),
	(88,111,114),
	(89,111,114),
	(90,111,114),
	(91,111,114),
	(92,111,114),
	(93,111,114),
	(94,111,114),
	(95,111,114),
	(96,111,114),
	(97,111,114),
	(98,111,114),
	(99,111,114),
	(100,111,114),
	(101,111,114),
	(102,111,114),
	(103,111,114),
	(104,111,114),
	(105,111,114),
	(106,111,114),
	(107,111,114),
	(108,111,114),
	(109,111,114),
	(110,111,114),
	(111,111,114),
	(112,111,114),
	(113,111,114),
	(114,111,114),
	(115,111,114),
	(116,111,114),
	(117,111,114),
	(118,111,114),
	(119,111,114),
	(120,111,114),
	(121,111,114),
	(122,111,114),
	(123,111,114),
	(124,111,114),
	(125,111,114),
	(126,111,114),
	(127,111,168),
	(128,111,168),
	(129,111,168),
	(130,111,168),
	(131,111,114),
	(132,111,114),
	(133,111,114),
	(134,111,114),
	(135,111,114),
	(136,111,114),
	(137,111,114),
	(138,111,114),
	(139,111,111),
	(140,111,114),
	(141,111,114),
	(142,111,114),
	(143,111,114),
	(144,111,114),
	(145,111,114),
	(146,111,114),
	(147,111,114),
	(148,111,114),
	(149,111,114),
	(150,111,114),
	(151,111,114),
	(152,111,114),
	(153,111,114),
	(154,111,114),
	(155,111,114),
	(156,111,114),
	(157,111,114),
	(158,111,114),
	(159,111,114),
	(160,111,114),
	(161,111,114),
	(162,111,114),
	(163,111,114),
	(164,111,114),
	(165,111,114),
	(166,111,114),
	(167,111,168),
	(168,111,114),
	(169,111,114),
	(170,111,114),
	(171,111,111),
	(172,111,114),
	(173,111,114),
	(174,111,114),
	(175,111,114),
	(176,111,114),
	(177,111,114),
	(178,111,114),
	(179,111,114),
	(180,111,114),
	(181,111,114),
	(182,111,114),
	(183,111,114),
	(184,111,114),
	(185,111,114),
	(186,111,114),
	(187,111,114),
	(188,111,114),
	(189,111,114),
	(190,111,114),
	(191,111,114),
	(192,111,114),
	(193,111,114),
	(194,111,114),
	(195,111,114),
	(196,111,114),
	(197,111,114),
	(198,111,114),
	(199,111,114),
	(200,111,114),
	(201,111,114),
	(202,111,114),
	(203,111,114),
	(204,111,114),
	(205,111,114),
	(206,111,114),
	(207,111,114),
	(208,111,114),
	(209,111,114),
	(210,111,114),
	(211,111,168),
	(212,111,168),
	(213,111,111),
	(214,111,111),
	(215,111,111),
	(216,111,111),
	(217,111,111),
	(218,111,114),
	(219,111,114),
	(220,111,114),
	(221,111,111),
	(222,111,168),
	(223,111,114),
	(224,111,114),
	(225,111,168),
	(226,111,168),
	(227,111,114),
	(228,111,168),
	(229,111,168),
	(230,111,168),
	(231,111,114),
	(232,111,168),
	(233,111,168),
	(234,111,168),
	(235,111,114),
	(236,111,168),
	(237,111,168),
	(238,114,111),
	(239,114,114),
	(240,114,114),
	(241,114,111),
	(242,114,111),
	(243,114,111),
	(244,114,111),
	(245,114,111),
	(246,114,111),
	(247,114,111),
	(248,114,111),
	(249,114,111),
	(250,114,111),
	(251,114,111),
	(252,114,111),
	(253,114,111),
	(254,114,111),
	(255,114,111),
	(256,114,111),
	(257,114,111),
	(258,114,111),
	(259,114,111),
	(260,114,111),
	(261,114,111),
	(262,114,111),
	(263,114,111),
	(264,114,111),
	(265,114,111),
	(266,114,111),
	(267,114,111),
	(268,114,111),
	(269,114,111),
	(270,114,111),
	(271,114,111),
	(272,114,111),
	(273,114,111),
	(274,114,111),
	(275,114,111),
	(276,114,114),
	(277,114,114),
	(278,114,168),
	(279,114,111),
	(280,114,114),
	(281,114,168),
	(282,114,168),
	(283,114,168),
	(284,111,168),
	(285,111,168),
	(286,111,168),
	(287,111,168),
	(288,111,114),
	(289,111,168),
	(290,111,168),
	(291,111,111),
	(292,111,114),
	(293,111,168),
	(294,111,114),
	(295,111,123),
	(296,111,123),
	(297,111,123),
	(298,111,123),
	(299,111,123),
	(300,123,114),
	(301,123,114),
	(302,111,123),
	(303,123,114),
	(304,111,168),
	(305,111,168),
	(306,111,168),
	(307,111,168);

/*!40000 ALTER TABLE `check_history` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `happiness_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `content` varchar(500) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table credit_comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `credit_comment`;

CREATE TABLE `credit_comment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid1` int(11) NOT NULL,
  `daterecordid` int(11) NOT NULL,
  `basiccomment` int(11) NOT NULL,
  `extracomment` int(11) NOT NULL,
  `labelcomment` int(11) NOT NULL,
  `userid2` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table date_apply
# ------------------------------------------------------------

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

LOCK TABLES `date_apply` WRITE;
/*!40000 ALTER TABLE `date_apply` DISABLE KEYS */;

INSERT INTO `date_apply` (`id`, `userid1`, `userid2`, `applyinfo`, `replyinfo`, `result`, `applydate`)
VALUES
	(1,169,111,'小哥哥约会吗~','好的呀~','通过','2020-05-13'),
	(2,176,177,'约会吧~','好的~~','通过','2020-05-13'),
	(3,178,179,'','','通过','2020-05-19');

/*!40000 ALTER TABLE `date_apply` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table date_record
# ------------------------------------------------------------

DROP TABLE IF EXISTS `date_record`;

CREATE TABLE `date_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid2` int(11) NOT NULL,
  `userid1` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `date_record` WRITE;
/*!40000 ALTER TABLE `date_record` DISABLE KEYS */;

INSERT INTO `date_record` (`id`, `userid2`, `userid1`, `start_date`, `end_date`)
VALUES
	(1,169,111,'2020-05-13','2020-05-13'),
	(2,177,176,'2020-05-13',NULL),
	(3,179,178,'2020-05-19','2020-05-19');

/*!40000 ALTER TABLE `date_record` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table date_standard
# ------------------------------------------------------------

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

LOCK TABLES `date_standard` WRITE;
/*!40000 ALTER TABLE `date_standard` DISABLE KEYS */;

INSERT INTO `date_standard` (`id`, `agemin`, `agemax`, `heightmin`, `heightmax`, `salary`, `education`, `address`, `marrige`, `userid`)
VALUES
	(1,25,30,170,185,'10000-20000元','本科','北京市/市辖区',0,111),
	(2,31,40,175,190,'10000-20000元','本科','辽宁省',3,114),
	(3,22,28,175,180,'10000-20000元','本科','北京市',0,168),
	(4,20,28,175,180,'不限','不限','河北省',0,123),
	(5,18,24,0,999,'不限','不限','北京市/不限',3,169),
	(6,18,24,0,999,'不限','不限','江苏省/不限',3,170),
	(7,18,25,0,999,'不限','不限','江苏省/南京市/不限',3,171),
	(8,18,25,0,175,'2000元以下','不限','北京市/不限',0,172),
	(9,18,24,129,157,'2000元以下','高中中专及以下','北京市/不限',0,173),
	(10,18,24,131,157,'2000元以下','不限','北京市/不限',3,174),
	(11,18,24,0,999,'2000-5000元','高中中专及以下','北京市/不限',3,175),
	(12,18,26,129,143,'不限','不限','北京市/不限',3,176),
	(13,18,24,0,999,'2000元以下','不限','北京市/不限',3,177),
	(14,18,24,0,999,'不限','不限','北京市/不限',3,178),
	(15,18,24,0,999,'不限','不限','天津市/不限',3,179);

/*!40000 ALTER TABLE `date_standard` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table friend_apply
# ------------------------------------------------------------

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

LOCK TABLES `friend_apply` WRITE;
/*!40000 ALTER TABLE `friend_apply` DISABLE KEYS */;

INSERT INTO `friend_apply` (`id`, `userid1`, `userid2`, `applyInfo`, `result`, `replyInfo`, `applydate`)
VALUES
	(3,169,111,'帅哥加我！！','通过','好的呀','2020-05-12'),
	(5,169,111,'帅哥加我呀','已撤销','暂无','2020-05-13'),
	(7,169,111,'加我一下呗~','拒绝','滚呐','2020-05-13'),
	(8,169,111,'小哥哥加我呗~','通过','好的呀~','2020-05-13'),
	(9,174,173,'加我为好友吧~','等待','暂无','2020-05-13'),
	(10,174,171,'加我为好友吧~','等待','暂无','2020-05-13'),
	(11,176,175,'加个好友吧~','通过','haode~','2020-05-13'),
	(12,177,176,'加个好友吧~','通过','好的~','2020-05-13'),
	(13,179,178,'','通过','','2020-05-19');

/*!40000 ALTER TABLE `friend_apply` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table happiness
# ------------------------------------------------------------

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



# Dump of table happiness_likes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `happiness_likes`;

CREATE TABLE `happiness_likes` (
  `id` int(11) NOT NULL,
  `happiness_id` int(11) NOT NULL,
  `like_id` int(11) NOT NULL,
  `state` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table happiness_photo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `happiness_photo`;

CREATE TABLE `happiness_photo` (
  `id` int(11) NOT NULL,
  `happiness_id` int(11) NOT NULL,
  `photo` mediumblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table label_heat
# ------------------------------------------------------------

DROP TABLE IF EXISTS `label_heat`;

CREATE TABLE `label_heat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(50) DEFAULT NULL COMMENT '标签',
  `heat` int(11) NOT NULL COMMENT '热度（次数）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `label_heat` WRITE;
/*!40000 ALTER TABLE `label_heat` DISABLE KEYS */;

INSERT INTO `label_heat` (`id`, `label`, `heat`)
VALUES
	(1,'开朗',212),
	(2,'大方',240),
	(3,'主动',160),
	(4,'外向',165),
	(5,'俏皮',21),
	(6,'敏捷',59),
	(7,'乐观',89),
	(8,'调皮',52),
	(10,'豪爽',1),
	(11,'爽朗',105),
	(12,'正直',48),
	(13,'直率',1),
	(14,'直爽',31),
	(21,'耿直',1),
	(22,'公正',1),
	(26,'正派',1),
	(27,'爽快',1),
	(28,'率真',1),
	(29,'真诚',1),
	(30,'热诚',1),
	(31,'纯真',1),
	(32,'坦率',1),
	(33,'笃实',1),
	(34,'热忱',1),
	(35,'热心',31),
	(36,'好客',1),
	(37,'和气',1),
	(38,'和蔼',1),
	(39,'和善',1),
	(40,'亲切',1),
	(41,'谦让',1),
	(42,'谦虚',1),
	(43,'谦逊',1),
	(44,'虚心',1),
	(45,'自谦',1),
	(46,'强硬',1),
	(47,'坚强',1),
	(48,'毅力',1),
	(49,'勇敢',1),
	(50,'果敢',1);

/*!40000 ALTER TABLE `label_heat` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table search
# ------------------------------------------------------------

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



# Dump of table system_message
# ------------------------------------------------------------

DROP TABLE IF EXISTS `system_message`;

CREATE TABLE `system_message` (
  `id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `content` varchar(500) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table user
# ------------------------------------------------------------

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
  `balance` double NOT NULL DEFAULT '0',
  `trueness` int(11) NOT NULL DEFAULT '60',
  `profession` varchar(30) NOT NULL DEFAULT '',
  `albumid` int(11) unsigned NOT NULL,
  `vip_enddate` date DEFAULT NULL,
  `date_status` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user2album` (`albumid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `username`, `password`, `phone`, `usertype`, `fullname`, `sex`, `birthday`, `height`, `introduction`, `address`, `education`, `marrige`, `salary`, `balance`, `trueness`, `profession`, `albumid`, `vip_enddate`, `date_status`)
VALUES
	(111,'mark','111','0002',0,'马克',0,'1992-12-12',175,'sss','北京市/市辖区/朝阳区','本科',0,'10000-20000元',0,80,'教育培训',135,'2020-03-10',0),
	(114,'sara','123','0001',1,'李琳',1,'1991-09-13',162,'llk','辽宁省/沈阳市/和平区','本科',0,'10000-20000元',0,60,'教育培训',12,'2020-04-15',0),
	(123,'aliee','000','0004',1,'爱丽',1,'1996-03-04',164,'哈哈哈哈','河北省/石家庄市/长安区','大专',0,'5000-10000元',0,60,'房产中介',77,'2020-01-07',0),
	(168,'tryna','145','0003',0,'李想',1,'1994-12-09',172,'ffff','北京市/市辖区/朝阳区','本科',0,'10000-20000元',0,70,'教育培训',111,'2020-04-22',0),
	(169,'piqiu','111','13218051808',1,'沈璿',1,'1998-06-30',172,'啦啦啦','北京市/市辖区/丰台区','本科',0,'10000-20000元',87,60,'计算机/互联网/通信',137,'2021-06-11',0),
	(171,'test01','123456','13218051808',0,'沈璿',1,'1998-06-30',172,'啦啦啦~~~haha','江苏省/宿迁市/泗阳县','高中中专及以下',0,'2000-5000元',0,60,'销售',139,NULL,0),
	(172,'test2','123456','13218051808',0,'沈璿',1,'1998-06-30',172,'哈哈哈~','北京市/市辖区/东城区','高中中专及以下',1,'2000元以下',0,60,'销售',140,NULL,0),
	(173,'test3','123456','13218051808',1,'沈璿',1,'1998-06-30',172,'啦啦啦~','北京市/市辖区/东城区','高中中专及以下',0,'5000-10000元',10,60,'销售',141,'2020-09-10',0),
	(174,'test4','123456','13218051808',0,'沈璿',1,'1998-06-30',172,'哈哈哈~~~adada','北京市/市辖区/东城区','高中中专及以下',0,'2000元以下',0,60,'销售',142,NULL,0),
	(175,'test5','123456','13218051808',0,'沈璿',1,'1998-06-30',172,'啦啦啦~啦啦啦~','北京市/市辖区/东城区','高中中专及以下',0,'2000元以下',0,60,'销售',143,NULL,0),
	(176,'test6','123456','13218051808',0,'沈璿',1,'1998-06-30',172,'啦啦啦~lalala~','北京市/市辖区/东城区','本科',0,'2000元以下',0,60,'销售',144,NULL,1),
	(177,'test7','123456789','13218051808',1,'沈璿',1,'1998-07-01',172,'啦啦啦~lalala~','北京市/市辖区/东城区','高中中专及以下',0,'2000-5000元',30,60,'人事/行政/后勤',145,'2020-06-12',1),
	(178,'test8','MTIzNDU2','13218051808',0,'沈璿',1,'1998-06-30',172,'哈哈哈','北京市/市辖区/东城区','高中中专及以下',0,'10000-20000元',0,60,'超市/百货/零售',146,NULL,0),
	(179,'test9','MTIzNDU2','13218051808',0,'沈璿',1,'1998-06-30',172,'阿斯顿','天津市/市辖区/和平区','本科',0,'10000-20000元',0,60,'超市/百货/零售',147,NULL,0);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_label
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_label`;

CREATE TABLE `user_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `label` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user_label` WRITE;
/*!40000 ALTER TABLE `user_label` DISABLE KEYS */;

INSERT INTO `user_label` (`id`, `user_id`, `label`)
VALUES
	(1,111,'开朗'),
	(2,111,'大方'),
	(3,111,'敏捷'),
	(4,111,'乐观'),
	(5,114,'开朗'),
	(6,114,'调皮'),
	(7,114,'外向'),
	(8,114,'大方'),
	(12,114,'乐观'),
	(14,111,'正直'),
	(24,168,'大方'),
	(26,168,'直爽'),
	(27,168,'调皮'),
	(28,168,'主动'),
	(29,168,'热心'),
	(30,114,'主动'),
	(31,123,'大方'),
	(32,123,'外向'),
	(33,123,'开朗'),
	(47,169,'现实主义者'),
	(48,169,'天不怕地不怕'),
	(49,169,'爽朗'),
	(50,169,'勇敢'),
	(51,169,'谦逊'),
	(52,169,'狮子座'),
	(53,169,'热忱'),
	(54,170,'有自己的小算盘'),
	(55,170,'沟通能力满分'),
	(56,170,'无人可及的毅力'),
	(57,170,'有些小精明'),
	(58,170,'俏皮'),
	(64,171,'摩羯座'),
	(65,171,'双鱼座'),
	(66,171,'可以一心二用'),
	(72,172,'八卦大喇叭'),
	(73,172,'和蔼'),
	(74,172,'令人难以捉摸'),
	(75,172,'会抓住机会'),
	(81,173,'既理想又实际'),
	(82,173,'天蝎座'),
	(83,173,'不会聊天'),
	(84,173,'一言不合就吃饭'),
	(85,173,'和善'),
	(91,174,'依赖型强'),
	(92,174,'爽朗'),
	(93,174,'天天想着发财'),
	(94,174,'果敢'),
	(95,174,'自谦'),
	(96,174,'思想保守'),
	(102,175,'做事全神贯注'),
	(103,175,'爱出风头'),
	(104,175,'现实主义者'),
	(105,175,'天不怕地不怕'),
	(106,175,'调皮'),
	(107,175,'天赋异禀'),
	(112,176,'可以一心二用'),
	(113,176,'敏捷'),
	(114,176,'一言不合就吃饭'),
	(115,176,'笑点非常低'),
	(122,177,'果敢'),
	(123,177,'脚踏实地'),
	(124,177,'从不装逼'),
	(125,177,'三分钟热度'),
	(126,177,'时刻在抢戏'),
	(127,178,'八卦大喇叭'),
	(128,178,'拥有年轻的心'),
	(129,178,'经常不小心得罪人'),
	(130,179,'见解很独到'),
	(131,179,'爱心泛滥'),
	(132,179,'沟通能力满分');

/*!40000 ALTER TABLE `user_label` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_limit
# ------------------------------------------------------------

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

LOCK TABLES `user_limit` WRITE;
/*!40000 ALTER TABLE `user_limit` DISABLE KEYS */;

INSERT INTO `user_limit` (`id`, `userid`, `limit_browse`, `limit_message`, `limit_fapply`, `limit_dapply`)
VALUES
	(1,169,1,1,0,1),
	(2,170,0,0,0,0),
	(3,171,0,0,0,0),
	(4,172,0,0,0,0),
	(5,173,0,0,0,0),
	(6,174,0,0,0,0),
	(7,175,0,0,0,0),
	(8,176,0,0,0,0),
	(9,177,1,0,1,0),
	(10,178,0,0,0,0),
	(11,179,0,0,0,0);

/*!40000 ALTER TABLE `user_limit` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_question
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_question`;

CREATE TABLE `user_question` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `questionid` int(11) NOT NULL,
  `answer` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user_question` WRITE;
/*!40000 ALTER TABLE `user_question` DISABLE KEYS */;

INSERT INTO `user_question` (`id`, `userid`, `questionid`, `answer`)
VALUES
	(1,114,2,'justin'),
	(2,114,0,'jump'),
	(7,169,0,'王者荣耀'),
	(8,169,1,'跑步'),
	(9,170,0,'王者荣耀'),
	(10,170,1,'跑步'),
	(13,171,0,'王者荣耀'),
	(14,171,1,'跑步'),
	(17,172,0,'王者荣耀'),
	(18,172,1,'跑步'),
	(19,172,2,'成龙'),
	(22,173,1,'跑步'),
	(23,173,0,'王者荣耀'),
	(24,173,3,'提拉米苏'),
	(27,174,0,'王者荣耀'),
	(28,174,1,'跑步'),
	(31,175,0,'王者荣耀'),
	(32,175,1,'跑步'),
	(35,176,0,'王者荣耀'),
	(36,176,1,'跑步'),
	(39,177,0,'王者荣耀'),
	(40,177,1,'跑步'),
	(41,178,0,'王者荣耀'),
	(42,179,0,'王者荣耀');

/*!40000 ALTER TABLE `user_question` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_relation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_relation`;

CREATE TABLE `user_relation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid1` int(11) NOT NULL,
  `userid2` int(11) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `type` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user_relation` WRITE;
/*!40000 ALTER TABLE `user_relation` DISABLE KEYS */;

INSERT INTO `user_relation` (`id`, `userid1`, `userid2`, `remark`, `type`)
VALUES
	(3,169,111,'markhaha',1),
	(4,111,169,'pikaqiu',1),
	(5,176,175,'test5',1),
	(6,175,176,'test6',1),
	(7,177,176,'啦啦啦~',1),
	(8,176,177,'test7',1),
	(9,179,178,'test8',1),
	(10,178,179,'test9',1);

/*!40000 ALTER TABLE `user_relation` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table wedding
# ------------------------------------------------------------

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



# Dump of table wedding_record
# ------------------------------------------------------------

DROP TABLE IF EXISTS `wedding_record`;

CREATE TABLE `wedding_record` (
  `id` int(11) NOT NULL,
  `wedding_id` int(11) NOT NULL,
  `approver_id` int(11) NOT NULL,
  `time` date NOT NULL,
  `result` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
