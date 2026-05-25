<template>
  <div>
    <div class="page-header">
      <h2>首页设置</h2>
    </div>

    <!-- Hero -->
    <el-card style="margin-bottom:20px">
      <template #header><strong>Hero 主视觉</strong></template>
      <el-form :model="hero" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="中文标题">
              <el-input v-model="hero.zh" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="英文标题">
              <el-input v-model="hero.en" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="副标题">
          <el-input v-model="hero.sub" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveHero" :loading="heroSaving">保存 Hero</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- Philosophy -->
    <el-card style="margin-bottom:20px">
      <template #header><strong>品牌理念</strong></template>
      <el-form :model="philosophy" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="中文">
              <el-input v-model="philosophy.zh" type="textarea" :rows="3" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="英文">
              <el-input v-model="philosophy.en" type="textarea" :rows="3" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="副标题">
          <el-input v-model="philosophy.sub" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="savePhilosophy" :loading="philSaving">保存理念</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- Marquee -->
    <el-card style="margin-bottom:20px">
      <template #header>
        <div style="display:flex;justify-content:space-between;align-items:center">
          <strong>Marquee 滚动文字</strong>
          <el-button size="small" type="primary" @click="openMarqueeDialog()">新增</el-button>
        </div>
      </template>
      <el-table :data="marquees" border stripe v-loading="marqueeLoading">
        <el-table-column prop="zh" label="中文" />
        <el-table-column prop="en" label="英文" />
        <el-table-column prop="sortOrder" label="排序" width="80" />
        <el-table-column label="操作" width="160">
          <template #default="{ row }">
            <el-button size="small" @click="openMarqueeDialog(row)">编辑</el-button>
            <el-button size="small" type="danger" @click="delMarquee(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- Stats -->
    <el-card style="margin-bottom:20px">
      <template #header>
        <div style="display:flex;justify-content:space-between;align-items:center">
          <strong>统计数据</strong>
          <el-button size="small" type="primary" @click="openStatDialog()">新增</el-button>
        </div>
      </template>
      <el-table :data="stats" border stripe v-loading="statLoading">
        <el-table-column prop="num" label="数值" width="140" />
        <el-table-column prop="zh" label="中文标签" />
        <el-table-column prop="en" label="英文标签" />
        <el-table-column prop="sortOrder" label="排序" width="80" />
        <el-table-column label="操作" width="160">
          <template #default="{ row }">
            <el-button size="small" @click="openStatDialog(row)">编辑</el-button>
            <el-button size="small" type="danger" @click="delStat(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- Process -->
    <el-card style="margin-bottom:20px">
      <template #header>
        <div style="display:flex;justify-content:space-between;align-items:center">
          <strong>工作流程</strong>
          <el-button size="small" type="primary" @click="openProcessDialog()">新增</el-button>
        </div>
      </template>
      <el-table :data="processes" border stripe v-loading="processLoading">
        <el-table-column prop="zh" label="中文标题" />
        <el-table-column prop="en" label="英文标题" />
        <el-table-column prop="descCn" label="描述" />
        <el-table-column prop="sortOrder" label="排序" width="80" />
        <el-table-column label="操作" width="160">
          <template #default="{ row }">
            <el-button size="small" @click="openProcessDialog(row)">编辑</el-button>
            <el-button size="small" type="danger" @click="delProcess(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- Clients -->
    <el-card style="margin-bottom:20px">
      <template #header>
        <div style="display:flex;justify-content:space-between;align-items:center">
          <strong>合作客户</strong>
          <el-button size="small" type="primary" @click="openClientDialog()">新增</el-button>
        </div>
      </template>
      <el-table :data="clients" border stripe v-loading="clientLoading">
        <el-table-column prop="name" label="名称" />
        <el-table-column prop="zh" label="中文描述" />
        <el-table-column prop="sortOrder" label="排序" width="80" />
        <el-table-column label="操作" width="160">
          <template #default="{ row }">
            <el-button size="small" @click="openClientDialog(row)">编辑</el-button>
            <el-button size="small" type="danger" @click="delClient(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- Dialogs -->
    <el-dialog v-model="marqueeDialog" :title="marqueeEditId ? '编辑滚动文字' : '新增滚动文字'" width="500px">
      <el-form :model="marqueeForm" label-width="80px">
        <el-form-item label="中文"><el-input v-model="marqueeForm.zh" /></el-form-item>
        <el-form-item label="英文"><el-input v-model="marqueeForm.en" /></el-form-item>
        <el-form-item label="排序"><el-input-number v-model="marqueeForm.sortOrder" :min="0" /></el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="marqueeDialog = false">取消</el-button>
        <el-button type="primary" @click="saveMarquee">保存</el-button>
      </template>
    </el-dialog>

    <el-dialog v-model="statDialog" :title="statEditId ? '编辑统计' : '新增统计'" width="500px">
      <el-form :model="statForm" label-width="80px">
        <el-form-item label="数值"><el-input v-model="statForm.num" /></el-form-item>
        <el-form-item label="中文"><el-input v-model="statForm.zh" /></el-form-item>
        <el-form-item label="英文"><el-input v-model="statForm.en" /></el-form-item>
        <el-form-item label="排序"><el-input-number v-model="statForm.sortOrder" :min="0" /></el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="statDialog = false">取消</el-button>
        <el-button type="primary" @click="saveStat">保存</el-button>
      </template>
    </el-dialog>

    <el-dialog v-model="processDialog" :title="processEditId ? '编辑流程' : '新增流程'" width="500px">
      <el-form :model="processForm" label-width="80px">
        <el-form-item label="中文"><el-input v-model="processForm.zh" /></el-form-item>
        <el-form-item label="英文"><el-input v-model="processForm.en" /></el-form-item>
        <el-form-item label="描述"><el-input v-model="processForm.descCn" type="textarea" /></el-form-item>
        <el-form-item label="排序"><el-input-number v-model="processForm.sortOrder" :min="0" /></el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="processDialog = false">取消</el-button>
        <el-button type="primary" @click="saveProcess">保存</el-button>
      </template>
    </el-dialog>

    <el-dialog v-model="clientDialog" :title="clientEditId ? '编辑客户' : '新增客户'" width="500px">
      <el-form :model="clientForm" label-width="80px">
        <el-form-item label="名称"><el-input v-model="clientForm.name" /></el-form-item>
        <el-form-item label="中文"><el-input v-model="clientForm.zh" /></el-form-item>
        <el-form-item label="排序"><el-input-number v-model="clientForm.sortOrder" :min="0" /></el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="clientDialog = false">取消</el-button>
        <el-button type="primary" @click="saveClient">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import request from '@/api'

