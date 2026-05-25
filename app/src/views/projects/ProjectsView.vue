<template>
    <div class="projects-page">
        <el-skeleton :loading="!loaded" animated>
            <template #template>
                <section class="projects-hero">
                    <div class="projects-hero-text font-error">
                        <el-skeleton-item variant="h1" style="width:200px" />
                        <el-skeleton-item variant="text" style="width:120px;margin-top:0.75rem" />
                    </div>
                </section>
                <section class="projects-grid-section">
                    <div class="projects-list">
                        <div v-for="i in 3" :key="i" class="project-card">
                            <div class="project-image">
                                <el-skeleton-item variant="image" style="width:100%;height:240px;border-radius:16px" />
                            </div>
                            <div class="project-body">
                                <el-skeleton-item variant="text" style="width:3rem;height:3rem" />
                                <el-skeleton-item variant="h3" style="width:60%" />
                                <el-skeleton-item variant="text" style="width:40%;margin-top:0.3rem" />
                                <el-skeleton-item variant="text" style="width:90%;margin-top:0.5rem" />
                                <el-skeleton-item variant="text" style="width:70%" />
                            </div>
                        </div>
                    </div>
                </section>
            </template>
            <template #default>
                <template v-if="data">
                <section class="projects-hero">
                    <div class="projects-hero-text font-error">
                        <h1>项目作品</h1>
                        <p class="en">Selected Works</p>
                    </div>
                </section>
                <section class="projects-grid-section">
                    <div v-if="data.length" class="projects-list">
                        <router-link v-for="(p, i) in data" :key="p.id" :to="`/projects/${p.id}`" class="project-card">
                            <div class="project-image">
                                <img :src="p.image" :alt="p.zh" />
                            </div>
                            <div class="project-body">
                                <span class="project-num">{{ String(i + 1).padStart(2, '0') }}</span>
                                <h3>{{ p.zh }}</h3>
                                <p class="project-en">{{ p.en }}</p>
                                <p class="project-desc">{{ p.desc }}</p>
                                <p class="project-detail">{{ p.detail }}</p>
                            </div>
                        </router-link>
                    </div>
                    <div v-else class="empty-state font-error">
                        <p>暂无项目</p>
                        <p class="en">No projects yet</p>
                    </div>
                </section>
                <SiteFooter v-if="footer && (footer.copyright || footer.location)" :footer="footer" />
                </template>
            </template>
        </el-skeleton>
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { getPageData } from '@/api/pages';
import { getHomeData } from '@/api/home';

import SiteFooter from '@/components/home/Footer.vue';

const data = ref(null);
const footer = ref(null);
const loaded = ref(false);

onMounted(async () => {
    const [pageData, home] = await Promise.all([getPageData('projects'), getHomeData()]);
    data.value = pageData;
    footer.value = home.footer;
    loaded.value = true;
});
</script>

<style lang="scss" scoped>
.projects-page {
    color: #1a1a1a;
    background: #f8f8f8;
}

.empty-state {
    text-align: center;
    padding: 4rem 2rem;
    opacity: 0.2;
}

.projects-hero {
    min-height: 35vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: center;
    padding: 6rem 2rem 3rem;
    background: url('/images/3.jpg') center / cover no-repeat;
    position: relative;

    &::before {
        content: '';
        position: absolute;
        inset: 0;
        background: rgba(0, 0, 0, 0.25);
    }

    .projects-hero-text {
        position: relative;
        z-index: 1;
        color: rgba(255, 255, 255, 0.95);

        h1 {
            font-size: clamp(2.5rem, 6vw, 5rem);
            margin: 0;
            letter-spacing: -0.02em;
        }

        .en {
            font-size: 1rem;
            opacity: 0.5;
            margin-top: 0.75rem;
        }
    }
}

.projects-grid-section {
    max-width: 860px;
    margin: 0 auto;
    padding: 4rem 2rem;

    @media (max-width: 640px) {
        padding: 2.5rem 1.25rem;
    }
}

.projects-list {
    display: flex;
    flex-direction: column;
    gap: 3rem;
}

.project-card {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 2.5rem;
    align-items: center;
    color: inherit;
    text-decoration: none;

    &:nth-child(even) {
        .project-image { order: 2; }
        .project-body { order: 1; }
    }

    &:hover .project-image img {
        transform: scale(1.03);
    }
}

.project-image img {
    width: 100%;
    aspect-ratio: 4 / 3;
    object-fit: cover;
    border-radius: 16px;
    display: block;
    transition: transform 0.5s ease;
}

.project-body {
    display: flex;
    flex-direction: column;
    gap: 0.4rem;
}

.project-num {
    font-size: 3rem;
    font-weight: bold;
    opacity: 0.08;
    line-height: 1;
}

.project-body h3 {
    font-size: 1.6rem;
    font-weight: bold;
    margin: 0;
    letter-spacing: -0.01em;
}

.project-en {
    font-size: 0.75rem;
    opacity: 0.25;
    letter-spacing: 0.05em;
    margin: 0;
}

.project-desc {
    font-size: 0.95rem;
    opacity: 0.5;
    line-height: 1.6;
    margin: 0;
}

.project-detail {
    font-size: 0.85rem;
    opacity: 0.35;
    line-height: 1.6;
    margin-top: 0.5rem;
}

@media (max-width: 640px) {
    .project-card,
    .project-card:nth-child(even) {
        grid-template-columns: 1fr;
        gap: 1rem;

        .project-image,
        .project-body {
            order: unset;
        }
    }

    .project-body h3 {
        font-size: 1.3rem;
    }

    .project-num {
        font-size: 2rem;
    }
}
</style>
