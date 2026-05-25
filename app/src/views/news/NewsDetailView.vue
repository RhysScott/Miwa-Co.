<template>
    <div class="detail-page">
        <el-skeleton :loading="!loaded" animated>
            <template #template>
                <section class="detail-hero">
                    <div class="detail-hero-text font-error">
                        <el-skeleton-item variant="h1" style="width:280px" />
                        <el-skeleton-item variant="text" style="width:120px;margin-top:0.75rem" />
                    </div>
                </section>
                <section class="detail-layout">
                    <div class="detail-main">
                        <el-skeleton-item variant="image" style="width:100%;height:300px;border-radius:16px" />
                        <el-skeleton-item variant="text" style="width:100%;height:1.5rem;margin-top:2rem" />
                        <el-skeleton-item variant="text" style="width:100%;margin-top:1rem" />
                        <el-skeleton-item variant="text" style="width:80%;margin-top:0.5rem" />
                        <el-skeleton-item variant="text" style="width:90%;margin-top:0.5rem" />
                        <el-skeleton-item variant="text" style="width:60%;margin-top:0.5rem" />
                    </div>
                    <aside class="detail-sidebar">
                        <el-skeleton-item variant="text" style="width:60px;margin-bottom:1.25rem" />
                        <el-skeleton-item variant="text" style="width:100%;margin-bottom:0.7rem" v-for="i in 4" :key="i" />
                    </aside>
                </section>
            </template>
            <template #default>
                <template v-if="article">
                <section class="detail-hero">
                    <div class="detail-hero-text font-error">
                        <h1>{{ article.title }}</h1>
                        <p class="detail-date">{{ article.date }}</p>
                    </div>
                </section>
                <section class="detail-layout">
                    <div class="detail-main">
                        <div class="detail-image" v-if="article.image">
                            <img :src="article.image" :alt="article.title" />
                        </div>
                        <div class="detail-content" v-html="htmlContent" />
                    </div>
                    <aside class="detail-sidebar">
                        <h3 class="sidebar-title">更多动态</h3>
                        <nav class="sidebar-list">
                            <router-link
                                v-for="item in otherNews"
                                :key="item.id"
                                :to="`/news/${item.id}`"
                                class="sidebar-item"
                                :class="{ active: item.id === article.id }"
                            >
                                <span class="sidebar-item-date">{{ item.date }}</span>
                                <span class="sidebar-item-title">{{ item.title }}</span>
                            </router-link>
                        </nav>
                    </aside>
                </section>
                <SiteFooter v-if="footer" :footer="footer" />
                </template>
            </template>
        </el-skeleton>
        <div v-if="loaded && !article" class="detail-page not-found">
            <section class="detail-hero">
                <div class="detail-hero-text font-error">
                    <h1>文章不存在</h1>
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
import { getNewsById, getPageData } from '@/api/pages';
import { getHomeData } from '@/api/home';

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
const allNews = ref([]);
const article = ref(null);
const footer = ref(null);
const loaded = ref(false);

const htmlContent = computed(() => {
    if (!article.value) return '';
    return marked(article.value.content);
});

const otherNews = computed(() => {
    return allNews.value.filter(n => n.id !== article.value?.id);
});

async function loadArticle(id) {
    loaded.value = false;
    article.value = await getNewsById(id);
    loaded.value = true;
    if (article.value) {
        document.title = `${article.value.title} - Miwa & Co.`;
    }
}

onMounted(async () => {
    const [newsList, home] = await Promise.all([
        getPageData('news'),
        getHomeData(),
    ]);
    allNews.value = newsList;
    footer.value = home.footer;
    await loadArticle(route.params.id);
});

watch(() => route.params.id, (newId) => {
    loadArticle(newId);
});
</script>

<style lang="scss" scoped>
.detail-page {
    color: #1a1a1a;
    background: #f8f8f8;

    &.not-found {
        min-height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
    }
}

.detail-hero {
    min-height: 35vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: center;
    padding: 6rem 2rem 3rem;
    background: url('/images/2.jpg') center / cover no-repeat;
    position: relative;

    &::before {
        content: '';
        position: absolute;
        inset: 0;
        background: rgba(0, 0, 0, 0.25);
    }

    .detail-hero-text {
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

        .detail-date {
            font-size: 0.9rem;
            opacity: 0.5;
            margin-top: 1rem;
        }
    }
}

.detail-layout {
    max-width: 1200px;
    margin: 0 auto;
    padding: 4rem 3rem;
    display: grid;
    grid-template-columns: minmax(0, 1fr) 220px;
    gap: 3rem;
    align-items: flex-start;

    @media (max-width: 768px) {
        grid-template-columns: 1fr;
        gap: 2.5rem;
        padding: 2.5rem 1.25rem;
    }
}

.detail-main {
    min-width: 0;
}

