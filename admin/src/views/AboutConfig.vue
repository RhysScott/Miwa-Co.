<template>
  <div>
    <div class="page-header">
      <h2>关于页设置</h2>
    </div>

    <!-- Chinese paragraphs -->
    <el-card style="margin-bottom:20px">
      <template #header>
        <div style="display:flex;justify-content:space-between;align-items:center">
          <strong>中文段落</strong>
          <el-button size="small" type="primary" @click="addParagraph('zh')">新增段落</el-button>
        </div>
      </template>
      <div v-loading="loading">
        <div v-if="zhParagraphs.length === 0" style="color:#909399;padding:20px;text-align:center">暂无段落，点击上方按钮新增</div>
        <div v-for="(p, i) in zhParagraphs" :key="p.id" style="display:flex;gap:12px;margin-bottom:12px;align-items:flex-start">
          <el-input v-model="p.content" type="textarea" :rows="3" style="flex:1" />
          <el-button size="small" @click="saveParagraph(p)">保存</el-button>
          <el-button size="small" type="danger" @click="delParagraph(p.id)">删除</el-button>
        </div>
      </div>
    </el-card>

    <!-- English paragraphs -->
    <el-card style="margin-bottom:20px">
      <template #header>
        <div style="display:flex;justify-content:space-between;align-items:center">
          <strong>英文段落</strong>
          <el-button size="small" type="primary" @click="addParagraph('en')">新增段落</el-button>
        </div>
      </template>
      <div v-loading="loading">
        <div v-if="enParagraphs.length === 0" style="color:#909399;padding:20px;text-align:center">暂无段落，点击上方按钮新增</div>
        <div v-for="p in enParagraphs" :key="p.id" style="display:flex;gap:12px;margin-bottom:12px;align-items:flex-start">
          <el-input v-model="p.content" type="textarea" :rows="3" style="flex:1" />
          <el-button size="small" @click="saveParagraph(p)">保存</el-button>
          <el-button size="small" type="danger" @click="delParagraph(p.id)">删除</el-button>
        </div>
      </div>
    </el-card>

    <!-- Values link -->
    <el-card>
      <template #header><strong>价值观</strong></template>
      <p style="color:#909399">价值观在独立页面管理。</p>
      <el-button @click="$router.push('/values')">前往价值观管理</el-button>
    </el-card>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import request from '@/api'

const loading = ref(false)
const paragraphs = ref([])

const zhParagraphs = computed(() =>
  paragraphs.value.filter(p => p.lang === 'zh').sort((a, b) => a.sortOrder - b.sortOrder)
)
const enParagraphs = computed(() =>
  paragraphs.value.filter(p => p.lang === 'en').sort((a, b) => a.sortOrder - b.sortOrder)
)

async function fetchParagraphs() {
  loading.value = true
  try { paragraphs.value = await request.get('/admin/paragraphs') } catch {}
  loading.value = false
}

async function addParagraph(lang) {
  const sortOrder = paragraphs.value.filter(p => p.lang === lang).length
  try {
    await request.post('/admin/paragraphs', { lang, content: '', sortOrder })
    ElMessage.success('已新增段落')
    fetchParagraphs()
  } catch {}
}

async function saveParagraph(p) {
  try {
    await request.put(`/admin/paragraphs/${p.id}`, { lang: p.lang, content: p.content, sortOrder: p.sortOrder })
    ElMessage.success('保存成功')
  } catch {}
}

async function delParagraph(id) {
  try {
    await ElMessageBox.confirm('确定删除？', '提示', { type: 'warning' })
    await request.delete(`/admin/paragraphs/${id}`)
    ElMessage.success('删除成功')
    fetchParagraphs()
  } catch {}
}

onMounted(fetchParagraphs)
</script>
