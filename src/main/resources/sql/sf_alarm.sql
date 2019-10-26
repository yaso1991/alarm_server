/*
 Navicat Premium Data Transfer

 Source Server         : SQL80
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : sf_alarm

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 26/10/2019 15:52:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for alarm_info
-- ----------------------------
DROP TABLE IF EXISTS `alarm_info`;
CREATE TABLE `alarm_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `card_reader_id` int(11) NULL DEFAULT NULL,
  `state` enum('监控','维护','退出') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '维护',
  `alarming` tinyint(1) NULL DEFAULT 0,
  `alarm_time` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '-',
  `alarm_span` int(11) NULL DEFAULT NULL,
  `push_level` enum('未推送','班组长级','主任级','经理级') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未推送',
  `employee_id` int(11) NULL DEFAULT NULL,
  `master_id` int(11) NULL DEFAULT NULL,
  `com_port` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `point_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_UNIQUE`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of alarm_info
-- ----------------------------
INSERT INTO `alarm_info` VALUES (1, '1#报警点', '测厚系统', 1, '监控', 0, NULL, 0, '未推送', 4, 17, 'COM8', '1#报警点');
INSERT INTO `alarm_info` VALUES (2, '2#报警点', '瑕疵识别', 1, '监控', 0, NULL, 0, '未推送', 4, 17, 'COM8', '2#报警点');
INSERT INTO `alarm_info` VALUES (3, '3号报警点', '测厚系统', 1, '维护', 0, NULL, 0, '未推送', 4, 17, 'COM9', '3#报警点');
INSERT INTO `alarm_info` VALUES (4, '4号报警点', '测温系统', 1, '维护', 0, NULL, 0, '未推送', 4, 17, 'COM9', '4#报警点');
INSERT INTO `alarm_info` VALUES (5, '5号报警点', '位移同步', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (6, '6号报警点', '位移同步', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (7, '7号报警点', '测厚系统', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (8, '8号报警点', '瑕疵识别', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (9, '9号报警点', '测厚系统', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (10, '10号报警点', '测温系统', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (11, '11号报警点', '位移同步', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (12, '12号报警点', '位移同步', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (13, '13号报警点', '测厚系统', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (14, '14号报警点', '测温系统', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (15, '15号报警点', '位移同步', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (16, '16号报警点', '位移同步', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (17, '17号报警点', '测厚系统', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (18, '18号报警点', '瑕疵识别', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (19, '19号报警点', '测厚系统', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (20, '20号报警点', '测温系统', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (21, '21号报警点', '位移同步', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (22, '22号报警点', '位移同步', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (23, '23号报警点', '测厚系统', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (24, '24号报警点', '测温系统', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (25, '25号报警点', '位移同步', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (26, '26号报警点', '位移同步', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (27, '27号报警点', '测厚系统', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (28, '28号报警点', '瑕疵识别', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (29, '29号报警点', '测厚系统', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (30, '30号报警点', '测温系统', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (31, '31号报警点', '位移同步', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);
INSERT INTO `alarm_info` VALUES (32, '32号报警点', '位移同步', 1, '维护', 0, NULL, 0, '未推送', 4, 17, NULL, NULL);

-- ----------------------------
-- Table structure for alarm_item_info
-- ----------------------------
DROP TABLE IF EXISTS `alarm_item_info`;
CREATE TABLE `alarm_item_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alarm_id` int(11) NULL DEFAULT NULL,
  `alarm_start_time` timestamp(4) NULL DEFAULT NULL,
  `alarm_span` int(11) NULL DEFAULT 0,
  `push_level` enum('未推送','班组长级','主任级','经理级') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未推送',
  `employee_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `master_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 225 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of alarm_item_info
-- ----------------------------
INSERT INTO `alarm_item_info` VALUES (23, 1, '2019-07-26 08:43:09.0000', 12, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (24, 1, '2019-07-26 08:43:49.0000', 27, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (25, 1, '2019-07-26 08:45:27.0000', 3, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (26, 1, '2019-07-26 08:46:52.0000', 4, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (27, 1, '2019-07-26 08:50:38.0000', 38, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (28, 1, '2019-07-26 08:53:13.0000', 1042, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (29, 1, '2019-07-26 09:12:20.0000', 26, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (30, 1, '2019-07-26 09:14:16.0000', 21, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (31, 1, '2019-07-26 09:17:26.0000', 8, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (32, 1, '2019-07-26 09:18:10.0000', 15, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (33, 1, '2019-07-26 09:19:32.0000', 3, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (34, 1, '2019-07-26 09:21:42.0000', 2, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (35, 1, '2019-07-26 09:23:31.0000', 96, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (36, 1, '2019-07-26 09:25:24.0000', 96, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (37, 1, '2019-07-26 09:27:15.0000', 10, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (38, 1, '2019-07-26 09:28:37.0000', 33, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (39, 1, '2019-07-26 09:31:13.0000', 1333, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (40, 1, '2019-07-26 09:55:39.0000', 73, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (41, 1, '2019-07-26 09:57:06.0000', 9, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (42, 1, '2019-07-26 09:58:01.0000', 35, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (43, 1, '2019-07-26 09:59:22.0000', 11, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (44, 1, '2019-07-26 10:00:18.0000', 42, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (45, 1, '2019-07-26 10:01:41.0000', 76, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (46, 1, '2019-07-26 10:03:11.0000', 56, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (47, 1, '2019-07-26 10:05:00.0000', 8, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (48, 1, '2019-07-26 10:05:40.0000', 35, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (49, 1, '2019-07-26 10:07:33.0000', 14, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (50, 1, '2019-07-26 10:11:20.0000', 59, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (51, 1, '2019-07-26 10:14:02.0000', 112, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (52, 1, '2019-07-26 10:16:12.0000', 33, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (53, 1, '2019-07-26 10:17:21.0000', 86, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (54, 1, '2019-07-26 10:19:15.0000', 18, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (55, 1, '2019-07-26 10:20:05.0000', 31, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (56, 1, '2019-07-26 10:21:30.0000', 7, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (57, 1, '2019-07-26 10:22:27.0000', 18, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (58, 1, '2019-07-26 10:24:09.0000', 54, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (59, 1, '2019-07-26 10:25:16.0000', 15, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (60, 1, '2019-07-26 10:26:12.0000', 45, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (61, 1, '2019-07-26 10:27:07.0000', 23, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (62, 1, '2019-07-26 10:28:02.0000', 20, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (63, 1, '2019-07-26 10:29:51.0000', 9, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (64, 1, '2019-07-26 10:30:17.0000', 14, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (65, 1, '2019-07-26 10:30:58.0000', 3, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (66, 1, '2019-07-26 10:32:53.0000', 23, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (67, 1, '2019-07-26 10:33:52.0000', 39, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (68, 1, '2019-07-26 10:34:42.0000', 30, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (69, 1, '2019-07-26 10:35:23.0000', 35, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (70, 1, '2019-07-26 10:36:08.0000', 14, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (71, 1, '2019-07-26 10:36:46.0000', 41, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (72, 1, '2019-07-26 10:37:39.0000', 69, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (73, 1, '2019-07-26 10:39:04.0000', 10, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (74, 1, '2019-07-26 10:40:01.0000', 145, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (75, 1, '2019-07-26 10:42:47.0000', 16, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (76, 1, '2019-07-26 10:44:53.0000', 7, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (77, 1, '2019-07-26 10:45:51.0000', 9, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (78, 1, '2019-07-26 10:47:16.0000', 30, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (79, 1, '2019-07-26 10:49:19.0000', 51, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (80, 1, '2019-07-26 11:00:03.0000', 10, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (81, 1, '2019-07-26 11:00:54.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (82, 1, '2019-07-26 11:04:09.0000', 3, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (83, 1, '2019-07-26 11:06:41.0000', 4, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (84, 1, '2019-07-26 11:07:56.0000', 0, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (85, 1, '2019-07-26 11:08:34.0000', 3, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (86, 1, '2019-07-26 11:09:08.0000', 11, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (87, 1, '2019-07-26 11:09:44.0000', 76, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (88, 1, '2019-07-26 11:11:14.0000', 14, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (89, 1, '2019-07-26 11:12:37.0000', 319, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (90, 1, '2019-07-26 11:18:34.0000', 48, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (91, 1, '2019-07-26 11:19:36.0000', 14, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (92, 1, '2019-07-26 11:20:38.0000', 8, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (93, 1, '2019-07-26 11:24:03.0000', 31, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (94, 1, '2019-07-26 11:25:14.0000', 15, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (95, 1, '2019-07-26 11:26:03.0000', 44, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (96, 1, '2019-07-26 11:27:00.0000', 54, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (97, 1, '2019-07-26 11:28:07.0000', 10, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (98, 1, '2019-07-26 11:28:59.0000', 0, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (99, 1, '2019-07-26 11:29:35.0000', 32, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (100, 1, '2019-07-26 11:30:31.0000', 59, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (101, 1, '2019-07-26 11:34:01.0000', 218, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (102, 1, '2019-07-26 11:38:05.0000', 27, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (103, 1, '2019-07-26 11:38:56.0000', 4, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (104, 1, '2019-07-26 11:40:43.0000', 7, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (105, 1, '2019-07-26 11:43:05.0000', 9, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (106, 1, '2019-07-26 13:00:39.0000', 33, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (107, 1, '2019-07-26 14:10:16.0000', 1501, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (108, 1, '2019-07-29 15:35:29.0000', 54, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (109, 1, '2019-07-29 16:29:45.0000', 87, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (110, 1, '2019-07-29 16:31:36.0000', 102, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (111, 1, '2019-07-29 16:34:04.0000', 45, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (112, 1, '2019-07-29 16:36:33.0000', 99, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (113, 1, '2019-07-29 16:37:12.0000', 0, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (114, 1, '2019-07-29 16:46:52.0000', 82, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (115, 1, '2019-07-29 16:48:29.0000', 55, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (116, 1, '2019-07-29 16:49:56.0000', 11, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (117, 1, '2019-07-29 17:00:44.0000', 5, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (118, 1, '2019-07-29 17:01:49.0000', 3, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (119, 1, '2019-07-29 17:10:56.0000', 34, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (120, 1, '2019-07-29 17:27:13.0000', 114, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (121, 1, '2019-07-30 08:31:40.0000', 13, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (122, 1, '2019-07-30 08:57:04.0000', 1605, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (123, 1, '2019-07-31 15:55:36.0000', 114, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (124, 1, '2019-07-31 15:58:23.0000', 215, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (125, 1, '2019-07-31 16:02:47.0000', 47, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (126, 1, '2019-07-31 16:04:18.0000', 6, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (127, 1, '2019-07-31 16:04:56.0000', 758, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (128, 1, '2019-07-31 16:55:16.0000', 804, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (129, 1, '2019-08-05 13:20:39.0000', 182, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (130, 1, '2019-08-05 13:42:19.0000', 77, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (131, 1, '2019-08-05 13:47:21.0000', 267, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (132, 1, '2019-08-05 13:59:24.0000', 528, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (133, 1, '2019-08-05 14:09:43.0000', 908, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (134, 1, '2019-08-07 16:45:18.0000', 0, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (135, 1, '2019-08-07 16:55:20.0000', 36, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (136, 1, '2019-08-07 17:00:03.0000', 117, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (137, 1, '2019-08-07 17:07:41.0000', 121, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (138, 1, '2019-08-07 17:10:42.0000', 196, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (139, 1, '2019-08-08 09:01:37.0000', 36, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (140, 1, '2019-08-08 18:26:39.0000', 208, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (141, 1, '2019-08-08 18:52:56.0000', 13, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (142, 1, '2019-08-09 08:22:47.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (143, 1, '2019-08-09 08:24:33.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (144, 1, '2019-08-09 08:28:05.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (145, 1, '2019-08-09 08:31:00.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (146, 1, '2019-08-09 08:41:00.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (147, 1, '2019-08-09 08:44:34.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (148, 1, '2019-08-09 08:46:18.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (149, 1, '2019-08-09 08:51:00.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (150, 1, '2019-08-09 08:52:49.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (151, 1, '2019-08-09 08:54:34.0000', 0, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (152, 1, '2019-08-09 08:56:20.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (153, 1, '2019-08-09 10:41:35.0000', 206, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (154, 1, '2019-08-09 11:11:03.0000', 44, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (155, 1, '2019-08-09 12:10:51.0000', 0, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (156, 1, '2019-08-09 12:20:17.0000', 257, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (157, 1, '2019-08-09 12:40:17.0000', 251, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (158, 1, '2019-08-09 15:32:14.0000', 346, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (159, 1, '2019-08-09 16:00:59.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (160, 1, '2019-08-10 04:36:55.0000', 351, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (161, 1, '2019-08-10 04:39:00.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (162, 1, '2019-08-10 04:39:59.0000', 217, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (163, 1, '2019-08-10 06:59:01.0000', 249, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (164, 1, '2019-08-10 15:33:53.0000', 75, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (165, 1, '2019-08-11 06:11:06.0000', 21, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (166, 1, '2019-08-11 07:16:23.0000', 105, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (167, 1, '2019-08-11 10:16:36.0000', 53, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (168, 1, '2019-08-11 13:48:29.0000', 3, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (169, 1, '2019-08-11 19:44:27.0000', 39, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (170, 1, '2019-08-11 20:57:19.0000', 9, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (171, 1, '2019-08-11 20:59:41.0000', 33, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (172, 1, '2019-08-11 21:16:05.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (173, 1, '2019-08-11 21:27:42.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (174, 1, '2019-08-11 21:48:30.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (175, 1, '2019-08-11 22:37:43.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (176, 1, '2019-08-11 22:45:11.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (177, 1, '2019-08-11 22:58:31.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (178, 1, '2019-08-11 23:11:00.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (179, 1, '2019-08-11 23:28:31.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (180, 1, '2019-08-11 23:48:29.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (181, 1, '2019-08-11 23:53:29.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (182, 1, '2019-08-12 00:00:10.0000', 0, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (183, 1, '2019-08-12 06:25:36.0000', 229, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (184, 1, '2019-08-12 06:47:58.0000', 13, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (185, 1, '2019-08-12 07:01:22.0000', 149, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (186, 1, '2019-08-12 08:10:49.0000', 207, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (187, 1, '2019-08-12 08:19:46.0000', 337, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (188, 1, '2019-08-12 10:13:01.0000', 27, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (189, 1, '2019-08-12 11:31:59.0000', 17, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (190, 1, '2019-08-12 12:45:46.0000', 31, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (191, 1, '2019-08-12 13:16:03.0000', 0, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (192, 1, '2019-08-12 13:19:34.0000', 11, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (193, 1, '2019-08-12 17:16:51.0000', 408723, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (194, 1, '2019-08-20 14:36:49.0000', 9, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (195, 1, '2019-08-20 14:38:56.0000', 12, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (196, 1, '2019-08-20 14:39:17.0000', 18, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (197, 1, '2019-08-21 20:28:27.0000', 7, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (198, 1, '2019-08-21 20:34:08.0000', 5, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (199, 1, '2019-08-21 20:34:58.0000', 55, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (200, 1, '2019-08-22 14:38:32.0000', 5, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (201, 1, '2019-08-22 14:59:20.0000', 26, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (202, 1, '2019-08-23 07:07:04.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (203, 1, '2019-08-24 08:12:26.0000', 15, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (204, 1, '2019-08-24 08:12:56.0000', 110, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (205, 1, '2019-08-24 09:26:16.0000', 0, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (206, 1, '2019-08-24 21:20:42.0000', 229, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (207, 1, '2019-08-25 04:16:14.0000', 7, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (208, 1, '2019-08-25 04:30:08.0000', 61, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (209, 1, '2019-08-25 04:31:37.0000', 3, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (210, 1, '2019-08-25 07:16:26.0000', 1, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (211, 1, '2019-08-25 07:16:42.0000', 3, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (212, 1, '2019-08-25 16:58:01.0000', 37, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (213, 1, '2019-08-26 01:40:32.0000', 171, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (214, 1, '2019-08-26 02:25:19.0000', 413, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (215, 1, '2019-08-26 15:46:31.0000', 1107, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (216, 1, '2019-08-26 15:52:07.0000', 15, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (217, 1, '2019-08-26 16:37:59.0000', 16, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (218, 1, '2019-08-26 17:41:09.0000', 205, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (219, 1, '2019-08-26 18:01:35.0000', 275, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (220, 1, '2019-08-26 18:05:40.0000', 13, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (221, 1, '2019-08-27 10:37:26.0000', 5, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (222, 1, '2019-08-27 10:38:34.0000', 0, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (223, 1, '2019-08-27 10:42:58.0000', 16, '未推送', '4', 17);
INSERT INTO `alarm_item_info` VALUES (224, 1, '2019-08-27 13:16:39.0000', 99, '未推送', '4', 17);

-- ----------------------------
-- Table structure for card_reader_info
-- ----------------------------
DROP TABLE IF EXISTS `card_reader_info`;
CREATE TABLE `card_reader_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of card_reader_info
-- ----------------------------
INSERT INTO `card_reader_info` VALUES (1, '1#刷卡点', '');
INSERT INTO `card_reader_info` VALUES (2, '2#刷卡点', ' ');
INSERT INTO `card_reader_info` VALUES (3, '3#刷卡点', ' ');
INSERT INTO `card_reader_info` VALUES (4, '4#刷卡点', ' ');
INSERT INTO `card_reader_info` VALUES (5, '5#刷卡点', ' ');
INSERT INTO `card_reader_info` VALUES (6, '管理刷卡点', NULL);

-- ----------------------------
-- Table structure for employee_info
-- ----------------------------
DROP TABLE IF EXISTS `employee_info`;
CREATE TABLE `employee_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `position` enum('员工','班组长','主任','经理') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '员工',
  `work_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '-',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `employeeNum_UNIQUE`(`work_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_info
-- ----------------------------
INSERT INTO `employee_info` VALUES (4, '员工1', '员工', '14835', '');
INSERT INTO `employee_info` VALUES (14, '员工2', '员工', '35156', '');
INSERT INTO `employee_info` VALUES (17, '班组长1', '班组长', '12345678', '1721662545@qq.com');
INSERT INTO `employee_info` VALUES (21, '班组长2', '班组长', '123456789', '1441825297@qq.com');
INSERT INTO `employee_info` VALUES (22, '主任1', '主任', '43567', '1721662545@qq.com');
INSERT INTO `employee_info` VALUES (23, '主任2', '主任', '9875643', '1441825297@qq.com');
INSERT INTO `employee_info` VALUES (24, '经理1', '经理', '43242', '1721662545@qq.com');
INSERT INTO `employee_info` VALUES (25, '经理2', '经理', '423423', '-1441825297@qq.com');

-- ----------------------------
-- Table structure for manager_info
-- ----------------------------
DROP TABLE IF EXISTS `manager_info`;
CREATE TABLE `manager_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role` enum('ADMIN','USER') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'USER',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager_info
-- ----------------------------
INSERT INTO `manager_info` VALUES (1, 'admin', '$2a$04$4BCSLvJRCFzZvT9cyLjVJOV8ojcxn1EnNM0OC7kNMCrXZ/B9PZVqm', '系统管理员', 'ADMIN');
INSERT INTO `manager_info` VALUES (2, 'user', '$2a$04$d6b4gfm3M/M/IxesGGLIXuTo1qLic4cas3pRG/4U7UyU0AknPrm6W', '游客', 'USER');

-- ----------------------------
-- Table structure for system_config
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `monitor_push_delay` int(11) NULL DEFAULT NULL,
  `master_push_delay` int(11) NULL DEFAULT NULL,
  `manager_push_delay` int(11) NULL DEFAULT NULL,
  `sum_push_time` time(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_config
-- ----------------------------
INSERT INTO `system_config` VALUES (1, 20, 40, 60, '15:22:13');

SET FOREIGN_KEY_CHECKS = 1;
