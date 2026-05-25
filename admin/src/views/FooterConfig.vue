<template>
  <div>
    <div class="page-header">
      <h2>页脚设置</h2>
      <el-button type="primary" @click="handleSave">保存</el-button>
    </div>
    <el-card>
      <el-form :model="form" label-width="120px">
        <el-divider content-position="left">中文</el-divider>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" />
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.phone" />
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" />
        </el-form-item>

        <el-divider content-position="left">English</el-divider>
        <el-form-item label="Email">
          <el-input v-model="form.en.email" />
        </el-form-item>
        <el-form-item label="Phone">
          <el-input v-model="form.en.phone" />
        </el-form-item>
        <el-form-item label="Address">
          <el-input v-model="form.en.address" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="handleSave">保存</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import request from '@/api'

const form = reactive({
  email: '', phone: '', address: '',
  en: { email: '', phone: '', address: '' },
})

async function fetchConfig() {
  try {
    const data = await request.get('/home')
    if (data.footer) {
      form.email = data.footer.email || ''
      form.phone = data.footer.phone || ''
      form.address = data.footer.address || ''
      if (data.footer.en) Object.assign(form.en, data.footer.en)
    }
  } catch {}
}

async function handleSave() {
  try {
    await request.put('/admin/footer', { ...form })
    ElMessage.success('保存成功')
  } catch {}
}

onMounted(fetchConfig)
</script>
