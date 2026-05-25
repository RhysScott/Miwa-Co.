<template>
    <div class="about-page">
        <LoadingSpinner v-if="!loaded" />
        <template v-else-if="data">
        <section class="about-hero">
            <div class="about-hero-text font-error">
                <h1>关于 Miwa</h1>
                <p class="en">About Us</p>
            </div>
        </section>

        <!-- 公司介绍 -->
        <section class="about-intro">
            <div class="intro-zh">
                <p v-for="(p, i) in data.zh" :key="'zh'+i">{{ p }}</p>
            </div>
            <div class="intro-en">
                <p v-for="(p, i) in data.en" :key="'en'+i">{{ p }}</p>
            </div>
        </section>

        <!-- 价值观 -->
        <section class="about-values">
            <h2 class="section-title font-error">
                <span class="zh">我们的价值观</span>
                <span class="label-en">Our Values</span>
            </h2>
            <div class="values-grid">
                <div v-for="v in data.values" :key="v.zh" class="value-card">
                    <h3>{{ v.zh }}</h3>
                    <p class="value-en">{{ v.en }}</p>
                    <p class="value-desc">{{ v.desc }}</p>
                </div>
            </div>
        </section>

        <!-- CTA -->
        <section class="about-cta">
            <div class="cta-inner">
                <p class="cta-zh">有项目想聊？</p>
                <a href="mailto:hello@miwa-co.com" class="cta-email">hello@miwa-co.com →</a>
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
import SiteFooter from '@/components/home/Footer.vue';

const data = ref(null);
const footer = ref(null);
const loaded = ref(false);

onMounted(async () => {
    const [pageData, home] = await Promise.all([getPageData('about'), getHomeData()]);
    data.value = pageData;
    footer.value = home.footer;
    loaded.value = true;
});
</script>

<style lang="scss" scoped>
.about-page {
    color: #1a1a1a;
    background: #f8f8f8;
}

.about-hero {
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

    .about-hero-text {
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

.section-title {
    text-align: center;
    margin-bottom: 3rem;
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 0.3rem;

    .zh {
        font-size: 2.5rem;
        font-weight: bold;
        letter-spacing: -0.02em;
    }

    .label-en {
        font-size: 0.75rem;
        opacity: 0.25;
        letter-spacing: 0.06em;
        white-space: nowrap;
    }
}

.about-intro {
    max-width: 700px;
    margin: 0 auto;
    padding: 5rem 2rem;
    border-bottom: 1px solid rgba(0, 0, 0, 0.05);
}

.intro-zh p {
    font-size: 1.5rem;
    line-height: 1.6;
    letter-spacing: -0.01em;
    margin: 0 0 1rem;
}

.intro-en {
    margin-top: 2.5rem;

    p {
        font-size: 0.95rem;
        opacity: 0.3;
        line-height: 1.6;
        margin: 0 0 0.3rem;
    }
}

.about-values {
    padding: 5rem 2rem;
}

.values-grid {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 1.5rem;
    max-width: 800px;
    margin: 0 auto;

    @media (max-width: 640px) {
        grid-template-columns: 1fr;
    }
}

.value-card {
    border: 1px solid rgba(0, 0, 0, 0.06);
    border-radius: 24px;
    padding: 2rem;

    h3 {
        font-size: 1.6rem;
        font-weight: bold;
        margin: 0;
        letter-spacing: -0.01em;
    }

    .value-en {
        font-size: 0.75rem;
        opacity: 0.25;
        letter-spacing: 0.05em;
        margin: 0.3rem 0 1rem;
    }

    .value-desc {
        font-size: 0.9rem;
        opacity: 0.4;
        line-height: 1.6;
        margin: 0;
    }
}

.about-cta {
    padding: 5rem 2rem;
    border-top: 1px solid rgba(0, 0, 0, 0.05);
    text-align: center;
}

.cta-inner {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 0.75rem;
}

.cta-zh {
    font-size: 1rem;
    opacity: 0.35;
    margin: 0;
}

.cta-email {
    font-size: 2.25rem;
    font-weight: bold;
    color: #1a1a1a;
    text-decoration: none;
    border-bottom: 2px solid rgba(0, 0, 0, 0.15);
    padding-bottom: 0.35rem;
    letter-spacing: -0.01em;
    transition: border-color 0.3s ease;

    &:hover {
        border-color: rgba(0, 0, 0, 0.6);
    }
}
</style>
