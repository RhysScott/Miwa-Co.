import axios from 'axios';
import { ElMessage } from 'element-plus';

export const request = axios.create({
    baseURL: '/api',
    timeout: 15000
});

request.interceptors.response.use(
    (res) => {
        const { code, message, data } = res.data;
        if (code === 200) return data;
        ElMessage.error(message || '请求失败');
        return Promise.reject(new Error(message));
    },
    (error) => {
        const msg = error.response?.data?.message || error.message || '服务器异常，请稍后重试';
        ElMessage.error(msg);
        return Promise.reject(error);
    }
);
