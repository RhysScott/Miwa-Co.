import { MOCK_SWITCH, request } from "@/common/request";

const servicesMock = [
    { icon: 'brain', zh: '人工智能', en: 'AI Solutions', desc: '大模型应用、智能体开发、NLP 与计算机视觉 —— 为企业注入 AI 能力' },
    { icon: 'code', zh: '软件开发', en: 'Software Dev', desc: 'Web / 移动端 / 后端系统全栈开发，从原型到上线的完整交付' },
    { icon: 'cpu', zh: '物联网', en: 'IoT', desc: '设备接入、边缘计算、数据中台 —— 连接物理世界与数字世界' },
    { icon: 'globe', zh: '数字转型', en: 'Digital Transformation', desc: '技术咨询、架构设计、系统集成，助力企业完成数字化升级' },
];

const projectsMock = [
    { zh: '智能客服平台', en: 'AI Customer Service', image: '/images/1.jpg' },
    { zh: '工业 IoT 中台', en: 'Industrial IoT Platform', image: '/images/2.jpg' },
    { zh: '企业数据大脑', en: 'Enterprise Data Brain', image: '/images/3.jpg' },
    { zh: '智慧零售系统', en: 'Smart Retail System', image: '/images/4.jpg' },
];

const processMock = [
    { zh: '需求诊断', en: 'Discovery & Analysis' },
    { zh: '方案架构', en: 'Architecture & Design' },
    { zh: '敏捷交付', en: 'Agile Development' },
    { zh: '持续运维', en: 'Operations & Scale' },
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
    { date: '2026.04', title: 'Miwa 发布企业级 AI Agent 开发框架 v2.0' },
    { date: '2026.02', title: '与华为联合打造工业 IoT 边缘计算方案落地' },
    { date: '2025.12', title: '智能客服平台日均调用量突破 5000 万次' },
    { date: '2025.10', title: '获得 ISO 27001 信息安全认证' },
    { date: '2025.08', title: '与蔚来达成战略合作，共建车载 AI 实验室' },
    { date: '2025.07', title: '发布开源 IoT 中间件 MiwaLink v1.0' },
    { date: '2025.05', title: '完成 A 轮融资，加速国际化布局' },
];

const statsMock = [
    { num: '200+', label: '交付项目' },
    { num: '50+', label: '服务企业' },
    { num: '99.9%', label: '系统可用率' },
    { num: '24/7', label: '运维保障' },
];

export function getHomeData() {
    if (MOCK_SWITCH) {
        return Promise.resolve({
            services: servicesMock,
            projects: projectsMock,
            process: processMock,
            clients: clientsMock,
            news: newsMock,
            stats: statsMock,
        });
    }
    return Promise.all([
        request.get("/services").then(r => r.data),
        request.get("/projects").then(r => r.data),
        request.get("/process").then(r => r.data),
        request.get("/clients").then(r => r.data),
        request.get("/news").then(r => r.data),
        request.get("/stats").then(r => r.data),
    ]).then(([services, projects, process, clients, news, stats]) => ({
        services, projects, process, clients, news, stats,
    }));
}
