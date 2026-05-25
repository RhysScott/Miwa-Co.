<template>
    <div class="news-page">
        <el-skeleton :loading="!loaded" animated>
            <template #template>
                <section class="news-hero">
                    <div class="news-hero-text font-error">
                        <el-skeleton-item variant="h1" style="width:200px" />
                        <el-skeleton-item variant="text" style="width:120px;margin-top:0.75rem" />
                    </div>
                </section>
                <section class="news-grid-section">
                    <div class="news-grid">
                        <div v-for="i in 4" :key="i" class="sk-card">
                            <el-skeleton-item variant="image" style="width:100%;height:200px;border-radius:24px" />
                            <el-skeleton-item variant="text" style="width:30%;margin-top:1rem" />
                            <el-skeleton-item variant="text" style="width:80%;margin-top:0.5rem" />
                        </div>
                    </div>
                </section>
            </template>
            <template #default>
                <template v-if="news.length">
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
            </template>
        </el-skeleton>
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { getPageData } from '@/api/pages';
import { getHomeData } from '@/api/home';
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
    background: url('/images/2.jpg') center / cover no-repeat;
    position: relative;
    isolation: isolate;

    &::before {
        content: '';
        position: absolute;
        inset: 0;
        z-index: 0;
        background: linear-gradient(180deg, rgba(0,0,0,0.1) 0%, rgba(0,0,0,0.25) 40%, rgba(0,0,0,0.5) 100%);
    }

    &::after {
        content: '';
        position: absolute;
        inset: 0;
        z-index: 0;
        opacity: 0.12;
        background-image: radial-gradient(circle, rgba(255,255,255,0.4) 1px, transparent 1px);
        background-size: 28px 28px;
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

    @media (max-width: 640px) {
        padding: 2.5rem 1.25rem;
    }
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
