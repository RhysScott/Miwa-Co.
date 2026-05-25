<template>
    <div class="home">
        <!-- Hero 大字标语 -->
        <section class="hero">
            <div class="hero-text font-error">
                <h1 class="hero-zh">用技术驱动未来</h1>
                <p class="hero-en">AI · Software · IoT — We build what's next.</p>
            </div>
            <div class="scroll-hint" aria-hidden="true">↓</div>
        </section>

        <!-- 横向滚动服务词条 -->
        <section class="marquee">
            <div class="marquee-inner">
                <div class="marquee-track">
                    <span class="marquee-item">人工智能</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">AI</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">软件开发</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">Software</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">物联网</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">IoT</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">云计算</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">Cloud</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">数字转型</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">DX</span>
                </div>
                <div class="marquee-track" aria-hidden="true">
                    <span class="marquee-item">人工智能</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">AI</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">软件开发</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">Software</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">物联网</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">IoT</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">云计算</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">Cloud</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">数字转型</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">DX</span>
                </div>
            </div>
        </section>

        <!-- 服务能力 -->
        <section class="services">
            <div class="section-header font-error">
                <span class="label-zh">我们的服务</span>
                <span class="label-en">What We Do</span>
            </div>
            <div class="service-grid">
                <div class="service-card" v-for="s in services" :key="s.zh">
                    <component :is="iconMap[s.icon]" class="service-icon" :size="28" />
                    <h3 class="service-zh">{{ s.zh }}</h3>
                    <p class="service-en">{{ s.en }}</p>
                    <p class="service-desc">{{ s.desc }}</p>
                </div>
            </div>
        </section>

        <!-- 数据亮点 -->
        <section class="stats">
            <div class="stats-grid">
                <div class="stat-item" v-for="s in stats" :key="s.num">
                    <span class="stat-num">{{ s.num }}</span>
                    <span class="stat-label">{{ s.label }}</span>
                </div>
            </div>
        </section>

        <!-- 精选项目 -->
        <section class="projects">
            <div class="section-header font-error">
                <span class="label-zh">精选项目</span>
                <span class="label-en">Selected Works</span>
            </div>
            <div class="project-grid">
                <div
                    class="project-card"
                    v-for="(p, i) in projects"
                    :key="i"
                    :style="{ backgroundImage: `url(${p.image})` }"
                >
                    <div class="card-gradient">
                        <span class="card-num">0{{ i + 1 }}</span>
                        <div class="card-info">
                            <span class="card-zh">{{ p.zh }}</span>
                            <span class="card-en">{{ p.en }}</span>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- 工作流程 -->
        <section class="process">
            <div class="section-header font-error">
                <span class="label-zh">工作流程</span>
                <span class="label-en">How We Work</span>
            </div>
            <div class="process-track">
                <div class="process-step" v-for="(step, i) in process" :key="i">
                    <div class="step-marker">
                        <span class="step-num">0{{ i + 1 }}</span>
                    </div>
                    <div class="step-text">
                        <span class="step-zh">{{ step.zh }}</span>
                        <span class="step-en">{{ step.en }}</span>
                    </div>
                </div>
            </div>
        </section>

        <!-- 合作客户 -->
        <section class="clients">
            <div class="section-header font-error">
                <span class="label-zh">合作客户</span>
                <span class="label-en">Clients & Partners</span>
            </div>
            <div class="client-grid">
                <div class="client-card" v-for="c in clients" :key="c.name">
                    <span class="client-zh">{{ c.name }}</span>
                    <span class="client-en">{{ c.en }}</span>
                </div>
            </div>
        </section>

        <!-- 品牌理念 -->
        <section class="philosophy">
            <div class="philosophy-quote font-error">
                <p class="quote-zh">"技术不应是黑箱，它应当透明、可靠、为人所用"</p>
                <p class="quote-en">Technology should not be a black box. It should be transparent, reliable, and human.</p>
            </div>
        </section>

        <!-- 最新动态 -->
        <section class="news-preview">
            <div class="section-header font-error">
                <span class="label-zh">最新动态</span>
                <span class="label-en">Latest</span>
            </div>
            <div class="news-list">
                <router-link to="/news" class="news-item" v-for="n in news" :key="n.date">
                    <span class="news-date">{{ n.date }}</span>
                    <span class="news-title">{{ n.title }}</span>
                    <span class="news-arrow">→</span>
                </router-link>
            </div>
        </section>

        <!-- 品牌简介 -->
        <section class="about">
            <div class="section-header font-error">
                <span class="label-zh">关于我们</span>
                <span class="label-en">About</span>
            </div>
            <div class="about-grid">
                <div class="about-text font-error">
                    <p>Miwa & Co. 是一家技术驱动的创新公司，</p>
                    <p>专注 AI 应用、软件工程与物联网解决方案。</p>
                    <p>我们相信技术的力量在于让它变得简单可靠。</p>
                    <p class="about-en">A tech-driven company focused on AI applications,</p>
                    <p class="about-en">software engineering, and IoT solutions.</p>
                </div>
                <div class="about-cta">
                    <p class="cta-text">有项目想聊？</p>
                    <a href="mailto:hello@miwa-co.com" class="cta-link">hello@miwa-co.com →</a>
                </div>
            </div>
        </section>

        <!-- 页脚 -->
        <footer class="footer">
            <span>© 2026 Miwa & Co.</span>
            <span>Shanghai, China</span>
        </footer>
    </div>
