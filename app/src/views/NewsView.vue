<template>
    <div class="news-page" v-if="news.length">
        <section class="news-hero">
            <div class="news-hero-text font-error">
                <h1>最新动态</h1>
                <p class="en">Latest News</p>
            </div>
        </section>

        <section class="news-grid-section">
            <div class="news-grid">
                <router-link v-for="item in news" :key="item.id" :to="`/news/${item.id}`" class="news-card">
                    <div class="card-image">
                        <img v-if="item.image" :src="item.image" :alt="item.title" />
                        <div v-else class="card-image-placeholder" />
                    </div>
                    <div class="card-body">
                        <span class="card-date">{{ item.date }}</span>
                        <h2 class="card-title">{{ item.title }}</h2>
                        <p class="card-excerpt">{{ item.excerpt }}</p>
                        <span class="card-arrow">→</span>
                    </div>
                </router-link>
            </div>
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

.news-card {
    display: flex;
    flex-direction: column;
    border: 1px solid rgba(0, 0, 0, 0.06);
    border-radius: 20px;
    color: #1a1a1a;
    text-decoration: none;
    overflow: hidden;
    transition:
        border-color 0.4s ease,
        background 0.4s ease,
        transform 0.4s ease;

    &:hover {
        border-color: rgba(0, 0, 0, 0.15);
        background: rgba(0, 0, 0, 0.01);
        transform: translateY(-4px);

        .card-arrow {
            opacity: 0.4;
            transform: translateX(4px);
        }
    }
}

.card-image {
    img {
        width: 100%;
        aspect-ratio: 16 / 10;
        object-fit: cover;
        display: block;
    }

    .card-image-placeholder {
        width: 100%;
        aspect-ratio: 16 / 10;
        background: rgba(0, 0, 0, 0.04);
    }
}

.card-body {
    display: flex;
    flex-direction: column;
    gap: 0.5rem;
    padding: 1.5rem;
    flex: 1;
}

.card-date {
    font-size: 0.75rem;
    opacity: 0.3;
    white-space: nowrap;
}

.card-title {
    font-size: 1.2rem;
    font-weight: bold;
    margin: 0;
    letter-spacing: -0.01em;
    line-height: 1.35;
}

.card-excerpt {
    font-size: 0.85rem;
    opacity: 0.4;
    line-height: 1.6;
    margin: 0;
    flex: 1;
}

.card-arrow {
    font-size: 0.9rem;
    opacity: 0;
    align-self: flex-end;
    transition: opacity 0.3s ease, transform 0.3s ease;
    margin-top: 0.25rem;
}
</style>
