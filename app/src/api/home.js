import { MOCK_SWITCH, request } from "@/common/request";

const servicesMock = [
    { icon: 'brain', image: '', zh: '人工智能', en: 'AI Solutions', desc: '大模型应用、智能体开发、NLP 与计算机视觉。从模型选型到上线调优，我们帮助企业将 AI 从概念验证推向生产环境' },
    { icon: 'code', image: '', zh: '软件开发', en: 'Software Dev', desc: 'Web / 移动端 / 后端全栈工程团队。采用敏捷开发与 DevOps 最佳实践，从原型验证到大规模上线，全程透明交付' },
    { icon: 'cpu', image: '', zh: '物联网', en: 'IoT', desc: '设备接入、边缘计算、数据管道。打通 OT 与 IT 之间的壁垒，让物理世界的数据真正流动起来，驱动实时决策' },
    { icon: 'globe', image: '', zh: '数字转型', en: 'Digital Transformation', desc: '技术尽调、架构重构、系统集成。不只是写代码——我们帮助组织理清技术债，规划可落地的演进路线' },
];

const projectsMock = [
    { zh: '智能客服平台', en: 'AI Customer Service', desc: '基于自研 NLP 引擎，日均处理 5000 万次对话', image: '/images/1.jpg' },
    { zh: '工业 IoT 中台', en: 'Industrial IoT Platform', desc: '接入 10 万+ 设备，实时数据管道与预测性维护', image: '/images/2.jpg' },
    { zh: '企业数据大脑', en: 'Enterprise Data Brain', desc: '统一多源异构数据，构建企业级知识图谱与 BI 看板', image: '/images/3.jpg' },
    { zh: '智慧零售系统', en: 'Smart Retail System', desc: '线上线下全渠道打通，AI 驱动库存优化与精准营销', image: '/images/4.jpg' },
];

const processMock = [
    { zh: '需求诊断', en: 'Discovery & Analysis', desc: '深入业务场景，梳理真实需求与技术约束' },
    { zh: '方案架构', en: 'Architecture & Design', desc: '输出可落地的技术方案与系统架构设计' },
    { zh: '敏捷交付', en: 'Agile Development', desc: '两周迭代节奏，每轮产出可演示的功能增量' },
    { zh: '持续运维', en: 'Operations & Scale', desc: '7×24 监控告警，自动扩缩容与持续性能优化' },
];

const clientsMock = [
    { name: '蔚来', en: 'NIO', image: '' },
    { name: '比亚迪', en: 'BYD', image: '' },
    { name: '华为', en: 'HUAWEI', image: '' },
    { name: '招商银行', en: 'CMB', image: '' },
    { name: '顺丰', en: 'SF Express', image: '' },
    { name: '万科', en: 'VANKE', image: '' },
    { name: '字节跳动', en: 'ByteDance', image: '' },
    { name: '美的', en: 'MIDEA', image: '' },
];

const newsMock = [
    { id: 2, date: '2026.04', title: 'Miwa 发布企业级 AI Agent 开发框架 v2.0', image: '/images/1.jpg' },
    { id: 3, date: '2026.02', title: '与华为联合打造工业 IoT 边缘计算方案落地', image: '/images/2.jpg' },
    { id: 4, date: '2025.12', title: '智能客服平台日均调用量突破 5000 万次', image: '/images/3.jpg' },
    { id: 5, date: '2025.10', title: '获得 ISO 27001 信息安全认证', image: '/images/4.jpg' },
    { id: 6, date: '2025.08', title: '与蔚来达成战略合作，共建车载 AI 实验室', image: '/images/5.jpg' },
    { id: 7, date: '2025.07', title: '发布开源 IoT 中间件 MiwaLink v1.0', image: '/images/1.jpg' },
    { id: 8, date: '2025.05', title: '完成 A 轮融资，加速国际化布局', image: '/images/2.jpg' },
];

const statsMock = [
    { num: '200+', label: '交付项目' },
    { num: '50+', label: '服务企业' },
    { num: '99.9%', label: '系统可用率' },
    { num: '24/7', label: '运维保障' },
];

const heroMock = {
    zh: '用技术驱动未来',
    en: 'AI · Software · IoT — We build what\'s next.',
    sub: '我们相信，每一行代码都应当创造真实价值。从 AI 到物联网，从上海到世界。',
};

const marqueeMock = [
    { zh: '人工智能', en: 'AI' },
    { zh: '软件开发', en: 'Software' },
    { zh: '物联网', en: 'IoT' },
    { zh: '云计算', en: 'Cloud' },
    { zh: '数字转型', en: 'DX' },
];

const philosophyMock = {
    zh: '"技术不应是黑箱，它应当透明、可靠、为人所用"',
    sub: '这是 Miwa 每一个项目背后的核心信念。我们拒绝过度包装，坚持技术向善，让复杂的事情变得清晰简单。',
    en: 'Technology should not be a black box. It should be transparent, reliable, and human.',
};

const aboutMock = {
    zh: [
        'Miwa & Co. 是一家技术驱动的创新公司，成立于上海。',
        '我们专注 AI 应用、软件工程与物联网解决方案，帮助企业跨越从技术到产品的最后一公里。',
        '我们的团队来自全球顶尖科技公司与实验室，相信技术的力量在于让它变得简单、可靠、可及。',
    ],
    en: [
        'A tech-driven company born in Shanghai, focused on AI applications,',
        'software engineering, and IoT solutions. We exist to close the gap',
        'between technology and real-world impact.',
    ],
    cta: { zh: '有项目想聊？', email: 'hello@miwa-co.com' },
};

const footerMock = {
    copyright: '© 2026 Miwa & Co.',
    location: 'Shanghai, China',
};

export function getHomeData() {
    if (MOCK_SWITCH) {
        return Promise.resolve({
            hero: heroMock,
            marquee: marqueeMock,
            services: servicesMock,
            projects: projectsMock,
            process: processMock,
            clients: clientsMock,
            news: newsMock,
            stats: statsMock,
            philosophy: philosophyMock,
            about: aboutMock,
            footer: footerMock,
        });
    }
    return Promise.all([
        request.get("/hero").then(r => r.data),
        request.get("/marquee").then(r => r.data),
        request.get("/services").then(r => r.data),
        request.get("/projects").then(r => r.data),
        request.get("/process").then(r => r.data),
        request.get("/clients").then(r => r.data),
        request.get("/news").then(r => r.data),
        request.get("/stats").then(r => r.data),
        request.get("/philosophy").then(r => r.data),
        request.get("/about").then(r => r.data),
        request.get("/footer").then(r => r.data),
    ]).then(([hero, marquee, services, projects, process, clients, news, stats, philosophy, about, footer]) => ({
        hero, marquee, services, projects, process, clients, news, stats, philosophy, about, footer,
    }));
}