</template>

<script setup>
import { Brain, Code, Cpu, Globe } from '@lucide/vue';
import { gsap } from 'gsap';
import { ScrollTrigger } from 'gsap/ScrollTrigger';
import { SplitText } from 'gsap/SplitText';
import { onMounted, ref } from 'vue';
import { getServices } from '@/api/services';
import { getProjects } from '@/api/projects';
import { getClients } from '@/api/clients';
import { getStats } from '@/api/stats';
import { getNewsList } from '@/api/news';
import { getProcess } from '@/api/process';

gsap.registerPlugin(SplitText, ScrollTrigger);

const services = ref([]);
const projects = ref([]);
const process = ref([]);
const clients = ref([]);
const news = ref([]);
const stats = ref([]);

const iconMap = { brain: Brain, code: Code, cpu: Cpu, globe: Globe };

onMounted(async () => {
    // 通过各 API 模块获取数据
    const results = await Promise.all([getServices(), getProjects(), getProcess(), getClients(), getNewsList(), getStats()]);
    [services.value, projects.value, process.value, clients.value, news.value, stats.value] = results;

    // Hero 逐字弹跳入场
    const heroSplit = SplitText.create('.hero-zh, .hero-en', { type: 'chars,words' });
    gsap.fromTo(heroSplit.chars,
        { y: 80, opacity: 0 },
        { y: 0, opacity: 1, stagger: 0.02, duration: 0.8, ease: 'elastic.out(1, 0.5)' }
    );

    // 服务卡片滚动入场
    gsap.fromTo('.service-card',
        { y: 60, opacity: 0 },
        {
            y: 0, opacity: 1, stagger: 0.12, duration: 0.7, ease: 'power3.out',
            scrollTrigger: { trigger: '.services', start: 'top 80%' }
        }
    );

    // 项目卡片滚动入场
    gsap.fromTo('.project-card',
        { y: 80, opacity: 0 },
        {
            y: 0, opacity: 1, stagger: 0.15, duration: 0.8, ease: 'elastic.out(1, 0.5)',
            scrollTrigger: { trigger: '.projects', start: 'top 80%' }
        }
    );

    // 流程卡片滚动入场
    gsap.fromTo('.process-step',
        { x: -40, opacity: 0 },
        {
            x: 0, opacity: 1, stagger: 0.2, duration: 0.6, ease: 'power3.out',
            scrollTrigger: { trigger: '.process', start: 'top 80%' }
        }
    );

    // 客户名称滚动入场
    gsap.fromTo('.client-card',
        { y: 30, opacity: 0 },
        {
            y: 0, opacity: 1, stagger: 0.05, duration: 0.5, ease: 'power3.out',
            scrollTrigger: { trigger: '.clients', start: 'top 85%' }
        }
    );

    // 新闻列表滚动入场
    gsap.fromTo('.news-item',
        { y: 30, opacity: 0 },
        {
            y: 0, opacity: 1, stagger: 0.1, duration: 0.5, ease: 'power3.out',
            scrollTrigger: { trigger: '.news-preview', start: 'top 85%' }
        }
    );

    // 数据数字滚动入场
    gsap.fromTo('.stat-item',
        { y: 40, opacity: 0 },
        {
            y: 0, opacity: 1, stagger: 0.15, duration: 0.6, ease: 'power3.out',
            scrollTrigger: { trigger: '.stats', start: 'top 85%' }
        }
    );

    // 理念语录入场
    const quoteSplit = SplitText.create('.quote-zh, .quote-en', { type: 'chars,words' });
    gsap.fromTo(quoteSplit.chars,
        { y: 40, opacity: 0 },
        {
            y: 0, opacity: 1, stagger: 0.015, duration: 0.6, ease: 'power3.out',
            scrollTrigger: { trigger: '.philosophy', start: 'top 80%' }
        }
    );

    // About 文本滚动入场
    const aboutSplit = SplitText.create('.about-text p', { type: 'lines' });
    gsap.fromTo(aboutSplit.lines,
        { y: 40, opacity: 0 },
        {
            y: 0, opacity: 1, stagger: 0.1, duration: 0.6, ease: 'power3.out',
            scrollTrigger: { trigger: '.about', start: 'top 85%' }
        }
    );
});
</script>

