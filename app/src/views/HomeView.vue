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
                <section class="marquee">
                    <div v-if="marquee.length" class="marquee-inner">
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

                <!-- 服务能力 -->
                <section class="services">
                    <div class="services-top">
                        <div>
                            <SectionHeader zh="我们的服务" en="What We Do" />
                        </div>
                        <p class="services-intro">从 AI 应用到物联网，从架构设计到持续交付。<br />我们提供端到端的技术服务，帮助企业将想法变为现实。</p>
                    </div>
                    <div v-if="services.length" class="service-list">
                        <div v-for="(s, i) in services" :key="s.zh" class="service-row">
                            <div class="service-row-left">
                                <span class="service-row-num">{{ String(i + 1).padStart(2, '0') }}</span>
                                <h3 class="service-row-zh">{{ s.zh }}</h3>
                                <span class="service-row-en">{{ s.en }}</span>
                            </div>
                            <p class="service-row-desc">{{ s.desc }}</p>
                            <span class="service-row-arrow">→</span>
                        </div>
                    </div>
                    <div v-else class="empty-hint font-error">
                        <p class="en">No services yet</p>
                    </div>
                </section>

                <!-- 数据亮点 -->
                <section class="stats">
                    <div v-if="stats.length" class="stats-grid">
                        <StatItem v-for="s in stats" :key="s.num" :stat="s" />
                    </div>
                    <div v-else class="empty-hint font-error">
                        <p class="en">No stats yet</p>
                    </div>
                </section>

                <!-- 精选项目 -->
                <section class="projects">
                    <SectionHeader zh="精选项目" en="Selected Works" />
                    <div v-if="projects.length" class="project-grid">
                        <ProjectCard v-for="(p, i) in projects" :key="i" :project="p" :index="i" />
                    </div>
                    <div v-else class="empty-hint font-error">
                        <p class="en">No projects yet</p>
                    </div>
                </section>

                <!-- 工作流程 -->
                <section class="process">
                    <SectionHeader zh="工作流程" en="How We Work" />
                    <div v-if="process.length" class="process-track">
                        <ProcessStep v-for="(step, i) in process" :key="i" :step="step" :index="i" />
                    </div>
                    <div v-else class="empty-hint font-error">
                        <p class="en">No process steps yet</p>
                    </div>
                </section>

                <!-- 合作客户 -->
                <section class="clients">
                    <SectionHeader zh="合作客户" en="Clients & Partners" />
                    <div v-if="clients.length" class="client-grid">
                        <ClientCard v-for="c in clients" :key="c.name" :client="c" />
                    </div>
                    <div v-else class="empty-hint font-error">
                        <p class="en">No clients yet</p>
                    </div>
                </section>

                <!-- 品牌理念 — 全页 -->
                <section class="philosophy" v-if="philosophy && philosophy.zh">
                    <div class="philosophy-quote font-error">
                        <p class="quote-zh">{{ philosophy.zh }}</p>
                        <p class="quote-sub">{{ philosophy.sub }}</p>
                        <p class="quote-en">{{ philosophy.en }}</p>
                    </div>
                </section>

                <!-- 最新动态 -->
                <section class="news-preview">
                    <SectionHeader zh="最新动态" en="Latest" />
                    <div v-if="news.length" class="news-grid">
                        <NewsCard v-for="n in news" :key="n.id" :item="n" :to="`/news/${n.id}`" />
                    </div>
                    <div v-else class="empty-hint font-error">
                        <p class="en">No news yet</p>
                    </div>
                </section>

                <!-- 品牌简介 — 全页 -->
                <section class="about" v-if="about && about.zh && about.zh.length">
                    <SectionHeader zh="关于我们" en="About" />
                    <div class="about-grid">
                        <div class="about-text font-error">
                            <p v-for="(p, i) in about.zh" :key="'zh'+i">{{ p }}</p>
                            <p v-for="(p, i) in about.en" :key="'en'+i" class="about-en">{{ p }}</p>
                        </div>
                        <div v-if="about.cta" class="about-cta">
                            <p class="cta-text">{{ about.cta.zh }}</p>
                            <a v-if="about.cta.email" :href="'mailto:' + about.cta.email" class="cta-link">{{ about.cta.email }} →</a>
                        </div>
                    </div>
                </section>

                <SiteFooter v-if="footer && (footer.email || footer.phone || footer.address)" :footer="footer" />
            </template>
        </el-skeleton>
    </div>
</template>

