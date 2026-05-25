<template>
  <div>
    <div class="page-header">
      <h2>项目管理</h2>
      <el-button type="primary" @click="openDialog()">新增项目</el-button>
    </div>
    <el-table :data="list" border stripe v-loading="loading">
      <el-table-column prop="id" label="ID" width="60" />
      <el-table-column prop="zh" label="中文名" />
      <el-table-column prop="en" label="英文名" />
      <el-table-column prop="sortOrder" label="排序" width="80" />
      <el-table-column label="操作" width="180">
        <template #default="{ row }">
          <el-button size="small" @click="openDialog(row)">编辑</el-button>
          <el-button size="small" type="danger" @click="handleDelete(row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="dialogVisible" :title="isEdit ? '编辑项目' : '新增项目'" width="700px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="中文名">
          <el-input v-model="form.zh" />
        </el-form-item>
        <el-form-item label="英文名">
          <el-input v-model="form.en" />
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="form.desc" type="textarea" :rows="2" />
        </el-form-item>
        <el-form-item label="图片">
          <el-input v-model="form.image" placeholder="OSS URL" />
          <UploadButton @uploaded="url => form.image = url" />
        </el-form-item>
        <el-form-item label="正文">
          <el-input v-model="form.content" type="textarea" :rows="8" placeholder="Markdown 格式" />
        </el-form-item>
        <el-form-item label="排序">
          <el-input-number v-model="form.sortOrder" :min="0" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleSave">保存</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import request from '@/api'
import UploadButton from '@/components/UploadButton.vue'

const list = ref([])
const loading = ref(false)
const dialogVisible = ref(false)
const isEdit = ref(false)
const editId = ref(null)
const form = reactive({ zh: '', en: '', desc: '', image: '', content: '', sortOrder: 0 })

async function fetchData() {
  loading.value = true
  try {
    list.value = await request.get('/projects')
  } finally { loading.value = false }
}

function openDialog(row) {
  if (row) {
    isEdit.value = true
    editId.value = row.id
    Object.assign(form, row)
  } else {
    isEdit.value = false
    editId.value = null
    Object.assign(form, { zh: '', en: '', desc: '', image: '', content: '', sortOrder: 0 })
  }
  dialogVisible.value = true
}

async function handleSave() {
  try {
    if (isEdit.value) {
      await request.put(`/admin/projects/${editId.value}`, form)
    } else {
      await request.post('/admin/projects', form)
    }
    ElMessage.success(isEdit.value ? '更新成功' : '创建成功')
    dialogVisible.value = false
    fetchData()
  } catch {}
}

async function handleDelete(id) {
  try {
    await ElMessageBox.confirm('确定删除？', '提示', { type: 'warning' })
    await request.delete(`/admin/projects/${id}`)
    ElMessage.success('删除成功')
    fetchData()
  } catch {}
}

onMounted(fetchData)
</script>
