<template>
  <input type="file" ref="inputRef" :accept="accept" style="display:none" @change="handleChange" />
  <el-button size="small" @click="inputRef.click()" :loading="uploading">上传文件</el-button>
</template>

<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import request from '@/api'

const emit = defineEmits(['uploaded'])
const props = defineProps({ accept: { type: String, default: 'image/*' } })
const inputRef = ref(null)
const uploading = ref(false)

async function handleChange(e) {
  const file = e.target.files[0]
  if (!file) return
  uploading.value = true
  try {
    const formData = new FormData()
    formData.append('file', file)
    const result = await request.post('/admin/upload', formData, {
      headers: { 'Content-Type': 'multipart/form-data' },
    })
    emit('uploaded', result.url)
    ElMessage.success('上传成功')
  } catch {
  } finally {
    uploading.value = false
    if (inputRef.value) inputRef.value.value = ''
  }
}
</script>
