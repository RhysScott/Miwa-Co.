import { request, MOCK_SWITCH } from "./request";

const mock = [
    { zh: '需求诊断', en: 'Discovery & Analysis' },
    { zh: '方案架构', en: 'Architecture & Design' },
    { zh: '敏捷交付', en: 'Agile Development' },
    { zh: '持续运维', en: 'Operations & Scale' },
];

export function getProcess() {
    if (MOCK_SWITCH) return Promise.resolve(mock);
    return request.get("/process").then(r => r.data);
}
