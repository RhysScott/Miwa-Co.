import { createRouter, createWebHashHistory } from 'vue-router'

const routes = [
  { path: '/login', name: 'Login', component: () => import('@/views/Login.vue') },
  {
    path: '/',
    component: () => import('@/views/Layout.vue'),
    children: [
      { path: '', name: 'Dashboard', component: () => import('@/views/Dashboard.vue') },
      { path: 'news', name: 'News', component: () => import('@/views/NewsManage.vue') },
      { path: 'projects', name: 'Projects', component: () => import('@/views/ProjectManage.vue') },
      { path: 'people', name: 'People', component: () => import('@/views/PeopleManage.vue') },
      { path: 'services', name: 'Services', component: () => import('@/views/ServiceManage.vue') },
      { path: 'cases', name: 'Cases', component: () => import('@/views/CaseManage.vue') },
      { path: 'values', name: 'Values', component: () => import('@/views/ValueManage.vue') },
      { path: 'home-config', name: 'HomeConfig', component: () => import('@/views/HomeConfig.vue') },
      { path: 'about-config', name: 'AboutConfig', component: () => import('@/views/AboutConfig.vue') },
      { path: 'footer-config', name: 'FooterConfig', component: () => import('@/views/FooterConfig.vue') },
    ],
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('admin_token')
  if (to.name !== 'Login' && !token) {
    next({ name: 'Login' })
  } else if (to.name === 'Login' && token) {
    next({ name: 'Dashboard' })
  } else {
    next()
  }
})

export default router
