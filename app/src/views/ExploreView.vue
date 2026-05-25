<template>
    <div class="explore-page" v-if="data">
        <section class="explore-hero">
            <div class="explore-hero-text font-error">
                <h1>探索 Miwa</h1>
                <p class="en">Explore What We Build</p>
            </div>
        </section>

        <!-- 服务能力 -->
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

        <!-- 项目作品 -->
        <section class="explore-projects">
            <h2 class="section-title font-error">
                <span class="zh">项目作品</span>
                <span class="label-en">Selected Works</span>
            </h2>
            <div class="projects-grid">
                <div v-for="(p, i) in data.projects" :key="i" class="project-card">
                    <div class="project-image">
                        <img :src="p.image" :alt="p.zh" />
                    </div>
                    <div class="project-body">
                        <span class="project-num">{{ String(i + 1).padStart(2, '0') }}</span>
                        <h3>{{ p.zh }}</h3>
                        <p class="project-en">{{ p.en }}</p>
                        <p class="project-desc">{{ p.desc }}</p>
                        <p class="project-detail">{{ p.detail }}</p>
                    </div>
                </div>
            </div>
        </section>

        <!-- 案例 -->
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
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { getPageData } from '@/api/pages';
import { getHomeData } from '@/api/home';
import SiteFooter from '@/components/home/Footer.vue';

const data = ref(null);
const footer = ref(null);

onMounted(async () => {
    data.value = await getPageData('explore');
    const home = await getHomeData();
    footer.value = home.footer;
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

.explore-projects {
    padding: 5rem 2rem;
    border-bottom: 1px solid rgba(0, 0, 0, 0.05);
}

.projects-grid {
    display: flex;
    flex-direction: column;
    gap: 2.5rem;
    max-width: 800px;
    margin: 0 auto;
}

.project-card {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 2.5rem;
    align-items: center;

    &:nth-child(even) {
        .project-image { order: 2; }
        .project-body { order: 1; }
    }
}

.project-image img {
    width: 100%;
    aspect-ratio: 4 / 3;
    object-fit: cover;
    border-radius: 16px;
    display: block;
}

.project-body {
    display: flex;
    flex-direction: column;
    gap: 0.4rem;
}

.project-num {
    font-size: 3rem;
    font-weight: bold;
    opacity: 0.08;
    line-height: 1;
}

.project-body h3 {
    font-size: 1.6rem;
    font-weight: bold;
    margin: 0;
    letter-spacing: -0.01em;
}

.project-en {
    font-size: 0.75rem;
    opacity: 0.25;
    letter-spacing: 0.05em;
    margin: 0;
}

.project-desc {
    font-size: 0.95rem;
    opacity: 0.5;
    line-height: 1.6;
    margin: 0;
}

.project-detail {
    font-size: 0.85rem;
    opacity: 0.35;
    line-height: 1.6;
    margin-top: 0.5rem;
}

@media (max-width: 640px) {
    .project-card,
    .project-card:nth-child(even) {
        grid-template-columns: 1fr;
        gap: 1rem;

        .project-image,
        .project-body {
            order: unset;
        }
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
</style>
