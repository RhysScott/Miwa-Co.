import { MOCK_SWITCH, request } from "@/common/request";

const newsMock = [
    { id: 1, date: '2026.05', title: 'Miwa AI Agent 框架获信通院认证', image: '/images/1.jpg', excerpt: '成为首批获得该认证的民营企业之一，已在金融、制造、零售三大行业落地超过 50 个场景。', content: `Miwa 自主研发的企业级 AI Agent 开发框架 v2.0 正式通过中国信通院认证，成为首批获得该认证的民营企业之一。

## 核心能力

该框架聚焦企业级 AI 应用的核心痛点，提供 **多智能体协作**、**工具链可视化编排**、**安全审计** 等关键能力。

- **金融领域** — 某股份制银行基于该框架构建智能风控助手，信贷审批效率提升 60%
- **制造业** — 某头部车企用于产线质量异常检测，误报率降至 0.3% 以下
- **零售行业** — 某连锁品牌部署为门店运营助手，覆盖全国 3000+ 门店

> 信通院专家组评价该框架"在架构设计、安全合规和工程化落地方面达到了行业领先水平"。` },
    { id: 2, date: '2026.04', title: 'Miwa 发布企业级 AI Agent 开发框架 v2.0', image: '/images/1.jpg', excerpt: '多智能体协作、工具链可视化编排、企业级安全审计，开发效率提升 3 倍。', content: `Miwa 正式发布企业级 AI Agent 开发框架 v2.0，这是自 2024 年 v1.0 发布以来最重要的一次大版本升级。

## v2.0 核心升级

- **多智能体协作引擎** — 支持 10+ Agent 并行协同工作
- **拖拽式工具链编排器** — 将 Agent 开发从纯代码转变为可视化配置
- **企业级安全审计模块** — 完整记录每一次 AI 决策链路，满足金融、医疗等强监管行业需求

> "我们观察到企业在 AI 落地过程中最大的障碍不是模型能力，而是工程化。v2.0 的目标就是让 AI Agent 从实验室走进生产环境，像部署普通微服务一样简单。"
>
> — Miwa CTO` },
    { id: 3, date: '2026.02', title: '与华为联合打造工业 IoT 边缘计算方案落地', image: '/images/2.jpg', excerpt: '在长三角某新能源汽车工厂投入运营，实现产线数据毫秒级响应与预测性维护。', content: `Miwa 与华为云联合研发的工业 IoT 边缘计算方案在长三角某新能源汽车工厂正式投入运营。

## 技术架构

该方案采用 **"端-边-云"三级架构**：

- **端侧** — 部署轻量级数据采集 Agent，覆盖产线设备超过 3000 台
- **边侧** — 运行 Miwa 自研实时推理引擎，数据采集频率达毫秒级，边缘推理延迟低于 10ms
- **云端** — 负责模型训练与全局调度

## 运行成果

项目上线三个月以来：

- 产线非计划停机 **减少 45%**
- 设备综合效率（OEE）**提升 12 个百分点**

> "这次合作验证了 Miwa 在边缘智能领域的技术实力，我们计划将该方案推广到更多行业场景。"
>
> — 华为云工业 IoT 负责人` },
    { id: 4, date: '2025.12', title: '智能客服平台日均调用量突破 5000 万次', image: '/images/3.jpg', excerpt: '双十一期间准确率保持 97.8% 以上，人工转接率降低 60%。', content: `基于自研 NLP 引擎的智能客服平台在双十一期间创下新纪录——日均处理对话量突破 5000 万次，峰值 QPS 达到 12 万。

## 平台能力

该平台支持文本、语音、图像多模态交互，覆盖 30+ 语种，已为超过 **200 家企业** 提供智能客服能力。

核心技术栈：
- 基于 Transformer 的意图识别模型，准确率稳定在 **97.8%** 以上
- 多轮对话状态追踪
- 知识图谱增强的问答系统

## 业务价值

> "5000 万次不仅仅是一个数字。每次对话背后都是一个真实的用户需求，我们帮助客户在保证服务质量的同时，将人工客服转接率降低了 60%。"
>
> — Miwa 产品负责人` },
    { id: 5, date: '2025.10', title: '获得 ISO 27001 信息安全认证', image: '/images/4.jpg', excerpt: '标志着公司在数据安全与隐私保护方面达到国际标准。', content: `Miwa 正式通过 ISO 27001 信息安全管理体系认证，该认证由国际权威认证机构 SGS 颁发。

## 关于 ISO 27001

ISO 27001 是全球最广泛认可的信息安全管理标准，涵盖 **14 个控制域**、**114 项控制措施**。

Miwa 的认证范围包括：
- AI 平台研发
- SaaS 服务运维
- 客户数据管理

## 下一步

> "安全是 AI 服务的底线。获得 ISO 27001 认证不是终点，而是我们持续投入安全建设的节点。下一步我们将推进 **SOC 2 Type II** 认证，进一步向国际客户证明我们的安全保障能力。"
>
> — Miwa 安全负责人` },
    { id: 6, date: '2025.08', title: '与蔚来达成战略合作，共建车载 AI 实验室', image: '/images/5.jpg', excerpt: '在智能座舱、自动驾驶数据标注、车联网安全三个方向展开深度合作。', content: `Miwa 与蔚来汽车签署战略合作协议，双方将在上海张江联合建立车载 AI 实验室。

## 三大合作方向

- **智能座舱** — 基于 Miwa 的 NLP 和多模态技术，打造下一代车载语音助手
- **自动驾驶数据标注** — 利用 Miwa 自动标注平台，标注效率提升 **5 倍** 以上
- **车联网安全** — 联合研发车辆异常行为检测系统

## 团队规划

联合实验室初期团队规模约 **50 人**，计划在未来两年内扩展至 **200 人**。

> "Miwa 在 AI 应用工程化方面的经验与我们'技术驱动用户体验'的理念高度契合。我们期待通过深度合作，将最前沿的 AI 能力带入每一辆蔚来汽车。"
>
> — 蔚来 CTO` },
    { id: 7, date: '2025.07', title: '发布开源 IoT 中间件 MiwaLink v1.0', image: '/images/1.jpg', excerpt: '轻量级高性能 IoT 设备连接中间件，GitHub 已获 2.3k stars。', content: `Miwa 正式开源 IoT 中间件 MiwaLink v1.0，采用 Apache 2.0 许可证。

## 核心特性

- **多协议自适应接入** — 支持 MQTT 5.0、CoAP、HTTP/3
- **高性能** — 单节点支持 100 万并发设备连接
- **低代码数据流编排** — 内置规则引擎
- **边缘网关模式** — 断网时可离线运行

## 社区反响

发布首月在 GitHub 获得 **2.3k stars**，社区贡献者超过 30 人。目前已有多家智能制造企业基于 MiwaLink 构建设备管理平台。

> "开源是我们回馈技术社区的方式。IoT 领域缺乏好的中间件，我们希望 MiwaLink 能填补这个空白。"
>
> — Miwa 开源负责人` },
    { id: 8, date: '2025.05', title: '完成 A 轮融资，加速国际化布局', image: '/images/2.jpg', excerpt: '红杉中国领投，用于扩充核心研发团队、拓展东南亚及欧洲市场。', content: `Miwa 宣布完成 A 轮融资，由**红杉中国**领投，上一轮投资方跟投。

## 资金用途

**研发扩张**
上海研发中心从 80 人扩展至 200 人，重点招募 AI 算法、分布式系统、安全领域的资深工程师。

**国际化布局**
启动新加坡办公室筹建，预计 2026 年 Q1 投入运营，拓展东南亚及欧洲市场。

**基础设施**
加大 AI 基础能力投入，包括自研模型训练基础设施和开源生态建设。

> "我们看好 Miwa 在 AI 应用工程化领域的独特定位。他们不追逐模型参数竞赛，而是专注将 AI 真正落地到产业场景中，这是一种更务实的商业路径。"
>
> — 红杉中国合伙人` },
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
        { zh: '智能客服平台', en: 'AI Customer Service', desc: '基于自研 NLP 引擎，日均处理 5000 万次对话', image: '/images/1.jpg', detail: '为某头部互联网企业打造的全渠道智能客服平台，整合文本、语音、图像多模态交互，支持 30+ 语种。采用自研 NLP 引擎，意图识别准确率 97.8%，日均处理对话量突破 5000 万次，人工客服转接率降低 60%。' },
        { zh: '工业 IoT 中台', en: 'Industrial IoT Platform', desc: '接入 10 万+ 设备，实时数据管道与预测性维护', image: '/images/2.jpg', detail: '为某新能源车企构建的工业 IoT 数据中台，接入产线设备、AGV、环境传感器等 10 万+ 终端。通过边缘计算节点实现毫秒级数据响应，结合 ML 模型实现设备预测性维护，非计划停机减少 45%。' },
        { zh: '企业数据大脑', en: 'Enterprise Data Brain', desc: '统一多源异构数据，构建企业级知识图谱与 BI 看板', image: '/images/3.jpg', detail: '为某股份制银行打造的统一数据平台，整合 200+ 业务系统数据源，构建企业级知识图谱。支持自然语言查询的 BI 看板让业务人员无需 SQL 即可自助分析，报表产出效率提升 10 倍。' },
        { zh: '智慧零售系统', en: 'Smart Retail System', desc: '线上线下全渠道打通，AI 驱动库存优化与精准营销', image: '/images/4.jpg', detail: '为某跨国零售集团打造的 O2O 全渠道中台，统一线上商城与线下 3000+ 门店的会员、库存、营销体系。AI 驱动的智能补货与动态定价系统将库存周转率提升 30%，支撑日均千万级订单并发。' },
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
        const map = { news: newsMock, contact: contactMock, explore: exploreMock, about: aboutMock };
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
