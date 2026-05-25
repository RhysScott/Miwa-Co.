-- Miwa & Co. Database Schema
-- Run this against your MySQL 8.x database

CREATE DATABASE IF NOT EXISTS miwa DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE miwa;

-- 新闻
CREATE TABLE IF NOT EXISTS news (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    date        VARCHAR(10)  NOT NULL COMMENT '日期（2026.05）',
    title       VARCHAR(200) NOT NULL,
    image       VARCHAR(500) COMMENT 'OSS URL',
    excerpt     VARCHAR(500) COMMENT '摘要',
    content     TEXT         COMMENT 'Markdown 正文',
    sort_order  INT DEFAULT 0,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_news_sort (sort_order),
    INDEX idx_news_created (created_at)
);

-- 项目
CREATE TABLE IF NOT EXISTS projects (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    zh          VARCHAR(100) NOT NULL,
    en          VARCHAR(200) NOT NULL,
    desc_cn     VARCHAR(500) COMMENT '中文描述',
    image       VARCHAR(500) COMMENT 'OSS URL',
    content     TEXT         COMMENT 'Markdown 正文',
    sort_order  INT DEFAULT 0,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_projects_sort (sort_order)
);

-- 成员
CREATE TABLE IF NOT EXISTS people (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50)  NOT NULL,
    en          VARCHAR(100) NOT NULL,
    role_cn     VARCHAR(100) COMMENT '中文职位',
    role_en     VARCHAR(100) COMMENT '英文职位',
    bio         VARCHAR(500) COMMENT '简短介绍',
    image       VARCHAR(500) COMMENT 'OSS URL（头像）',
    content     TEXT         COMMENT 'Markdown 正文',
    sort_order  INT DEFAULT 0,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_people_sort (sort_order)
);

-- 服务能力
CREATE TABLE IF NOT EXISTS services (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    zh          VARCHAR(100) NOT NULL,
    en          VARCHAR(200) NOT NULL,
    desc_cn     VARCHAR(500),
    detail      TEXT,
    icon        VARCHAR(50)  COMMENT '图标标识',
    image       VARCHAR(500) COMMENT 'OSS URL',
    sort_order  INT DEFAULT 0,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_services_sort (sort_order)
);

-- 精选案例
CREATE TABLE IF NOT EXISTS cases (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    zh          VARCHAR(200) NOT NULL,
    detail      TEXT,
    sort_order  INT DEFAULT 0,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_cases_sort (sort_order)
);

-- 价值观
CREATE TABLE IF NOT EXISTS company_values (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    zh          VARCHAR(100) NOT NULL,
    en          VARCHAR(200) NOT NULL,
    desc_cn     TEXT,
    sort_order  INT DEFAULT 0,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_values_sort (sort_order)
);

-- 首页配置（单行 JSON）
CREATE TABLE IF NOT EXISTS home_config (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    config      JSON NOT NULL COMMENT '首页完整 JSON 配置',
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 关于页配置（单行 JSON）
CREATE TABLE IF NOT EXISTS about_config (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    zh_text     JSON COMMENT '中文段落数组',
    en_text     JSON COMMENT '英文段落数组',
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 页脚配置（单行）
CREATE TABLE IF NOT EXISTS footer_config (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    email       VARCHAR(100),
    phone       VARCHAR(30),
    address     VARCHAR(300),
    en_email    VARCHAR(100),
    en_phone    VARCHAR(30),
    en_address  VARCHAR(300),
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
