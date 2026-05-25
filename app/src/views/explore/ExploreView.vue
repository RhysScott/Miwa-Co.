<template>
    <div class="explore-page">
        <el-skeleton :loading="!loaded" animated>
            <template #template>
                <section class="explore-hero">
                    <div class="explore-hero-text font-error">
                        <el-skeleton-item variant="h1" style="width:240px" />
                        <el-skeleton-item variant="text" style="width:200px;margin-top:0.75rem" />
                    </div>
                </section>
                <section class="explore-services">
                    <div class="section-title font-error" style="text-align:center;margin-bottom:3rem">
                        <el-skeleton-item variant="text" style="width:120px;margin:0 auto" />
                        <el-skeleton-item variant="text" style="width:80px;margin:0.3rem auto 0" />
                    </div>
                    <div class="services-grid">
                        <div v-for="i in 4" :key="i" class="sk-card">
                            <el-skeleton-item variant="h3" style="width:120px" />
                            <el-skeleton-item variant="text" style="width:80px;margin-top:0.3rem" />
                            <el-skeleton-item variant="text" style="width:100%;margin-top:1rem" />
                            <el-skeleton-item variant="text" style="width:70%;margin-top:0.4rem" />
                        </div>
                    </div>
                </section>
                <section class="explore-cases">
                    <div class="section-title font-error" style="text-align:center;margin-bottom:3rem">
                        <el-skeleton-item variant="text" style="width:120px;margin:0 auto" />
                        <el-skeleton-item variant="text" style="width:80px;margin:0.3rem auto 0" />
                    </div>
                    <div class="cases-list">
                        <div v-for="i in 3" :key="i" class="sk-card">
                            <el-skeleton-item variant="h3" style="width:60%" />
                            <el-skeleton-item variant="text" style="width:90%;margin-top:0.6rem" />
                            <el-skeleton-item variant="text" style="width:70%;margin-top:0.4rem" />
                        </div>
                    </div>
                </section>
            </template>
            <template #default>
                <template v-if="data">
                <section class="explore-hero">
                    <div class="explore-hero-text font-error">
                        <h1>探索 Miwa</h1>
                        <p class="en">Explore What We Build</p>
                    </div>
                </section>
                <section class="explore-services">
                    <h2 class="section-title font-error">
                        <span class="zh">服务能力</span>
                        <span class="label-en">Capabilities</span>
                    </h2>
                    <div class="services-grid">
                        <div v-for="s in data.services" :key="s.zh" class="service-card">
                            <h3>{{ s.zh }}</h3>
                            <p class="service-en">{{ s.en }}</p>
                            <p class="service-desc">{{ s.desc }}</p>
                            <p class="service-detail">{{ s.detail }}</p>
                        </div>
                    </div>
                </section>
                <section class="explore-cases">
                    <h2 class="section-title font-error">
                        <span class="zh">精选案例</span>
                        <span class="label-en">Case Studies</span>
                    </h2>
                    <div class="cases-list">
                        <div v-for="c in data.cases" :key="c.zh" class="case-card">
                            <h3>{{ c.zh }}</h3>
                            <p>{{ c.detail }}</p>
                        </div>
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
import SiteFooter from '@/components/home/Footer.vue';

const data = ref(null);
const footer = ref(null);
const loaded = ref(false);

onMounted(async () => {
    const [pageData, home] = await Promise.all([getPageData('explore'), getHomeData()]);
    data.value = pageData;
    footer.value = home.footer;
    loaded.value = true;
});
</script>

<style lang="scss" scoped>
.explore-page {
    color: #1a1a1a;
    background: #f8f8f8;
}

.explore-hero {
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

    .explore-hero-text {
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

    @media (max-width: 640px) {
        margin-bottom: 2rem;

        .zh {
            font-size: 1.75rem;
        }
    }
}

.explore-services {
    padding: 5rem 2rem;
    border-bottom: 1px solid rgba(0, 0, 0, 0.05);
}

.services-grid {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 1.5rem;
    max-width: 900px;
    margin: 0 auto;

    @media (max-width: 640px) {
        grid-template-columns: 1fr;
    }
}

.service-card {
    border: 1px solid rgba(0, 0, 0, 0.06);
    border-radius: 24px;
    padding: 2rem;

    h3 {
        font-size: 1.6rem;
        font-weight: bold;
        margin: 0;
        letter-spacing: -0.01em;
    }

    .service-en {
        font-size: 0.75rem;
        opacity: 0.25;
        letter-spacing: 0.05em;
        margin: 0.3rem 0 1rem;
    }

    .service-desc {
        font-size: 0.9rem;
        opacity: 0.5;
        line-height: 1.6;
        margin: 0 0 1rem;
    }

    .service-detail {
        font-size: 0.85rem;
        opacity: 0.35;
        line-height: 1.6;
        margin: 0;
    }
}

.explore-cases {
    padding: 5rem 2rem;
}

.cases-list {
    display: flex;
    flex-direction: column;
    gap: 1.5rem;
    max-width: 700px;
    margin: 0 auto;
}

.case-card {
    border: 1px solid rgba(0, 0, 0, 0.06);
    border-radius: 20px;
    padding: 2rem;
    transition: border-color 0.3s ease, transform 0.3s ease;

    &:hover {
        border-color: rgba(0, 0, 0, 0.15);
        transform: translateX(6px);
    }

    h3 {
        font-size: 1.3rem;
        font-weight: bold;
        margin: 0 0 0.6rem;
        letter-spacing: -0.01em;
    }

    p {
        font-size: 0.9rem;
        opacity: 0.4;
        line-height: 1.7;
        margin: 0;
    }
}

.sk-card {
    border: 1px solid rgba(0, 0, 0, 0.06);
    border-radius: 24px;
    padding: 2rem;
}

</style>