// ── Hero ──
const hero = reactive({ zh: '', en: '', sub: '' })
const heroSaving = ref(false)

async function fetchHero() {
  try {
    const data = await request.get('/admin/hero')
    if (data) Object.assign(hero, data)
  } catch {}
}

async function saveHero() {
  heroSaving.value = true
  try {
    await request.put('/admin/hero', { ...hero })
    ElMessage.success('Hero 保存成功')
  } catch {}
  heroSaving.value = false
}

// ── Philosophy ──
const philosophy = reactive({ zh: '', en: '', sub: '' })
const philSaving = ref(false)

async function fetchPhilosophy() {
  try {
    const data = await request.get('/admin/philosophy')
    if (data) Object.assign(philosophy, data)
  } catch {}
}

async function savePhilosophy() {
  philSaving.value = true
  try {
    await request.put('/admin/philosophy', { ...philosophy })
    ElMessage.success('理念保存成功')
  } catch {}
  philSaving.value = false
}

// ── Marquee ──
const marquees = ref([])
const marqueeLoading = ref(false)
const marqueeDialog = ref(false)
const marqueeEditId = ref(null)
const marqueeForm = reactive({ zh: '', en: '', sortOrder: 0 })

async function fetchMarquees() {
  marqueeLoading.value = true
  try { marquees.value = await request.get('/admin/marquee') } catch {}
  marqueeLoading.value = false
}

function openMarqueeDialog(row) {
  if (row) {
    marqueeEditId.value = row.id
    Object.assign(marqueeForm, row)
  } else {
    marqueeEditId.value = null
    Object.assign(marqueeForm, { zh: '', en: '', sortOrder: 0 })
  }
  marqueeDialog.value = true
}

async function saveMarquee() {
  try {
    if (marqueeEditId.value) {
      await request.put(`/admin/marquee/${marqueeEditId.value}`, { ...marqueeForm })
    } else {
      await request.post('/admin/marquee', { ...marqueeForm })
    }
    ElMessage.success('保存成功')
    marqueeDialog.value = false
    fetchMarquees()
  } catch {}
}