<style lang="scss" scoped>
.home {
    color: white;
    background: #0d0d0d;
}

// Hero
.hero {
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: center;
    padding: 2rem;
    background: url('/images/5.jpg') center / cover no-repeat;
    position: relative;

    &::before {
        content: '';
        position: absolute;
        inset: 0;
        background: rgba(0, 0, 0, 0.35);
    }

    .hero-text {
        position: relative;
        z-index: 1;
    }

    .hero-zh {
        font-size: clamp(3rem, 8vw, 8rem);
        margin: 0;
        line-height: 1.1;
    }

    .hero-en {
        font-size: clamp(1rem, 2.5vw, 2rem);
        margin-top: 1rem;
        opacity: 0.6;
    }

    .scroll-hint {
        position: absolute;
        bottom: 2rem;
        z-index: 1;
        font-size: 1.25rem;
        opacity: 0.3;
        animation: float 2s ease-in-out infinite;
    }
}

@keyframes float {
    0%, 100% { transform: translateY(0); }
    50% { transform: translateY(8px); }
}

// Shared section header
.section-header {
    margin-bottom: 3rem;

    .label-zh {
        font-size: 0.8rem;
        opacity: 0.35;
        display: block;
        text-transform: uppercase;
        letter-spacing: 0.12em;
    }

    .label-en {
        font-size: 2.5rem;
    }
}

// Marquee
.marquee {
    width: 100%;
    overflow: hidden;
    border-top: 1px solid rgba(255, 255, 255, 0.06);
    border-bottom: 1px solid rgba(255, 255, 255, 0.06);
    padding: 2.5rem 0;

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
        color: rgba(255, 255, 255, 0.12);
    }
}

@keyframes marquee {
    from { transform: translateX(0); }
    to { transform: translateX(-50%); }
}

// Services
.services {
    padding: 8rem 2rem;
}

