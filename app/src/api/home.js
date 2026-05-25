import { request } from "@/common/request";

export function getHomeData() {
    return Promise.all([
        request.get("/home"),
        request.get("/news", { params: { page: 1, pageSize: 6 } }),
    ]).then(([home, news]) => ({
        ...home,
        news: news.records || [],
    }));
}
