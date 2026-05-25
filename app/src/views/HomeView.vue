<template>
    <div class="home">
        <el-skeleton :loading="!loaded" animated>
            <template #template>
                <section class="hero" style="min-height:100vh;display:flex;flex-direction:column;align-items:center;justify-content:center;background:#0b0b12">
                    <div style="display:flex;flex-direction:column;align-items:center;gap:1rem">
                        <el-skeleton-item variant="text" style="width:clamp(200px,40vw,500px);height:clamp(3rem,8vw,8rem)" />
                        <el-skeleton-item variant="text" style="width:clamp(120px,20vw,260px);height:1.5rem" />
                        <el-skeleton-item variant="text" style="width:clamp(160px,28vw,360px);height:1rem;margin-top:0.5rem" />
                    </div>
                </section>
            </template>
            <template #default>
                <!-- Hero 大字标语 -->
                <section class="hero" v-if="hero">
                    <div class="hero-text font-error">
                        <h1 class="hero-zh">{{ hero.zh }}</h1>
                        <p class="hero-en">{{ hero.en }}</p>
                        <p class="hero-sub">{{ hero.sub }}</p>
                    </div>
                    <div class="scroll-hint" aria-hidden="true">↓</div>
                </section>

                <!-- 横向滚动服务词条 -->
                <section class="marquee" v-if="marquee.length">
                    <div class="marquee-inner">
                        <div class="marquee-track">
                            <template v-for="(m, i) in marquee" :key="i">
                                <span class="marquee-item">{{ m.zh }}</span>
                                <span class="marquee-divider">/</span>
                                <span class="marquee-item">{{ m.en }}</span>
                                <span v-if="i < marquee.length - 1" class="marquee-divider">/</span>
                            </template>
                        </div>
                        <div class="marquee-track" aria-hidden="true">
                            <template v-for="(m, i) in marquee" :key="i">
                                <span class="marquee-item">{{ m.zh }}</span>
                                <span class="marquee-divider">/</span>
                                <span class="marquee-item">{{ m.en }}</span>
                                <span v-if="i < marquee.length - 1" class="marquee-divider">/</span>
                            </template>
                        </div>
                    </div>
                </section>

                <!-- 服务能力 — 全页 -->
                <section class="services" v-if="services.length">
                    <SectionHeader zh="我们的服务" en="What We Do" />
                    <div class="service-grid">
                        <ServiceCard v-for="s in services" :key="s.zh" :service="s" :icon="iconMap[s.icon]" />
                    </div>
                </section>

                <!-- 数据亮点 -->
                <section class="stats" v-if="stats.length">
                    <div class="stats-grid">
                        <StatItem v-for="s in stats" :key="s.num" :stat="s" />
                    </div>
                </section>

                <!-- 精选项目 -->
                <section class="projects" v-if="projects.length">
                    <SectionHeader zh="精选项目" en="Selected Works" />
                    <div class="project-grid">
                        <ProjectCard v-for="(p, i) in projects" :key="i" :project="p" :index="i" />
                    </div>
                </section>

                <!-- 工作流程 -->
                <section class="process" v-if="process.length">
                    <SectionHeader zh="工作流程" en="How We Work" />
                    <div class="process-track">
                        <ProcessStep v-for="(step, i) in process" :key="i" :step="step" :index="i" />
                    </div>
                </section>

                <!-- 合作客户 -->
                <section class="clients" v-if="clients.length">
                    <SectionHeader zh="合作客户" en="Clients & Partners" />
                    <div class="client-grid">
                        <ClientCard v-for="c in clients" :key="c.name" :client="c" />
                    </div>
                </section>

                <!-- 品牌理念 — 全页 -->
                <section class="philosophy" v-if="philosophy">
                    <div class="philosophy-quote font-error">
                        <p class="quote-zh">{{ philosophy.zh }}</p>
                        <p class="quote-sub">{{ philosophy.sub }}</p>
                        <p class="quote-en">{{ philosophy.en }}</p>
                    </div>
                </section>

                <!-- 最新动态 -->
                <section class="news-preview" v-if="news.length">
                    <SectionHeader zh="最新动态" en="Latest" />
                    <div class="news-grid">
                        <NewsCard v-for="n in news" :key="n.id" :item="n" :to="`/news/${n.id}`" />
                    </div>
                </section>

                <!-- 品牌简介 — 全页 -->
                <section class="about" v-if="about">
                    <SectionHeader zh="关于我们" en="About" />
                    <div class="about-grid">
                        <div class="about-text font-error">
                            <p v-for="(p, i) in about.zh" :key="'zh'+i">{{ p }}</p>
                            <p v-for="(p, i) in about.en" :key="'en'+i" class="about-en">{{ p }}</p>
                        </div>
                        <div class="about-cta">
                            <p class="cta-text">{{ about.cta.zh }}</p>
                            <a :href="'mailto:' + about.cta.email" class="cta-link">{{ about.cta.email }} →</a>
                        </div>
                    </div>
                </section>

                <SiteFooter v-if="footer" :footer="footer" />
            </template>
        </el-skeleton>
    </div>