<script setup>
import { Brain, Code, Cpu, Globe, CpuIcon, Compass } from '@lucide/vue';
import { gsap } from 'gsap';
import { ScrollTrigger } from 'gsap/ScrollTrigger';
import { onMounted, onUnmounted, ref, nextTick } from 'vue';
import { getHomeData } from '@/api/home';
import ClientCard from '@/components/home/ClientCard.vue';
import NewsCard from '@/components/home/NewsCard.vue';
import ProcessStep from '@/components/home/ProcessStep.vue';
import ProjectCard from '@/components/home/ProjectCard.vue';
import SiteFooter from '@/components/home/Footer.vue';
import SectionHeader from '@/components/shared/SectionHeader.vue';
import ServiceCard from '@/components/home/ServiceCard.vue';
import StatItem from '@/components/home/StatItem.vue';

gsap.registerPlugin(ScrollTrigger);

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

const iconMap = { brain: Brain, code: Code, cpu: Cpu, chip: Cpu, compass: Compass, globe: Globe };
let scrollTriggers = [];

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

    // Hero 简单淡入（不依赖 SplitText 付费插件）
    gsap.fromTo('.hero-text',
        { y: 30, opacity: 0 },
        { y: 0, opacity: 1, duration: 1, ease: 'power3.out' }
    );

    const animate = (selector, fromVars, toVars, start) => {
        const elements = document.querySelectorAll(selector);
        if (!elements.length) return;
        const st = ScrollTrigger.create({
            trigger: elements[0].closest('section') || elements[0],
            start: start || 'top 80%',
            once: true,
            onEnter: () => {
                gsap.fromTo(elements, fromVars, { ...toVars, duration: toVars.duration || 0.8 });
            }
        });
        scrollTriggers.push(st);
    };

    animate('.service-card',
        { y: 60, opacity: 0, scale: 0.95 },
        { y: 0, opacity: 1, scale: 1, stagger: 0.1, duration: 0.8, ease: 'power3.out' },
        'top 75%'
    );

    animate('.project-card',
        { y: 60, opacity: 0 },
        { y: 0, opacity: 1, stagger: 0.12, duration: 0.8, ease: 'power3.out' },
        'top 75%'
    );

    animate('.process-step',
        { y: 40, opacity: 0 },
        { y: 0, opacity: 1, stagger: 0.12, duration: 0.7, ease: 'power3.out' },
        'top 80%'
    );

    animate('.client-card',
        { y: 20, opacity: 0 },
        { y: 0, opacity: 1, stagger: 0.06, duration: 0.5, ease: 'power3.out' },
        'top 85%'
    );

    animate('.news-card',
        { y: 20, opacity: 0 },
        { y: 0, opacity: 1, stagger: 0.08, duration: 0.5, ease: 'power3.out' },
        'top 85%'
    );

    animate('.stat-item',
        { y: 40, opacity: 0, scale: 0.9 },
        { y: 0, opacity: 1, scale: 1, stagger: 0.12, duration: 0.7, ease: 'power3.out' },
        'top 80%'
    );

    animate('.philosophy-quote',
        { y: 30, opacity: 0 },
        { y: 0, opacity: 1, duration: 0.8, ease: 'power3.out' },
        'top 70%'
    );

    animate('.about-text p',
        { y: 30, opacity: 0 },
        { y: 0, opacity: 1, stagger: 0.1, duration: 0.6, ease: 'power3.out' },
        'top 70%'
    );
});

onUnmounted(() => {
    scrollTriggers.forEach(st => st.kill());
    scrollTriggers = [];
});
</script>

<style lang="scss" scoped>
.home {
    color: #e5e5e5;
    background: #0a0a0a;
}

