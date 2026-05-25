<template>
    <div class="project-detail">
        <el-skeleton :loading="!loaded" animated>
            <template #template>
                <section class="project-hero">
                    <div class="project-hero-text font-error">
                        <el-skeleton-item variant="h1" style="width:280px" />
                        <el-skeleton-item variant="text" style="width:180px;margin-top:0.75rem" />
                    </div>
                </section>
                <section class="project-body">
                    <el-skeleton-item variant="image" style="width:100%;height:300px;border-radius:16px" />
                    <el-skeleton-item variant="text" style="width:100%;height:1.5rem;margin-top:3rem" />
                    <el-skeleton-item variant="text" style="width:100%;margin-top:0.75rem" />
                    <el-skeleton-item variant="text" style="width:80%;margin-top:0.75rem" />
                    <el-skeleton-item variant="text" style="width:90%;margin-top:0.75rem" />
                    <el-skeleton-item variant="text" style="width:60%;margin-top:0.75rem" />
                </section>
            </template>
            <template #default>
                <template v-if="project">
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
                        <div class="detail-content" v-html="htmlContent" />
                    </div>
                </section>
                <section class="project-others" v-if="otherProjects.length">
                    <h3 class="others-title">更多项目</h3>
                    <nav class="others-list">
                        <router-link
                            v-for="p in otherProjects"
                            :key="p.id"
                            :to="`/projects/${p.id}`"
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
            </template>
        </el-skeleton>
        <div v-if="!loaded && !project" class="project-detail not-found">
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
import { marked } from 'marked';
import { markedHighlight } from 'marked-highlight';
import hljs from 'highlight.js';
import { getProjectById, getPageData } from '@/api/pages';
import { getHomeData } from '@/api/home';
import LoadingSpinner from '@/components/shared/LoadingSpinner.vue';
import SiteFooter from '@/components/home/Footer.vue';

marked.use(markedHighlight({
    langPrefix: 'hljs language-',
    highlight(code, lang) {
        if (lang && hljs.getLanguage(lang)) {
            return hljs.highlight(code, { language: lang }).value;
        }
        return hljs.highlightAuto(code).value;
    },
}));

const route = useRoute();
const allProjects = ref([]);
const project = ref(null);
const footer = ref(null);
const loaded = ref(false);

const htmlContent = computed(() => {
    if (!project.value) return '';
    return marked(project.value.content || project.value.detail || '');
});

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
    background: url('/images/3.jpg') center / cover no-repeat;
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

    @media (max-width: 640px) {
        .project-desc {
            font-size: 1.2rem;
            margin-bottom: 1.25rem;
        }
    }
}

// shared markdown content styles
.detail-content {
    :deep(img) { max-width: 100%; height: auto; border-radius: 12px; margin: 1.5rem 0; display: block; }
    :deep(h2) { font-size: 1.5rem; font-weight: bold; margin: 3rem 0 1rem; letter-spacing: -0.01em; }
    :deep(h3) { font-size: 1.2rem; font-weight: bold; margin: 2rem 0 0.75rem; }
    :deep(p) { font-size: 1.05rem; line-height: 1.85; opacity: 0.55; margin: 0 0 1.5rem; }
    :deep(a) { color: #1a1a1a; text-decoration: underline; text-decoration-color: rgba(0,0,0,0.2); text-underline-offset: 0.2em; }
    :deep(ul), :deep(ol) { padding-left: 1.5rem; margin: 0 0 1.5rem; }
    :deep(li) { font-size: 1rem; line-height: 1.75; opacity: 0.5; margin-bottom: 0.4rem; }
    :deep(strong) { color: #1a1a1a; opacity: 0.8; }
    :deep(code) { font-family: 'SF Mono', 'Cascadia Code', 'Fira Code', monospace; font-size: 0.85em; background: rgba(0,0,0,0.05); padding: 0.15em 0.4em; border-radius: 4px; color: #333; }
    :deep(pre) { background: #f6f8fa; border: 1px solid rgba(0,0,0,0.06); border-radius: 12px; padding: 1.25rem 1.5rem; margin: 1.5rem 0; overflow-x: auto; font-size: 0.85rem; line-height: 1.65; }
    :deep(pre code) { background: none; padding: 0; border-radius: 0; font-size: inherit; }
    :deep(table) { width: 100%; border-collapse: collapse; margin: 1.5rem 0; font-size: 0.9rem; }
    :deep(th) { text-align: left; font-weight: 600; opacity: 0.6; padding: 0.6rem 0.75rem; border-bottom: 2px solid rgba(0,0,0,0.08); font-size: 0.8rem; text-transform: uppercase; }
    :deep(td) { padding: 0.6rem 0.75rem; border-bottom: 1px solid rgba(0,0,0,0.04); opacity: 0.55; }
    :deep(hr) { border: none; border-top: 1px solid rgba(0,0,0,0.06); margin: 2.5rem 0; }
    :deep(blockquote) { border-left: 3px solid rgba(0,0,0,0.15); margin: 2rem 0; padding: 0.5rem 0 0.5rem 1.5rem; }
    :deep(blockquote p) { font-size: 1rem; opacity: 0.45; font-style: italic; margin-bottom: 0.5rem; }

    @media (max-width: 640px) {
        :deep(h2) { font-size: 1.25rem; margin: 2rem 0 0.75rem; }
        :deep(p) { font-size: 0.95rem; line-height: 1.75; margin-bottom: 1.25rem; }
        :deep(pre) { padding: 1rem; border-radius: 8px; font-size: 0.8rem; }
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