</template>

<script setup>
import { Brain, Code, Cpu, Globe } from '@lucide/vue';
import { gsap } from 'gsap';
import { ScrollTrigger } from 'gsap/ScrollTrigger';
import { SplitText } from 'gsap/SplitText';
import { onMounted, ref, nextTick } from 'vue';
import { getHomeData } from '@/api/home';
import ClientCard from '@/components/home/ClientCard.vue';
import NewsCard from '@/components/home/NewsCard.vue';
import ProcessStep from '@/components/home/ProcessStep.vue';
import ProjectCard from '@/components/home/ProjectCard.vue';
import SiteFooter from '@/components/home/Footer.vue';
import SectionHeader from '@/components/shared/SectionHeader.vue';
import ServiceCard from '@/components/home/ServiceCard.vue';
import StatItem from '@/components/home/StatItem.vue';

gsap.registerPlugin(SplitText, ScrollTrigger);

const loaded = ref(false);
const hero = ref(null);
const marquee = ref([]);
const services = ref([]);
const projects = ref([]);
const process = ref([]);
const clients = ref([]);
const news = ref([]);
const stats = ref([]);
const philosophy = ref(null);
const about = ref(null);
const footer = ref(null);

const iconMap = { brain: Brain, code: Code, cpu: Cpu, globe: Globe };

onMounted(async () => {
    const data = await getHomeData();
    hero.value = data.hero;
    marquee.value = data.marquee;
    services.value = data.services;
    projects.value = data.projects;
    process.value = data.process;
    clients.value = data.clients;
    news.value = data.news;
    stats.value = data.stats;
    philosophy.value = data.philosophy;
    about.value = data.about;
    footer.value = data.footer;
    loaded.value = true;
    await nextTick();

    // Hero 逐字弹跳入场
    const heroSplit = SplitText.create('.hero-zh, .hero-en, .hero-sub', { type: 'chars,words' });
    gsap.fromTo(heroSplit.chars,
        { y: 80, opacity: 0 },
        { y: 0, opacity: 1, stagger: 0.02, duration: 0.8, ease: 'elastic.out(1, 0.5)' }
    );

    // 服务卡片滚动入场（awwwards 风格：弹性缩放 + 交错延迟）
    gsap.fromTo('.service-card',
        { y: 120, opacity: 0, scale: 0.85, rotation: 1 },
        {
            y: 0, opacity: 1, scale: 1, rotation: 0,
            stagger: 0.12, duration: 1, ease: 'expo.out',
            scrollTrigger: { trigger: '.services', start: 'top 75%' }
        }
    );

    // 项目卡片滚动入场（awwwards 风格：缩放弹入）
    gsap.fromTo('.project-card',
        { y: 120, opacity: 0, scale: 0.88 },
        {
            y: 0, opacity: 1, scale: 1,
            stagger: 0.14, duration: 1, ease: 'expo.out',
            scrollTrigger: { trigger: '.projects', start: 'top 75%' }
        }
    );

    // 流程步骤滚动入场（awwwards 风格：底部弹入）
    gsap.fromTo('.process-step',
        { y: 60, opacity: 0, scale: 0.9 },
        {
            y: 0, opacity: 1, scale: 1,
            stagger: 0.15, duration: 0.8, ease: 'expo.out',
            scrollTrigger: { trigger: '.process', start: 'top 80%' }
        }
    );

    // 客户名称滚动入场
    gsap.fromTo('.client-card',
        { y: 30, opacity: 0 },
        {
            y: 0, opacity: 1, stagger: 0.06, duration: 0.5, ease: 'power3.out',
            scrollTrigger: { trigger: '.clients', start: 'top 85%' }
        }
    );

    // 新闻列表滚动入场
    gsap.fromTo('.news-card',
        { y: 30, opacity: 0 },
        {
            y: 0, opacity: 1, stagger: 0.1, duration: 0.5, ease: 'power3.out',
            scrollTrigger: { trigger: '.news-preview', start: 'top 85%' }
        }
    );

    // 数据数字滚动入场
    gsap.fromTo('.stat-item',
        { y: 60, opacity: 0, scale: 0.8 },
        {
            y: 0, opacity: 1, scale: 1, stagger: 0.15, duration: 0.8, ease: 'elastic.out(1, 0.7)',
            scrollTrigger: { trigger: '.stats', start: 'top 80%' }
        }
    );

    // 理念语录入场
    const quoteSplit = SplitText.create('.quote-zh, .quote-sub, .quote-en', { type: 'chars,words' });
    gsap.fromTo(quoteSplit.chars,
        { y: 40, opacity: 0 },
        {
            y: 0, opacity: 1, stagger: 0.015, duration: 0.6, ease: 'power3.out',
            scrollTrigger: { trigger: '.philosophy', start: 'top 70%' }
        }
    );

    // About 文本滚动入场
    const aboutSplit = SplitText.create('.about-text p', { type: 'lines' });
    gsap.fromTo(aboutSplit.lines,
        { y: 40, opacity: 0 },
        {
            y: 0, opacity: 1, stagger: 0.1, duration: 0.6, ease: 'power3.out',
            scrollTrigger: { trigger: '.about', start: 'top 70%' }
        }
    );
});
</script>

