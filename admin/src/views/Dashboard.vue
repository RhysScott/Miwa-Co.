<template>
  <div>
    <div class="page-header">
      <h2>仪表盘</h2>
    </div>
    <el-row :gutter="20" v-loading="loading">
      <el-col :span="8" v-for="card in cards" :key="card.label" style="margin-bottom:20px">
        <el-card shadow="hover">
          <div style="display:flex;justify-content:space-between;align-items:center">
            <div>
              <p style="margin:0;font-size:0.85rem;color:#909399">{{ card.label }}</p>
              <p style="margin:8px 0 0;font-size:2rem;font-weight:bold">{{ card.value }}</p>
            </div>
            <el-icon :size="36" color="#dcdfe6"><component :is="card.icon" /></el-icon>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-card shadow="hover" style="margin-top:10px">
      <template #header>快捷操作</template>
      <el-space>
        <el-button type="primary" @click="$router.push('/news')">新建新闻</el-button>
        <el-button type="primary" @click="$router.push('/projects')">管理项目</el-button>
        <el-button @click="$router.push('/home-config')">编辑首页</el-button>
        <el-button @click="$router.push('/services')">管理服务</el-button>
      </el-space>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { Notebook, Folder, User, Setting, Star, Flag } from '@element-plus/icons-vue'
import request from '@/api'

const loading = ref(false)
const cards = ref([
  { label: '新闻', value: 0, icon: Notebook },
  { label: '项目', value: 0, icon: Folder },
  { label: '成员', value: 0, icon: User },
  { label: '服务', value: 0, icon: Setting },
  { label: '案例', value: 0, icon: Star },
  { label: '价值观', value: 0, icon: Flag },
])

onMounted(async () => {
  loading.value = true
  try {
    const data = await request.get('/admin/dashboard')
    cards.value[0].value = data.news
    cards.value[1].value = data.projects
    cards.value[2].value = data.people
    cards.value[3].value = data.services
    cards.value[4].value = data.cases
    cards.value[5].value = data.values
  } catch {}
  loading.value = false
})
</script>
