# Miwa & Co. API 文档

## 概述

- **Base URL**: `https://api.miwa-co.com/api`
- **字符编码**: UTF-8
- **请求格式**: JSON (`Content-Type: application/json`)
- **响应格式**: JSON
- **认证方式**: Bearer Token（管理端接口）

---

## 通用响应结构

### 成功响应

```json
{
  "code": 200,
  "message": "success",
  "data": { }
}
```

### 分页响应

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "records": [ ],
    "total": 100,
    "page": 1,
    "pageSize": 10
  }
}
```

### 错误响应

```json
{
  "code": 400,
  "message": "参数校验失败",
  "errors": [
    { "field": "zh", "message": "中文名称不能为空" }
  ]
}
```

### HTTP 状态码

| 状态码 | 含义 |
|--------|------|
| 200 | 成功 |
| 201 | 创建成功 |
| 400 | 参数错误 |
| 401 | 未认证 |
| 403 | 无权限 |
| 404 | 资源不存在 |
| 500 | 服务器错误 |

---

## 一、公共接口（无需认证）

### 1.1 首页数据

**GET** `/home`

**响应**:
```json
{
  "code": 200,
  "data": {
    "hero": {
      "zh": "让技术回归简单",
      "en": "Technology, Made Simple",
      "sub": "AI 应用 · 软件工程 · 物联网 — 从概念到落地，全程可信赖"
    },
    "marquee": [
      { "zh": "人工智能", "en": "AI Solutions" },
      { "zh": "软件开发", "en": "Software Dev" }
    ],
    "services": [
      {
        "id": 1,
        "zh": "人工智能",
        "en": "AI Solutions",
        "desc": "大模型应用、智能体开发、NLP 与计算机视觉",
        "detail": "从模型选型到上线调优...",
        "icon": "cpu",
        "image": "https://oss.miwa-co.com/services/ai.png",
        "sortOrder": 1
      }
    ],
    "stats": [
      { "num": "5000万+", "label": "日均 API 调用" },
      { "num": "200+", "label": "服务企业" }
    ],
    "projects": [
      {
        "id": 1,
        "zh": "智能客服平台",
        "en": "AI Customer Service",
        "desc": "基于自研 NLP 引擎，日均处理 5000 万次对话",
        "image": "https://oss.miwa-co.com/projects/cs.png",
        "sortOrder": 1
      }
    ],
    "process": [
      { "zh": "需求分析", "en": "Discovery", "desc": "深入理解业务场景与技术约束" }
    ],
    "clients": [
      { "name": "蔚来", "en": "NIO", "logo": "https://oss.miwa-co.com/clients/nio.png" }
    ],
    "philosophy": {
      "zh": "好的技术应该像水电一样随处可得",
      "sub": "不追逐模型参数竞赛，专注将 AI 真正落地到产业场景",
      "en": "Good technology, like water and electricity, should be accessible everywhere."
    },
    "about": {
      "zh": "Miwa & Co. 是一家技术驱动的创新公司...",
      "en": "A tech-driven company born in Shanghai..."
    },
    "footer": {
      "email": "hello@miwa-co.com",
      "phone": "+86 21 8888 6666",
      "address": "上海市浦东新区张江高科技园区",
      "en": {
        "email": "hello@miwa-co.com",
        "phone": "+86 21 8888 6666",
        "address": "Zhangjiang Hi-Tech Park, Pudong, Shanghai"
      }
    }
  }
}
```

---

### 1.2 新闻列表

**GET** `/news`

**查询参数**:

| 参数 | 类型 | 必填 | 默认值 | 说明 |
|------|------|------|--------|------|
| page | int | 否 | 1 | 页码 |
| pageSize | int | 否 | 10 | 每页条数（最大 50） |
| sort | string | 否 | `sortOrder,asc` | 排序字段与方向 |

**响应**:
```json
{
  "code": 200,
  "data": {
    "records": [
      {
        "id": 1,
        "date": "2026.05",
        "title": "Miwa AI Agent 框架获信通院认证",
        "image": "https://oss.miwa-co.com/news/1.jpg",
        "excerpt": "成为首批获得该认证的民营企业之一...",
        "content": "## 核心能力\n\n完整的 Markdown 正文...",
        "sortOrder": 1,
        "createdAt": "2026-05-01T10:00:00Z",
        "updatedAt": "2026-05-20T14:30:00Z"
      }
    ],
    "total": 8,
    "page": 1,
    "pageSize": 10
  }
}
```

---

### 1.3 新闻详情

**GET** `/news/{id}`

**路径参数**:

| 参数 | 类型 | 说明 |
|------|------|------|
| id | long | 新闻 ID |

**响应**:
```json
{
  "code": 200,
  "data": {
    "id": 1,
    "date": "2026.05",
    "title": "Miwa AI Agent 框架获信通院认证",
    "image": "https://oss.miwa-co.com/news/1.jpg",
    "excerpt": "成为首批获得该认证的民营企业之一...",
    "content": "## 核心能力\n\n完整的 Markdown 正文...",
    "sortOrder": 1,
    "createdAt": "2026-05-01T10:00:00Z",
    "updatedAt": "2026-05-20T14:30:00Z"
  }
}
```

---

### 1.4 项目列表

**GET** `/projects`

**查询参数**:

| 参数 | 类型 | 必填 | 默认值 | 说明 |
|------|------|------|--------|------|
| sort | string | 否 | `sortOrder,asc` | 排序字段与方向（可选 `sortOrder` / `createdAt`） |

> 项目不分页，直接返回全部（数量有限）

**响应**:
```json
{
  "code": 200,
  "data": [
    {
      "id": 1,
      "zh": "智能客服平台",
      "en": "AI Customer Service",
      "desc": "基于自研 NLP 引擎，日均处理 5000 万次对话",
      "image": "https://oss.miwa-co.com/projects/1.jpg",
      "content": "## 项目背景\n\nMarkdown 正文...",
      "sortOrder": 1,
      "createdAt": "2026-01-15T08:00:00Z",
      "updatedAt": "2026-05-10T16:00:00Z"
    }
  ]
}
```

---

### 1.5 项目详情

**GET** `/projects/{id}`

**路径参数**:

| 参数 | 类型 | 说明 |
|------|------|------|
| id | long | 项目 ID |

**响应**: 单个项目对象，结构同列表项。

---

### 1.6 团队成员列表

**GET** `/people`

**查询参数**:

| 参数 | 类型 | 必填 | 默认值 | 说明 |
|------|------|------|--------|------|
| sort | string | 否 | `sortOrder,asc` | 排序字段与方向 |

> 成员不分页，直接返回全部

**响应**:
```json
{
  "code": 200,
  "data": [
    {
      "id": 1,
      "name": "陈思远",
      "en": "Siyuan Chen",
      "role": "创始人 / CEO",
      "roleEn": "Founder & CEO",
      "bio": "前 Google Brain 研究员，CMU 计算机博士...",
      "image": "https://oss.miwa-co.com/people/1.jpg",
      "content": "## 经历\n\nMarkdown 正文...",
      "sortOrder": 1,
      "createdAt": "2026-01-01T00:00:00Z",
      "updatedAt": "2026-01-01T00:00:00Z"
    }
  ]
}
```

---

### 1.7 成员详情

**GET** `/people/{id}`

**路径参数**:

| 参数 | 类型 | 说明 |
|------|------|------|
| id | long | 成员 ID |

**响应**: 单个成员对象，结构同列表项。

---

### 1.8 探索页数据

**GET** `/explore`

> 返回服务能力列表 + 精选案例列表

**响应**:
```json
{
  "code": 200,
  "data": {
    "services": [
      {
        "id": 1,
        "zh": "人工智能",
        "en": "AI Solutions",
        "desc": "大模型应用、智能体开发、NLP 与计算机视觉",
        "detail": "从模型选型到上线调优...",
        "icon": "cpu",
        "image": "https://oss.miwa-co.com/services/ai.png",
        "sortOrder": 1
      }
    ],
    "cases": [
      {
        "id": 1,
        "zh": "某头部新能源车企 — 智能产线 AI 质检",
        "detail": "基于计算机视觉的实时缺陷检测系统...",
        "sortOrder": 1
      }
    ]
  }
}
```

---

### 1.9 关于页数据

**GET** `/about`

**响应**:
```json
{
  "code": 200,
  "data": {
    "zh": [
      "Miwa & Co. 是一家技术驱动的创新公司，成立于上海。",
      "我们专注 AI 应用、软件工程与物联网解决方案..."
    ],
    "en": [
      "A tech-driven company born in Shanghai...",
      "software engineering, and IoT solutions..."
    ],
    "values": [
      {
        "id": 1,
        "zh": "技术透明",
        "en": "Radical Transparency",
        "desc": "代码开源、架构文档化、决策可追溯...",
        "sortOrder": 1
      }
    ]
  }
}
```

---

## 二、管理接口（需认证）

> 所有管理接口需在 Header 中携带 `Authorization: Bearer <token>`

### 2.1 文件上传（OSS）

**POST** `/admin/upload`

**请求**: `multipart/form-data`

| 字段 | 类型 | 必填 | 说明 |
|------|------|------|------|
| file | File | 是 | 文件（支持 jpg/png/gif/webp/svg/pdf，最大 10MB） |
| dir | string | 否 | 存储目录，默认 `common`（可选 `news` / `projects` / `people` / `services` / `clients`） |

**响应**:
```json
{
  "code": 200,
  "data": {
    "url": "https://oss.miwa-co.com/news/2026/05/abc123.jpg",
    "key": "news/2026/05/abc123.jpg",
    "name": "hero-banner.jpg",
    "size": 204800
  }
}
```

### 2.2 删除文件

**DELETE** `/admin/upload`

**请求体**:
```json
{
  "key": "news/2026/05/abc123.jpg"
}
```

---

### 2.3 新闻管理

#### 创建新闻

**POST** `/admin/news`

**请求体**:
```json
{
  "date": "2026.06",
  "title": "新闻标题",
  "image": "https://oss.miwa-co.com/news/1.jpg",
  "excerpt": "摘要文字",
  "content": "## Markdown 正文",
  "sortOrder": 1
}
```

#### 更新新闻

**PUT** `/admin/news/{id}`

**请求体**: 同创建（所有字段可选，只更新传入的字段）

#### 删除新闻

**DELETE** `/admin/news/{id}`

#### 批量排序

**PUT** `/admin/news/sort`

**请求体**:
```json
{
  "items": [
    { "id": 1, "sortOrder": 3 },
    { "id": 2, "sortOrder": 1 },
    { "id": 3, "sortOrder": 2 }
  ]
}
```

> 排序对所有支持 `sortOrder` 的实体通用：`projects` / `people` / `services` / `cases` / `values` 同理，对应端点路径替换实体名即可。

---

### 2.4 项目管理

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/admin/projects` | 创建项目 |
| PUT | `/admin/projects/{id}` | 更新项目 |
| DELETE | `/admin/projects/{id}` | 删除项目 |
| PUT | `/admin/projects/sort` | 批量排序 |