<style lang="scss" scoped>
.home {
    color: #1a1a1a;
    background: #f8f8f8;
}

// ---------- Hero ----------
.hero {
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: center;
    padding: 2rem;
    background: url('/images/1.jpg') center / cover no-repeat;
    position: relative;

    &::before {
        content: '';
        position: absolute;
        inset: 0;
        background: rgba(0, 0, 0, 0.25);
    }

    .hero-text {
        position: relative;
        z-index: 1;
        color: rgba(255, 255, 255, 0.95);
    }

    .hero-zh {
        font-size: clamp(3rem, 8vw, 8rem);
        margin: 0;
        line-height: 1.1;
        letter-spacing: -0.02em;
    }

    .hero-en {
        font-size: clamp(1rem, 2.5vw, 2rem);
        margin-top: 1rem;
        opacity: 0.6;
    }

    .hero-sub {
        font-size: clamp(0.85rem, 1.5vw, 1.15rem);
        margin-top: 1.5rem;
        opacity: 0.4;
        max-width: 600px;
        line-height: 1.6;
    }

    .scroll-hint {
        position: absolute;
        bottom: 2rem;
        z-index: 1;
        font-size: 1.25rem;
        color: rgba(255, 255, 255, 0.55);
        animation: float 2s ease-in-out infinite;
    }
}

@keyframes float {
    0%, 100% { transform: translateY(0); }
    50% { transform: translateY(8px); }
}

// ---------- Marquee ----------
.marquee {
    width: 100%;
    overflow: hidden;
    border-top: 1px solid rgba(0, 0, 0, 0.06);
    border-bottom: 1px solid rgba(0, 0, 0, 0.06);
    padding: 1.5rem 0;

    .marquee-inner {
        display: flex;
        width: max-content;
        animation: marquee 40s linear infinite;
    }

    .marquee-track {
        display: flex;
        align-items: baseline;
        gap: 2rem;
    }

    .marquee-item {
        font-size: 2.5rem;
        font-weight: bold;
        white-space: nowrap;
    }

    .marquee-divider {
        font-size: 1.5rem;
        color: rgba(0, 0, 0, 0.12);
    }

    @media (max-width: 640px) {
        padding: 1rem 0;

        .marquee-item { font-size: 1.5rem; }
        .marquee-divider { font-size: 1rem; }
    }
}

@keyframes marquee {
    from { transform: translateX(0); }
    to { transform: translateX(-50%); }
}

// ---------- Services (full-page) ----------
.services {
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding: 3rem 2rem;
}

.service-grid {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 1.5rem;

    @media (max-width: 1024px) {
        grid-template-columns: repeat(2, 1fr);
    }
    @media (max-width: 640px) {
        grid-template-columns: 1fr;
    }
}

// ---------- Stats ----------
.stats {
    padding: 4rem 2rem;
    border-top: 1px solid rgba(0, 0, 0, 0.05);
    border-bottom: 1px solid rgba(0, 0, 0, 0.05);
}

.stats-grid {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 3rem;
    max-width: 800px;
    margin: 0 auto;

    @media (max-width: 640px) {
        grid-template-columns: repeat(2, 1fr);
        gap: 2rem;
    }
}


