import axios from 'axios'
import { ElMessage } from 'element-plus'

const request = axios.create({
  baseURL: '/api',
  timeout: 15000,
})

request.interceptors.request.use((config) => {
  const token = localStorage.getItem('admin_token')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

request.interceptors.response.use(
  (res) => {
    const { code, message, data } = res.data
    if (code === 200) return data
    if (code === 401) {
      localStorage.removeItem('admin_token')
      window.location.hash = '#/login'
    }
    ElMessage.error(message || '请求失败')
    return Promise.reject(new Error(message))
  },
  (error) => {
    const msg = error.response?.data?.message || '服务器异常'
    ElMessage.error(msg)
    return Promise.reject(error)
  }
)

export default request
