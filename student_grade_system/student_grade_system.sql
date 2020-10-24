/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : student_grade_system

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2018-12-04 22:33:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class` varchar(45) NOT NULL,
  `credit` varchar(6) NOT NULL,
  `gpa` varchar(6) NOT NULL,
  `name` varchar(45) NOT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAF42E01B6A48A78B` (`teacher_id`),
  CONSTRAINT `FKAF42E01B6A48A78B` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '数学', '6', '6', '高等数学', '17');
INSERT INTO `course` VALUES ('2', '数学', '4', '4', '高等数学2', '17');
INSERT INTO `course` VALUES ('3', '数学', '6', '6', '高等数学3', null);
INSERT INTO `course` VALUES ('4', '数学', '4', '4', '高等数学4', '17');
INSERT INTO `course` VALUES ('11', '英语', '4', '4', '大学英语', '17');
INSERT INTO `course` VALUES ('12', '计算机', '4', '4', '数据结构', '17');
INSERT INTO `course` VALUES ('13', '计算机', '4', '4', '数据库', '17');
INSERT INTO `course` VALUES ('14', '计算机', '4', '4', '软件工程', '17');

-- ----------------------------
-- Table structure for `grade`
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(100) DEFAULT NULL,
  `credit` varchar(6) DEFAULT NULL,
  `date` datetime NOT NULL,
  `gpa` varchar(6) DEFAULT NULL,
  `grade` varchar(10) DEFAULT NULL,
  `property` varchar(20) NOT NULL,
  `term` varchar(45) NOT NULL,
  `way` varchar(20) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK5E0BFD737EB4702` (`course_id`),
  KEY `FK5E0BFD773E0A692` (`student_id`),
  CONSTRAINT `FK5E0BFD737EB4702` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FK5E0BFD773E0A692` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('1', 'youxiu', '3.60', '2018-11-07 13:04:46', '3.60', '90', '初修', '大一第一学期', '考试', '1', '1');
