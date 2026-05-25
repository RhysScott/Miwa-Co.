<template>
    <div class="project-detail">
        <LoadingSpinner v-if="!loaded" />
        <template v-else-if="project">
        <section class="project-hero">
            <div class="project-hero-text font-error">
                <h1>{{ project.zh }}</h1>
                <p class="hero-en">{{ project.en }}</p>
            </div>
        </section>

        <section class="project-body">
            <div class="project-image">
                <img :src="project.image" :alt="project.zh" />
            </div>
            <div class="project-content">
                <p class="project-desc">{{ project.desc }}</p>
                <p class="project-detail">{{ project.detail }}</p>
            </div>
        </section>

        <section class="project-others" v-if="otherProjects.length">
            <h3 class="others-title">更多项目</h3>
            <nav class="others-list">
                <router-link
                    v-for="p in otherProjects"
                    :key="p.id"
                    :to="`/explore/project/${p.id}`"
                    class="others-item"
                    :class="{ active: p.id === project.id }"
                >
                    <span class="others-num">{{ String(p.id).padStart(2, '0') }}</span>
                    <div class="others-text">
                        <span class="others-zh">{{ p.zh }}</span>
                        <span class="others-en">{{ p.en }}</span>
                    </div>
                </router-link>
            </nav>
        </section>

        <SiteFooter v-if="footer" :footer="footer" />
        </template>
        <div v-else class="project-detail not-found">
            <section class="project-hero">
                <div class="project-hero-text font-error">
                    <h1>项目不存在</h1>
                </div>
            </section>
        </div>
    </div>
</template>

<script setup>
import { computed, onMounted, ref, watch } from 'vue';
import { useRoute } from 'vue-router';
import { getProjectById, getPageData } from '@/api/pages';
import { getHomeData } from '@/api/home';
import LoadingSpinner from '@/components/shared/LoadingSpinner.vue';
import SiteFooter from '@/components/home/Footer.vue';

const route = useRoute();
const allProjects = ref([]);
const project = ref(null);
const footer = ref(null);
const loaded = ref(false);

const otherProjects = computed(() => {
    return allProjects.value.filter(p => p.id !== project.value?.id);
});

async function loadProject(id) {
    loaded.value = false;
    project.value = await getProjectById(id);
    loaded.value = true;
    if (project.value) {
        document.title = `${project.value.zh} - Miwa & Co.`;
    }
}

onMounted(async () => {
    const [exploreData, home] = await Promise.all([
        getPageData('explore'),
        getHomeData(),
    ]);
    allProjects.value = exploreData.projects;
    footer.value = home.footer;
    await loadProject(route.params.id);
});

watch(() => route.params.id, (newId) => {
    loadProject(newId);
});
</script>

<style lang="scss" scoped>
.project-detail {
    color: #1a1a1a;
    background: #f8f8f8;

    &.not-found {
        min-height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
    }
}

.project-hero {
    min-height: 35vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: center;
    padding: 6rem 2rem 3rem;
    background: url('/images/5.jpg') center / cover no-repeat;
    position: relative;

    &::before {
        content: '';
        position: absolute;
        inset: 0;
        background: rgba(0, 0, 0, 0.25);
    }

    .project-hero-text {
        position: relative;
        z-index: 1;
        color: rgba(255, 255, 255, 0.95);
        max-width: 700px;

        h1 {
            font-size: clamp(2rem, 5vw, 3.5rem);
            margin: 0;
            letter-spacing: -0.02em;
            line-height: 1.25;
        }

        .hero-en {
            font-size: 1rem;
            opacity: 0.5;
            margin-top: 0.75rem;
        }
    }
}

.project-body {
    max-width: 800px;
    margin: 0 auto;
    padding: 4rem 3rem;

    @media (max-width: 640px) {
        padding: 2.5rem 1.25rem;
    }
}

.project-image {
    margin-bottom: 3rem;

    img {
        width: 100%;
        border-radius: 16px;
        display: block;
    }

    @media (max-width: 640px) {
        margin-bottom: 1.5rem;

        img { border-radius: 12px; }
    }
}

.project-content {
    .project-desc {
        font-size: 1.5rem;
        font-weight: bold;
        line-height: 1.5;
        letter-spacing: -0.01em;
        margin: 0 0 2rem;
    }

    .project-detail {
        font-size: 1.05rem;
        line-height: 1.85;
        opacity: 0.55;
        margin: 0;
    }

    @media (max-width: 640px) {
        .project-desc {
            font-size: 1.2rem;
            margin-bottom: 1.25rem;
        }

        .project-detail {
            font-size: 0.95rem;
            line-height: 1.75;
        }
    }
}

// ---------- others ----------
.project-others {
    max-width: 800px;
    margin: 0 auto;
    padding: 3rem 3rem 5rem;
    border-top: 1px solid rgba(0, 0, 0, 0.05);

    @media (max-width: 640px) {
        padding: 2.5rem 1.25rem;
    }
}

.others-title {
    font-size: 0.75rem;
    opacity: 0.3;
    letter-spacing: 0.06em;
    text-transform: uppercase;
    margin: 0 0 1.5rem;
}

.others-list {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 0.75rem;

    @media (max-width: 640px) {
        grid-template-columns: 1fr;
    }
}

.others-item {
    display: flex;
    align-items: center;
    gap: 1rem;
    padding: 1rem 1.25rem;
    border: 1px solid rgba(0, 0, 0, 0.06);
    border-radius: 16px;
    color: #1a1a1a;
    text-decoration: none;
    transition: border-color 0.3s ease, transform 0.3s ease;

    &:hover {
        border-color: rgba(0, 0, 0, 0.15);
        transform: translateX(4px);
    }

    &.active {
        border-color: rgba(0, 0, 0, 0.2);
        background: rgba(0, 0, 0, 0.02);

        .others-num {
            opacity: 0.25;
        }
    }
}

.others-num {
    font-size: 1.5rem;
    font-weight: bold;
    opacity: 0.1;
    flex-shrink: 0;
}

.others-text {
    display: flex;
    flex-direction: column;
    gap: 0.15rem;

    .others-zh {
        font-size: 1rem;
        font-weight: bold;
        letter-spacing: -0.01em;
    }

    .others-en {
        font-size: 0.7rem;
        opacity: 0.28;
        letter-spacing: 0.04em;
    }
}
</style>
