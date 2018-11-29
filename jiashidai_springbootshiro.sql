/*
Navicat MySQL Data Transfer

Source Server         : ssmtes
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : jiashidai_springbootshiro

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-11-29 17:36:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for jiashidai_demo
-- ----------------------------
DROP TABLE IF EXISTS `jiashidai_demo`;
CREATE TABLE `jiashidai_demo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jiashidai_demo
-- ----------------------------
INSERT INTO `jiashidai_demo` VALUES ('1', '刘旭');
INSERT INTO `jiashidai_demo` VALUES ('2', '刘冲');

-- ----------------------------
-- Table structure for jiashidai_quanxian_authority
-- ----------------------------
DROP TABLE IF EXISTS `jiashidai_quanxian_authority`;
CREATE TABLE `jiashidai_quanxian_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键编号自增长',
  `menu_code` varchar(50) NOT NULL COMMENT '菜单编码',
  `menu_name` varchar(255) DEFAULT NULL,
  `menu_type` int(11) DEFAULT NULL COMMENT '菜单类型 1 导航 2 按钮',
  `data_url` varchar(255) DEFAULT NULL COMMENT '连接路径或方法',
  `menu_class` varchar(255) DEFAULT NULL COMMENT '菜单样式',
  `parent_menucode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '上级菜单编码',
  `sequence` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排序',
  `create_time` varchar(30) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- ----------------------------
-- Records of jiashidai_quanxian_authority
-- ----------------------------
INSERT INTO `jiashidai_quanxian_authority` VALUES ('1', '100', '首页', '1', '/home', null, null, '0', null);

-- ----------------------------
-- Table structure for jiashidai_quanxian_role
-- ----------------------------
DROP TABLE IF EXISTS `jiashidai_quanxian_role`;
CREATE TABLE `jiashidai_quanxian_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_key` varchar(30) DEFAULT NULL COMMENT '角色编码',
  `create_time` varchar(30) DEFAULT NULL COMMENT '创建时间',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `role_value` varchar(40) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of jiashidai_quanxian_role
-- ----------------------------
INSERT INTO `jiashidai_quanxian_role` VALUES ('1', 'ROLE_USER', null, null, '用户');
INSERT INTO `jiashidai_quanxian_role` VALUES ('2', 'ROLE_ADMIN', null, null, '管理员');

-- ----------------------------
-- Table structure for jiashidai_quanxian_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `jiashidai_quanxian_role_authority`;
CREATE TABLE `jiashidai_quanxian_role_authority` (
  `role_id` int(11) NOT NULL COMMENT '角色主键编号',
  `authority_id` bigint(20) DEFAULT NULL COMMENT '权限主键编号',
  KEY `FK9q28ewrhntqeipl1t04kh1be7` (`role_id`) USING BTREE,
  CONSTRAINT `FK9q28ewrhntqeipl1t04kh1be7` FOREIGN KEY (`role_id`) REFERENCES `jiashidai_quanxian_role` (`role_id`),
  CONSTRAINT `fk_sys_role_permission_role_id` FOREIGN KEY (`role_id`) REFERENCES `jiashidai_quanxian_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色按钮权限表';

-- ----------------------------
-- Records of jiashidai_quanxian_role_authority
-- ----------------------------
INSERT INTO `jiashidai_quanxian_role_authority` VALUES ('1', '1');

-- ----------------------------
-- Table structure for jiashidai_quanxian_user
-- ----------------------------
DROP TABLE IF EXISTS `jiashidai_quanxian_user`;
CREATE TABLE `jiashidai_quanxian_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `login_account` varchar(30) NOT NULL COMMENT '登录账号',
  `login_pass` varchar(65) NOT NULL COMMENT '登录密码',
  `user_name` varchar(20) DEFAULT NULL COMMENT '昵称',
  `user_head` varchar(30) DEFAULT NULL COMMENT '头像',
  `user_phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `user_email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `user_sex` int(11) DEFAULT NULL COMMENT '性别',
  `user_birthday` varchar(30) DEFAULT NULL COMMENT '生日',
  `register_time` varchar(30) NOT NULL COMMENT '注册时间',
  `department_key` varchar(20) DEFAULT NULL COMMENT '部门编码',
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_sys_user_login_account` (`login_account`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of jiashidai_quanxian_user
-- ----------------------------
INSERT INTO `jiashidai_quanxian_user` VALUES ('2', 'hzw2312', 'e3217fe0af348633f645bbd6aa8135e1', null, null, null, 'hzw2312@sina.com', null, null, '2017-01-18 14:39:23', null, 'hhsykx');
INSERT INTO `jiashidai_quanxian_user` VALUES ('3', 'hzw2312f', 'e3217fe0af348633f645bbd6aa8135e1', null, null, null, 'hzw23d12@sina.com', null, null, '2017-01-18 15:25:08', null, 'hhsykx');
INSERT INTO `jiashidai_quanxian_user` VALUES ('4', 'hhsykx', 'e3217fe0af348633f645bbd6aa8135e1', null, null, null, 'hhs2312@sina.com', null, null, '2017-01-18 15:25:47', null, 'hhsykx');

-- ----------------------------
-- Table structure for jiashidai_quanxian_user_role
-- ----------------------------
DROP TABLE IF EXISTS `jiashidai_quanxian_user_role`;
CREATE TABLE `jiashidai_quanxian_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户编号',
  `role_id` int(20) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  CONSTRAINT `FKb40xxfch70f5qnyfw8yme1n1s` FOREIGN KEY (`user_id`) REFERENCES `jiashidai_quanxian_user` (`user_id`),
  CONSTRAINT `FKhh52n8vd4ny9ff4x9fb8v65qx` FOREIGN KEY (`role_id`) REFERENCES `jiashidai_quanxian_role` (`role_id`),
  CONSTRAINT `fk_sys_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `jiashidai_quanxian_role` (`role_id`),
  CONSTRAINT `fk_sys_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `jiashidai_quanxian_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色映射表';

-- ----------------------------
-- Records of jiashidai_quanxian_user_role
-- ----------------------------
INSERT INTO `jiashidai_quanxian_user_role` VALUES ('3', '1');
INSERT INTO `jiashidai_quanxian_user_role` VALUES ('4', '1');
INSERT INTO `jiashidai_quanxian_user_role` VALUES ('2', '2');
INSERT INTO `jiashidai_quanxian_user_role` VALUES ('4', '2');
