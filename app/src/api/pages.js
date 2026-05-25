import { request } from "@/common/request";

export function getPageData(page) {
    switch (page) {
        case 'news':
            return request.get("/news", { params: { page: 1, pageSize: 50 } }).then(d => d.records || []);
        case 'contact':
            return request.get("/home").then(d => d.footer || null);
        case 'explore':
            return request.get("/explore");
        case 'about':
            return request.get("/about");
        case 'people':
            return request.get("/people");
        case 'projects':
            return request.get("/projects");
        default:
            return Promise.resolve(null);
    }
}

export function getNewsById(id) {
    return request.get(`/news/${id}`);
}

export function getProjectById(id) {
    return request.get(`/projects/${id}`);
}

export function getPersonById(id) {
    return request.get(`/people/${id}`);
}
