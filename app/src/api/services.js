import { request, MOCK_SWITCH } from "./request";

const mock = [
    { icon: 'brain', zh: '人工智能', en: 'AI Solutions', desc: '大模型应用、智能体开发、NLP 与计算机视觉 —— 为企业注入 AI 能力' },
    { icon: 'code', zh: '软件开发', en: 'Software Dev', desc: 'Web / 移动端 / 后端系统全栈开发，从原型到上线的完整交付' },
    { icon: 'cpu', zh: '物联网', en: 'IoT', desc: '设备接入、边缘计算、数据中台 —— 连接物理世界与数字世界' },
    { icon: 'globe', zh: '数字转型', en: 'Digital Transformation', desc: '技术咨询、架构设计、系统集成，助力企业完成数字化升级' },
];

export function getServices() {
    if (MOCK_SWITCH) return Promise.resolve(mock);
    return request.get("/services").then(r => r.data);
}