async function delMarquee(id) {
  try {
    await ElMessageBox.confirm('确定删除？', '提示', { type: 'warning' })
    await request.delete(`/admin/marquee/${id}`)
    ElMessage.success('删除成功')
    fetchMarquees()
  } catch {}
}

// ── Stats ──
const stats = ref([])
const statLoading = ref(false)
const statDialog = ref(false)
const statEditId = ref(null)
const statForm = reactive({ num: '', zh: '', en: '', sortOrder: 0 })

async function fetchStats() {
  statLoading.value = true
  try { stats.value = await request.get('/admin/stats') } catch {}
  statLoading.value = false
}

function openStatDialog(row) {
  if (row) {
    statEditId.value = row.id
    Object.assign(statForm, row)
  } else {
    statEditId.value = null
    Object.assign(statForm, { num: '', zh: '', en: '', sortOrder: 0 })
  }
  statDialog.value = true
}

async function saveStat() {
  try {
    if (statEditId.value) {
      await request.put(`/admin/stats/${statEditId.value}`, { ...statForm })
    } else {
      await request.post('/admin/stats', { ...statForm })
    }
    ElMessage.success('保存成功')
    statDialog.value = false
    fetchStats()
  } catch {}
}

async function delStat(id) {
  try {
    await ElMessageBox.confirm('确定删除？', '提示', { type: 'warning' })
    await request.delete(`/admin/stats/${id}`)
    ElMessage.success('删除成功')
    fetchStats()
  } catch {}
}

// ── Process ──
const processes = ref([])
const processLoading = ref(false)
const processDialog = ref(false)
const processEditId = ref(null)
const processForm = reactive({ zh: '', en: '', descCn: '', sortOrder: 0 })

async function fetchProcesses() {
  processLoading.value = true
  try { processes.value = await request.get('/admin/process') } catch {}
  processLoading.value = false
}

function openProcessDialog(row) {
  if (row) {
    processEditId.value = row.id
    Object.assign(processForm, row)
  } else {
    processEditId.value = null
    Object.assign(processForm, { zh: '', en: '', descCn: '', sortOrder: 0 })
  }
  processDialog.value = true
}

async function saveProcess() {
  try {
    if (processEditId.value) {
      await request.put(`/admin/process/${processEditId.value}`, { ...processForm })
    } else {
      await request.post('/admin/process', { ...processForm })
    }
    ElMessage.success('保存成功')
    processDialog.value = false
    fetchProcesses()
  } catch {}
}

async function delProcess(id) {
  try {
    await ElMessageBox.confirm('确定删除？', '提示', { type: 'warning' })
    await request.delete(`/admin/process/${id}`)
    ElMessage.success('删除成功')
    fetchProcesses()
  } catch {}
}

// ── Clients ──
const clients = ref([])
const clientLoading = ref(false)
const clientDialog = ref(false)
const clientEditId = ref(null)
const clientForm = reactive({ name: '', zh: '', sortOrder: 0 })

async function fetchClients() {
  clientLoading.value = true
  try { clients.value = await request.get('/admin/clients') } catch {}
  clientLoading.value = false
}

function openClientDialog(row) {
  if (row) {
    clientEditId.value = row.id
    Object.assign(clientForm, row)
  } else {
    clientEditId.value = null
    Object.assign(clientForm, { name: '', zh: '', sortOrder: 0 })
  }
  clientDialog.value = true
}

async function saveClient() {
  try {
    if (clientEditId.value) {
      await request.put(`/admin/clients/${clientEditId.value}`, { ...clientForm })
    } else {
      await request.post('/admin/clients', { ...clientForm })
    }
    ElMessage.success('保存成功')
    clientDialog.value = false
    fetchClients()
  } catch {}
}

async function delClient(id) {
  try {
    await ElMessageBox.confirm('确定删除？', '提示', { type: 'warning' })
    await request.delete(`/admin/clients/${id}`)
    ElMessage.success('删除成功')
    fetchClients()
  } catch {}
}

onMounted(() => {
  fetchHero()
  fetchPhilosophy()
  fetchMarquees()
  fetchStats()
  fetchProcesses()
  fetchClients()
})
</script>
