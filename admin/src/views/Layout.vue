<template>
  <div class="admin-layout">
    <aside class="admin-sidebar">
      <div class="logo">Miwa Admin</div>
      <el-menu :default-active="activeMenu" router background-color="#1d1e2c" text-color="#bfcbd9" active-text-color="#409eff">
        <el-menu-item index="/">
          <el-icon><Monitor /></el-icon>
          <span>首页配置</span>
        </el-menu-item>
        <el-menu-item index="/news">
          <el-icon><Document /></el-icon>
          <span>新闻管理</span>
        </el-menu-item>
        <el-menu-item index="/projects">
          <el-icon><Folder /></el-icon>
          <span>项目管理</span>
        </el-menu-item>
        <el-menu-item index="/people">
          <el-icon><User /></el-icon>
          <span>成员管理</span>
        </el-menu-item>
        <el-menu-item index="/services">
          <el-icon><Setting /></el-icon>
          <span>服务管理</span>
        </el-menu-item>
        <el-menu-item index="/cases">
          <el-icon><Star /></el-icon>
          <span>案例管理</span>
        </el-menu-item>
        <el-menu-item index="/values">
          <el-icon><Flag /></el-icon>
          <span>价值观管理</span>
        </el-menu-item>
        <el-menu-item index="/home-config">
          <el-icon><HomeFilled /></el-icon>
          <span>首页设置</span>
        </el-menu-item>
        <el-menu-item index="/about-config">
          <el-icon><InfoFilled /></el-icon>
          <span>关于页设置</span>
        </el-menu-item>
        <el-menu-item index="/footer-config">
          <el-icon><Bottom /></el-icon>
          <span>页脚设置</span>
        </el-menu-item>
      </el-menu>
      <div style="padding:16px;border-top:1px solid rgba(255,255,255,0.1)">
        <el-button text style="color:#bfcbd9;width:100%" @click="logout">
          <el-icon><SwitchButton /></el-icon> 退出登录
        </el-button>
      </div>
    </aside>
    <main class="admin-main">
      <router-view />
    </main>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()
const activeMenu = computed(() => '/' + route.path.split('/').filter(Boolean)[0])

function logout() {
  localStorage.removeItem('admin_token')
  router.push('/login')
}
</script>
