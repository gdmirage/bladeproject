/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : blade_system

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 04/01/2020 14:17:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `creator` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `modifier` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '修改人',
  `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除。0-未删除。1-已删除',
  `dept_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '\"' COMMENT '部门名称',
  `pid` bigint(20) NOT NULL COMMENT '上级部门ID',
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'enabled' COMMENT '状态(1、enabled 2、disabled',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (1, '', '2019-12-20 16:19:42.544', '', '2019-12-20 16:19:42.544', 0, 'eladmin', 0, 'enabled');
INSERT INTO `dept` VALUES (2, '', '2019-12-20 16:19:42.544', '', '2019-12-20 16:47:43.803', 0, '研发部', 5, 'enabled');
INSERT INTO `dept` VALUES (3, '', '2019-12-20 16:19:42.544', '', '2019-12-20 16:47:51.074', 0, '运维部', 5, 'enabled');
INSERT INTO `dept` VALUES (4, '', '2019-12-20 16:19:42.544', '', '2019-12-20 16:47:59.041', 0, '测试部', 6, 'enabled');
INSERT INTO `dept` VALUES (5, '', '2019-12-20 16:19:42.544', '', '2019-12-20 16:19:42.544', 0, '华南分部', 1, 'enabled');
INSERT INTO `dept` VALUES (6, '', '2019-12-20 16:19:42.544', '', '2019-12-20 16:19:42.544', 0, '华北分部', 1, 'enabled');
INSERT INTO `dept` VALUES (7, '', '2019-12-20 16:19:42.544', '', '2019-12-20 16:49:46.256', 0, '人事部', 6, 'enabled');

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `creator` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `modifier` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '修改人',
  `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除。0-未删除。1-已删除',
  `dict_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典名称',
  `remark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '\"' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES (2, '', '2019-12-20 14:27:12.260', '', '2019-12-20 14:27:18.270', 0, 'test2', '测2试');

-- ----------------------------
-- Table structure for dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `dict_detail`;
CREATE TABLE `dict_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `creator` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `modifier` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '修改人',
  `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除。0-未删除。1-已删除',
  `dict_id` bigint(20) NOT NULL COMMENT '字典ID',
  `label` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典标签',
  `value` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典值',
  `sort` tinyint(4) NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '字典详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_detail
-- ----------------------------
INSERT INTO `dict_detail` VALUES (1, '', '2019-12-20 15:55:28.870', '', '2019-12-20 15:56:10.682', 0, 1, '是', 'true', 1);

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `creator` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `modifier` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '修改人',
  `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除。0-未删除。1-已删除',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  `job_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '岗位名称',
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'enabled' COMMENT '状态(1、enabled 2、disabled',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '岗位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES (1, '', '2019-12-20 16:16:32.759', '', '2019-12-20 16:16:32.759', 0, 1, '董事长秘书', 'enabled', 0);
INSERT INTO `job` VALUES (2, '', '2019-12-20 16:16:32.759', '', '2019-12-20 16:48:26.813', 0, 5, '人事专员', 'enabled', 0);
INSERT INTO `job` VALUES (3, '', '2019-12-20 16:16:32.759', '', '2019-12-20 16:16:32.759', 0, 2, '产品经理', 'enabled', 0);
INSERT INTO `job` VALUES (4, '', '2019-12-20 16:16:32.759', '', '2019-12-20 16:16:32.759', 0, 2, '全栈开发', 'enabled', 0);
INSERT INTO `job` VALUES (5, '', '2019-12-20 16:16:32.759', '', '2019-12-20 16:16:32.759', 0, 2, '软件测试', 'enabled', 0);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `creator` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `modifier` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '修改人',
  `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除。0-未删除。1-已删除',
  `menu_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '菜单名称',
  `component` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '\"' COMMENT '组件',
  `path` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '\"' COMMENT '链接地址',
  `icon` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图标',
  `pid` bigint(20) NULL DEFAULT 0 COMMENT '上级菜单ID',
  `hidden` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'no' COMMENT '是否隐藏',
  `permission_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '\"' COMMENT '权限代码',
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '系统管理', NULL, 'system', 'system', 0, 'yes', NULL, '0');
INSERT INTO `menu` VALUES (2, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '用户管理', 'system/user/index', 'user', 'peoples', 1, 'yes', 'user:list', '1');
INSERT INTO `menu` VALUES (3, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '角色管理', 'system/role/index', 'role', 'role', 1, 'yes', 'roles:list', '1');
INSERT INTO `menu` VALUES (5, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '菜单管理', 'system/menu/index', 'menu', 'menu', 1, 'yes', 'menu:list', '1');
INSERT INTO `menu` VALUES (6, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '系统监控', NULL, 'monitor', 'monitor', 0, 'yes', NULL, '0');
INSERT INTO `menu` VALUES (7, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '操作日志', 'monitor/log/index', 'logs', 'log', 6, 'yes', NULL, '1');
INSERT INTO `menu` VALUES (8, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '系统缓存', 'monitor/redis/index', 'redis', 'redis', 6, 'yes', 'redis:list', '1');
INSERT INTO `menu` VALUES (9, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, 'SQL监控', 'monitor/sql/index', 'druid', 'sqlMonitor', 6, 'yes', NULL, '1');
INSERT INTO `menu` VALUES (10, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '组件管理', NULL, 'components', 'zujian', 0, 'yes', NULL, '0');
INSERT INTO `menu` VALUES (11, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '图标库', 'components/IconSelect', 'icon', 'icon', 10, 'yes', NULL, '1');
INSERT INTO `menu` VALUES (14, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '邮件工具', 'tools/email/index', 'email', 'email', 36, 'yes', NULL, '1');
INSERT INTO `menu` VALUES (15, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '富文本', 'components/Editor', 'tinymce', 'fwb', 10, 'yes', NULL, '1');
INSERT INTO `menu` VALUES (16, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '图床管理', 'tools/picture/index', 'pictures', 'image', 36, 'yes', 'pictures:list', '1');
INSERT INTO `menu` VALUES (18, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '存储管理', 'tools/storage/index', 'storage', 'qiniu', 36, 'yes', 'storage:list', '1');
INSERT INTO `menu` VALUES (19, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '支付宝工具', 'tools/aliPay/index', 'aliPay', 'alipay', 36, 'yes', NULL, '1');
INSERT INTO `menu` VALUES (21, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '多级菜单', '', 'nested', 'menu', 0, 'no', NULL, '0');
INSERT INTO `menu` VALUES (22, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '二级菜单1', 'nested/menu1/index', 'menu1', 'menu', 21, 'yes', NULL, '1');
INSERT INTO `menu` VALUES (23, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '二级菜单2', 'nested/menu2/index', 'menu2', 'menu', 21, 'yes', NULL, '1');
INSERT INTO `menu` VALUES (24, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '三级菜单1', 'nested/menu1/menu1-1', 'menu1-1', 'menu', 22, 'yes', NULL, '1');
INSERT INTO `menu` VALUES (27, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '三级菜单2', 'nested/menu1/menu1-2', 'menu1-2', 'menu', 22, 'yes', NULL, '1');
INSERT INTO `menu` VALUES (28, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '定时任务', 'system/timing/index', 'timing', 'timing', 36, 'yes', 'timing:list', '1');
INSERT INTO `menu` VALUES (30, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '代码生成', 'generator/index', 'generator', 'dev', 36, 'yes', NULL, '1');
INSERT INTO `menu` VALUES (32, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '异常日志', 'monitor/log/errorLog', 'errorLog', 'error', 6, 'yes', NULL, '1');
INSERT INTO `menu` VALUES (33, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, 'Markdown', 'components/MarkDown', 'markdown', 'markdown', 10, 'yes', NULL, '1');
INSERT INTO `menu` VALUES (34, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, 'Yaml编辑器', 'components/YamlEdit', 'yaml', 'dev', 10, 'yes', NULL, '1');
INSERT INTO `menu` VALUES (35, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '部门管理', 'system/dept/index', 'dept', 'dept', 1, 'yes', 'dept:list', '1');
INSERT INTO `menu` VALUES (36, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '系统工具', '', 'sys-tools', 'sys-tools', 0, 'yes', NULL, '0');
INSERT INTO `menu` VALUES (37, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '岗位管理', 'system/job/index', 'job', 'Steve-Jobs', 1, 'yes', 'job:list', '1');
INSERT INTO `menu` VALUES (38, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '接口文档', 'tools/swagger/index', 'swagger2', 'swagger', 36, 'yes', NULL, '1');
INSERT INTO `menu` VALUES (39, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '字典管理', 'system/dict/index', 'dict', 'dictionary', 1, 'yes', 'dict:list', '1');
INSERT INTO `menu` VALUES (41, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '在线用户', 'monitor/online/index', 'online', 'Steve-Jobs', 6, 'yes', NULL, '1');
INSERT INTO `menu` VALUES (44, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '用户新增', '', '', '', 2, 'yes', 'user:add', '2');
INSERT INTO `menu` VALUES (45, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '用户编辑', '', '', '', 2, 'yes', 'user:edit', '2');
INSERT INTO `menu` VALUES (46, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '用户删除', '', '', '', 2, 'yes', 'user:del', '2');
INSERT INTO `menu` VALUES (48, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '角色创建', '', '', '', 3, 'yes', 'roles:add', '2');
INSERT INTO `menu` VALUES (49, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '角色修改', '', '', '', 3, 'yes', 'roles:edit', '2');
INSERT INTO `menu` VALUES (50, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '角色删除', '', '', '', 3, 'yes', 'roles:del', '2');
INSERT INTO `menu` VALUES (52, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '菜单新增', '', '', '', 5, 'yes', 'menu:add', '2');
INSERT INTO `menu` VALUES (53, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '菜单编辑', '', '', '', 5, 'yes', 'menu:edit', '2');
INSERT INTO `menu` VALUES (54, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '菜单删除', '', '', '', 5, 'yes', 'menu:del', '2');
INSERT INTO `menu` VALUES (56, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '部门新增', '', '', '', 35, 'yes', 'dept:add', '2');
INSERT INTO `menu` VALUES (57, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '部门编辑', '', '', '', 35, 'yes', 'dept:edit', '2');
INSERT INTO `menu` VALUES (58, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '部门删除', '', '', '', 35, 'yes', 'dept:del', '2');
INSERT INTO `menu` VALUES (60, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '岗位新增', '', '', '', 37, 'yes', 'job:add', '2');
INSERT INTO `menu` VALUES (61, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '岗位编辑', '', '', '', 37, 'yes', 'job:edit', '2');
INSERT INTO `menu` VALUES (62, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '岗位删除', '', '', '', 37, 'yes', 'job:del', '2');
INSERT INTO `menu` VALUES (64, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '字典新增', '', '', '', 39, 'yes', 'dict:add', '2');
INSERT INTO `menu` VALUES (65, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '字典编辑', '', '', '', 39, 'yes', 'dict:edit', '2');
INSERT INTO `menu` VALUES (66, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '字典删除', '', '', '', 39, 'yes', 'dict:del', '2');
INSERT INTO `menu` VALUES (68, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '缓存删除', '', '', '', 8, 'yes', 'redis:del', '2');
INSERT INTO `menu` VALUES (70, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '图片上传', '', '', '', 16, 'yes', 'pictures:add', '2');
INSERT INTO `menu` VALUES (71, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '图片删除', '', '', '', 16, 'yes', 'pictures:del', '2');
INSERT INTO `menu` VALUES (73, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '任务新增', '', '', '', 28, 'yes', 'timing:add', '2');
INSERT INTO `menu` VALUES (74, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '任务编辑', '', '', '', 28, 'yes', 'timing:edit', '2');
INSERT INTO `menu` VALUES (75, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '任务删除', '', '', '', 28, 'yes', 'timing:del', '2');
INSERT INTO `menu` VALUES (77, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '上传文件', '', '', '', 18, 'yes', 'storage:add', '2');
INSERT INTO `menu` VALUES (78, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '文件编辑', '', '', '', 18, 'yes', 'storage:edit', '2');
INSERT INTO `menu` VALUES (79, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '文件删除', '', '', '', 18, 'yes', 'storage:del', '2');
INSERT INTO `menu` VALUES (81, '', '2019-12-20 16:54:54.015', '', '2019-12-20 16:54:54.015', 0, '外链', '', 'http://www.baidu.com', 'develop', 0, 'yes', '', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `creator` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `modifier` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '修改人',
  `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除。0-未删除。1-已删除',
  `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '角色名称',
  `level` int(11) NULL DEFAULT NULL COMMENT '级别',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '备注',
  `data_scope` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '数据范围',
  `permission` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '', '2019-12-20 16:22:20.106', '', '2019-12-20 16:22:20.106', 0, '超级管理员', 1, '-', '全部', 'admin');
INSERT INTO `role` VALUES (2, '', '2019-12-20 16:22:20.106', '', '2019-12-20 16:22:20.106', 0, '普通用户', 2, '-', '自定义', 'common');
INSERT INTO `role` VALUES (3, '', '2019-12-20 16:22:20.106', '', '2019-12-20 16:22:20.106', 0, '测试角色', 3, 'this is test', '自定义', 'test');

-- ----------------------------
-- Table structure for role_depts
-- ----------------------------
DROP TABLE IF EXISTS `role_depts`;
CREATE TABLE `role_depts`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色 -- 部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_depts
-- ----------------------------
INSERT INTO `role_depts` VALUES (1, 2, 2);
INSERT INTO `role_depts` VALUES (2, 2, 3);
INSERT INTO `role_depts` VALUES (3, 3, 3);
INSERT INTO `role_depts` VALUES (4, 3, 4);

-- ----------------------------
-- Table structure for role_menus
-- ----------------------------
DROP TABLE IF EXISTS `role_menus`;
CREATE TABLE `role_menus`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 117 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色 -- 菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menus
-- ----------------------------
INSERT INTO `role_menus` VALUES (1, 1, 1);
INSERT INTO `role_menus` VALUES (2, 1, 2);
INSERT INTO `role_menus` VALUES (3, 1, 3);
INSERT INTO `role_menus` VALUES (4, 1, 5);
INSERT INTO `role_menus` VALUES (5, 1, 6);
INSERT INTO `role_menus` VALUES (6, 1, 7);
INSERT INTO `role_menus` VALUES (7, 1, 8);
INSERT INTO `role_menus` VALUES (8, 1, 9);
INSERT INTO `role_menus` VALUES (9, 1, 10);
INSERT INTO `role_menus` VALUES (10, 1, 11);
INSERT INTO `role_menus` VALUES (11, 1, 14);
INSERT INTO `role_menus` VALUES (12, 1, 15);
INSERT INTO `role_menus` VALUES (13, 1, 16);
INSERT INTO `role_menus` VALUES (14, 1, 18);
INSERT INTO `role_menus` VALUES (15, 1, 19);
INSERT INTO `role_menus` VALUES (16, 1, 21);
INSERT INTO `role_menus` VALUES (17, 1, 22);
INSERT INTO `role_menus` VALUES (18, 1, 23);
INSERT INTO `role_menus` VALUES (19, 1, 24);
INSERT INTO `role_menus` VALUES (20, 1, 27);
INSERT INTO `role_menus` VALUES (21, 1, 28);
INSERT INTO `role_menus` VALUES (22, 1, 30);
INSERT INTO `role_menus` VALUES (23, 1, 32);
INSERT INTO `role_menus` VALUES (24, 1, 33);
INSERT INTO `role_menus` VALUES (25, 1, 34);
INSERT INTO `role_menus` VALUES (26, 1, 35);
INSERT INTO `role_menus` VALUES (27, 1, 36);
INSERT INTO `role_menus` VALUES (28, 1, 37);
INSERT INTO `role_menus` VALUES (29, 1, 38);
INSERT INTO `role_menus` VALUES (30, 1, 39);
INSERT INTO `role_menus` VALUES (31, 1, 41);
INSERT INTO `role_menus` VALUES (32, 1, 44);
INSERT INTO `role_menus` VALUES (33, 1, 45);
INSERT INTO `role_menus` VALUES (34, 1, 46);
INSERT INTO `role_menus` VALUES (35, 1, 48);
INSERT INTO `role_menus` VALUES (36, 1, 49);
INSERT INTO `role_menus` VALUES (37, 1, 50);
INSERT INTO `role_menus` VALUES (38, 1, 52);
INSERT INTO `role_menus` VALUES (39, 1, 53);
INSERT INTO `role_menus` VALUES (40, 1, 54);
INSERT INTO `role_menus` VALUES (41, 1, 56);
INSERT INTO `role_menus` VALUES (42, 1, 57);
INSERT INTO `role_menus` VALUES (43, 1, 58);
INSERT INTO `role_menus` VALUES (44, 1, 60);
INSERT INTO `role_menus` VALUES (45, 1, 61);
INSERT INTO `role_menus` VALUES (46, 1, 62);
INSERT INTO `role_menus` VALUES (47, 1, 64);
INSERT INTO `role_menus` VALUES (48, 1, 65);
INSERT INTO `role_menus` VALUES (49, 1, 66);
INSERT INTO `role_menus` VALUES (50, 1, 68);
INSERT INTO `role_menus` VALUES (51, 1, 70);
INSERT INTO `role_menus` VALUES (52, 1, 71);
INSERT INTO `role_menus` VALUES (53, 1, 73);
INSERT INTO `role_menus` VALUES (54, 1, 74);
INSERT INTO `role_menus` VALUES (55, 1, 75);
INSERT INTO `role_menus` VALUES (56, 1, 77);
INSERT INTO `role_menus` VALUES (57, 1, 78);
INSERT INTO `role_menus` VALUES (58, 1, 79);
INSERT INTO `role_menus` VALUES (59, 1, 81);
INSERT INTO `role_menus` VALUES (60, 2, 1);
INSERT INTO `role_menus` VALUES (61, 2, 2);
INSERT INTO `role_menus` VALUES (62, 2, 3);
INSERT INTO `role_menus` VALUES (63, 2, 5);
INSERT INTO `role_menus` VALUES (64, 2, 6);
INSERT INTO `role_menus` VALUES (65, 2, 8);
INSERT INTO `role_menus` VALUES (66, 2, 9);
INSERT INTO `role_menus` VALUES (67, 2, 10);
INSERT INTO `role_menus` VALUES (68, 2, 11);
INSERT INTO `role_menus` VALUES (69, 2, 14);
INSERT INTO `role_menus` VALUES (70, 2, 15);
INSERT INTO `role_menus` VALUES (71, 2, 16);
INSERT INTO `role_menus` VALUES (72, 2, 18);
INSERT INTO `role_menus` VALUES (73, 2, 19);
INSERT INTO `role_menus` VALUES (74, 2, 21);
INSERT INTO `role_menus` VALUES (75, 2, 23);
INSERT INTO `role_menus` VALUES (76, 2, 24);
INSERT INTO `role_menus` VALUES (77, 2, 27);
INSERT INTO `role_menus` VALUES (78, 2, 28);
INSERT INTO `role_menus` VALUES (79, 2, 30);
INSERT INTO `role_menus` VALUES (80, 2, 33);
INSERT INTO `role_menus` VALUES (81, 2, 34);
INSERT INTO `role_menus` VALUES (82, 2, 35);
INSERT INTO `role_menus` VALUES (83, 2, 36);
INSERT INTO `role_menus` VALUES (84, 2, 37);
INSERT INTO `role_menus` VALUES (85, 2, 38);
INSERT INTO `role_menus` VALUES (86, 2, 39);
INSERT INTO `role_menus` VALUES (87, 2, 70);
INSERT INTO `role_menus` VALUES (88, 2, 71);
INSERT INTO `role_menus` VALUES (89, 2, 77);
INSERT INTO `role_menus` VALUES (90, 2, 78);
INSERT INTO `role_menus` VALUES (91, 2, 79);
INSERT INTO `role_menus` VALUES (92, 6, 1);
INSERT INTO `role_menus` VALUES (93, 6, 2);
INSERT INTO `role_menus` VALUES (94, 6, 3);
INSERT INTO `role_menus` VALUES (95, 6, 5);
INSERT INTO `role_menus` VALUES (96, 6, 35);
INSERT INTO `role_menus` VALUES (97, 6, 37);
INSERT INTO `role_menus` VALUES (98, 6, 39);
INSERT INTO `role_menus` VALUES (99, 6, 44);
INSERT INTO `role_menus` VALUES (100, 6, 45);
INSERT INTO `role_menus` VALUES (101, 6, 46);
INSERT INTO `role_menus` VALUES (102, 6, 48);
INSERT INTO `role_menus` VALUES (103, 6, 49);
INSERT INTO `role_menus` VALUES (104, 6, 50);
INSERT INTO `role_menus` VALUES (105, 6, 52);
INSERT INTO `role_menus` VALUES (106, 6, 53);
INSERT INTO `role_menus` VALUES (107, 6, 54);
INSERT INTO `role_menus` VALUES (108, 6, 56);
INSERT INTO `role_menus` VALUES (109, 6, 57);
INSERT INTO `role_menus` VALUES (110, 6, 58);
INSERT INTO `role_menus` VALUES (111, 6, 60);
INSERT INTO `role_menus` VALUES (112, 6, 61);
INSERT INTO `role_menus` VALUES (113, 6, 62);
INSERT INTO `role_menus` VALUES (114, 6, 64);
INSERT INTO `role_menus` VALUES (115, 6, 65);
INSERT INTO `role_menus` VALUES (116, 6, 66);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `creator` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `modifier` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '修改人',
  `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除。0-未删除。1-已删除',
  `job_id` int(11) NULL DEFAULT NULL COMMENT '岗位ID',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '登陆名',
  `user_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户名',
  `avatar` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '头像',
  `real_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '邮箱',
  `mobile_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '移动号码',
  `tel_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '固话',
  `password` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'enabled' COMMENT '状态(1、enabled 2、disabled',
  `last_password_reset_time` datetime(3) NULL DEFAULT NULL COMMENT '最后修改密码日期',
  `last_login_time` datetime(3) NULL DEFAULT NULL COMMENT '上一次登陆时间',
  `last_logout_time` datetime(3) NULL DEFAULT NULL COMMENT '上一次退出时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '', '2019-12-20 16:42:41.532', '', '2020-01-04 09:25:34.737', 0, 11, 2, 'admin', '', '', '', 'admin@eladmin.net', '18888888888', '', 'e10adc3949ba59abbe56e057f20f883e', 'enabled', NULL, NULL, NULL);
INSERT INTO `user` VALUES (2, '', '2019-12-20 16:42:41.532', '', '2020-01-04 09:25:33.552', 0, 12, 2, 'test', '', '', '', 'test@eladmin.net', '17777777777', '', 'e10adc3949ba59abbe56e057f20f883e', 'enabled', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户 -- 角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES (1, 1, 1);
INSERT INTO `user_roles` VALUES (2, 2, 2);
INSERT INTO `user_roles` VALUES (3, 2, 6);

SET FOREIGN_KEY_CHECKS = 1;
