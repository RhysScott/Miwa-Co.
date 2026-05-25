import { MOCK_SWITCH, request } from "@/common/request";

const newsMock = [
    { id: 1, date: '2026.05', title: 'Miwa AI Agent 框架获信通院认证', image: 'https://picsum.photos/seed/news1/800/450', excerpt: '成为首批获得该认证的民营企业之一，已在金融、制造、零售三大行业落地超过 50 个场景。', content: `Miwa 自主研发的企业级 AI Agent 开发框架 v2.0 正式通过中国信通院认证，成为首批获得该认证的民营企业之一。

![架构示意图](https://picsum.photos/seed/diagram-arch/800/400)

## 核心能力

该框架聚焦企业级 AI 应用的核心痛点，提供 **多智能体协作**、**工具链可视化编排**、*安全审计* 等关键能力。核心引擎采用微内核架构，运行时仅 \`~500KB\`，支持 \`Node.js\` 与 \`Python\` 双语言 SDK。

### 性能指标

| 指标 | v1.0 | v2.0 | 提升幅度 |
|------|------|------|----------|
| 单节点 Agent 并发 | 5 | 15 | **200%** |
| 工具链编排耗时 | ~30min | ~5min | **83%** |
| 审计日志完整性 | 92% | 99.7% | **8.4%** |
| 平均冷启动时间 | 2.3s | 0.6s | **74%** |

---

### 落地案例

1. **金融领域** — 某股份制银行基于该框架构建智能风控助手，信贷审批效率提升 60%
2. **制造业** — 某头部车企用于产线质量异常检测，误报率降至 0.3% 以下
3. **零售行业** — 某连锁品牌部署为门店运营助手，覆盖全国 3000+ 门店

更多行业案例请参阅 [Miwa 官网案例库](https://miwa-co.com/cases)。

---

\`\`\`javascript
// Agent 定义示例
const riskAgent = new Agent({
  name: 'risk-analyzer',
  model: 'miwa-lite',
  tools: ['sql-query', 'rule-engine'],
  audit: { enabled: true, level: 'full' },
});

await riskAgent.run('分析近30天异常交易模式');
\`\`\`

---

> **信通院专家组评价：**
>
> "该框架在架构设计、安全合规和工程化落地方面达到了行业领先水平。"
>
> — 中国信通院 AI 评测中心

对于希望深入了解该框架的技术团队，建议从官方 \`quick-start\` 仓库入手，配合 [开发者文档](https://docs.miwa-co.com) 阅读。` },
    { id: 2, date: '2026.04', title: 'Miwa 发布企业级 AI Agent 开发框架 v2.0', image: 'https://picsum.photos/seed/news2/800/450', excerpt: '多智能体协作、工具链可视化编排、企业级安全审计，开发效率提升 3 倍。', content: `Miwa 正式发布企业级 AI Agent 开发框架 v2.0，这是自 2024 年 v1.0 发布以来最重要的一次大版本升级。

![发布现场](https://picsum.photos/seed/launch-event/800/400)

## v2.0 核心升级

本次升级聚焦三个关键词：**协作**、**可观测**、**易用**。

### 新特性一览

1. **多智能体协作引擎** — 支持 10+ Agent 并行协同工作，内置 *冲突检测* 与 *共识机制*
2. **拖拽式工具链编排器** — 将 Agent 开发从纯代码转变为可视化配置，降低 80% 上手门槛
3. **企业级安全审计模块** — 完整记录每一次 AI 决策链路，满足金融、医疗等强监管行业需求
4. **一键部署到 \`Kubernetes\`** — 新增 \`miwa deploy\` CLI 命令，3 分钟完成生产部署

### 兼容性说明

v1.0 用户可通过 \`miwa migrate\` 命令平滑升级，所有现有 Agent 定义 *向后兼容*。

> "我们观察到企业在 AI 落地过程中最大的障碍不是模型能力，而是工程化。v2.0 的目标就是让 AI Agent 从实验室走进生产环境，像部署普通微服务一样简单。"
>
> — Miwa CTO

更多技术细节请访问 [v2.0 Release Notes](https://github.com/miwa-co/framework/releases/v2.0)。` },
    { id: 3, date: '2026.02', title: '与华为联合打造工业 IoT 边缘计算方案落地', image: 'https://picsum.photos/seed/news3/800/450', excerpt: '在长三角某新能源汽车工厂投入运营，实现产线数据毫秒级响应与预测性维护。', content: `Miwa 与华为云联合研发的工业 IoT 边缘计算方案在长三角某新能源汽车工厂正式投入运营。

![工厂产线](https://picsum.photos/seed/factory-line/800/400)

## 技术架构

该方案采用 **"端-边-云"三级架构**，在*实时性*、*可靠性*与*成本*之间取得平衡：

### 端侧
部署轻量级数据采集 Agent，覆盖产线设备超过 **3000 台**，协议适配层支持 \`Modbus\`、\`OPC UA\`、\`MQTT\` 等 20+ 工业协议。

### 边侧
运行 Miwa 自研实时推理引擎：
- 数据采集频率达毫秒级
- 边缘推理延迟 **< 10ms**
- 断网离线运行能力，*本地缓存* 最多 72 小时数据

### 云端
负责模型训练与全局调度，基于 \`Kubernetes\` 的弹性集群可按需扩缩。

---

## 运行成果

项目上线三个月以来的核心数据：

| 指标 | 上线前 | 上线后 | 变化 |
|------|--------|--------|------|
| 非计划停机（次/月） | 2.4 | 1.3 | **-45%** |
| OEE | 73.2% | 85.4% | **+12.2pp** |
| 故障定位时间 | ~45min | ~6min | **-87%** |

---

> "这次合作验证了 Miwa 在边缘智能领域的技术实力。端-边-云协同不是 PPT 概念——它在真实产线上跑出了可量化的业务价值。"
>
> — 华为云工业 IoT 负责人

下一步双方将把该方案推广至 **3 家 Tier-1 零部件供应商**，构建更完整的供应链质量网络。` },
    { id: 4, date: '2025.12', title: '智能客服平台日均调用量突破 5000 万次', image: 'https://picsum.photos/seed/news4/800/450', excerpt: '双十一期间准确率保持 97.8% 以上，人工转接率降低 60%。', content: `基于自研 NLP 引擎的智能客服平台在双十一期间创下新纪录——日均处理对话量突破 **5000 万次**，峰值 QPS 达到 12 万。

![数据看板](https://picsum.photos/seed/dashboard-chart/800/400)

## 平台能力

该平台支持 *文本*、*语音*、*图像* 多模态交互，覆盖 **30+ 语种**，已为超过 200 家企业提供智能客服能力。

### 核心技术栈

- 基于 \`Transformer\` 的意图识别模型，准确率稳定在 **97.8%** 以上
- *多轮对话状态追踪*（DST），支持上下文跨轮记忆
- 知识图谱增强的问答系统（\`KG-RAG\` 混合检索）

### 压力测试表现

\`\`\`
峰值 QPS：      120,000
P99 延迟：      180ms
可用性：        99.97%（双十一期间）
降级触发次数：   0
\`\`\`

---

> "5000 万次不仅仅是一个数字。每次对话背后都是一个真实的用户需求——我们帮助客户在保证服务质量的同时，将人工客服转接率降低了 **60%**。"
>
> — Miwa 产品负责人

目前团队正在探索基于 *大语言模型* 的下一代对话引擎，预计 2026 年 Q3 进入灰度测试。` },
    { id: 5, date: '2025.10', title: '获得 ISO 27001 信息安全认证', image: 'https://picsum.photos/seed/news5/800/450', excerpt: '标志着公司在数据安全与隐私保护方面达到国际标准。', content: `Miwa 正式通过 ISO 27001 信息安全管理体系认证，该认证由国际权威认证机构 SGS 颁发。

## 关于 ISO 27001

ISO 27001 是全球最广泛认可的信息安全管理标准，涵盖 **14 个控制域**、**114 项控制措施**，覆盖从物理安全到密码学的完整信息安全生命周期。

### Miwa 的认证范围

1. AI 平台研发全流程（*需求 → 开发 → 测试 → 部署*）
2. SaaS 服务运维与监控
3. 客户数据全生命周期管理

### 认证过程

\`\`\`
阶段一（1个月）：差距分析与整改计划
阶段二（2个月）：体系搭建与试运行
阶段三（1个月）：SGS 现场审核
\`\`\`

> "安全是 AI 服务的底线。获得 ISO 27001 认证不是终点——下一步我们将推进 **SOC 2 Type II** 认证，进一步向国际客户证明我们的安全保障能力。"
>
> — Miwa 安全负责人

相关认证信息可查询 [SGS 认证公示平台](https://www.sgs.com)。` },
    { id: 6, date: '2025.08', title: '与蔚来达成战略合作，共建车载 AI 实验室', image: 'https://picsum.photos/seed/news6/800/450', excerpt: '在智能座舱、自动驾驶数据标注、车联网安全三个方向展开深度合作。', content: `Miwa 与蔚来汽车签署战略合作协议，双方将在上海张江联合建立**车载 AI 实验室**。

![签约仪式](https://picsum.photos/seed/nio-signing/800/400)

## 三大合作方向

### 1. 智能座舱
基于 Miwa 的 NLP 和多模态技术，打造下一代车载语音助手。支持 *连续对话*、*多指令并发*、*方言识别*，目标唤醒率 99%+。

### 2. 自动驾驶数据标注
利用 Miwa 自动标注平台（\`AutoLabel v3\`）：
- 标注效率提升 **5 倍** 以上
- 人工审核工作量降低 70%
- 支持 2D/3D 融合标注

### 3. 车联网安全
联合研发车辆异常行为检测系统，覆盖 \`CAN\` 总线、\`T-Box\` 通信、云端 API 等攻击面。

## 团队规划

联合实验室初期团队规模约 **50 人**，计划在未来两年内扩展至 **200 人**，覆盖算法、系统工程、测试验证等岗位。

> "Miwa 在 AI 应用工程化方面的经验与我们 '技术驱动用户体验' 的理念高度契合。"
>
> — 蔚来 CTO` },
    { id: 7, date: '2025.07', title: '发布开源 IoT 中间件 MiwaLink v1.0', image: 'https://picsum.photos/seed/news7/800/450', excerpt: '轻量级高性能 IoT 设备连接中间件，GitHub 已获 2.3k stars。', content: `Miwa 正式开源 IoT 中间件 MiwaLink v1.0，采用 **Apache 2.0** 许可证。

\`\`\`bash
npm install miwalink
# 或
docker pull miwa/miwalink:1.0
\`\`\`

## 核心特性

- **多协议自适应接入** — 支持 \`MQTT 5.0\`、\`CoAP\`、\`HTTP/3\`，协议插件热加载
- **高性能** — 单节点支持 **100 万** 并发设备连接，内存占用 < 512MB
- **低代码数据流编排** — 内置规则引擎，拖拽配置即生效
- **边缘网关模式** — 断网时可离线运行，*本地规则引擎* 独立决策

## 快速开始

\`\`\`yaml
# miwalink.yaml
server:
  port: 1883
  protocol: mqtt
rules:
  - name: high-temp-alert
    when: "temp > 80"
    then: "notify(ops-channel)"
\`\`\`

## 社区反响

发布首月在 GitHub 获得 **2.3k stars**，社区贡献者超过 **30 人**。目前已有多家智能制造企业基于 MiwaLink 构建设备管理平台。

> "开源是我们回馈技术社区的方式。IoT 领域缺乏好的中间件，我们希望 MiwaLink 能填补这个空白。"
>
> — Miwa 开源负责人

GitHub 仓库：[miwa-co/miwalink](https://github.com/miwa-co/miwalink)` },
    { id: 8, date: '2025.05', title: '完成 A 轮融资，加速国际化布局', image: 'https://picsum.photos/seed/news8/800/450', excerpt: '红杉中国领投，用于扩充核心研发团队、拓展东南亚及欧洲市场。', content: `Miwa 宣布完成 **A 轮融资**，由 *红杉中国* 领投，上一轮投资方跟投。本轮融资将主要用于研发扩张、国际化布局与基础设施建设。

## 资金用途

### 研发扩张
上海研发中心从 **80 人** 扩展至 **200 人**，重点招募方向：

1. AI 算法 — 大模型训练与推理优化
2. 分布式系统 — 高并发高可用架构
3. 安全工程 — 零信任架构与数据隐私
4. 开发者工具 — \`CLI\`、\`SDK\`、\`VS Code 插件\`

### 国际化布局
启动新加坡办公室筹建，预计 **2026 年 Q1** 投入运营，初期聚焦东南亚及欧洲市场。

### 基础设施
加大 AI 基础能力投入，包括自研模型训练集群（首批 **128×A100**）和开源生态建设。

---

> "我们看好 Miwa 在 AI 应用工程化领域的独特定位。他们不追逐模型参数竞赛，而是专注将 AI 真正落地到产业场景中，这是一种更务实的商业路径。"
>
> — 红杉中国合伙人

目前多个核心岗位开放招聘中，欢迎投递简历至 [hello@miwa-co.com](mailto:hello@miwa-co.com)。` },
];