**请求体** (创建/更新):
```json
{
  "zh": "项目中文名",
  "en": "Project English Name",
  "desc": "简短描述",
  "image": "https://oss.miwa-co.com/projects/1.jpg",
  "content": "## Markdown 正文",
  "sortOrder": 1
}
```

---

### 2.5 成员管理

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/admin/people` | 创建成员 |
| PUT | `/admin/people/{id}` | 更新成员 |
| DELETE | `/admin/people/{id}` | 删除成员 |
| PUT | `/admin/people/sort` | 批量排序 |

**请求体** (创建/更新):
```json
{
  "name": "陈思远",
  "en": "Siyuan Chen",
  "role": "创始人 / CEO",
  "roleEn": "Founder & CEO",
  "bio": "简短介绍",
  "image": "https://oss.miwa-co.com/people/1.jpg",
  "content": "## 经历\n\nMarkdown 正文",
  "sortOrder": 1
}
```

---

### 2.6 服务能力管理

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/admin/services` | 创建服务 |
| PUT | `/admin/services/{id}` | 更新服务 |
| DELETE | `/admin/services/{id}` | 删除服务 |
| PUT | `/admin/services/sort` | 批量排序 |

**请求体**:
```json
{
  "zh": "人工智能",
  "en": "AI Solutions",
  "desc": "简短描述",
  "detail": "详细说明",
  "icon": "cpu",
  "image": "https://oss.miwa-co.com/services/ai.png",
  "sortOrder": 1
}
```

