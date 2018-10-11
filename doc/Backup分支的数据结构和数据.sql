/*
Navicat MySQL Data Transfer

Source Server         : demo
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-10-11 16:57:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for le_one_one
-- ----------------------------
DROP TABLE IF EXISTS `le_one_one`;
CREATE TABLE `le_one_one` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of le_one_one
-- ----------------------------
INSERT INTO `le_one_one` VALUES ('123', 'asdfa', '124');

-- ----------------------------
-- Table structure for le_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `le_role_resource`;
CREATE TABLE `le_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(20) DEFAULT NULL COMMENT '角色id',
  `resource_id` varchar(20) DEFAULT NULL COMMENT '资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of le_role_resource
-- ----------------------------
INSERT INTO `le_role_resource` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for le_system_resource
-- ----------------------------
DROP TABLE IF EXISTS `le_system_resource`;
CREATE TABLE `le_system_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` varchar(20) DEFAULT NULL COMMENT '所属父级id  （系统节点默认为system 其他类型的随意）',
  `type` int(1) DEFAULT NULL COMMENT '资源类型（1 ：目录  2：按钮  3：数据或者文件接口）',
  `resource_name` varchar(100) DEFAULT NULL COMMENT '资源名称',
  `perms` varchar(255) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL COMMENT '资源地址',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_code` varchar(60) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_code` varchar(60) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of le_system_resource
-- ----------------------------
INSERT INTO `le_system_resource` VALUES ('1', null, '1', 'home主页', 'home:index.action', '/home/index.action', null, null, null, null, null);
INSERT INTO `le_system_resource` VALUES ('2', '1', '2', '查找所有', 'home:findAll.json', '', null, null, null, null, null);

-- ----------------------------
-- Table structure for le_system_role
-- ----------------------------
DROP TABLE IF EXISTS `le_system_role`;
CREATE TABLE `le_system_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(60) DEFAULT NULL,
  `role_name` varchar(60) DEFAULT NULL,
  `create_code` varchar(60) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_code` varchar(60) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `state` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of le_system_role
-- ----------------------------
INSERT INTO `le_system_role` VALUES ('1', 'ADMIN', '超级管理员', null, null, null, null, 'Y');
INSERT INTO `le_system_role` VALUES ('2', 'DATAQUERY', '查看员', null, null, null, null, 'Y');
INSERT INTO `le_system_role` VALUES ('3', 'OPERATER', '操作员', null, null, null, null, 'Y');

-- ----------------------------
-- Table structure for le_system_user
-- ----------------------------
DROP TABLE IF EXISTS `le_system_user`;
CREATE TABLE `le_system_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `password` varchar(60) DEFAULT NULL,
  `user_account` varchar(60) DEFAULT NULL COMMENT '账号',
  `user_name` varchar(60) DEFAULT NULL COMMENT '姓名',
  `age` int(3) DEFAULT NULL COMMENT '性别',
  `sex` varchar(10) DEFAULT NULL COMMENT '年龄',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL,
  `create_code` varchar(60) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_code` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of le_system_user
-- ----------------------------
INSERT INTO `le_system_user` VALUES ('123', 'bcd9af9337cfd7ff0619d6d28b9023ad', 'user1', 'ad测试1', '17', 'man', 'iiiii@qq.com1', '2018-08-16 11:36:40', 'root', '2018-08-23 11:13:00', 'he1');
INSERT INTO `le_system_user` VALUES ('124', '7d9eb7653f1b6fc507e9966e720bdce1', 'user2', 'ad测试2', '17', 'man', 'iiiii@qq.com2', null, 'wu', '2018-08-23 11:13:00', 'ad');
INSERT INTO `le_system_user` VALUES ('125', null, 'user3', 'ad测试3', '19', 'man', 'iiiii@qq.com3', null, 'wu', '2018-08-23 11:13:00', 'he3');
INSERT INTO `le_system_user` VALUES ('126', null, 'user4', 'ad测试4', '17', 'man', 'iiiii@qq.com4', null, 'wu', '2018-08-23 11:13:00', 'haa');
INSERT INTO `le_system_user` VALUES ('127', null, 'user5', 'ad测试5', '21', 'man', 'iiiii@qq.com5', null, 'wu', '2018-08-23 11:13:00', 'he5');
INSERT INTO `le_system_user` VALUES ('128', null, 'user6', 'ad测试6', '22', 'man', 'iiiii@qq.com6', null, 'wu', '2018-08-23 11:13:00', 'he6');
INSERT INTO `le_system_user` VALUES ('129', null, 'user7', 'ad测试7', '23', 'man', 'iiiii@qq.com7', null, 'wu', '2018-08-23 11:13:00', 'he7');
INSERT INTO `le_system_user` VALUES ('130', null, 'user8', 'ad测试8', '24', 'man', 'iiiii@qq.com8', null, 'wu', '2018-08-23 11:13:00', 'he8');
INSERT INTO `le_system_user` VALUES ('131', null, 'user9', 'ad测试9', '25', 'man', 'iiiii@qq.com9', null, 'wu', '2018-08-23 11:13:00', 'he9');
INSERT INTO `le_system_user` VALUES ('132', null, 'user10', 'ad测试10', '25', 'man', 'iiiii@qq.com10', null, 'wu', '2018-08-23 11:13:00', 'he10');
INSERT INTO `le_system_user` VALUES ('133', null, 'adtesti11', 'ad测试11', '27', 'man', 'iiiii@qq.com11', null, 'wu', '2018-08-23 11:13:00', 'he11');
INSERT INTO `le_system_user` VALUES ('134', null, 'adtesti12', 'ad测试12', '25', 'man', 'iiiii@qq.com12', null, 'wu', '2018-08-23 11:13:00', 'aaa');
INSERT INTO `le_system_user` VALUES ('135', null, 'adtesti13', 'ad测试13', '29', 'man', 'iiiii@qq.com13', null, 'wu', '2018-08-23 11:13:00', 'asdfas');
INSERT INTO `le_system_user` VALUES ('136', null, 'adtesti14', 'ad测试14', '25', 'man', 'iiiii@qq.com14', null, 'wu', '2018-08-23 11:13:00', 'he14');
INSERT INTO `le_system_user` VALUES ('137', null, 'adtesti15', 'ad测试15', '31', 'man', 'iiiii@qq.com15', null, 'wu', '2018-08-23 11:13:00', 'he15');
INSERT INTO `le_system_user` VALUES ('138', null, 'adtesti16', 'ad测试16', '17', 'man', 'iiiii@qq.com16', null, 'wu', '2018-08-23 11:13:00', 'he16');
INSERT INTO `le_system_user` VALUES ('139', null, 'adtesti17', 'ad测试17', '17', 'man', 'iiiii@qq.com17', null, 'wu', '2018-08-23 11:13:00', 'he17');
INSERT INTO `le_system_user` VALUES ('140', null, 'adtesti18', 'ad测试18', '34', 'man', 'iiiii@qq.com18', null, 'wu', '2018-08-23 11:13:00', 'he18');
INSERT INTO `le_system_user` VALUES ('141', null, 'adtesti19', 'ad测试19', '35', 'man', 'iiiii@qq.com19', null, 'wu', '2018-08-23 11:13:00', 'h32');
INSERT INTO `le_system_user` VALUES ('142', null, 'adtesti20', 'ad测试20', '17', 'man', 'iiiii@qq.com20', null, 'wu', '2018-08-23 11:13:00', 'h213');
INSERT INTO `le_system_user` VALUES ('143', null, 'adtesti21', 'ad测试21', '37', 'man', 'iiiii@qq.com21', null, 'wu', '2018-08-23 11:13:00', 'he21');
INSERT INTO `le_system_user` VALUES ('144', null, 'adtesti22', 'ad测试22', '38', 'man', 'iiiii@qq.com22', null, 'wu', '2018-08-23 11:13:00', 'he22');
INSERT INTO `le_system_user` VALUES ('145', null, 'adtesti23', 'ad测试23', '39', 'man', 'iiiii@qq.com23', null, 'wu', '2018-08-23 11:13:00', 'he23');

-- ----------------------------
-- Table structure for le_user_authorization
-- ----------------------------
DROP TABLE IF EXISTS `le_user_authorization`;
CREATE TABLE `le_user_authorization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(60) DEFAULT NULL COMMENT '用户账号',
  `role_id` varchar(60) DEFAULT NULL COMMENT '权限id',
  `auth_area` varchar(1000) DEFAULT NULL COMMENT '授权的区域数据（模拟）',
  `auth_data` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of le_user_authorization
-- ----------------------------
INSERT INTO `le_user_authorization` VALUES ('21', 'user0', '1', null, null);
INSERT INTO `le_user_authorization` VALUES ('22', 'user1', '1', null, null);
INSERT INTO `le_user_authorization` VALUES ('23', 'user2', '1', null, null);
INSERT INTO `le_user_authorization` VALUES ('24', 'user3', '1', null, null);
INSERT INTO `le_user_authorization` VALUES ('25', 'user4', '1', null, null);
INSERT INTO `le_user_authorization` VALUES ('26', 'user5', '1', null, null);
INSERT INTO `le_user_authorization` VALUES ('27', 'user6', '1', null, null);
INSERT INTO `le_user_authorization` VALUES ('28', 'user7', '1', null, null);
INSERT INTO `le_user_authorization` VALUES ('29', 'user8', '1', null, null);
INSERT INTO `le_user_authorization` VALUES ('30', 'user9', '1', null, null);
INSERT INTO `le_user_authorization` VALUES ('31', 'user0', '2', null, null);
INSERT INTO `le_user_authorization` VALUES ('32', 'user1', '2', null, null);
INSERT INTO `le_user_authorization` VALUES ('33', 'user2', '2', null, null);
INSERT INTO `le_user_authorization` VALUES ('34', 'user3', '2', null, null);
INSERT INTO `le_user_authorization` VALUES ('35', 'user4', '2', null, null);
INSERT INTO `le_user_authorization` VALUES ('36', 'user5', '2', null, null);
INSERT INTO `le_user_authorization` VALUES ('37', 'user6', '2', null, null);
INSERT INTO `le_user_authorization` VALUES ('38', 'user7', '2', null, null);
INSERT INTO `le_user_authorization` VALUES ('39', 'user8', '2', null, null);
INSERT INTO `le_user_authorization` VALUES ('40', 'user9', '2', null, null);
