import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        { path: '/', name: 'home', component: HomeView },
        { path: '/news', name: 'news', component: () => import('../views/NewsView.vue') },
        { path: '/contact', name: 'contact', component: () => import('../views/ContactView.vue') },
        { path: '/explore', name: 'explore', component: () => import('../views/ExploreView.vue') },
        { path: '/about', name: 'about', component: () => import('../views/AboutView.vue') },
    ],
    scrollBehavior() {
        return { top: 0 };
    },
});

export default router;