.empty-hint {
    text-align: center;
    padding: 3rem 2rem;
    opacity: 0.3;
    font-size: 0.9rem;
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
        background: rgba(0, 0, 0, 0.4);
    }

    .hero-text {
        position: relative;
        z-index: 1;
        color: #fff;
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
        opacity: 0.7;
    }

    .hero-sub {
        font-size: clamp(0.85rem, 1.5vw, 1.15rem);
        margin-top: 1.5rem;
        opacity: 0.5;
        max-width: 600px;
        line-height: 1.6;
    }

    .scroll-hint {
        position: absolute;
        bottom: 2rem;
        z-index: 1;
        font-size: 1.25rem;
        color: rgba(255, 255, 255, 0.5);
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
    border-top: 1px solid rgba(255, 255, 255, 0.08);
    border-bottom: 1px solid rgba(255, 255, 255, 0.08);
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
        color: #fff;
    }

    .marquee-divider {
        font-size: 1.5rem;
        color: rgba(255, 255, 255, 0.2);
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

// ---------- Services ----------
.services {
    padding: 5rem 2rem 3rem;
}

.services-top {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
    gap: 2rem;
    margin-bottom: 2rem;

    .services-intro {
        max-width: 380px;
        font-size: 0.95rem;
        line-height: 1.7;
        opacity: 0.45;
        margin: 0;
        color: #e5e5e5;
        flex-shrink: 0;
    }
}

.service-list {
    display: flex;
    flex-direction: column;
}

.service-row {
    display: grid;
    grid-template-columns: 1fr 1fr 32px;
    align-items: center;
    gap: 2rem;
    padding: 1.25rem 0;
    border-bottom: 1px solid rgba(255, 255, 255, 0.06);
    cursor: pointer;
    transition: all 0.25s ease;

    &:last-child {
        border-bottom: none;
    }

    &:hover {
        padding-left: 0.5rem;

        .service-row-arrow {
            opacity: 0.8;
            transform: translateX(4px);
        }
    }

    .service-row-left {
        display: flex;
        align-items: baseline;
        gap: 1rem;
        min-width: 0;
    }

    .service-row-num {
        font-size: 0.75rem;
        font-weight: 500;
        opacity: 0.25;
        font-family: 'JetBrainsMono', monospace;
        color: #e5e5e5;
        flex-shrink: 0;
    }

    .service-row-zh {
        margin: 0;
        font-size: 1.15rem;
        font-weight: 700;
        letter-spacing: -0.01em;
        color: #fff;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .service-row-en {
        font-size: 0.65rem;
        opacity: 0.35;
        text-transform: uppercase;
        letter-spacing: 0.08em;
        font-weight: 500;
        color: #e5e5e5;
        white-space: nowrap;
        flex-shrink: 0;
    }

    .service-row-desc {
        margin: 0;
        font-size: 0.85rem;
        opacity: 0.45;
        line-height: 1.5;
        color: #e5e5e5;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        overflow: hidden;
    }

    .service-row-arrow {
        font-size: 1rem;
        opacity: 0;
        transition: opacity 0.25s ease, transform 0.25s ease;
        color: #fff;
        text-align: right;
    }

    @media (max-width: 768px) {
        grid-template-columns: 1fr;
        gap: 0.5rem;
        padding: 1rem 0;

        .service-row-left {
            gap: 0.75rem;
        }

        .service-row-desc,
        .service-row-arrow {
            display: none;
        }

        .service-row-zh {
            font-size: 1rem;
        }
    }
}

// ---------- Stats ----------
.stats {
    padding: 4rem 2rem;
    border-top: 1px solid rgba(255, 255, 255, 0.06);
    border-bottom: 1px solid rgba(255, 255, 255, 0.06);
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
    grid-template-columns: repeat(2, 1fr);
    gap: 1.5rem;

    @media (max-width: 1024px) {
        grid-template-columns: repeat(2, 1fr);
    }
    @media (max-width: 640px) {
        grid-template-columns: 1fr;
    }
}

.project-grid:hover :deep(.project-card:not(:hover)) {
    opacity: 0.5;
}

.project-grid :deep(.project-card:hover) {
    transform: scale(1.02);
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
    border-top: 1px dashed rgba(255, 255, 255, 0.15);
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
        border-left: 1px dashed rgba(255, 255, 255, 0.15);
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
    border-top: 1px solid rgba(255, 255, 255, 0.06);

    .philosophy-quote {
        text-align: center;
        max-width: 750px;

        .quote-zh {
            font-size: clamp(1.75rem, 4.5vw, 3rem);
            font-weight: bold;
            line-height: 1.5;
            margin: 0 0 2rem;
            letter-spacing: -0.02em;
            color: #fff;
        }

        .quote-sub {
            font-size: 1rem;
            opacity: 0.5;
            line-height: 1.7;
            margin: 0 0 1.5rem;
            max-width: 550px;
            margin-left: auto;
            margin-right: auto;
        }

        .quote-en {
            font-size: 1.15rem;
            opacity: 0.4;
            margin: 0;
            line-height: 1.6;
        }
    }
}

// ---------- News ----------
.news-preview {
    padding: 5rem 2rem;
    border-top: 1px solid rgba(255, 255, 255, 0.06);
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
    border-top: 1px solid rgba(255, 255, 255, 0.06);
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
        color: #fff;
    }

    .about-en {
        font-size: 1rem;
        opacity: 0.4;
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
        opacity: 0.5;
        margin: 0 0 0.75rem;
    }

    .cta-link {
        font-size: 2.25rem;
        font-weight: bold;
        color: #fff;
        text-decoration: none;
        border-bottom: 2px solid rgba(255, 255, 255, 0.2);
        padding-bottom: 0.35rem;
        letter-spacing: -0.01em;
        transition: border-color 0.3s ease, opacity 0.3s ease;

        &:hover {
            border-color: rgba(255, 255, 255, 0.6);
        }

        @media (max-width: 640px) {
            font-size: 1.5rem;
        }
    }
}

</style>