const contactMock = {
    email: 'hello@miwa-co.com',
    phone: '+86 21 8888 6666',
    address: '上海市浦东新区张江高科技园区',
    en: {
        email: 'hello@miwa-co.com',
        phone: '+86 21 8888 6666',
        address: 'Zhangjiang Hi-Tech Park, Pudong, Shanghai',
    },
};

const exploreMock = {
    projects: [
        { id: 1, zh: '智能客服平台', en: 'AI Customer Service', desc: '基于自研 NLP 引擎，日均处理 5000 万次对话', image: 'https://picsum.photos/seed/ep-cs/800/500', content: `为某头部互联网企业打造的全渠道智能客服平台，整合文本、语音、图像多模态交互，支持 30+ 语种。

![客服平台架构](https://picsum.photos/seed/cs-arch/800/400)

## 项目背景

客户原有客服系统采用传统人工 + 关键词匹配模式，面临三大挑战：

1. **人力成本高** — 客服团队超过 2000 人，年成本超 2 亿元
2. **响应速度慢** — 高峰期用户平均等待时间超过 3 分钟
3. **知识分散** — 业务知识散落在 30+ 系统中，客服需要频繁切换查询

## 技术方案

### 核心引擎
采用自研 NLP 引擎，基于 \`Transformer\` 架构微调，在中文客服领域达到 SOTA：

- 意图识别准确率 **97.8%**
- 多轮对话上下文窗口支持 **20 轮**
- 实体抽取 F1 达 **0.94**

### 多模态交互

| 渠道 | 日均量 | 准确率 |
|------|--------|--------|
| 文本 | 3500 万 | 98.1% |
| 语音 | 1200 万 | 96.5% |
| 图像 | 300 万 | 94.2% |

### 语种覆盖
支持中文、英语、日语、韩语、泰语等 **30+ 语种**，通过统一的跨语言嵌入表示实现零样本迁移。

\`\`\`python
# 多语种意图识别示例
from miwa.nlp import IntentClassifier

classifier = IntentClassifier.load("customer-service-v3")
result = classifier.predict("How can I track my order?")
# => {"intent": "order_tracking", "confidence": 0.983, "lang": "en"}
\`\`\`

## 项目成果

上线六个月后：

- 人工客服转接率从 45% 降至 **18%**（降低 60%）
- 用户满意度从 3.8 提升至 **4.6**（5 分制）
- 日均处理对话量突破 **5000 万次**

> "Miwa 团队不仅交付了一套高质量的系统，更重要的是帮助我们的客服团队完成了从'操作工'到'AI 训练师'的角色转型。"
>
> — 客户方项目负责人` },
        { id: 2, zh: '工业 IoT 中台', en: 'Industrial IoT Platform', desc: '接入 10 万+ 设备，实时数据管道与预测性维护', image: 'https://picsum.photos/seed/ep-iot/800/500', content: `为某新能源车企构建的工业 IoT 数据中台，接入产线设备、AGV、环境传感器等 10 万+ 终端。

![IoT 数据看板](https://picsum.photos/seed/iot-dashboard/800/400)

## 项目挑战

客户工厂拥有来自 15+ 供应商的设备，协议各异、数据格式不统一，且对实时性要求极高（产线停机一分钟损失约 8 万元）。

## 技术架构

### 端-边-云三层体系

- **端侧** — 部署轻量级数据采集 Agent，适配 \`Modbus\`、\`OPC UA\`、\`PROFINET\` 等 20+ 工业协议
- **边侧** — 在每个车间部署边缘计算节点，运行实时推理引擎，响应延迟 **< 5ms**
- **云端** — 基于 \`Kubernetes\` 的全局调度与模型训练平台

### 设备接入规模

| 设备类型 | 数量 | 数据频率 |
|----------|------|----------|
| 产线 PLC | 2,800+ | 100ms |
| AGV | 350 | 500ms |
| 环境传感器 | 8,000+ | 1s |
| 视觉检测相机 | 1,200 | 实时流 |

### 预测性维护模型

\`\`\`python
# 设备健康评分示例
from miwa.iot import PredictiveEngine

engine = PredictiveEngine(model="cnc-v2")
health = engine.assess(device_id="CNC-A17")
print(f"健康度: {health.score:.1%}, 预计剩余寿命: {health.rul_days} 天")
# => 健康度: 83.2%, 预计剩余寿命: 45 天
\`\`\`

## 项目成果

- 非计划停机减少 **45%**
- 设备综合效率（OEE）从 73.2% 提升至 **85.4%**
- 运维团队从 120 人精简至 **85 人**（人员转岗至更高价值工作）

> "这套系统最让我们惊喜的不是技术本身，而是它让数据真正流动起来了——以前各车间是数据孤岛，现在整个工厂有了统一的数字神经。"
>
> — 客户数字化负责人` },
        { id: 3, zh: '企业数据大脑', en: 'Enterprise Data Brain', desc: '统一多源异构数据，构建企业级知识图谱与 BI 看板', image: 'https://picsum.photos/seed/ep-data/800/500', content: `为某股份制银行打造的统一数据平台，整合 200+ 业务系统数据源，构建企业级知识图谱。

![数据架构图](https://picsum.photos/seed/data-brain/800/400)

## 项目背景

银行拥有 200+ 业务系统，数据分散在 Oracle、MySQL、Hadoop、Kafka 等多种存储中。业务团队要做一次跨系统分析，平均需要 **2 周**时间协调数据权限、编写 SQL、制作报表。

## 解决方案

### 统一数据层
构建基于 \`Apache Iceberg\` 的数据湖，将 200+ 系统数据实时同步至统一存储层：

- 日增量数据 **5TB+**
- 数据新鲜度 **< 5 分钟**
- 查询引擎支持 \`Trino\` + \`Spark\`

### 知识图谱
抽取实体与关系，构建包含 **1.2 亿节点**、**8 亿边**的企业知识图谱：

| 实体类型 | 数量 | 示例 |
|----------|------|------|
| 客户 | 3500 万 | 个人/企业 |
| 账户 | 1.2 亿 | 储蓄/信贷/理财 |
| 交易 | 日均 800 万 | 转账/支付/投资 |
| 产品 | 5000+ | 基金/保险/贷款 |

### 自然语言 BI
\`\`\`sql
-- 用户输入："上月信用卡消费金额最高的10个商户"
-- 系统自动生成并执行：
SELECT merchant_name, SUM(amount) as total
FROM credit_card_transactions
WHERE transaction_date BETWEEN '2026-04-01' AND '2026-04-30'
GROUP BY merchant_name
ORDER BY total DESC
LIMIT 10;
\`\`\`

## 项目成果

- 跨系统分析从 **2 周** 缩短至 **30 分钟**
- 报表产出效率提升 **10 倍**
- 数据团队从接需求模式转型为赋能平台模式

> "以前业务提一个数据需求要走五个审批。现在他们自己在 BI 看板上用自然语言问一句就出结果，这才是真正的数据民主化。"
>
> — 银行数据平台部负责人` },
        { id: 4, zh: '智慧零售系统', en: 'Smart Retail System', desc: '线上线下全渠道打通，AI 驱动库存优化与精准营销', image: 'https://picsum.photos/seed/ep-retail/800/500', content: `为某跨国零售集团打造的 O2O 全渠道中台，统一线上商城与线下 3000+ 门店的会员、库存、营销体系。

![零售数据看板](https://picsum.photos/seed/retail-ops/800/400)

## 业务挑战

客户在中国拥有 3000+ 门店，同时运营线上商城、小程序、第三方平台等多个渠道。核心痛点：

1. **库存割裂** — 线上线下库存各自管理，经常出现"A 店积压、B 店缺货"
2. **会员分散** — 同一用户在多个渠道有不同 ID，无法统一画像
3. **促销滞后** — 从数据分析到营销执行需要 3-5 天

## 技术方案

### 全渠道中台架构

\`\`\`
 ┌──────────┐  ┌──────────┐  ┌──────────┐
 │  线上商城  │  │  小程序   │  │ 第三方平台 │
 └────┬─────┘  └────┬─────┘  └────┬─────┘
      └──────────────┼──────────────┘
              ┌──────┴──────┐
              │   中台 API   │
              └──────┬──────┘
         ┌───────────┼───────────┐
    ┌────┴────┐ ┌────┴────┐ ┌────┴────┐
    │  订单中心 │ │  库存中心 │ │  会员中心 │
    └─────────┘ └─────────┘ └─────────┘
\`\`\`

### AI 智能补货
基于 \`XGBoost\` + \`Prophet\` 的混合预测模型，结合天气、节假日、促销计划等多维特征：

| 指标 | 人工补货 | AI 补货 | 提升 |
|------|----------|---------|------|
| 缺货率 | 8.3% | 3.1% | **-63%** |
| 库存周转天 | 45 | 32 | **-29%** |
| 报损率 | 2.1% | 0.8% | **-62%** |

### 统一会员画像
通过手机号、设备指纹、支付 ID 等多维匹配，将多渠道用户统一为 **OneID**，构建 360° 用户画像。

## 项目成果

- 支撑日均 **千万级** 订单并发
- 库存周转率提升 **30%**
- 营销活动 ROI 提升 **2.4 倍**

> "全渠道中台上线后，我们第一次真正做到了'线上下单、门店发货、30 分钟达'。这不仅是技术升级，更是商业模式的进化。"
>
> — 集团 CIO` },
    ],
    services: [
        { zh: '人工智能', en: 'AI Solutions', desc: '大模型应用、智能体开发、NLP 与计算机视觉', detail: '从模型选型到上线调优，我们帮助企业将 AI 从概念验证推向生产环境。服务涵盖大语言模型微调、RAG 知识库构建、多智能体协作系统、以及端到端的 MLOps 流水线搭建。' },
        { zh: '软件开发', en: 'Software Dev', desc: 'Web / 移动端 / 后端全栈工程', detail: '采用敏捷开发与 DevOps 最佳实践，从原型验证到大规模上线，全程透明交付。技术栈覆盖 React、Vue、Node.js、Go、Python，支持云原生与微服务架构。' },
        { zh: '物联网', en: 'IoT', desc: '设备接入、边缘计算、数据管道', detail: '打通 OT 与 IT 之间的壁垒，让物理世界的数据真正流动起来。提供设备管理平台、边缘网关部署、实时数据流处理与可视化，支持百万级设备并发接入。' },
        { zh: '数字转型', en: 'Digital Transformation', desc: '技术尽调、架构重构、系统集成', detail: '不只是写代码——我们帮助组织理清技术债，规划可落地的演进路线。从 legacy 系统迁移到云原生改造，提供全周期的技术咨询与实施服务。' },
    ],
    cases: [
        { zh: '某头部新能源车企 — 智能产线 AI 质检', detail: '基于计算机视觉的实时缺陷检测系统，误检率 < 0.5%，每条产线每年节省人力成本约 200 万元。' },
        { zh: '某股份制银行 — 智能风控决策引擎', detail: '整合 200+ 数据源，实时计算风险评分，将信贷审批时间从天级压缩到分钟级，不良率下降 15%。' },
        { zh: '某跨国零售集团 — 全渠道 O2O 中台', detail: '统一线上商城与线下 3000+ 门店的库存、会员、营销体系，支撑日均千万级订单并发。' },
    ],
};

