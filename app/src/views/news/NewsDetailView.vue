<template>
    <div class="detail-page">
        <LoadingSpinner v-if="!loaded" />
        <template v-else-if="article">
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
        <div v-else class="detail-page not-found">
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
import { getNewsById, getPageData } from '@/api/pages';
import { getHomeData } from '@/api/home';
import LoadingSpinner from '@/components/shared/LoadingSpinner.vue';
import SiteFooter from '@/components/home/Footer.vue';

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
    background: url('/images/5.jpg') center / cover no-repeat;
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
    max-width: 1100px;
    margin: 0 auto;
    padding: 4rem 2rem;
    display: grid;
    grid-template-columns: 1fr 220px;
    gap: 2.5rem;
    align-items: flex-start;

    @media (max-width: 768px) {
        grid-template-columns: 1fr;
        gap: 3rem;
    }
}

.detail-main {
    min-width: 0;
}

.detail-image {
    margin-bottom: 3rem;

    img {
        width: 100%;
        border-radius: 16px;
        display: block;
    }
}

.detail-content {
    :deep(h2) {
        font-size: 1.5rem;
        font-weight: bold;
        margin: 3rem 0 1rem;
        letter-spacing: -0.01em;
        color: #1a1a1a;
    }

    :deep(p) {
        font-size: 1.05rem;
        line-height: 1.85;
        opacity: 0.55;
        margin: 0 0 1.5rem;
    }

    :deep(ul) {
        padding-left: 1.25rem;
        margin: 0 0 1.5rem;
    }

    :deep(li) {
        font-size: 1rem;
        line-height: 1.75;
        opacity: 0.5;
        margin-bottom: 0.4rem;
    }

    :deep(strong) {
        color: #1a1a1a;
        opacity: 0.8;
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
</style>
