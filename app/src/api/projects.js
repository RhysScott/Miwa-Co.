import { request, MOCK_SWITCH } from "@/common/request";

const mock = [
    { zh: '智能客服平台', en: 'AI Customer Service', image: '/images/1.jpg' },
    { zh: '工业 IoT 中台', en: 'Industrial IoT Platform', image: '/images/2.jpg' },
    { zh: '企业数据大脑', en: 'Enterprise Data Brain', image: '/images/3.jpg' },
    { zh: '智慧零售系统', en: 'Smart Retail System', image: '/images/4.jpg' },
];

export function getProjects() {
    if (MOCK_SWITCH) return Promise.resolve(mock);
    return request.get("/projects").then(r => r.data);
}
