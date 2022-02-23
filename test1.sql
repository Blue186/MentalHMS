/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : test1

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 19/06/2021 01:29:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for instructorinfo
-- ----------------------------
DROP TABLE IF EXISTS `instructorinfo`;
CREATE TABLE `instructorinfo`  (
  `SInstructor` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '辅导员编号',
  `IName` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '辅导员学院',
  `College` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属学院',
  `IPNo` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '辅导员电话',
  `Email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '辅导员邮箱',
  PRIMARY KEY (`SInstructor`) USING BTREE,
  INDEX `SInstructor`(`SInstructor`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of instructorinfo
-- ----------------------------
INSERT INTO `instructorinfo` VALUES ('x2019212110', '张老师', '生物信息学院', '13072827487', '1293802001@qq.emil');
INSERT INTO `instructorinfo` VALUES ('x2019212111', '李老师', '计算机科学与技术学院', '13828599823', '10294829822@qq.emil');
INSERT INTO `instructorinfo` VALUES ('x2019212112', '王老师', '理学院', '19282928491', '294992991@qq.emil');
INSERT INTO `instructorinfo` VALUES ('x2019212113', '刘老师', '外国语学院', '19883884848', '1938478382@qq.emil');

-- ----------------------------
-- Table structure for qresult
-- ----------------------------
DROP TABLE IF EXISTS `qresult`;
CREATE TABLE `qresult`  (
  `QRNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '问卷的编号',
  `QNo` int NOT NULL COMMENT '问卷类型的编号',
  `QREva` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '老师对问卷的评估',
  `QGrade` int NULL DEFAULT NULL COMMENT '老师对问卷的打分',
  `SNo` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生学号',
  `TNo` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '老师工号',
  PRIMARY KEY (`QRNo`) USING BTREE,
  INDEX `qresult_QNo`(`QNo`) USING BTREE,
  INDEX `qresult_TNo`(`TNo`) USING BTREE,
  INDEX `qresult_SNo`(`SNo`) USING BTREE,
  CONSTRAINT `qresult_QNo` FOREIGN KEY (`QNo`) REFERENCES `qtype` (`QNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `qresult_SNo` FOREIGN KEY (`SNo`) REFERENCES `studentinfo` (`SNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `qresult_TNo` FOREIGN KEY (`TNo`) REFERENCES `teacherinfo` (`TNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qresult
-- ----------------------------
INSERT INTO `qresult` VALUES ('201921214120210619003944', 3, '5555', 20, '2019212141', 'T200001');

-- ----------------------------
-- Table structure for qtype
-- ----------------------------
DROP TABLE IF EXISTS `qtype`;
CREATE TABLE `qtype`  (
  `Type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '问卷类型名',
  `QNo` int NOT NULL COMMENT '问卷类型编号',
  PRIMARY KEY (`QNo`, `Type`) USING BTREE,
  INDEX `Type`(`Type`) USING BTREE,
  INDEX `QNo`(`QNo`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qtype
-- ----------------------------
INSERT INTO `qtype` VALUES ('情感类', 1);
INSERT INTO `qtype` VALUES ('生活类', 2);
INSERT INTO `qtype` VALUES ('学习类', 3);
INSERT INTO `qtype` VALUES ('交友类', 4);

-- ----------------------------
-- Table structure for qtype_study
-- ----------------------------
DROP TABLE IF EXISTS `qtype_study`;
CREATE TABLE `qtype_study`  (
  `QNum` int NOT NULL AUTO_INCREMENT COMMENT '导入问题后的问题编号',
  `QNo` int NULL DEFAULT NULL COMMENT '问卷的类型，与qtype对应',
  `Question` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '问题',
  `AnswerA` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选项A',
  `AnswerB` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选项B',
  `AnswerC` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选项C',
  `AnswerD` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选项D',
  PRIMARY KEY (`QNum`) USING BTREE,
  INDEX `qtype_study_QNo`(`QNo`) USING BTREE,
  CONSTRAINT `qtype_study_QNo` FOREIGN KEY (`QNo`) REFERENCES `qtype` (`QNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qtype_study
-- ----------------------------
INSERT INTO `qtype_study` VALUES (1, 1, '您觉得目前自己的状态是', '有目标并努力中', '有目标但还未开始实施', '没有目标', '不想理睬');
INSERT INTO `qtype_study` VALUES (2, 1, '当你意识到自己有心理方面的问题时,你会怎样做?', '自己沉默不语', '和家人倾诉', '找朋友聊天', '找老师话');
INSERT INTO `qtype_study` VALUES (3, 1, '下列哪些您觉得给您造成的压力大', '奖学金', '学生干都竟选', '等级考试', '学校期末考试');
INSERT INTO `qtype_study` VALUES (4, 1, '你觉得自己属于哪一类的大学生', '有理想,积极努力', '很茫然,不知如何努力', '无所谓,\r\n随遇而安', '悲观,不知道人生的');
INSERT INTO `qtype_study` VALUES (5, 1, '你认为造成大学生生活困扰或者心理问题的主要原因是什么?', '人生发展与职业选择上有困难', '思想上有困难', '课程学习有困难', '自我管理能力不强');

-- ----------------------------
-- Table structure for questions
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions`  (
  `Questions` int NOT NULL AUTO_INCREMENT COMMENT '答案的编号',
  `QRNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所答问卷的编号',
  `QNum` int NULL DEFAULT NULL COMMENT '问题的编号',
  `QAnswer` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '保存的对应问题的答案',
  PRIMARY KEY (`Questions`) USING BTREE,
  INDEX `questions_QNum`(`QNum`) USING BTREE,
  INDEX `questions_QRNo`(`QRNo`) USING BTREE,
  CONSTRAINT `questions_QNum` FOREIGN KEY (`QNum`) REFERENCES `qtype_study` (`QNum`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `questions_QRNo` FOREIGN KEY (`QRNo`) REFERENCES `qresult` (`QRNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of questions
-- ----------------------------
INSERT INTO `questions` VALUES (19, '201921214120210619003944', 1, '有目标但还未开始实施');
INSERT INTO `questions` VALUES (20, '201921214120210619003944', 2, '找朋友聊天');
INSERT INTO `questions` VALUES (21, '201921214120210619003944', 3, '等级考试');
INSERT INTO `questions` VALUES (22, '201921214120210619003944', 4, '很茫然,不知如何努力');
INSERT INTO `questions` VALUES (23, '201921214120210619003944', 5, '自我管理能力不强');

-- ----------------------------
-- Table structure for root
-- ----------------------------
DROP TABLE IF EXISTS `root`;
CREATE TABLE `root`  (
  `Root` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Pwd` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Root`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of root
-- ----------------------------
INSERT INTO `root` VALUES ('root', 'root');

-- ----------------------------
-- Table structure for sask
-- ----------------------------
DROP TABLE IF EXISTS `sask`;
CREATE TABLE `sask`  (
  `SNo` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生编号',
  `SAInfo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '提问内容',
  `Time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '提问时间',
  `Ano` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否匿名',
  `TNo` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '老师编号',
  `SANo` int NOT NULL AUTO_INCREMENT COMMENT '问题的编号',
  `TAnswer` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '老实的回答',
  PRIMARY KEY (`SANo`) USING BTREE,
  INDEX `sask_SNo`(`SNo`) USING BTREE,
  INDEX `sask_TNo`(`TNo`) USING BTREE,
  INDEX `sask_SNo_TNo_SANo`(`SNo`, `TNo`, `SANo`) USING BTREE,
  INDEX `sask_SANo`(`SANo`) USING BTREE,
  CONSTRAINT `sask_SNo` FOREIGN KEY (`SNo`) REFERENCES `studentinfo` (`SNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sask_TNo` FOREIGN KEY (`TNo`) REFERENCES `teacherinfo` (`TNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sask
-- ----------------------------

-- ----------------------------
-- Table structure for studentinfo
-- ----------------------------
DROP TABLE IF EXISTS `studentinfo`;
CREATE TABLE `studentinfo`  (
  `SNo` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生学号/登录名',
  `SName` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生姓名',
  `Birth` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生生日',
  `SPNo` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生电话',
  `Major` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生学院专业',
  `Grade` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生年级',
  `SInstructor` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生辅导员编号',
  `SPwd` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生登录密码',
  PRIMARY KEY (`SNo`) USING BTREE,
  INDEX `studentinfo_SInstructor`(`SInstructor`) USING BTREE,
  INDEX `SNo`(`SNo`) USING BTREE,
  INDEX `SNo_SPwd`(`SNo`, `SPwd`) USING BTREE,
  CONSTRAINT `studentinfo_SInstructor` FOREIGN KEY (`SInstructor`) REFERENCES `instructorinfo` (`SInstructor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of studentinfo
-- ----------------------------
INSERT INTO `studentinfo` VALUES ('2019212141', '小文', '2020年1月1日', '13072994898', '生物信息学院医学信息工程', '2019', 'x2019212110', '2019212141');
INSERT INTO `studentinfo` VALUES ('2019212142', '小屋', '2001年1月2日', '13029498493', '计算机学院计算机科学与技术', '2019', 'x2019212111', '2019212142');
INSERT INTO `studentinfo` VALUES ('2019212143', '小刘', '2000年1月3日', '12302029399', '外国语学院商务英语', '2019', 'x2019212113', '2019212143');
INSERT INTO `studentinfo` VALUES ('2019212144', '小王', '2000年1月4日', '13992991000', '理学院物理学', '2019', 'x2019212112', '2019212144');
INSERT INTO `studentinfo` VALUES ('2019212145', '小周', '2000年1月5日', '19882888888', '生物信息学院医学信息工程', '2019', 'x2019212110', '2019212145');
INSERT INTO `studentinfo` VALUES ('2019212146', '小郑', '2001年1月1日', '19282828288', '生物信息学院医学信息工程', '2019', 'x2019212110', '2019212146');

-- ----------------------------
-- Table structure for teacherinfo
-- ----------------------------
DROP TABLE IF EXISTS `teacherinfo`;
CREATE TABLE `teacherinfo`  (
  `TNo` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '老师的工号/登录名',
  `TName` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '老师的名字',
  `TBirth` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '老师生日',
  `TPhone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '老师手机',
  `TEmail` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '老师邮箱',
  `TCollege` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属学院',
  `TWork` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '老师工作时间',
  `TPwd` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'l老师的登录密码',
  PRIMARY KEY (`TNo`) USING BTREE,
  INDEX `teacherinfo_TNo_TPwd`(`TNo`, `TPwd`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teacherinfo
-- ----------------------------
INSERT INTO `teacherinfo` VALUES ('T200001', '文老师', '1990年', '19202020200', '1029393922@qq.co', '生物信息学院', '9:00-17:00', 'T200001');
INSERT INTO `teacherinfo` VALUES ('T200002', '姜老师', '1990年', '13294939032', '1992293839@qq.com', '计算机科学与技术学院', '8:00-12:00', 'T200002');
INSERT INTO `teacherinfo` VALUES ('T200003', '李老师', '1991年', '18282828992', '1000299299@qq.com', '理学院', '9:20-18:00', 'T200003');
INSERT INTO `teacherinfo` VALUES ('T200004', '冯老师', '1994年', '1999292929', '1918181818@qq.com', '外国语学院', '9:00-16:00', 'T200004');

-- ----------------------------
-- Table structure for tevalute
-- ----------------------------
DROP TABLE IF EXISTS `tevalute`;
CREATE TABLE `tevalute`  (
  `TENo` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评价的编号',
  `Evalute` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '评价的内容',
  `TETime` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评价时间',
  `TAno` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否匿名',
  `SNo` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生学号',
  `TNo` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '老师工号',
  PRIMARY KEY (`TENo`) USING BTREE,
  INDEX `tevalute_SNo`(`SNo`) USING BTREE,
  INDEX `tevalute_TNo`(`TNo`) USING BTREE,
  INDEX `tevalute_TENo`(`TENo`) USING BTREE,
  CONSTRAINT `tevalute_SNo` FOREIGN KEY (`SNo`) REFERENCES `studentinfo` (`SNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tevalute_TNo` FOREIGN KEY (`TNo`) REFERENCES `teacherinfo` (`TNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tevalute
-- ----------------------------

-- ----------------------------
-- View structure for view_question_teacher
-- ----------------------------
DROP VIEW IF EXISTS `view_question_teacher`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_question_teacher` AS select `questions`.`Questions` AS `Questions`,`questions`.`QRNo` AS `QRNo`,`questions`.`QAnswer` AS `QAnswer`,`qtype_study`.`Question` AS `Question` from (`questions` join `qtype_study` on((`questions`.`QNum` = `qtype_study`.`QNum`)));

-- ----------------------------
-- View structure for view_student_evalute_show
-- ----------------------------
DROP VIEW IF EXISTS `view_student_evalute_show`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_student_evalute_show` AS select `tevalute`.`TENo` AS `TENo`,`tevalute`.`Evalute` AS `Evalute`,`tevalute`.`TETime` AS `TETime`,`tevalute`.`TAno` AS `TAno`,`teacherinfo`.`TName` AS `TName`,`tevalute`.`SNo` AS `SNo`,`tevalute`.`TNo` AS `TNo` from (`tevalute` join `teacherinfo` on((`tevalute`.`TNo` = `teacherinfo`.`TNo`)));

-- ----------------------------
-- View structure for view_student_show_ask
-- ----------------------------
DROP VIEW IF EXISTS `view_student_show_ask`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_student_show_ask` AS select `sask`.`SNo` AS `SNo`,`sask`.`SAInfo` AS `SAInfo`,`sask`.`Time` AS `Time`,`sask`.`Ano` AS `Ano`,`teacherinfo`.`TName` AS `TName`,`sask`.`SANo` AS `SANo`,`sask`.`TAnswer` AS `TAnswer` from (`sask` join `teacherinfo` on((`sask`.`TNo` = `teacherinfo`.`TNo`)));

-- ----------------------------
-- View structure for view_studentinfo_show
-- ----------------------------
DROP VIEW IF EXISTS `view_studentinfo_show`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_studentinfo_show` AS select `studentinfo`.`SNo` AS `SNo`,`studentinfo`.`SName` AS `SName`,`studentinfo`.`Birth` AS `Birth`,`studentinfo`.`SPNo` AS `SPNo`,`studentinfo`.`Major` AS `Major`,`studentinfo`.`Grade` AS `Grade`,`instructorinfo`.`IName` AS `IName`,`studentinfo`.`SPwd` AS `SPwd` from (`studentinfo` join `instructorinfo` on((`studentinfo`.`SInstructor` = `instructorinfo`.`SInstructor`)));

-- ----------------------------
-- View structure for view_studentinfo_sno_spwd
-- ----------------------------
DROP VIEW IF EXISTS `view_studentinfo_sno_spwd`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_studentinfo_sno_spwd` AS select `studentinfo`.`SNo` AS `SNo`,`studentinfo`.`SPwd` AS `SPwd` from `studentinfo`;

-- ----------------------------
-- View structure for view_teacherinfo_tno_tpwd
-- ----------------------------
DROP VIEW IF EXISTS `view_teacherinfo_tno_tpwd`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_teacherinfo_tno_tpwd` AS select `teacherinfo`.`TNo` AS `TNo`,`teacherinfo`.`TPwd` AS `TPwd` from `teacherinfo`;

SET FOREIGN_KEY_CHECKS = 1;
