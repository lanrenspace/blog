/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1_pg
 Source Server Type    : PostgreSQL
 Source Server Version : 130003
 Source Host           : localhost:5432
 Source Catalog        : 1024notes
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 130003
 File Encoding         : 65001

 Date: 22/07/2021 13:34:18
*/


-- ----------------------------
-- Sequence structure for labels_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."labels_id_seq";
CREATE SEQUENCE "public"."labels_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for userinfo_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."userinfo_id_seq";
CREATE SEQUENCE "public"."userinfo_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for b_labels
-- ----------------------------
DROP TABLE IF EXISTS "public"."b_labels";
CREATE TABLE "public"."b_labels" (
  "id" int2 NOT NULL,
  "label_name" varchar(50) COLLATE "pg_catalog"."default",
  "label_alias" varchar(50) COLLATE "pg_catalog"."default",
  "label_description" varchar(200) COLLATE "pg_catalog"."default",
  "article_count" int2,
  "popular" int2,
  "tenant_code" varchar(20) COLLATE "pg_catalog"."default",
  "create_by" int2,
  "create_time" timestamp(6),
  "update_by" int2,
  "update_time" timestamp(6),
  "del_flag" int2
)
;

-- ----------------------------
-- Records of b_labels
-- ----------------------------

-- ----------------------------
-- Table structure for invite_code
-- ----------------------------
DROP TABLE IF EXISTS "public"."invite_code";
CREATE TABLE "public"."invite_code" (
  "id" int2 NOT NULL,
  "code" varchar(6) COLLATE "pg_catalog"."default" NOT NULL,
  "reuse" int2,
  "create_by" int2,
  "create_time" timestamp(6),
  "update_by" int2,
  "update_time" timestamp(6),
  "del_flag" int2
)
;

-- ----------------------------
-- Records of invite_code
-- ----------------------------
INSERT INTO "public"."invite_code" VALUES (1, '000000', 0, NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."user_info";
CREATE TABLE "public"."user_info" (
  "id" int2 NOT NULL DEFAULT nextval('labels_id_seq'::regclass),
  "account" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "pwd" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "invite_code" varchar(6) COLLATE "pg_catalog"."default",
  "tenant_code" varchar(20) COLLATE "pg_catalog"."default",
  "create_by" int2,
  "create_time" timestamp(6),
  "update_by" int2,
  "update_time" timestamp(6),
  "del_flag" int2
)
;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO "public"."user_info" VALUES (4, 'admin', '{bcrypt}$2a$10$cD9uRjmMUznZvnIJVLw.ZuB4YJZCVXpivQxwmAoEpXFvhfBljsAgq', '000000', 'L7MBoJ', NULL, '2021-07-22 11:50:19.536', NULL, NULL, 0);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."labels_id_seq"', 5, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."userinfo_id_seq"', 2, false);

-- ----------------------------
-- Primary Key structure for table b_labels
-- ----------------------------
ALTER TABLE "public"."b_labels" ADD CONSTRAINT "b_labels_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table invite_code
-- ----------------------------
ALTER TABLE "public"."invite_code" ADD CONSTRAINT "invite_code_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table user_info
-- ----------------------------
ALTER TABLE "public"."user_info" ADD CONSTRAINT "user_info_pkey" PRIMARY KEY ("id");
