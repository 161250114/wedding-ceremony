# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.29)
# Database: wedding
# Generation Time: 2020-05-05 11:50:29 +0000
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
	(3,0,5),
	(4,0,5),
	(5,0,5),
	(6,0,5),
	(7,0,5),
	(8,0,5),
	(9,0,5),
	(10,0,5),
	(11,0,5),
	(12,0,5),
	(13,0,5),
	(14,0,5);

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



# Dump of table chat_history
# ------------------------------------------------------------

DROP TABLE IF EXISTS `chat_history`;

CREATE TABLE `chat_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '聊天记录编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `chatted_user_id` int(11) DEFAULT NULL COMMENT '聊天对象编号',
  `begin_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `chat_history` WRITE;
/*!40000 ALTER TABLE `chat_history` DISABLE KEYS */;

INSERT INTO `chat_history` (`id`, `user_id`, `chatted_user_id`, `begin_time`, `end_time`)
VALUES
	(1,111,321,'2020-04-08 17:54:23','2020-04-08 21:54:35');

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
	(58,111,321);

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



# Dump of table date_record
# ------------------------------------------------------------

DROP TABLE IF EXISTS `date_record`;

CREATE TABLE `date_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid2` int(11) NOT NULL,
  `userid1` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



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
	(1,18,24,0,999,'不限','不限','北京市/市辖区/东城区',3,1),
	(2,18,24,0,999,'不限','不限','北京市/市辖区/东城区',4,2),
	(3,18,24,0,999,'不限','不限','北京市/市辖区/东城区',4,3),
	(4,18,24,0,999,'不限','不限','北京市/市辖区/东城区',4,4),
	(5,18,24,0,999,'不限','不限','北京市/市辖区/东城区',4,5),
	(6,18,24,0,999,'不限','不限','北京市/市辖区/东城区',4,6),
	(7,18,24,0,999,'不限','不限','北京市/市辖区/东城区',4,7),
	(8,18,24,0,999,'不限','不限','北京市/市辖区/东城区',4,8),
	(9,18,24,0,999,'不限','不限','北京市/市辖区/东城区',4,9);

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
	(1,'sexy',52),
	(2,'teacher',151),
	(3,'beautiful',43),
	(4,'pretty',109),
	(5,'rich',136),
	(6,'funny',28),
	(7,'outgoing',16),
	(8,'handsome',4);

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



# Dump of table standard
# ------------------------------------------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `standard` WRITE;
/*!40000 ALTER TABLE `standard` DISABLE KEYS */;

INSERT INTO `standard` (`id`, `user_id`, `youngest`, `oldest`, `shortest`, `tallest`, `nation`, `education`, `marriage`, `address`, `sex`)
VALUES
	(1,111,25,30,170,185,'汉族','本科',0,'北京',0),
	(2,114,31,40,175,190,'汉族','本科',0,'辽宁',0),
	(3,178,28,36,185,195,'汉族','博士',0,'美国',0),
	(4,321,26,30,175,180,'汉族','本科',0,'江苏',0),
	(5,666,24,29,155,175,'汉族','本科',0,'北京',1);

/*!40000 ALTER TABLE `standard` ENABLE KEYS */;
UNLOCK TABLES;


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
  `balance` int(11) NOT NULL DEFAULT '0',
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
	(9,'pikaqiu','123456','13218051808',0,'沈璿',1,'1998-06-30',172,'我是一只皮皮~','北京市/市辖区/东城区','本科',1,'2000-5000元',0,60,'超市/百货/零售',14,NULL,0);

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
	(6,9,'勇猛'),
	(7,9,'坚忍'),
	(8,9,'公道'),
	(9,9,'坚定'),
	(10,9,'果敢');

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
	(1,1,0,0,0,0),
	(2,2,0,0,0,0),
	(3,3,0,0,0,0),
	(4,4,0,0,0,0),
	(5,5,0,0,0,0),
	(6,6,0,0,0,0),
	(7,7,0,0,0,0),
	(8,8,0,0,0,0),
	(9,9,0,0,0,0);

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
	(1,9,1,'长跑'),
	(2,9,3,'提拉米苏');

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