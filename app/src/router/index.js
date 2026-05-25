import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        { path: '/', name: 'home', component: HomeView },
        { path: '/news', name: 'news', component: () => import('../views/news/NewsView.vue') },
        { path: '/news/:id', name: 'news-detail', component: () => import('../views/news/NewsDetailView.vue') },
        { path: '/contact', name: 'contact', component: () => import('../views/contact/ContactView.vue') },
        { path: '/explore', name: 'explore', component: () => import('../views/explore/ExploreView.vue') },
        { path: '/explore/project/:id', name: 'project-detail', component: () => import('../views/explore/ProjectDetailView.vue') },
        { path: '/people', name: 'people', component: () => import('../views/people/PeopleView.vue') },
        { path: '/people/:id', name: 'people-detail', component: () => import('../views/people/PersonDetailView.vue') },
        { path: '/about', name: 'about', component: () => import('../views/about/AboutView.vue') },
        { path: '/:pathMatch(.*)*', name: 'not-found', component: () => import('../views/NotFoundView.vue') },
    ],
    scrollBehavior() {
        return { top: 0, behavior: 'smooth' };
    },
});

export default router;
