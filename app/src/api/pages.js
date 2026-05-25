import { MOCK_SWITCH, request } from "@/common/request";

const newsMock = [
    { id: 1, date: '2026.05', title: 'Miwa AI Agent 框架获信通院认证', image: '/images/1.jpg', content: 'Miwa 自主研发的企业级 AI Agent 开发框架 v2.0 正式通过中国信通院认证，成为首批获得该认证的民营企业之一。该框架已在金融、制造、零售三大行业落地超过 50 个场景。' },
    { id: 2, date: '2026.04', title: 'Miwa 发布企业级 AI Agent 开发框架 v2.0', image: '/images/1.jpg', content: '全新的 v2.0 版本引入多智能体协作机制、工具链可视化编排以及企业级安全审计模块，将 AI Agent 的开发效率提升 3 倍以上。' },
    { id: 3, date: '2026.02', title: '与华为联合打造工业 IoT 边缘计算方案落地', image: '/images/2.jpg', content: 'Miwa 与华为云联合研发的工业 IoT 边缘计算方案在长三角某新能源汽车工厂正式投入运营，实现产线数据毫秒级响应与预测性维护。' },
    { id: 4, date: '2025.12', title: '智能客服平台日均调用量突破 5000 万次', image: '/images/3.jpg', content: '基于自研 NLP 引擎的智能客服平台在双十一期间创下新高，日均处理对话量突破 5000 万次，准确率保持在 97.8% 以上。' },
    { id: 5, date: '2025.10', title: '获得 ISO 27001 信息安全认证', image: '/images/4.jpg', content: 'Miwa 正式通过 ISO 27001 信息安全管理体系认证，标志着公司在数据安全与隐私保护方面达到国际标准。' },
    { id: 6, date: '2025.08', title: '与蔚来达成战略合作，共建车载 AI 实验室', image: '/images/5.jpg', content: '双方将在智能座舱、自动驾驶数据标注、车联网安全三个方向展开深度合作，联合实验室落户上海张江。' },
    { id: 7, date: '2025.07', title: '发布开源 IoT 中间件 MiwaLink v1.0', image: '/images/1.jpg', content: 'MiwaLink 是一款轻量级、高性能的 IoT 设备连接中间件，支持 MQTT、CoAP 等多种协议，已在 GitHub 获得 2.3k stars。' },
    { id: 8, date: '2025.05', title: '完成 A 轮融资，加速国际化布局', image: '/images/2.jpg', content: '本轮融资由红杉中国领投，将用于扩充核心研发团队、拓展东南亚及欧洲市场，以及加大 AI 基础能力投入。' },
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
