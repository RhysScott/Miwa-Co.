<template>
    <div class="news-page">
        <LoadingSpinner v-if="!loaded" />

        <template v-else-if="news.length">
            <section class="news-hero">
                <div class="news-hero-text font-error">
                    <h1>最新动态</h1>
                    <p class="en">Latest News</p>
                </div>
            </section>

            <section class="news-grid-section">
                <div class="news-grid">
                    <NewsCard v-for="item in news" :key="item.id" :item="item" variant="full" :to="`/news/${item.id}`" />
                </div>
            </section>

            <SiteFooter v-if="footer" :footer="footer" />
        </template>
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { getPageData } from '@/api/pages';
import { getHomeData } from '@/api/home';
import LoadingSpinner from '@/components/shared/LoadingSpinner.vue';
import NewsCard from '@/components/home/NewsCard.vue';
import SiteFooter from '@/components/home/Footer.vue';

const news = ref([]);
const footer = ref(null);
const loaded = ref(false);

onMounted(async () => {
    const [data, home] = await Promise.all([getPageData('news'), getHomeData()]);
    news.value = data;
    footer.value = home.footer;
    loaded.value = true;
});
</script>

<style lang="scss" scoped>
.news-page {
    color: #1a1a1a;
    background: #f8f8f8;
}

.news-hero {
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

.news-grid-section {
    max-width: 960px;
    margin: 0 auto;
    padding: 4rem 2rem;
}

.news-grid {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 1.5rem;

    @media (max-width: 640px) {
        grid-template-columns: 1fr;
    }
}

</style>
