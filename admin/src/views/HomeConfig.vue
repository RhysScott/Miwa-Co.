<template>
  <div>
    <div class="page-header">
      <h2>首页设置</h2>
      <el-button type="primary" @click="handleSave">保存首页配置</el-button>
    </div>
    <el-card>
      <el-form :model="form" label-width="100px">
        <el-divider content-position="left">Hero</el-divider>
        <el-form-item label="中文标题">
          <el-input v-model="form.hero.zh" />
        </el-form-item>
        <el-form-item label="英文标题">
          <el-input v-model="form.hero.en" />
        </el-form-item>
        <el-form-item label="副标题">
          <el-input v-model="form.hero.sub" type="textarea" :rows="2" />
        </el-form-item>

        <el-divider content-position="left">理念</el-divider>
        <el-form-item label="中文">
          <el-input v-model="form.philosophy.zh" type="textarea" />
        </el-form-item>
        <el-form-item label="英文">
          <el-input v-model="form.philosophy.en" type="textarea" />
        </el-form-item>
        <el-form-item label="副标题">
          <el-input v-model="form.philosophy.sub" type="textarea" />
        </el-form-item>

        <el-divider content-position="left">关于简介</el-divider>
        <el-form-item label="中文">
          <el-input v-model="form.about.zh" type="textarea" :rows="3" />
        </el-form-item>
        <el-form-item label="英文">
          <el-input v-model="form.about.en" type="textarea" :rows="3" />
        </el-form-item>

        <el-divider content-position="left">Marquee 滚动文字</el-divider>
        <el-form-item label="条目 (JSON)">
          <el-input v-model="marqueeJson" type="textarea" :rows="4" placeholder='[{"zh":"人工智能","en":"AI Solutions"}]' />
        </el-form-item>

        <el-divider content-position="left">统计数据</el-divider>
        <el-form-item label="数据 (JSON)">
          <el-input v-model="statsJson" type="textarea" :rows="4" placeholder='[{"num":"5000万+","label":"日均 API 调用"}]' />
        </el-form-item>

        <el-divider content-position="left">流程</el-divider>
        <el-form-item label="流程 (JSON)">
          <el-input v-model="processJson" type="textarea" :rows="4" placeholder='[{"zh":"需求分析","en":"Discovery","desc":"..."}]' />
        </el-form-item>

        <el-divider content-position="left">客户</el-divider>
        <el-form-item label="客户 (JSON)">
          <el-input v-model="clientsJson" type="textarea" :rows="4" placeholder='[{"name":"蔚来","en":"NIO","logo":"..."}]' />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="handleSave">保存</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { reactive, ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import request from '@/api'

const form = reactive({
  hero: { zh: '', en: '', sub: '' },
  philosophy: { zh: '', en: '', sub: '' },
  about: { zh: '', en: '' },
})
const marqueeJson = ref('[]')
const statsJson = ref('[]')
const processJson = ref('[]')
const clientsJson = ref('[]')

async function fetchConfig() {
  try {
    const data = await request.get('/home')
    if (data.hero) form.hero = data.hero
    if (data.philosophy) form.philosophy = data.philosophy
    if (data.about) form.about = data.about
    if (data.marquee) marqueeJson.value = JSON.stringify(data.marquee, null, 2)
    if (data.stats) statsJson.value = JSON.stringify(data.stats, null, 2)
    if (data.process) processJson.value = JSON.stringify(data.process, null, 2)
    if (data.clients) clientsJson.value = JSON.stringify(data.clients, null, 2)
  } catch {}
}

async function handleSave() {
  try {
    const payload = {
      hero: form.hero,
      philosophy: form.philosophy,
      about: form.about,
      marquee: JSON.parse(marqueeJson.value),
      stats: JSON.parse(statsJson.value),
      process: JSON.parse(processJson.value),
      clients: JSON.parse(clientsJson.value),
    }
    await request.put('/admin/home', payload)
    ElMessage.success('保存成功')
  } catch {}
}

onMounted(fetchConfig)
</script>