const peopleMock = [
    { id: 1, name: '陈思远', en: 'Siyuan Chen', role: '创始人 / CEO', roleEn: 'Founder & CEO', bio: '前 Google Brain 研究员，CMU 计算机博士。专注 AI 系统工程化十余年，相信好的技术应该像水电一样随处可得。', image: 'https://picsum.photos/seed/team-ceo/400/400', detail: `陈思远在创立 Miwa 之前，曾在 Google Brain 从事大规模机器学习系统的研发工作，参与了 TensorFlow 早期核心模块的设计与实现。

在 CMU 攻读博士期间，他的研究方向为分布式机器学习系统，多篇论文发表于 OSDI、SOSP 等顶级系统会议。博士毕业后，他意识到 AI 从实验室走向产业的最大障碍不是模型精度，而是工程化落地能力——这一洞察直接催生了 Miwa 的创立。

陈思远持有 CMU 计算机科学博士学位及清华大学计算机科学与技术学士学位。工作之余，他是一名马拉松跑者和开源社区的活跃贡献者。` },
    { id: 2, name: '林知夏', en: 'Zhixia Lin', role: 'CTO', roleEn: 'CTO', bio: '前阿里云分布式系统架构师，开源项目 Apache Kvrocks PMC 成员。对高并发、高可用的系统设计有近乎偏执的追求。', image: 'https://picsum.photos/seed/team-cto/400/400', detail: `林知夏拥有超过十年的分布式系统设计经验。在加入 Miwa 之前，他在阿里云负责核心中间件团队，主导设计了日调用量超千亿次的分布式缓存平台。

他是 Apache Kvrocks 项目的 PMC 成员，也是多个知名开源项目的活跃贡献者。在 Miwa，他负责所有技术架构决策，推动端-边-云协同架构从概念走向规模化落地。

林知夏毕业于上海交通大学计算机系，曾就职于百度、阿里云。他主张"好的架构是演进出来的，不是设计出来的"，在团队内部推行基于 SLO 的架构治理方法。` },
    { id: 3, name: '王若溪', en: 'Ruoxi Wang', role: '设计总监', roleEn: 'Head of Design', bio: '前 frog design 资深设计师，RCA 服务设计硕士。主张技术产品需要与之匹配的人文温度。', image: 'https://picsum.photos/seed/team-design/400/400', detail: `王若溪负责 Miwa 全线产品的设计策略与品牌体验。她曾在全球顶尖设计咨询公司 frog design 工作五年，为多家 Fortune 500 企业提供产品设计服务。

她在 RCA（英国皇家艺术学院）取得服务设计硕士学位，本科毕业于中央美术学院。她的设计理念是"技术产品不应是冰冷的工具，而应像好的建筑一样——功能强大，同时让人感到舒适和愉悦"。

在 Miwa，她主导了从品牌视觉到 B 端产品交互的完整设计体系搭建，推动设计系统从零到一的建设。` },
    { id: 4, name: '赵一宁', en: 'Yining Zhao', role: 'AI 研究负责人', roleEn: 'Head of AI Research', bio: '前腾讯 AI Lab 高级研究员，清华计算机博士。追求从 research 到 production 的最短路径。', image: 'https://picsum.photos/seed/team-ai/400/400', detail: `赵一宁在清华大学取得计算机科学与技术博士学位，师从自然语言处理领域的知名学者。博士期间，他在 ACL、EMNLP 等顶会发表论文十余篇，引用量超过 3000 次。

在腾讯 AI Lab 工作期间，他主导了多个千亿参数大模型的训练与部署优化。加入 Miwa 后，他带领团队从零搭建了自研 NLP 引擎，在意图识别、多轮对话等核心任务上达到工业级精度。

赵一宁相信"最好的 AI 研究不是发论文，而是让用户在不知不觉中享受 AI 带来的便利"。他目前专注于多智能体协作框架和大模型推理优化方向。` },
    { id: 5, name: '周涵宇', en: 'Hanyu Zhou', role: '工程 VP', roleEn: 'VP of Engineering', bio: '前字节跳动基础架构负责人，主导了抖音服务端架构从单体到微服务的演进。', image: 'https://picsum.photos/seed/team-eng/400/400', detail: `周涵宇在字节跳动工作了七年，亲历了抖音从百万 DAU 到十亿用户的技术演进全过程。他主导了服务端架构从单体应用到微服务体系的彻底重构，将系统可用性从 99.9% 提升至 99.99%。

在 Miwa，他负责工程团队管理和交付质量保障，推动 DevOps 文化和自动化测试体系建设。他坚信"工程效率是技术组织的核心竞争力"，在团队内部推行 AI 辅助编程和自动化代码审查。

周涵宇毕业于浙江大学软件工程专业，是一位狂热的登山爱好者和业余无线电操作员。` },
    { id: 6, name: '张明薇', en: 'Mingwei Zhang', role: '产品负责人', roleEn: 'Head of Product', bio: '前蚂蚁集团高级产品专家，深谙金融科技与企业服务领域的产品方法论。', image: 'https://picsum.photos/seed/team-pm/400/400', detail: `张明薇拥有十年企业服务产品经验，在蚂蚁集团期间负责过多款面向金融机构的 SaaS 产品，服务客户包括国有大行、股份制银行和头部券商。

她擅长在复杂技术能力与客户真实需求之间找到最优产品路径。在 Miwa，她主导了 AI Agent 框架和智能客服平台的产品定义与迭代，推动产品从"技术 demo"进化为"客户愿意付费的商业产品"。

张明薇毕业于复旦大学管理学院，拥有 MBA 学位。她相信"好的产品经理应该像翻译官一样，能把技术语言翻译成客户价值"。` },
];

