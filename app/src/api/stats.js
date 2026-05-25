import { request, MOCK_SWITCH } from "./request";

const mock = [
    { num: '200+', label: '交付项目' },
    { num: '50+', label: '服务企业' },
    { num: '99.9%', label: '系统可用率' },
    { num: '24/7', label: '运维保障' },
];

export function getStats() {
    if (MOCK_SWITCH) return Promise.resolve(mock);
    return request.get("/stats").then(r => r.data);
}
