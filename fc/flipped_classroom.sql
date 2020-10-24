/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : flipped_classroom

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 10/06/2020 14:00:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer_record
-- ----------------------------
DROP TABLE IF EXISTS `answer_record`;
CREATE TABLE `answer_record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `student_id` int(0) NULL DEFAULT NULL,
  `teacher_id` int(0) NULL DEFAULT NULL,
  `answerer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `answer_date` datetime(0) NULL DEFAULT NULL,
  `question_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE,
  INDEX `teacher_id`(`teacher_id`) USING BTREE,
  INDEX `question_id`(`question_id`) USING BTREE,
  CONSTRAINT `answer_record_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `answer_record_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `answer_record_ibfk_3` FOREIGN KEY (`question_id`) REFERENCES `question_record` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of answer_record
-- ----------------------------
INSERT INTO `answer_record` VALUES (1, 1, 1, '学生', '答案1', '2020-05-25 11:16:39', 1);
INSERT INTO `answer_record` VALUES (2, 1, 1, '学生', '答案1', '2020-05-25 11:16:39', 2);
INSERT INTO `answer_record` VALUES (3, 1, 1, '学生', '答案1', '2020-05-25 11:16:39', 3);
INSERT INTO `answer_record` VALUES (4, 1, 1, '学生', '答案1', '2020-05-25 11:16:39', 4);
INSERT INTO `answer_record` VALUES (5, 1, 1, '学生', '答案1', '2020-05-25 11:16:39', 5);
INSERT INTO `answer_record` VALUES (6, 1, 1, '学生', '答案1', '2020-05-25 11:16:39', 6);
INSERT INTO `answer_record` VALUES (7, 1, 1, '学生', '答案1', '2020-05-25 11:16:39', 7);
INSERT INTO `answer_record` VALUES (8, 1, 1, '学生', '答案1', '2020-05-25 11:16:39', 8);
INSERT INTO `answer_record` VALUES (9, 1, 1, '学生', '答案1', '2020-05-25 11:16:39', 9);
INSERT INTO `answer_record` VALUES (10, 1, 1, '教师', '答案1', '2020-05-25 11:16:39', 10);
INSERT INTO `answer_record` VALUES (11, 1, 1, '教师', '答案1', '2020-05-25 11:16:39', 11);
INSERT INTO `answer_record` VALUES (12, 1, 1, '教师', '答案1', '2020-05-25 11:16:39', 12);
INSERT INTO `answer_record` VALUES (13, 1, 1, '教师', '答案1', '2020-05-25 11:16:39', 13);
INSERT INTO `answer_record` VALUES (14, 1, 1, '教师', '答案1', '2020-05-25 11:16:39', 14);
INSERT INTO `answer_record` VALUES (15, 1, 1, '教师', '答案1', '2020-05-25 11:16:39', 15);
INSERT INTO `answer_record` VALUES (16, 1, 1, '教师', '答案1', '2020-05-25 11:16:39', 16);
INSERT INTO `answer_record` VALUES (17, 1, 1, '教师', '答案1', '2020-05-25 11:16:39', 17);
INSERT INTO `answer_record` VALUES (18, 1, 1, '教师', '答案1', '2020-05-25 11:16:39', 18);
INSERT INTO `answer_record` VALUES (19, 1, 1, '教师', '答案1', '2020-05-25 11:16:39', 19);
INSERT INTO `answer_record` VALUES (20, 1, 1, '教师', '答案1', '2020-05-25 11:16:39', 20);
INSERT INTO `answer_record` VALUES (21, 1, 1, '学生', '答案如下', '2020-05-30 18:31:01', 1);
INSERT INTO `answer_record` VALUES (22, 1, 1, '学生', '教师答案', '2020-06-01 13:58:24', 1);
INSERT INTO `answer_record` VALUES (23, 1, 1, '学生', '回答教师的答案如下', '2020-06-02 19:32:14', 1);
INSERT INTO `answer_record` VALUES (24, 1, 1, '学生', '回答老师提出的问题', '2020-06-02 19:35:17', 1);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detail` varchar(511) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `school` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `point_system` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '数据结构', '计算机', '/static/img/computer_ds.jpg', '暂无', '陕西理工大学', '百分制');
INSERT INTO `course` VALUES (2, '离散数学', '计算机', '/static/img/computer_maths.jpg', '暂无', '陕西理工大学', '百分制');
INSERT INTO `course` VALUES (3, '计算机网络', '计算机', '/static/img/computer_net.jpg', '暂无', '陕西理工大学', '百分制');
INSERT INTO `course` VALUES (4, '计算机组成原理', '计算机', '/static/img/computer_composition.jpg', '暂无', '陕西理工大学', '百分制');
INSERT INTO `course` VALUES (5, '数据库', '计算机', '/static/img/computer_database.jpg', '暂无', '陕西理工大学', '百分制');
INSERT INTO `course` VALUES (6, '操作系统', '计算机', '/static/img/default_course.png', '暂无', '陕西理工大学', '百分制');
INSERT INTO `course` VALUES (7, '高等数学', '数学', '/static/img/default_course.png', '暂无', '陕西理工大学', '百分制');
INSERT INTO `course` VALUES (8, '大学英语', '英语', '/static/img/default_course.png', '暂无', '陕西理工大学', '百分制');
INSERT INTO `course` VALUES (9, '线性代数', '数学', '/static/img/default_course.png', '暂无', '陕西理工大学', '百分制');
INSERT INTO `course` VALUES (10, '计算机图形学', '计算机', '/static/img/default_course.png', '暂无', '陕西理工大学', '百分制');
INSERT INTO `course` VALUES (11, 'OpenGL', '计算机', '/static/img/default_course.png', '暂无', '陕西理工大学', '百分制');
INSERT INTO `course` VALUES (16, '数据库', '计算机', '/static/img/default_course.png', '暂无', '陕西理工大学', '百分制');
INSERT INTO `course` VALUES (20, '开设课程', '计算机', NULL, '暂无', '陕西理工大学', '百分制');

