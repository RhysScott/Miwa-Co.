<template>
  <div>
    <div class="page-header">
      <h2>关于页设置</h2>
      <el-button type="primary" @click="handleSave">保存</el-button>
    </div>
    <el-card>
      <el-form label-width="100px">
        <el-divider content-position="left">中文段落</el-divider>
        <el-form-item>
          <el-input v-model="zhJson" type="textarea" :rows="6" placeholder='["段落1","段落2"]' />
        </el-form-item>

        <el-divider content-position="left">英文段落</el-divider>
        <el-form-item>
          <el-input v-model="enJson" type="textarea" :rows="6" placeholder='["Paragraph 1","Paragraph 2"]' />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="handleSave">保存</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import request from '@/api'

const zhJson = ref('[]')
const enJson = ref('[]')

async function fetchConfig() {
  try {
    const data = await request.get('/about')
    if (data.zh) zhJson.value = JSON.stringify(data.zh, null, 2)
    if (data.en) enJson.value = JSON.stringify(data.en, null, 2)
  } catch {}
}

async function handleSave() {
  try {
    await request.put('/admin/about', {
      zh: JSON.parse(zhJson.value),
      en: JSON.parse(enJson.value),
    })
    ElMessage.success('保存成功')
  } catch {}
}

onMounted(fetchConfig)
</script>