.detail-image {
    margin-bottom: 2.5rem;

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

.detail-content {
    :deep(img) {
        max-width: 100%;
        height: auto;
        border-radius: 12px;
        margin: 1.5rem 0;
        display: block;
    }

    :deep(h2) {
        font-size: 1.5rem;
        font-weight: bold;
        margin: 3rem 0 1rem;
        letter-spacing: -0.01em;
        color: #1a1a1a;
    }

    :deep(h3) {
        font-size: 1.2rem;
        font-weight: bold;
        margin: 2rem 0 0.75rem;
        color: #1a1a1a;
    }

    :deep(h4) {
        font-size: 1.05rem;
        font-weight: bold;
        margin: 1.5rem 0 0.5rem;
        color: #333;
    }

    :deep(p) {
        font-size: 1.05rem;
        line-height: 1.85;
        opacity: 0.55;
        margin: 0 0 1.5rem;
    }

    :deep(a) {
        color: #1a1a1a;
        text-decoration: underline;
        text-decoration-color: rgba(0, 0, 0, 0.2);
        text-underline-offset: 0.2em;
        transition: text-decoration-color 0.2s;

        &:hover {
            text-decoration-color: rgba(0, 0, 0, 0.6);
        }
    }

    :deep(ul), :deep(ol) {
        padding-left: 1.5rem;
        margin: 0 0 1.5rem;
    }

    :deep(li) {
        font-size: 1rem;
        line-height: 1.75;
        opacity: 0.5;
        margin-bottom: 0.4rem;

        &::marker {
            opacity: 0.3;
        }
    }

    :deep(strong) {
        color: #1a1a1a;
        opacity: 0.8;
    }

    :deep(em) {
        opacity: 0.65;
    }

    // inline code
    :deep(code) {
        font-family: 'SF Mono', 'Cascadia Code', 'Fira Code', monospace;
        font-size: 0.85em;
        background: rgba(0, 0, 0, 0.05);
        padding: 0.15em 0.4em;
        border-radius: 4px;
        color: #333;
    }

    // code blocks
    :deep(pre) {
        background: #f6f8fa;
        border: 1px solid rgba(0, 0, 0, 0.06);
        border-radius: 12px;
        padding: 1.25rem 1.5rem;
        margin: 1.5rem 0;
        overflow-x: auto;
        font-size: 0.85rem;
        line-height: 1.65;

        code {
            background: none;
            padding: 0;
            border-radius: 0;
            font-size: inherit;
            color: inherit;
        }
    }

    // tables
    :deep(table) {
        width: 100%;
        border-collapse: collapse;
        margin: 1.5rem 0;
        font-size: 0.9rem;
    }

    :deep(th) {
        text-align: left;
        font-weight: 600;
        opacity: 0.6;
        padding: 0.6rem 0.75rem;
        border-bottom: 2px solid rgba(0, 0, 0, 0.08);
        font-size: 0.8rem;
        text-transform: uppercase;
        letter-spacing: 0.04em;
    }

    :deep(td) {
        padding: 0.6rem 0.75rem;
        border-bottom: 1px solid rgba(0, 0, 0, 0.04);
        opacity: 0.55;
    }

    :deep(hr) {
        border: none;
        border-top: 1px solid rgba(0, 0, 0, 0.06);
        margin: 2.5rem 0;
    }

    :deep(blockquote) {
        border-left: 3px solid rgba(0, 0, 0, 0.15);
        margin: 2rem 0;
        padding: 0.5rem 0 0.5rem 1.5rem;

        p {
            font-size: 1rem;
            opacity: 0.45;
            font-style: italic;
            margin-bottom: 0.5rem;
        }
    }

    @media (max-width: 640px) {
        :deep(img) {
            border-radius: 8px;
            margin: 1rem 0;
        }

        :deep(h2) {
            font-size: 1.25rem;
            margin: 2rem 0 0.75rem;
        }

        :deep(h3) {
            font-size: 1.1rem;
        }

        :deep(p) {
            font-size: 0.95rem;
            line-height: 1.75;
            margin-bottom: 1.25rem;
        }

        :deep(pre) {
            padding: 1rem;
            border-radius: 8px;
            font-size: 0.8rem;
        }

        :deep(table) {
            font-size: 0.8rem;
        }

        :deep(th), :deep(td) {
            padding: 0.4rem 0.5rem;
        }
    }
}

// ---------- sidebar ----------
.detail-sidebar {
    position: sticky;
    top: 6rem;

    @media (max-width: 768px) {
        position: static;
        border-top: 1px solid rgba(0, 0, 0, 0.06);
        padding-top: 2rem;
    }
}

.sidebar-title {
    font-size: 0.75rem;
    opacity: 0.3;
    letter-spacing: 0.06em;
    text-transform: uppercase;
    margin: 0 0 1.25rem;
}

.sidebar-list {
    display: flex;
    flex-direction: column;
    gap: 0;
}

.sidebar-item {
    display: flex;
    flex-direction: column;
    gap: 0.25rem;
    padding: 0.9rem 0;
    border-bottom: 1px solid rgba(0, 0, 0, 0.05);
    color: #1a1a1a;
    text-decoration: none;
    transition: opacity 0.3s ease, padding-left 0.3s ease;

    &:hover {
        opacity: 0.6;
    }

    &.active {
        padding-left: 0.75rem;
        border-left: 2px solid #1a1a1a;

        .sidebar-item-title {
            opacity: 0.7;
        }
    }
}

.sidebar-item-date {
    font-size: 0.7rem;
    opacity: 0.25;
    white-space: nowrap;
}

.sidebar-item-title {
    font-size: 0.9rem;
    line-height: 1.35;
    opacity: 0.4;
}

@media (max-width: 640px) {
    .detail-hero {
        min-height: 25vh;
        padding: 5rem 1.25rem 2.5rem;

        .detail-hero-text h1 {
            font-size: clamp(1.5rem, 5vw, 2.5rem);
        }
    }

    .sidebar-item {
        padding: 0.7rem 0;
    }
}
</style>