-- ----------------------------
-- Table structure for course_record
-- ----------------------------
DROP TABLE IF EXISTS `course_record`;
CREATE TABLE `course_record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `course_id` int(0) NULL DEFAULT NULL,
  `teacher_id` int(0) NULL DEFAULT NULL,
  `record_date` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `course_id`(`course_id`) USING BTREE,
  INDEX `teacher_id`(`teacher_id`) USING BTREE,
  CONSTRAINT `course_record_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `course_record_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_record
-- ----------------------------
INSERT INTO `course_record` VALUES (1, 1, 1, '2020-05-23 17:33:41');
INSERT INTO `course_record` VALUES (2, 2, 1, '2020-05-23 17:35:41');
INSERT INTO `course_record` VALUES (3, 3, 1, '2020-05-23 17:35:54');
INSERT INTO `course_record` VALUES (4, 4, 1, '2020-05-23 17:36:05');
INSERT INTO `course_record` VALUES (5, 5, 1, '2020-05-23 17:36:19');
INSERT INTO `course_record` VALUES (9, 20, 1, '2020-06-02 19:37:40');

-- ----------------------------
-- Table structure for learning_record
-- ----------------------------
DROP TABLE IF EXISTS `learning_record`;
CREATE TABLE `learning_record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `student_id` int(0) NULL DEFAULT NULL,
  `course_record_id` int(0) NULL DEFAULT NULL,
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `record_date` datetime(0) NOT NULL,
  `learning_time` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE,
  INDEX `course_id`(`course_record_id`) USING BTREE,
  CONSTRAINT `learning_record_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `learning_record_ibfk_2` FOREIGN KEY (`course_record_id`) REFERENCES `course_record` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of learning_record
-- ----------------------------
INSERT INTO `learning_record` VALUES (1, 1, 3, '60', '2020-05-23 21:48:35', 30);
INSERT INTO `learning_record` VALUES (2, 1, 1, '100', '2020-05-23 21:48:42', 30);
INSERT INTO `learning_record` VALUES (3, 1, 2, '80', '2020-05-23 21:48:46', 30);
INSERT INTO `learning_record` VALUES (5, 1, 4, '20', '2020-05-23 22:55:46', 30);
INSERT INTO `learning_record` VALUES (6, 2, 1, '30', '2020-06-01 10:24:55', 30);
INSERT INTO `learning_record` VALUES (7, 3, 1, '66', '2020-06-01 10:25:06', 30);
INSERT INTO `learning_record` VALUES (8, 4, 1, '88', '2020-06-01 10:25:15', 30);
INSERT INTO `learning_record` VALUES (9, 5, 1, '77', '2020-06-01 10:25:25', 30);
INSERT INTO `learning_record` VALUES (10, 6, 1, '99', '2020-06-01 10:25:35', 30);
INSERT INTO `learning_record` VALUES (11, 7, 1, '78', '2020-06-01 10:25:44', 30);
INSERT INTO `learning_record` VALUES (12, 8, 1, '45', '2020-06-01 10:25:55', 30);
INSERT INTO `learning_record` VALUES (13, 21, 1, NULL, '2020-06-02 18:34:37', NULL);

-- ----------------------------
-- Table structure for question_record
-- ----------------------------
DROP TABLE IF EXISTS `question_record`;
CREATE TABLE `question_record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `teacher_id` int(0) NULL DEFAULT NULL,
  `student_id` int(0) NULL DEFAULT NULL,
  `questioner` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `teacher_id`(`teacher_id`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE,
  CONSTRAINT `question_record_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `question_record_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question_record
-- ----------------------------
INSERT INTO `question_record` VALUES (1, 1, 1, '教师', '问题1？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (2, 1, 1, '教师', '问题2？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (3, 1, 1, '教师', '问题3？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (4, 1, 1, '教师', '问题4？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (5, 1, 1, '教师', '问题5？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (6, 1, 1, '教师', '问题6？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (7, 1, 1, '教师', '问题7？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (8, 1, 1, '教师', '问题8？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (9, 1, 1, '教师', '问题9？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (10, 1, 1, '教师', '问题10？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (11, 1, 1, '学生', '问题1？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (12, 1, 1, '学生', '问题2？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (13, 1, 1, '学生', '问题3？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (14, 1, 1, '学生', '问题4？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (15, 1, 1, '学生', '问题5？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (16, 1, 1, '学生', '问题6？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (17, 1, 1, '学生', '问题7？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (18, 1, 1, '学生', '问题8？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (19, 1, 1, '学生', '问题9？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (20, 1, 1, '学生', '问题10？', '2020-05-25 11:16:13');
INSERT INTO `question_record` VALUES (21, 1, 1, '学生', '数据结构主要内容？', '2020-05-30 17:59:27');
INSERT INTO `question_record` VALUES (22, 1, 1, '学生', '拓扑排序算法？', '2020-06-01 13:54:18');
INSERT INTO `question_record` VALUES (23, 1, 1, '学生', '关键路径是什么？', '2020-06-02 19:31:40');
INSERT INTO `question_record` VALUES (24, 1, 1, '学生', '拓扑排序的实现？', '2020-06-02 19:34:49');

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `res_date` datetime(0) NOT NULL,
  `course_record_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `course_id`(`course_record_id`) USING BTREE,
  CONSTRAINT `resource_ibfk_1` FOREIGN KEY (`course_record_id`) REFERENCES `course_record` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES (1, '数据结构第一章', '文档', '/static/document/default_resource.docx', '2020-05-22 22:01:08', 1);
INSERT INTO `resource` VALUES (2, '数据结构第二章', '视频', '/static/video/default_resource.mp4', '2020-05-22 22:01:08', 1);
INSERT INTO `resource` VALUES (3, '数据结构第三章', '图片', '/static/img/default_resource.jpg', '2020-05-22 22:01:08', 1);
INSERT INTO `resource` VALUES (4, '数据库第一章', '图片', '/static/img/default_resource.jpg', '2020-05-22 22:01:08', 1);
INSERT INTO `resource` VALUES (5, '操作系统第一章', '图片', '/static/img/default_resource.jpg', '2020-05-22 22:01:08', 1);
INSERT INTO `resource` VALUES (6, '计算机网络第一章', '图片', '/static/img/default_resource.jpg', '2020-05-22 22:01:08', 1);
INSERT INTO `resource` VALUES (7, '人工智能第一章', '图片', '/static/img/default_resource.jpg', '2020-05-22 22:01:08', 1);
INSERT INTO `resource` VALUES (8, '算法设计与分析第一章', '图片', '/static/img/default_resource.jpg', '2020-05-22 22:01:08', 1);
INSERT INTO `resource` VALUES (11, '数据结构第一章', '文档', '/static/document/default_resource.docx', '2020-05-22 22:01:08', 1);
INSERT INTO `resource` VALUES (12, 'dxl', '文档', '答辩提纲.docx', '2020-05-31 15:14:36', 1);
INSERT INTO `resource` VALUES (13, '测试文档', '文档', '需求分析.docx', '2020-06-01 13:29:42', 1);
INSERT INTO `resource` VALUES (14, '新资料', '文档', '测试.docx', '2020-06-02 22:09:45', 1);

-- ----------------------------
-- Table structure for resource_record
-- ----------------------------
DROP TABLE IF EXISTS `resource_record`;
CREATE TABLE `resource_record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `res_id` int(0) NULL DEFAULT NULL,
  `student_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `res_id`(`res_id`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE,
  CONSTRAINT `resource_record_ibfk_1` FOREIGN KEY (`res_id`) REFERENCES `resource` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `resource_record_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource_record
-- ----------------------------
INSERT INTO `resource_record` VALUES (1, '已下载', 1, 1);

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduction` varchar(511) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cell` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES (1, '陕西理工大学', '/fc/static/img/default_school', '/fc/static/img/default_school_logo', '暂无', '123', '123');
INSERT INTO `school` VALUES (2, '北京大学', '/fc/static/img/default_school', '/fc/static/img/default_school_logo', '暂无', '12345678901', '123');
INSERT INTO `school` VALUES (3, '安徽大学', '/fc/static/img/default_school', '/fc/static/img/default_school_logo', '暂无', '12345678901', '123');
INSERT INTO `school` VALUES (4, '清华大学', '/fc/static/img/default_school', '/fc/static/img/default_school_logo', '暂无', '12345678901', '123');
INSERT INTO `school` VALUES (5, '上海交通大学', '/fc/static/img/default_school', '/fc/static/img/default_school_logo', '暂无', '12345678901', '123');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cell` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '段小龙', '男', '/fc/static/img/login-img.png', '123', '123');
INSERT INTO `student` VALUES (2, '段小龙2', '男', '/fc/static/img/login-img.png', '1232', '123');
INSERT INTO `student` VALUES (3, '段小龙3', '男', '/fc/static/img/login-img.png', '1233', '123');
INSERT INTO `student` VALUES (4, '段小龙4', '男', '/fc/static/img/login-img.png', '1234', '123');
INSERT INTO `student` VALUES (5, '段小龙5', '男', '/fc/static/img/login-img.png', '1235', '123');
INSERT INTO `student` VALUES (6, '段小6', '男', '/fc/static/img/login-img.png', '1236', '123');
INSERT INTO `student` VALUES (7, '段小龙7', '男', '/fc/static/img/login-img.png', '1237', '123');
INSERT INTO `student` VALUES (8, '段小龙8', '男', '/fc/static/img/login-img.png', '1238', '123');
INSERT INTO `student` VALUES (9, '段小龙', '男', '/fc/static/img/login-img.png', '1239', '123');
INSERT INTO `student` VALUES (10, '段小龙', '男', '/fc/static/img/login-img.png', '1230', '123');
INSERT INTO `student` VALUES (11, '段小龙', '男', '/fc/static/img/login-img.png', '12311', '123');
INSERT INTO `student` VALUES (12, '段小龙', '男', '/fc/static/img/login-img.png', '12312', '123');
INSERT INTO `student` VALUES (13, '段小龙', '男', '/fc/static/img/login-img.png', '12313', '123');
INSERT INTO `student` VALUES (14, '段小龙', '男', '/fc/static/img/login-img.png', '12314', '123');
INSERT INTO `student` VALUES (15, '段小龙', '男', '/fc/static/img/login-img.png', '12315', '123');
INSERT INTO `student` VALUES (16, '段小龙', '男', '/fc/static/img/login-img.png', '12316', '123');
INSERT INTO `student` VALUES (17, '段小龙', '男', '/fc/static/img/login-img.png', '12317', '123');
INSERT INTO `student` VALUES (18, '段小龙', '男', '/fc/static/img/login-img.png', '12318', '123');
INSERT INTO `student` VALUES (19, '段小龙', '男', '/fc/static/img/login-img.png', '12319', '123');
INSERT INTO `student` VALUES (21, '测试学生', '男', NULL, '12345678910', '123');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `school_id` int(0) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `introduction` varchar(511) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cell` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `school_id`(`school_id`) USING BTREE,
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 113 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '张三', '男', 1, '讲师', '暂无', '/fc/static/img/default_teacher.png', '123', '123');
INSERT INTO `teacher` VALUES (11, '张三', '男', 1, '讲师', '暂无', '/fc/static/img/default_teacher.png', '1231', '123');
INSERT INTO `teacher` VALUES (12, '张三', '男', 1, '讲师', '暂无', '/fc/static/img/default_teacher.png', '1232', '123');
INSERT INTO `teacher` VALUES (13, '张三', '男', 1, '讲师', '暂无', '/fc/static/img/default_teacher.png', '1233', '123');
INSERT INTO `teacher` VALUES (14, '张三', '男', 1, '讲师', '暂无', '/fc/static/img/default_teacher.png', '1234', '123');
INSERT INTO `teacher` VALUES (15, '张三', '男', 1, '讲师', '暂无', '/fc/static/img/default_teacher.png', '12345', '123');
INSERT INTO `teacher` VALUES (16, '张三', '男', 1, '讲师', '暂无', '/fc/static/img/default_teacher.png', '1236', '123');
INSERT INTO `teacher` VALUES (17, '张三', '男', 1, '讲师', '暂无', '/fc/static/img/default_teacher.png', '1237', '123');
INSERT INTO `teacher` VALUES (18, '张三', '男', 1, '讲师', '暂无', '/fc/static/img/default_teacher.png', '1238', '123');
INSERT INTO `teacher` VALUES (19, '张三', '男', 1, '讲师', '暂无', '/fc/static/img/default_teacher.png', '1239', '123');
INSERT INTO `teacher` VALUES (112, '张三', '男', 1, '讲师', '暂无', '/fc/static/img/default_teacher.png', '123', '123');
INSERT INTO `teacher` VALUES (113, '段老师', '男', NULL, '讲师', NULL, NULL, '12312312312', '123456');

-- ----------------------------
-- Table structure for work
-- ----------------------------
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_date` datetime(0) NOT NULL,
  `dead_date` datetime(0) NOT NULL,
  `course_record_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `course_id`(`course_record_id`) USING BTREE,
  CONSTRAINT `work_ibfk_1` FOREIGN KEY (`course_record_id`) REFERENCES `course_record` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work
-- ----------------------------
INSERT INTO `work` VALUES (1, '数据结构', '/static/document/default_resource.docx', '2020-05-22 22:01:42', '2020-05-22 22:01:44', 1);
INSERT INTO `work` VALUES (2, '线性表', '/static/document/default_resource.docx', '2020-05-22 22:01:42', '2020-05-22 22:01:44', 1);
INSERT INTO `work` VALUES (3, '二叉树', '/static/document/default_resource.docx', '2020-05-22 22:01:42', '2020-05-22 22:01:44', 1);
INSERT INTO `work` VALUES (4, '队列', '/static/document/default_resource.docx', '2020-05-22 22:01:42', '2020-05-22 22:01:44', 1);
INSERT INTO `work` VALUES (5, '栈', '/static/document/default_resource.docx', '2020-05-22 22:01:42', '2020-05-22 22:01:44', 1);
INSERT INTO `work` VALUES (6, '邻接矩阵', '/static/document/default_resource.docx', '2020-05-22 22:01:42', '2020-05-22 22:01:44', 1);
INSERT INTO `work` VALUES (7, '邻接表', '/static/document/default_resource.docx', '2020-05-22 22:01:42', '2020-05-22 22:01:44', 1);
INSERT INTO `work` VALUES (8, '毕设', '/static/document/1618014074_段小龙_任务书.doc', '2020-05-30 00:00:00', '2020-05-30 00:00:00', 1);
INSERT INTO `work` VALUES (9, '作业1', '测试.docx', '2020-05-30 00:00:00', '2020-05-30 00:00:00', 9);
INSERT INTO `work` VALUES (10, '作业1', NULL, '2020-05-30 00:00:00', '2020-05-30 00:00:00', 1);
INSERT INTO `work` VALUES (11, '作业2', NULL, '2020-05-30 00:00:00', '2020-05-30 00:00:00', 1);

-- ----------------------------
-- Table structure for work_record
-- ----------------------------
DROP TABLE IF EXISTS `work_record`;
CREATE TABLE `work_record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `work_id` int(0) NULL DEFAULT NULL,
  `student_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `work_id`(`work_id`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE,
  CONSTRAINT `work_record_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `work_record_ibfk_3` FOREIGN KEY (`work_id`) REFERENCES `work` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work_record
-- ----------------------------
INSERT INTO `work_record` VALUES (1, '批改完成', '90', 1, 1);
INSERT INTO `work_record` VALUES (2, '批改完成', '90', 2, 1);
INSERT INTO `work_record` VALUES (3, '未批改', '', 3, 1);
INSERT INTO `work_record` VALUES (4, '未下载', '', 4, 1);
INSERT INTO `work_record` VALUES (5, '批改完成', '70', 5, 1);
INSERT INTO `work_record` VALUES (6, '批改完成', '80', 6, 1);
INSERT INTO `work_record` VALUES (7, '批改完成', '90', 7, 1);

SET FOREIGN_KEY_CHECKS = 1;