INSERT INTO `grade` VALUES ('2', null, null, '2018-11-07 13:04:47', null, null, '初修', '大一第一学期', '考试', '2', '2');
INSERT INTO `grade` VALUES ('3', null, null, '2018-11-07 13:04:47', null, null, '初修', '大一第一学期', '考试', '3', '3');
INSERT INTO `grade` VALUES ('4', null, null, '2018-11-07 13:04:47', null, null, '初修', '大一第一学期', '考试', '4', '4');
INSERT INTO `grade` VALUES ('11', null, '3.60', '2018-11-07 13:12:00', '3.60', '90', '初修', '大一第一学期', '考试', '12', '1');
INSERT INTO `grade` VALUES ('12', null, '0.88', '2018-11-07 13:12:01', '0.88', '22', '初修', '大一第一学期', '考试', '12', '2');
INSERT INTO `grade` VALUES ('13', null, '1.32', '2018-11-07 13:12:02', '1.32', '33', '初修', '大一第一学期', '考试', '12', '3');
INSERT INTO `grade` VALUES ('14', null, null, '2018-11-07 13:12:02', null, null, '初修', '大一第一学期', '考试', '12', '4');
INSERT INTO `grade` VALUES ('15', null, '3.60', '2018-11-07 13:12:02', '3.60', '90', '初修', '大一第一学期', '考试', '12', '5');
INSERT INTO `grade` VALUES ('16', null, null, '2018-11-07 13:12:02', null, null, '初修', '大一第一学期', '考试', '12', '6');
INSERT INTO `grade` VALUES ('17', null, null, '2018-11-07 13:12:02', null, null, '初修', '大一第一学期', '考试', '12', '7');
INSERT INTO `grade` VALUES ('18', null, null, '2018-11-07 13:12:02', null, null, '初修', '大一第一学期', '考试', '12', '8');
INSERT INTO `grade` VALUES ('19', null, null, '2018-11-07 13:12:02', null, null, '初修', '大一第一学期', '考试', '12', '9');
INSERT INTO `grade` VALUES ('20', null, null, '2018-11-07 13:12:02', null, null, '初修', '大一第一学期', '考试', '12', '10');
INSERT INTO `grade` VALUES ('21', null, '4.00', '2018-11-07 16:14:33', '4.00', '100', '初修', '大一第一学期', '考试', '12', '31');
INSERT INTO `grade` VALUES ('22', ' ', '5.94', '2018-11-10 21:35:19', '5.94', '99', '初修', '大一第一学期', '考试', '1', '31');
INSERT INTO `grade` VALUES ('23', ' ', null, '2018-11-10 21:40:16', null, null, '初修', '大一第一学期', '考试', '2', '31');
INSERT INTO `grade` VALUES ('26', ' ', null, '2018-11-10 22:54:16', null, null, '初修', '大一第一学期', '考试', '1', '2');
INSERT INTO `grade` VALUES ('27', ' ', null, '2018-11-10 22:54:16', null, null, '初修', '大一第一学期', '考试', '1', '3');
INSERT INTO `grade` VALUES ('28', ' ', null, '2018-11-10 22:54:16', null, null, '初修', '大一第一学期', '考试', '1', '4');
INSERT INTO `grade` VALUES ('29', ' ', '3.60', '2018-11-10 22:54:17', '3.60', '60', '初修', '大一第一学期', '考试', '1', '5');
INSERT INTO `grade` VALUES ('30', ' ', null, '2018-11-10 22:54:17', null, null, '初修', '大一第一学期', '考试', '1', '6');
INSERT INTO `grade` VALUES ('31', ' ', null, '2018-11-10 22:54:17', null, null, '初修', '大一第一学期', '考试', '1', '7');
INSERT INTO `grade` VALUES ('32', ' ', null, '2018-11-10 22:54:17', null, null, '初修', '大一第一学期', '考试', '1', '8');
INSERT INTO `grade` VALUES ('33', ' ', null, '2018-11-10 22:54:17', null, null, '初修', '大一第一学期', '考试', '1', '9');
INSERT INTO `grade` VALUES ('34', ' ', null, '2018-11-10 22:54:17', null, null, '初修', '大一第一学期', '考试', '1', '10');
INSERT INTO `grade` VALUES ('35', ' ', null, '2018-11-10 23:48:05', null, null, '初修', '大一第一学期', '考试', '11', '1');
INSERT INTO `grade` VALUES ('36', ' ', null, '2018-11-10 23:48:05', null, null, '初修', '大一第一学期', '考试', '11', '2');
INSERT INTO `grade` VALUES ('37', ' ', null, '2018-11-10 23:48:06', null, null, '初修', '大一第一学期', '考试', '11', '3');
INSERT INTO `grade` VALUES ('38', ' ', null, '2018-11-10 23:48:06', null, null, '初修', '大一第一学期', '考试', '11', '4');
INSERT INTO `grade` VALUES ('39', ' ', '3.60', '2018-11-10 23:48:06', '3.60', '90', '初修', '大一第一学期', '考试', '11', '5');
INSERT INTO `grade` VALUES ('40', ' ', null, '2018-11-10 23:48:06', null, null, '初修', '大一第一学期', '考试', '11', '6');
INSERT INTO `grade` VALUES ('41', ' ', null, '2018-11-10 23:48:06', null, null, '初修', '大一第一学期', '考试', '11', '7');
INSERT INTO `grade` VALUES ('42', ' ', null, '2018-11-10 23:48:06', null, null, '初修', '大一第一学期', '考试', '11', '8');
INSERT INTO `grade` VALUES ('43', ' ', null, '2018-11-10 23:48:06', null, null, '初修', '大一第一学期', '考试', '11', '9');
INSERT INTO `grade` VALUES ('44', ' ', null, '2018-11-10 23:48:06', null, null, '初修', '大一第一学期', '考试', '11', '10');
INSERT INTO `grade` VALUES ('45', ' ', null, '2018-11-10 23:48:06', null, null, '初修', '大一第一学期', '考试', '11', '31');
INSERT INTO `grade` VALUES ('46', ' ', null, '2018-11-11 22:00:20', null, null, '初修', '大一第一学期', '考试', '13', '1');
INSERT INTO `grade` VALUES ('47', ' ', null, '2018-11-11 22:00:20', null, null, '初修', '大一第一学期', '考试', '13', '2');
INSERT INTO `grade` VALUES ('48', ' ', null, '2018-11-11 22:00:20', null, null, '初修', '大一第一学期', '考试', '13', '3');
INSERT INTO `grade` VALUES ('49', ' ', null, '2018-11-11 22:00:21', null, null, '初修', '大一第一学期', '考试', '13', '4');
INSERT INTO `grade` VALUES ('50', ' ', null, '2018-11-11 22:00:21', null, null, '初修', '大一第一学期', '考试', '13', '5');
INSERT INTO `grade` VALUES ('51', ' ', null, '2018-11-11 22:00:21', null, null, '初修', '大一第一学期', '考试', '13', '6');
INSERT INTO `grade` VALUES ('52', ' ', null, '2018-11-11 22:00:21', null, null, '初修', '大一第一学期', '考试', '13', '7');
INSERT INTO `grade` VALUES ('53', ' ', null, '2018-11-11 22:00:21', null, null, '初修', '大一第一学期', '考试', '13', '8');
INSERT INTO `grade` VALUES ('54', ' ', null, '2018-11-11 22:00:22', null, null, '初修', '大一第一学期', '考试', '13', '9');
INSERT INTO `grade` VALUES ('55', ' ', null, '2018-11-11 22:00:22', null, null, '初修', '大一第一学期', '考试', '13', '10');
INSERT INTO `grade` VALUES ('56', ' ', null, '2018-11-11 22:00:22', null, null, '初修', '大一第一学期', '考试', '13', '31');
INSERT INTO `grade` VALUES ('57', ' ', null, '2018-11-11 22:00:22', null, null, '初修', '大一第一学期', '考试', '13', '32');
INSERT INTO `grade` VALUES ('58', ' ', null, '2018-11-11 22:00:29', null, null, '初修', '大一第一学期', '考试', '14', '1');
INSERT INTO `grade` VALUES ('59', ' ', null, '2018-11-11 22:00:29', null, null, '初修', '大一第一学期', '考试', '14', '2');
INSERT INTO `grade` VALUES ('60', ' ', null, '2018-11-11 22:00:29', null, null, '初修', '大一第一学期', '考试', '14', '3');
INSERT INTO `grade` VALUES ('61', ' ', null, '2018-11-11 22:00:29', null, null, '初修', '大一第一学期', '考试', '14', '4');
INSERT INTO `grade` VALUES ('62', ' ', '3.60', '2018-11-11 22:00:29', '3.60', '90', '初修', '大一第一学期', '考试', '14', '5');
INSERT INTO `grade` VALUES ('63', ' ', null, '2018-11-11 22:00:29', null, null, '初修', '大一第一学期', '考试', '14', '6');
INSERT INTO `grade` VALUES ('64', ' ', null, '2018-11-11 22:00:29', null, null, '初修', '大一第一学期', '考试', '14', '7');
INSERT INTO `grade` VALUES ('65', ' ', null, '2018-11-11 22:00:29', null, null, '初修', '大一第一学期', '考试', '14', '8');
INSERT INTO `grade` VALUES ('66', ' ', null, '2018-11-11 22:00:29', null, null, '初修', '大一第一学期', '考试', '14', '9');
INSERT INTO `grade` VALUES ('67', ' ', null, '2018-11-11 22:00:29', null, null, '初修', '大一第一学期', '考试', '14', '10');
INSERT INTO `grade` VALUES ('68', ' ', null, '2018-11-11 22:00:29', null, null, '初修', '大一第一学期', '考试', '14', '31');
INSERT INTO `grade` VALUES ('69', ' ', null, '2018-11-11 22:00:29', null, null, '初修', '大一第一学期', '考试', '14', '32');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class` varchar(45) DEFAULT NULL,
  `date` datetime NOT NULL,
  `gender` varchar(4) NOT NULL,
  `institute` varchar(45) NOT NULL,
  `major` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `nation` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `user_class` varchar(45) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1603', '2018-11-07 12:58:59', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '学生', '1618014070');
INSERT INTO `user` VALUES ('2', '1603', '2018-11-07 12:59:00', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '学生', '1618014071');
INSERT INTO `user` VALUES ('3', '1603', '2018-11-07 12:59:00', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '学生', '1618014072');
INSERT INTO `user` VALUES ('4', '1603', '2018-11-07 12:59:00', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '学生', '1618014073');
INSERT INTO `user` VALUES ('5', '1603', '2018-11-07 12:59:00', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '学生', '1618014074');
INSERT INTO `user` VALUES ('6', '1603', '2018-11-07 12:59:00', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '学生', '1618014075');
INSERT INTO `user` VALUES ('7', '1603', '2018-11-07 12:59:00', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '学生', '1618014076');
INSERT INTO `user` VALUES ('8', '1603', '2018-11-07 12:59:00', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '学生', '1618014077');
INSERT INTO `user` VALUES ('9', '1603', '2018-11-07 12:59:00', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '学生', '1618014078');
INSERT INTO `user` VALUES ('10', '1603', '2018-11-07 12:59:00', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '学生', '1618014079');
INSERT INTO `user` VALUES ('11', null, '2018-11-07 13:00:34', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '教师', '20181100');
INSERT INTO `user` VALUES ('12', null, '2018-11-07 13:00:35', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '教师', '20181101');
INSERT INTO `user` VALUES ('13', null, '2018-11-07 13:00:35', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '教师', '20181102');
INSERT INTO `user` VALUES ('14', null, '2018-11-07 13:00:35', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '教师', '20181103');
INSERT INTO `user` VALUES ('15', null, '2018-11-07 13:00:35', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '教师', '20181104');
INSERT INTO `user` VALUES ('16', null, '2018-11-07 13:00:35', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '教师', '20181105');
INSERT INTO `user` VALUES ('17', null, '2018-11-07 13:00:35', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '教师', '20181106');
INSERT INTO `user` VALUES ('18', null, '2018-11-07 13:00:35', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '教师', '20181107');
INSERT INTO `user` VALUES ('19', null, '2018-11-07 13:00:35', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '教师', '20181108');
INSERT INTO `user` VALUES ('20', null, '2018-11-07 13:00:35', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '教师', '20181109');
INSERT INTO `user` VALUES ('21', null, '2018-11-07 13:01:33', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '管理员', '06210620');
INSERT INTO `user` VALUES ('22', null, '2018-11-07 13:01:34', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '管理员', '06210621');
INSERT INTO `user` VALUES ('23', null, '2018-11-07 13:01:34', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '管理员', '06210622');
INSERT INTO `user` VALUES ('24', null, '2018-11-07 13:01:34', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '管理员', '06210623');
INSERT INTO `user` VALUES ('25', null, '2018-11-07 13:01:34', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '管理员', '06210624');
INSERT INTO `user` VALUES ('26', null, '2018-11-07 13:01:34', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '管理员', '06210625');
INSERT INTO `user` VALUES ('27', null, '2018-11-07 13:01:34', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '管理员', '06210626');
INSERT INTO `user` VALUES ('28', null, '2018-11-07 13:01:34', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '管理员', '06210627');
INSERT INTO `user` VALUES ('29', null, '2018-11-07 13:01:34', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '管理员', '06210628');
INSERT INTO `user` VALUES ('30', null, '2018-11-07 13:01:34', '男', '数学与计算机科学学院', '计算机科学与技术', '段小龙', '回', '123123', '管理员', '06210629');
INSERT INTO `user` VALUES ('31', '1603', '2018-11-07 16:11:41', '女', '数学与计算机科学学院', '计算机科学与技术', '汪露', '汉', '123456', '学生', '1618014105');
INSERT INTO `user` VALUES ('32', '1603', '2018-11-11 21:39:22', '男', '数学与计算机科学学院', '计算机科学与技术', '段忧', '回', '123123', '学生', '1618014007');
