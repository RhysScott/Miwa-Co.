<template>
    <div class="detail-page" v-if="article">
        <section class="detail-hero">
            <div class="detail-hero-text font-error">
                <h1>{{ article.title }}</h1>
                <p class="detail-date">{{ article.date }}</p>
            </div>
        </section>

        <section class="detail-body">
            <div class="detail-image" v-if="article.image">
                <img :src="article.image" :alt="article.title" />
            </div>
            <p v-for="(p, i) in paragraphs" :key="i" class="detail-p">{{ p }}</p>
        </section>

        <SiteFooter v-if="footer" :footer="footer" />
    </div>
    <div class="detail-page not-found" v-else-if="loaded">
        <section class="detail-hero">
            <div class="detail-hero-text font-error">
                <h1>文章不存在</h1>
            </div>
        </section>
    </div>
</template>

<script setup>
import { computed, onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import { getNewsById } from '@/api/pages';
import { getHomeData } from '@/api/home';
import SiteFooter from '@/components/home/Footer.vue';

const route = useRoute();
const article = ref(null);
const footer = ref(null);
const loaded = ref(false);

const paragraphs = computed(() => {
    if (!article.value) return [];
    return article.value.content.split('\n\n').filter(p => p.trim());
});

onMounted(async () => {
    const id = route.params.id;
    article.value = await getNewsById(id);
    loaded.value = true;
    if (article.value) {
        document.title = `${article.value.title} - Miwa & Co.`;
    }
    const home = await getHomeData();
    footer.value = home.footer;
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

.detail-body {
    max-width: 680px;
    margin: 0 auto;
    padding: 4rem 2rem;
}

.detail-image {
    margin-bottom: 3rem;

    img {
        width: 100%;
        border-radius: 16px;
        display: block;
    }
}

.detail-p {
    font-size: 1.05rem;
    line-height: 1.85;
    opacity: 0.55;
    margin: 0 0 1.75rem;
}
</style>
