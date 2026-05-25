import axios from "axios";
import { ElMessage } from "element-plus";

export const MOCK_SWITCH = true;

export const request = axios.create({
  baseURL: "http://localhost:3000",
  timeout: 5000,
});

request.interceptors.request.use(
  (config) => {
    return config;
  },
  (error) => {
    ElMessage.error("请求发送失败，请检查网络连接");
    return Promise.reject(error);
  }
);

request.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    const msg = error.response?.data?.message || error.message || "服务器异常，请稍后重试";
    ElMessage.error(msg);
    return Promise.reject(error);
  }
);
