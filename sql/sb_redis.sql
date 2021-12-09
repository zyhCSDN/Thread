/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : sb_redis

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-11-07 11:38:19
*/

SET FOREIGN_KEY_CHECKS=0;


-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '字典类型',
  `name` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '字典名称',
  `code` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '选项编码',
  `value` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '选项取值',
  `order_by` int(11) DEFAULT '1' COMMENT '排序',
  `is_active` tinyint(4) DEFAULT '1' COMMENT '是否有效(1=是;0=否)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_type_code` (`type`,`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='字典配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'ReviewStatus', '审核状态', 'Passed', '通过', '1', '1', '2019-10-25 22:03:02');
INSERT INTO `sys_config` VALUES ('2', 'ReviewStatus', '审核状态', 'NotPassed', '不通过', '2', '1', '2019-10-25 22:03:02');
INSERT INTO `sys_config` VALUES ('3', 'Sex', '性别', 'Female', '女性', '1', '1', '2019-10-25 22:03:02');
INSERT INTO `sys_config` VALUES ('4', 'Sex', '性别', 'Male', '男性', '2', '1', '2019-10-25 22:03:02');
INSERT INTO `sys_config` VALUES ('5', 'Color', '颜色', 'Red', '红色', '1', '1', '2019-10-25 22:54:02');
INSERT INTO `sys_config` VALUES ('6', 'Color', '颜色', 'Black', '黑色', '2', '1', '2019-10-25 22:54:38');
INSERT INTO `sys_config` VALUES ('7', 'Color', '颜色', 'White', '白色', '3', '1', '2019-10-25 22:54:58');
INSERT INTO `sys_config` VALUES ('8', 'Color', '颜色', 'Pink', '粉色', '4', '1', '2019-10-25 22:55:09');
INSERT INTO `sys_config` VALUES ('9', 'Color', '颜色', 'brond', '棕色', '5', '1', '2019-10-31 21:34:13');
INSERT INTO `sys_config` VALUES ('10', 'HouseType', '户型', 'ThreeTwo', '三房两厅', '1', '1', '2019-10-31 21:42:28');
INSERT INTO `sys_config` VALUES ('11', 'HouseType', '户型', 'TwoOne', '两房一厅', '2', '1', '2019-10-31 21:43:05');

