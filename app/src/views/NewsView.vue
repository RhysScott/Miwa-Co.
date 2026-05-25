<template>
    <div class="news-page" v-if="news.length">
        <section class="news-hero">
            <div class="news-hero-text font-error">
                <h1>最新动态</h1>
                <p class="en">Latest News</p>
            </div>
        </section>

        <section class="news-list">
            <article v-for="item in news" :key="item.id" class="news-article">
                <div class="article-image">
                    <img v-if="item.image" :src="item.image" :alt="item.title" />
                    <div v-else class="article-image-placeholder" />
                </div>
                <div class="article-body">
                    <span class="article-date">{{ item.date }}</span>
                    <h2 class="article-title">{{ item.title }}</h2>
                    <p class="article-content">{{ item.content }}</p>
                </div>
            </article>
        </section>

        <SiteFooter v-if="footer" :footer="footer" />
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { getPageData } from '@/api/pages';
import { getHomeData } from '@/api/home';
import SiteFooter from '@/components/home/Footer.vue';

const news = ref([]);
const footer = ref(null);

onMounted(async () => {
    news.value = await getPageData('news');
    const home = await getHomeData();
    footer.value = home.footer;
});
</script>

<style lang="scss" scoped>
.news-page {
    color: #1a1a1a;
    background: #f8f8f8;
}

.news-hero {
    min-height: 40vh;
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

    .news-hero-text {
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

.news-list {
    max-width: 800px;
    margin: 0 auto;
    padding: 4rem 2rem;
    display: flex;
    flex-direction: column;
    gap: 3rem;
}

.news-article {
    display: grid;
    grid-template-columns: 200px 1fr;
    gap: 2rem;
    align-items: flex-start;
    padding-bottom: 3rem;
    border-bottom: 1px solid rgba(0, 0, 0, 0.06);

    &:last-child {
        border-bottom: none;
        padding-bottom: 0;
    }
}

.article-image {
    img {
        width: 100%;
        aspect-ratio: 4 / 3;
        object-fit: cover;
        border-radius: 12px;
        display: block;
    }

    .article-image-placeholder {
        width: 100%;
        aspect-ratio: 4 / 3;
        border-radius: 12px;
        background: rgba(0, 0, 0, 0.04);
    }
}

.article-body {
    display: flex;
    flex-direction: column;
    gap: 0.6rem;
}

.article-date {
    font-size: 0.75rem;
    opacity: 0.3;
    white-space: nowrap;
}

.article-title {
    font-size: 1.5rem;
    font-weight: bold;
    margin: 0;
    letter-spacing: -0.01em;
    line-height: 1.3;
}

.article-content {
    font-size: 0.95rem;
    opacity: 0.45;
    line-height: 1.7;
    margin: 0;
}

@media (max-width: 640px) {
    .news-article {
        grid-template-columns: 1fr;
        gap: 1rem;
    }
}
</style>
