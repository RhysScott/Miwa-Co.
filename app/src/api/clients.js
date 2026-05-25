import { request, MOCK_SWITCH } from "@/common/request";

const mock = [
    { name: '蔚来', en: 'NIO' },
    { name: '比亚迪', en: 'BYD' },
    { name: '华为', en: 'HUAWEI' },
    { name: '招商银行', en: 'CMB' },
    { name: '顺丰', en: 'SF Express' },
    { name: '万科', en: 'VANKE' },
    { name: '字节跳动', en: 'ByteDance' },
    { name: '美的', en: 'MIDEA' },
];

export function getClients() {
    if (MOCK_SWITCH) return Promise.resolve(mock);
    return request.get("/clients").then(r => r.data);
}