.service-grid {
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

.service-card {
    background: rgba(255, 255, 255, 0.03);
    border: 1px solid rgba(255, 255, 255, 0.06);
    border-radius: 24px;
    padding: 2.5rem 2rem;
    transition: background 0.4s ease, transform 0.4s ease, border-color 0.4s ease;

    &:hover {
        background: rgba(255, 255, 255, 0.06);
        border-color: rgba(255, 255, 255, 0.18);
        transform: translateY(-6px);
    }

    .service-icon {
        margin-bottom: 2rem;
        opacity: 0.5;
        transition: opacity 0.4s ease;
    }

    &:hover .service-icon {
        opacity: 0.9;
    }

    .service-zh {
        margin: 0 0 0.3rem;
        font-size: 1.35rem;
    }

    .service-en {
        margin: 0 0 1rem;
        font-size: 0.8rem;
        opacity: 0.3;
        text-transform: uppercase;
        letter-spacing: 0.05em;
    }

    .service-desc {
        margin: 0;
        font-size: 0.9rem;
        opacity: 0.45;
        line-height: 1.7;
    }
}

// Process
.process {
    padding: 6rem 2rem;
}

.process-track {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 1.5rem;

    @media (max-width: 768px) {
        grid-template-columns: 1fr;
    }
}

.process-step {
    display: flex;
    flex-direction: column;
    gap: 1.25rem;
    padding: 2rem;
    border: 1px solid rgba(255, 255, 255, 0.06);
    border-radius: 20px;
    transition: border-color 0.4s ease, background 0.4s ease;

    &:hover {
        border-color: rgba(255, 255, 255, 0.15);
        background: rgba(255, 255, 255, 0.02);
    }

    .step-marker {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        background: rgba(255, 255, 255, 0.06);
        display: flex;
        align-items: center;
        justify-content: center;
        flex-shrink: 0;
    }

    .step-num {
        font-size: 0.75rem;
        opacity: 0.4;
        font-weight: bold;
    }

    .step-text {
        display: flex;
        flex-direction: column;
        gap: 0.3rem;
    }

    .step-zh {
        font-size: 1.35rem;
        font-weight: bold;
    }

    .step-en {
        font-size: 0.8rem;
        opacity: 0.35;
    }
}

// Projects
.projects {
    padding: 6rem 2rem 8rem;
}

.project-grid {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 1.5rem;

    @media (max-width: 768px) {
        grid-template-columns: 1fr;
    }
}

.project-card {
    aspect-ratio: 4 / 3;
    background-size: cover;
    background-position: center;
    border-radius: 20px;
    position: relative;
    cursor: pointer;
    overflow: hidden;
    transition:
        transform 0.5s cubic-bezier(0.25, 0.1, 0.25, 1),
        opacity 0.5s ease;

    .card-gradient {
        position: absolute;
        inset: 0;
        background: linear-gradient(to top, rgba(0, 0, 0, 0.65) 0%, transparent 50%);
        padding: 1.5rem;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    .card-num {
        font-size: 0.75rem;
        opacity: 0.4;
        letter-spacing: 0.05em;
    }

    .card-info {
        display: flex;
        flex-direction: column;
        gap: 0.25rem;

        .card-zh {
            font-size: 1.75rem;
            font-weight: bold;
        }

        .card-en {
            font-size: 0.875rem;
            opacity: 0.45;
        }
    }
}

.project-grid:hover .project-card:not(:hover) {
    opacity: 0.35;
}

.project-card:hover {
    transform: scale(1.03);
    z-index: 2;
}

// Stats
.stats {
    padding: 5rem 2rem;
    border-top: 1px solid rgba(255, 255, 255, 0.05);
    border-bottom: 1px solid rgba(255, 255, 255, 0.05);
}

.stats-grid {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 2rem;
    max-width: 900px;
    margin: 0 auto;

    @media (max-width: 640px) {
        grid-template-columns: repeat(2, 1fr);
    }
}

.stat-item {
    text-align: center;
    display: flex;
    align-items: baseline;
    justify-content: center;
    gap: 0.5rem;

    .stat-num {
        font-size: 3rem;
        font-weight: bold;
        white-space: nowrap;
        background: linear-gradient(135deg, rgba(255,255,255,0.9), rgba(255,255,255,0.5));
        background-clip: text;
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
    }

    .stat-label {
        font-size: 0.85rem;
        opacity: 0.4;
        white-space: nowrap;
    }
}

// Clients
.clients {
    padding: 6rem 2rem;
}

.client-grid {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 1.5rem;

    @media (max-width: 768px) {
        grid-template-columns: repeat(2, 1fr);
    }
}

.client-card {
    display: flex;
    flex-direction: column;
    gap: 0.3rem;
    padding: 1.5rem;
    border: 1px solid rgba(255, 255, 255, 0.05);
    border-radius: 16px;
    transition: border-color 0.4s ease, background 0.4s ease;

    &:hover {
        border-color: rgba(255, 255, 255, 0.2);
        background: rgba(255, 255, 255, 0.03);
    }

    .client-zh {
        font-size: 1.5rem;
        font-weight: bold;
    }

    .client-en {
        font-size: 0.7rem;
        opacity: 0.3;
        letter-spacing: 0.08em;
    }
}

// Philosophy
.philosophy {
    padding: 8rem 2rem;
    display: flex;
    justify-content: center;

    .philosophy-quote {
        text-align: center;
        max-width: 700px;

        .quote-zh {
            font-size: clamp(1.5rem, 4vw, 2.5rem);
            line-height: 1.6;
            margin: 0 0 1.5rem;
            opacity: 0.9;
        }

        .quote-en {
            font-size: 1.1rem;
            opacity: 0.35;
            margin: 0;
        }
    }
}

// News Preview
.news-preview {
    padding: 6rem 2rem;
    border-top: 1px solid rgba(255, 255, 255, 0.05);
}

.news-list {
    display: flex;
    flex-direction: column;
    max-width: 700px;
}

.news-item {
    display: flex;
    align-items: baseline;
    gap: 1.5rem;
    padding: 1.25rem 0;
    border-bottom: 1px solid rgba(255, 255, 255, 0.05);
    color: white;
    text-decoration: none;
    transition: opacity 0.3s ease;

    &:hover {
        opacity: 0.6;
    }

    .news-date {
        font-size: 0.8rem;
        opacity: 0.35;
        flex-shrink: 0;
        width: 5rem;
    }

    .news-title {
        font-size: 1.25rem;
        flex: 1;
    }

    .news-arrow {
        font-size: 1rem;
        opacity: 0;
        transition: opacity 0.3s ease;
    }

    &:hover .news-arrow {
        opacity: 0.4;
    }
}

// About
.about {
    padding: 8rem 2rem 4rem;
    border-top: 1px solid rgba(255, 255, 255, 0.05);
}

.about-grid {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 4rem;

    @media (max-width: 768px) {
        grid-template-columns: 1fr;
        gap: 2rem;
    }
}

.about-text {
    p {
        margin: 0 0 1.25rem;
        font-size: 1.5rem;
        line-height: 1.5;
    }

    .about-en {
        font-size: 1rem;
        opacity: 0.3;
        margin-bottom: 0.5rem;
    }
}

.about-cta {
    display: flex;
    flex-direction: column;
    justify-content: flex-end;

    .cta-text {
        font-size: 1rem;
        opacity: 0.4;
        margin: 0 0 0.5rem;
    }

    .cta-link {
        font-size: 2rem;
        color: white;
        text-decoration: none;
        border-bottom: 1px solid rgba(255, 255, 255, 0.2);
        padding-bottom: 0.25rem;
        transition: border-color 0.3s ease, opacity 0.3s ease;
        display: inline-block;

        &:hover {
            border-color: rgba(255, 255, 255, 0.6);
        }
    }
}

// Footer
.footer {
    display: flex;
    justify-content: space-between;
    padding: 2rem;
    border-top: 1px solid rgba(255, 255, 255, 0.05);
    font-size: 0.8rem;
    opacity: 0.3;
}
</style>
