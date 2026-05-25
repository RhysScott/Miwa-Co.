import { request, MOCK_SWITCH } from "@/common/request";

const mock = [
    { date: '2026.04', title: 'Miwa 发布企业级 AI Agent 开发框架 v2.0' },
    { date: '2026.02', title: '与华为联合打造工业 IoT 边缘计算方案落地' },
    { date: '2025.12', title: '智能客服平台日均调用量突破 5000 万次' },
];

export function getNewsList() {
    if (MOCK_SWITCH) return Promise.resolve(mock);
    return request.get("/news").then(r => r.data);
}