const aboutMock = {
    zh: [
        'Miwa & Co. 是一家技术驱动的创新公司，成立于上海。',
        '我们专注 AI 应用、软件工程与物联网解决方案，帮助企业跨越从技术到产品的最后一公里。',
        '我们的团队来自全球顶尖科技公司与实验室，相信技术的力量在于让它变得简单、可靠、可及。',
        '目前公司在上海、新加坡设有办公室，服务客户覆盖新能源汽车、金融科技、智能制造、智慧零售等多个行业。',
    ],
    en: [
        'A tech-driven company born in Shanghai, focused on AI applications,',
        'software engineering, and IoT solutions. We exist to close the gap',
        'between technology and real-world impact.',
        'We have offices in Shanghai and Singapore, serving clients across',
        'EV, FinTech, smart manufacturing, and retail industries.',
    ],
    values: [
        { zh: '技术透明', en: 'Radical Transparency', desc: '代码开源、架构文档化、决策可追溯。我们相信透明的技术才是值得信赖的技术。' },
        { zh: '工程卓越', en: 'Engineering Excellence', desc: '不做一次性交付。每一个项目都经过严格的 code review、自动化测试和性能基准验证。' },
        { zh: '客户成功', en: 'Client Success First', desc: '不是卖完方案就走。我们与客户共建长期技术能力，让内部团队真正掌握系统。' },
        { zh: '持续创新', en: 'Continuous Innovation', desc: '每周五为全公司"Hack Day"，鼓励跨团队探索新技术、新工具、新范式。' },
    ],
};

export function getPageData(page) {
    if (MOCK_SWITCH) {
        const map = { news: newsMock, contact: contactMock, explore: exploreMock, about: aboutMock, people: peopleMock };
        return Promise.resolve(map[page] || null);
    }
    return request.get(`/${page}`).then(r => r.data);
}

export function getNewsById(id) {
    if (MOCK_SWITCH) {
        const item = newsMock.find(n => n.id === Number(id));
        return Promise.resolve(item || null);
    }
    return request.get(`/news/${id}`).then(r => r.data);
}

export function getProjectById(id) {
    if (MOCK_SWITCH) {
        const item = exploreMock.projects.find(p => p.id === Number(id));
        return Promise.resolve(item || null);
    }
    return request.get(`/projects/${id}`).then(r => r.data);
}

export function getPersonById(id) {
    if (MOCK_SWITCH) {
        const item = peopleMock.find(p => p.id === Number(id));
        return Promise.resolve(item || null);
    }
    return request.get(`/people/${id}`).then(r => r.data);
}