// ---------- Projects ----------
.projects {
    padding: 5rem 2rem;
}

.project-grid {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 1.25rem;

    @media (max-width: 1024px) {
        grid-template-columns: repeat(2, 1fr);
    }
    @media (max-width: 640px) {
        grid-template-columns: 1fr;
    }
}

.project-grid:hover :deep(.project-card:not(:hover)) {
    opacity: 0.3;
}

.project-grid :deep(.project-card:hover) {
    transform: scale(1.02);
    box-shadow: 0 24px 60px rgba(0, 0, 0, 0.13);
    z-index: 2;
}

// ---------- Process ----------
.process {
    padding: 5rem 2rem;
}

.process-track {
    display: flex;
    flex-direction: row;
    justify-content: center;
    position: relative;
}

// 水平连接线
.process-track::before {
    content: '';
    position: absolute;
    top: calc(1.5rem + 24px);
    left: calc(1rem + 24px);
    right: calc(1rem + 24px);
    height: 0;
    border-top: 1px dashed rgba(0, 0, 0, 0.12);
    z-index: 0;
}

@media (max-width: 768px) {
    .process-track {
        flex-direction: column;
        align-items: flex-start;
    }

    .process-track::before {
        top: calc(1.5rem + 24px);
        bottom: 1.5rem;
        left: 24px;
        right: auto;
        width: 0;
        height: auto;
        border-top: none;
        border-left: 1px dashed rgba(0, 0, 0, 0.12);
    }
}

// ---------- Clients ----------
.clients {
    padding: 5rem 2rem;
}

.client-grid {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 1rem;

    @media (max-width: 768px) {
        grid-template-columns: repeat(2, 1fr);
    }
}


// ---------- Philosophy (full-page) ----------
.philosophy {
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 2rem;
    border-top: 1px solid rgba(0, 0, 0, 0.05);

    .philosophy-quote {
        text-align: center;
        max-width: 750px;

        .quote-zh {
            font-size: clamp(1.75rem, 4.5vw, 3rem);
            font-weight: bold;
            line-height: 1.5;
            margin: 0 0 2rem;
            letter-spacing: -0.02em;
        }

        .quote-sub {
            font-size: 1rem;
            opacity: 0.4;
            line-height: 1.7;
            margin: 0 0 1.5rem;
            max-width: 550px;
            margin-left: auto;
            margin-right: auto;
        }

        .quote-en {
            font-size: 1.15rem;
            opacity: 0.3;
            margin: 0;
            line-height: 1.6;
        }
    }
}

// ---------- News ----------
.news-preview {
    padding: 5rem 2rem;
    border-top: 1px solid rgba(0, 0, 0, 0.05);
}

.news-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 1.25rem;

    @media (max-width: 1024px) {
        grid-template-columns: repeat(2, 1fr);
    }
    @media (max-width: 640px) {
        grid-template-columns: 1fr;
    }
}


// ---------- About (full-page) ----------
.about {
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding: 3rem 2rem;
    border-top: 1px solid rgba(0, 0, 0, 0.05);
}

.about-grid {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 6rem;
    align-items: center;

    @media (max-width: 768px) {
        grid-template-columns: 1fr;
        gap: 3rem;
    }
}

.about-text {
    p {
        margin: 0 0 1.25rem;
        font-size: 1.6rem;
        line-height: 1.5;
        letter-spacing: -0.01em;
    }

    .about-en {
        font-size: 1rem;
        opacity: 0.28;
        margin-bottom: 0.5rem;
    }

    @media (max-width: 640px) {
        p {
            font-size: 1.15rem;
            margin-bottom: 0.75rem;
        }

        .about-en {
            font-size: 0.85rem;
        }
    }
}

.about-cta {
    display: flex;
    flex-direction: column;
    align-items: flex-end;

    @media (max-width: 768px) {
        align-items: flex-start;
    }

    .cta-text {
        font-size: 1rem;
        opacity: 0.35;
        margin: 0 0 0.75rem;
    }

    .cta-link {
        font-size: 2.25rem;
        font-weight: bold;
        color: #1a1a1a;
        text-decoration: none;
        border-bottom: 2px solid rgba(0, 0, 0, 0.15);
        padding-bottom: 0.35rem;
        letter-spacing: -0.01em;
        transition: border-color 0.3s ease, opacity 0.3s ease;

        &:hover {
            border-color: rgba(0, 0, 0, 0.6);
        }

        @media (max-width: 640px) {
            font-size: 1.5rem;
        }
    }
}

</style>
