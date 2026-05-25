<template>
  <div>
    <div class="page-header">
      <h2>概览</h2>
    </div>
    <el-row :gutter="20">
      <el-col :span="8">
        <el-card shadow="hover">
          <template #header>快捷入口</template>
          <el-space direction="vertical">
            <el-button type="primary" @click="$router.push('/news')">管理新闻</el-button>
            <el-button type="primary" @click="$router.push('/projects')">管理项目</el-button>
            <el-button type="primary" @click="$router.push('/home-config')">编辑首页</el-button>
          </el-space>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="hover">
          <template #header>内容管理</template>
          <el-space direction="vertical">
            <el-button @click="$router.push('/services')">管理服务</el-button>
            <el-button @click="$router.push('/cases')">管理案例</el-button>
            <el-button @click="$router.push('/people')">管理成员</el-button>
            <el-button @click="$router.push('/values')">管理价值观</el-button>
          </el-space>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="hover">
          <template #header>站点设置</template>
          <el-space direction="vertical">
            <el-button @click="$router.push('/about-config')">关于页设置</el-button>
            <el-button @click="$router.push('/footer-config')">页脚设置</el-button>
          </el-space>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