---

### 2.7 精选案例管理

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/admin/cases` | 创建案例 |
| PUT | `/admin/cases/{id}` | 更新案例 |
| DELETE | `/admin/cases/{id}` | 删除案例 |
| PUT | `/admin/cases/sort` | 批量排序 |

**请求体**:
```json
{
  "zh": "某头部新能源车企 — 智能产线 AI 质检",
  "detail": "详细说明",
  "sortOrder": 1
}
```

---

### 2.8 首页配置

**PUT** `/admin/home`

> 整体更新首页数据，部分字段可为空（不展示对应模块）

**请求体**: 与 `GET /home` 返回结构一致，但不包含 `id` 等只读字段。

---

### 2.9 关于页配置

**PUT** `/admin/about`

**请求体**:
```json
{
  "zh": ["段落1", "段落2"],
  "en": ["Paragraph 1", "Paragraph 2"],
  "values": [
    {
      "id": 1,
      "zh": "技术透明",
      "en": "Radical Transparency",
      "desc": "描述文字",
      "sortOrder": 1
    }
  ]
}
```

---

### 2.10 页脚配置

**PUT** `/admin/footer`

**请求体**:
```json
{
  "email": "hello@miwa-co.com",
  "phone": "+86 21 8888 6666",
  "address": "上海市浦东新区张江高科技园区",
  "en": {
    "email": "hello@miwa-co.com",
    "phone": "+86 21 8888 6666",
    "address": "Zhangjiang Hi-Tech Park, Pudong, Shanghai"
  }
}
```

---

## 三、数据库设计概要

### 表结构

```sql
-- 新闻
CREATE TABLE news (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    date        VARCHAR(10)  NOT NULL COMMENT '日期（2026.05）',
    title       VARCHAR(200) NOT NULL,
    image       VARCHAR(500) COMMENT 'OSS URL',
    excerpt     VARCHAR(500) COMMENT '摘要',
    content     TEXT         COMMENT 'Markdown 正文',
    sort_order  INT DEFAULT 0,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 项目
CREATE TABLE projects (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    zh          VARCHAR(100) NOT NULL,
    en          VARCHAR(200) NOT NULL,
    desc_cn     VARCHAR(500) COMMENT '中文描述',
    image       VARCHAR(500) COMMENT 'OSS URL',
    content     TEXT         COMMENT 'Markdown 正文',
    sort_order  INT DEFAULT 0,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 成员
CREATE TABLE people (
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
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 服务能力
CREATE TABLE services (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    zh          VARCHAR(100) NOT NULL,
    en          VARCHAR(200) NOT NULL,
    desc_cn     VARCHAR(500),
    detail      TEXT,
    icon        VARCHAR(50)  COMMENT '图标标识',
    image       VARCHAR(500) COMMENT 'OSS URL',
    sort_order  INT DEFAULT 0,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 精选案例
CREATE TABLE cases (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    zh          VARCHAR(200) NOT NULL,
    detail      TEXT,
    sort_order  INT DEFAULT 0,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 价值观
CREATE TABLE company_values (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    zh          VARCHAR(100) NOT NULL,
    en          VARCHAR(200) NOT NULL,
    desc_cn     TEXT,
    sort_order  INT DEFAULT 0,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 首页配置（单行 JSON）
CREATE TABLE home_config (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    config      JSON NOT NULL COMMENT '首页完整 JSON 配置',
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 关于页配置（单行 JSON）
CREATE TABLE about_config (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    zh_text     JSON COMMENT '中文段落数组',
    en_text     JSON COMMENT '英文段落数组',
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 页脚配置（单行）
CREATE TABLE footer_config (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    email       VARCHAR(100),
    phone       VARCHAR(30),
    address     VARCHAR(300),
    en_email    VARCHAR(100),
    en_phone    VARCHAR(30),
    en_address  VARCHAR(300),
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

### 索引建议

```sql
-- 所有排序字段建索引
CREATE INDEX idx_sort ON news (sort_order);
CREATE INDEX idx_sort ON projects (sort_order);
CREATE INDEX idx_sort ON people (sort_order);
CREATE INDEX idx_sort ON services (sort_order);
CREATE INDEX idx_sort ON cases (sort_order);
CREATE INDEX idx_sort ON company_values (sort_order);

-- 时间索引（列表按时间倒序）
CREATE INDEX idx_created ON news (created_at);
```

---

## 四、SpringBoot 项目结构建议

```
server/
├── pom.xml
├── src/main/java/com/miwa/
│   ├── MiwaApplication.java
│   ├── config/
│   │   ├── OssConfig.java          # OSS 客户端配置
│   │   ├── SecurityConfig.java     # Spring Security 配置
│   │   └── WebMvcConfig.java       # CORS / 拦截器
│   ├── controller/
│   │   ├── public/
│   │   │   ├── HomeController.java
│   │   │   ├── NewsController.java
│   │   │   ├── ProjectController.java
│   │   │   ├── PeopleController.java
│   │   │   ├── ExploreController.java
│   │   │   └── AboutController.java
│   │   └── admin/
│   │       ├── AdminNewsController.java
│   │       ├── AdminProjectController.java
│   │       ├── AdminPeopleController.java
│   │       ├── AdminServiceController.java
│   │       ├── AdminCaseController.java
│   │       ├── AdminHomeController.java
│   │       ├── AdminAboutController.java
│   │       ├── AdminFooterController.java
│   │       └── UploadController.java
│   ├── service/
│   │   ├── NewsService.java
│   │   ├── ProjectService.java
│   │   ├── PeopleService.java
│   │   ├── OssService.java
│   │   └── ...
│   ├── repository/
│   │   ├── NewsRepository.java
│   │   ├── ProjectRepository.java
│   │   └── ...
│   ├── entity/
│   │   ├── News.java
│   │   ├── Project.java
│   │   ├── Person.java
│   │   ├── Service.java
│   │   ├── Case.java
│   │   ├── CompanyValue.java
│   │   └── ...
│   ├── dto/
│   │   ├── Result.java             # 统一响应 {code, message, data}
│   │   ├── PageResult.java         # 分页响应
│   │   ├── SortRequest.java        # 批量排序请求 {items: [{id, sortOrder}]}
│   │   └── ...
│   └── exception/
│       ├── GlobalExceptionHandler.java
│       └── BusinessException.java
├── src/main/resources/
│   ├── application.yml
│   └── application-prod.yml
```

### 核心依赖 (pom.xml)

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <!-- MySQL -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
    </dependency>
    <!-- Alibaba Cloud OSS -->
    <dependency>
        <groupId>com.aliyun.oss</groupId>
        <artifactId>aliyun-sdk-oss</artifactId>
        <version>3.17.4</version>
    </dependency>
    <!-- JWT -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.12.5</version>
    </dependency>
</dependencies>
```

---

## 五、Element Plus 前端对接

### 统一请求封装

```javascript
// src/common/request.js
import axios from 'axios';
import { ElMessage } from 'element-plus';

const request = axios.create({
    baseURL: import.meta.env.VITE_API_BASE || '/api',
    timeout: 15000,
});

// 响应拦截 — 统一错误提示
request.interceptors.response.use(
    (res) => {
        const { code, message, data } = res.data;
        if (code === 200) return data;
        ElMessage.error(message || '请求失败');
        return Promise.reject(new Error(message));
    },
    (error) => {
        const msg = error.response?.data?.message || '服务器异常';
        ElMessage.error(msg);
        return Promise.reject(error);
    }
);

export default request;
```

### ElMessage 错误码映射

Element Plus 的 `ElMessage.error()` 会在右上角弹出错误提示：

| 场景 | 后端 code | ElMessage 效果 |
|------|-----------|----------------|
| 参数校验失败 | 400 | 红色错误条：具体字段信息 |
| 未登录 | 401 | 红色错误条："请先登录" |
| 资源不存在 | 404 | 红色错误条："资源不存在" |
| 服务器错误 | 500 | 红色错误条："服务器异常，请稍后重试" |

### 排序拖拽（Element Plus + vuedraggable）

```vue
<template>
  <el-table :data="projects" row-key="id">
    <el-table-column label="排序" width="80">
      <template #default>
        <el-icon class="drag-handle"><Rank /></el-icon>
      </template>
    </el-table-column>
    <el-table-column prop="zh" label="项目名称" />
  </el-table>
</template>

<script setup>
// 拖拽结束后调用排序接口
async function onDragEnd() {
  const items = projects.value.map((p, i) => ({
    id: p.id,
    sortOrder: i,
  }));
  await request.put('/admin/projects/sort', { items });
}
</script>
```

---

## 六、OSS 存储规范

### 目录结构

```
oss.miwa-co.com/
├── news/
│   └── 2026/05/abc123.jpg
├── projects/
│   └── 2026/04/def456.jpg
├── people/
│   └── avatars/ghi789.jpg
├── services/
│   └── icons/jkl012.png
├── clients/
│   └── logos/mno345.png
└── common/
    └── 2026/05/pqr678.pdf
```

### 文件命名规则

- 格式：`{UUID 前8位}.{扩展名}`
- 保留原始文件通过数据库记录关联
- 图片自动处理：上传时生成缩略图（`?x-oss-process=image/resize,w_800`）

### 访问控制

- 公共读：`news/`、`projects/`、`people/`、`services/`、`clients/`
- 私有：`common/`（需签名 URL 访问）

### OSS 配置 (application.yml)

```yaml
aliyun:
  oss:
    endpoint: oss-cn-shanghai.aliyuncs.com
    bucket: miwa-co
    access-key-id: ${OSS_ACCESS_KEY}
    access-key-secret: ${OSS_SECRET_KEY}
    base-url: https://oss.miwa-co.com
```
