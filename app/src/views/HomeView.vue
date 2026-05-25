<template>
    <div class="home">
        <!-- Hero 大字标语 -->
        <section class="hero">
            <div class="hero-text font-error">
                <h1 class="hero-zh">用技术驱动未来</h1>
                <p class="hero-en">AI · Software · IoT — We build what's next.</p>
                <p class="hero-sub">我们相信，每一行代码都应当创造真实价值。从 AI 到物联网，从上海到世界。</p>
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

        <!-- 服务能力 — 全页 -->
        <section class="services" v-if="services.length">
            <SectionHeader zh="我们的服务" en="What We Do" />
            <div class="service-grid">
                <div class="service-card" v-for="s in services" :key="s.zh">
                    <div class="card-icon-ring">
                        <img v-if="s.image" :src="s.image" :alt="s.zh" class="service-img" />
                        <component v-else :is="iconMap[s.icon]" :size="32" />
                    </div>
                    <h3 class="service-zh">{{ s.zh }}</h3>
                    <p class="service-en">{{ s.en }}</p>
                    <p class="service-desc">{{ s.desc }}</p>
                </div>
            </div>
        </section>

        <!-- 数据亮点 -->
        <section class="stats" v-if="stats.length">
            <div class="stats-grid">
                <div class="stat-item" v-for="s in stats" :key="s.num">
                    <span class="stat-num">{{ s.num }}</span>
                    <span class="stat-label">{{ s.label }}</span>
                </div>
            </div>
        </section>

        <!-- 精选项目 -->
        <section class="projects" v-if="projects.length">
            <SectionHeader zh="精选项目" en="Selected Works" />
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
                            <span class="card-desc">{{ p.desc }}</span>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- 工作流程 -->
        <section class="process" v-if="process.length">
            <SectionHeader zh="工作流程" en="How We Work" />
            <div class="process-track">
                <div class="process-step" v-for="(step, i) in process" :key="i">
                    <div class="step-marker">
                        <span class="step-num">0{{ i + 1 }}</span>
                    </div>
                    <div class="step-text">
                        <span class="step-zh">{{ step.zh }}</span>
                        <span class="step-en">{{ step.en }}</span>
                        <span class="step-desc">{{ step.desc }}</span>
                    </div>
                </div>
            </div>
        </section>

        <!-- 合作客户 -->
        <section class="clients" v-if="clients.length">
            <SectionHeader zh="合作客户" en="Clients & Partners" />
            <div class="client-grid">
                <div class="client-card" v-for="c in clients" :key="c.name">
                    <img v-if="c.image" :src="c.image" :alt="c.name" class="client-logo" />
                    <div v-else class="client-avatar">{{ c.name[0] }}</div>
                    <div class="client-text">
                        <span class="client-zh">{{ c.name }}</span>
                        <span class="client-en">{{ c.en }}</span>
                    </div>
                </div>
            </div>
        </section>

        <!-- 品牌理念 — 全页 -->
        <section class="philosophy">
            <div class="philosophy-quote font-error">
                <p class="quote-zh">"技术不应是黑箱，它应当透明、可靠、为人所用"</p>
                <p class="quote-sub">这是 Miwa 每一个项目背后的核心信念。我们拒绝过度包装，坚持技术向善，让复杂的事情变得清晰简单。</p>
                <p class="quote-en">Technology should not be a black box. It should be transparent, reliable, and human.</p>
            </div>
        </section>

        <!-- 最新动态 -->
        <section class="news-preview" v-if="news.length">
            <SectionHeader zh="最新动态" en="Latest" />
            <div class="news-grid">
                <router-link to="/news" class="news-card" v-for="(n, idx) in news" :key="idx">
                    <img v-if="n.image" :src="n.image" :alt="n.title" class="news-thumb" />
                    <span class="news-date">{{ n.date }}</span>
                    <span class="news-title">{{ n.title }}</span>
                    <span class="news-arrow">→</span>
                </router-link>
            </div>
        </section>

        <!-- 品牌简介 — 全页 -->
        <section class="about">
            <SectionHeader zh="关于我们" en="About" />
            <div class="about-grid">
                <div class="about-text font-error">
                    <p>Miwa & Co. 是一家技术驱动的创新公司，成立于上海。</p>
                    <p>我们专注 AI 应用、软件工程与物联网解决方案，帮助企业跨越从技术到产品的最后一公里。</p>
                    <p>我们的团队来自全球顶尖科技公司与实验室，相信技术的力量在于让它变得简单、可靠、可及。</p>
                    <p class="about-en">A tech-driven company born in Shanghai, focused on AI applications,</p>
                    <p class="about-en">software engineering, and IoT solutions. We exist to close the gap</p>
                    <p class="about-en">between technology and real-world impact.</p>
                </div>
                <div class="about-cta">
                    <p class="cta-text">有项目想聊？</p>
                    <a href="mailto:hello@miwa-co.com" class="cta-link">hello@miwa-co.com →</a>
                </div>
            </div>
        </section>

        <!-- 页脚 -->
        <footer class="footer">
            <span>&copy; 2026 Miwa & Co.</span>
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
import { getHomeData } from '@/api/home';
import SectionHeader from '@/components/SectionHeader.vue';

gsap.registerPlugin(SplitText, ScrollTrigger);

const services = ref([]);
const projects = ref([]);
const process = ref([]);
const clients = ref([]);
const news = ref([]);
const stats = ref([]);

const iconMap = { brain: Brain, code: Code, cpu: Cpu, globe: Globe };

onMounted(async () => {
    const data = await getHomeData();
    services.value = data.services;
    projects.value = data.projects;
    process.value = data.process;
    clients.value = data.clients;
    news.value = data.news;
    stats.value = data.stats;

    // Hero 逐字弹跳入场
    const heroSplit = SplitText.create('.hero-zh, .hero-en, .hero-sub', { type: 'chars,words' });
    gsap.fromTo(heroSplit.chars,
        { y: 80, opacity: 0 },
        { y: 0, opacity: 1, stagger: 0.02, duration: 0.8, ease: 'elastic.out(1, 0.5)' }
    );

    // 服务卡片滚动入场
    gsap.fromTo('.service-card',
        { y: 80, opacity: 0 },
        {
            y: 0, opacity: 1, stagger: 0.1, duration: 0.8, ease: 'power3.out',
            scrollTrigger: { trigger: '.services', start: 'top 70%' }
        }
    );

    // 项目卡片滚动入场
    gsap.fromTo('.project-card',
        { y: 100, opacity: 0, scale: 0.95 },
        {
            y: 0, opacity: 1, scale: 1, stagger: 0.15, duration: 0.9, ease: 'power3.out',
            scrollTrigger: { trigger: '.projects', start: 'top 75%' }
        }
    );

    // 流程卡片滚动入场
    gsap.fromTo('.process-step',
        { y: 60, opacity: 0 },
        {
            y: 0, opacity: 1, stagger: 0.15, duration: 0.7, ease: 'power3.out',
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

.service-card {
    background: #fff;
    border: 1px solid rgba(0, 0, 0, 0.06);
    border-radius: 32px;
    padding: 2.5rem 2rem;
    transition:
        transform 0.5s cubic-bezier(0.25, 0.1, 0.25, 1),
        box-shadow 0.5s ease,
        border-color 0.5s ease;

    &:hover {
        transform: translateY(-8px);
        box-shadow: 0 20px 60px rgba(0, 0, 0, 0.08);
        border-color: rgba(0, 0, 0, 0.12);
    }

    .card-icon-ring {
        width: 56px;
        height: 56px;
        border-radius: 16px;
        background: rgba(0, 0, 0, 0.04);
        display: flex;
        align-items: center;
        justify-content: center;
        margin-bottom: 2rem;
        color: rgba(0, 0, 0, 0.55);
        overflow: hidden;
        transition: background 0.5s ease, color 0.5s ease, transform 0.5s ease;
    }

    .service-img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    &:hover .card-icon-ring {
        background: rgba(0, 0, 0, 0.08);
        color: rgba(0, 0, 0, 0.85);
        transform: scale(1.05);
    }

    .service-zh {
        margin: 0 0 0.25rem;
        font-size: 1.5rem;
        font-weight: bold;
        letter-spacing: -0.01em;
    }

    .service-en {
        margin: 0 0 1.25rem;
        font-size: 0.75rem;
        opacity: 0.3;
        text-transform: uppercase;
        letter-spacing: 0.08em;
    }

    .service-desc {
        margin: 0;
        font-size: 0.9rem;
        opacity: 0.45;
        line-height: 1.75;
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

.stat-item {
    text-align: center;
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 0.5rem;

    .stat-num {
        font-size: 4.5rem;
        font-weight: 800;
        line-height: 1;
        letter-spacing: -0.03em;
        background: linear-gradient(180deg, rgba(0,0,0,0.85) 0%, rgba(0,0,0,0.45) 100%);
        background-clip: text;
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
    }

    .stat-label {
        font-size: 0.85rem;
        opacity: 0.35;
        font-weight: 500;
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

.project-card {
    aspect-ratio: 3 / 2;
    background-size: cover;
    background-position: center;
    border-radius: 24px;
    position: relative;
    cursor: pointer;
    overflow: hidden;
    transition:
        transform 0.6s cubic-bezier(0.25, 0.1, 0.25, 1),
        box-shadow 0.6s ease,
        opacity 0.5s ease;

    .card-gradient {
        position: absolute;
        inset: 0;
        background: linear-gradient(to top, rgba(0,0,0,0.7) 0%, rgba(0,0,0,0.05) 55%, transparent 100%);
        padding: 1.5rem;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    .card-num {
        font-size: 0.75rem;
        font-weight: 600;
        letter-spacing: 0.06em;
        color: rgba(255, 255, 255, 0.55);
    }

    .card-info {
        display: flex;
        flex-direction: column;
        gap: 0.2rem;
        color: #fff;

        .card-zh {
            font-size: 1.35rem;
            font-weight: bold;
            letter-spacing: -0.01em;
        }

        .card-en {
            font-size: 0.75rem;
            opacity: 0.5;
        }

        .card-desc {
            font-size: 0.75rem;
            opacity: 0.35;
            margin-top: 0.35rem;
            line-height: 1.5;
        }
    }
}

.project-grid:hover .project-card:not(:hover) {
    opacity: 0.3;
}

.project-card:hover {
    transform: scale(1.02);
    box-shadow: 0 24px 60px rgba(0, 0, 0, 0.13);
    z-index: 2;
}

// ---------- Process ----------
.process {
    padding: 5rem 2rem;
}

.process-track {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 0;

    @media (max-width: 768px) {
        grid-template-columns: 1fr;
    }
}

.process-step {
    display: flex;
    flex-direction: column;
    gap: 1.75rem;
    padding: 2rem 1.75rem;
    position: relative;
    transition: transform 0.4s ease;

    &:hover {
        transform: translateY(-4px);
    }

    // 连接线：在非最后一个步骤右侧画一条水平虚线
    &:not(:last-child)::after {
        content: '';
        position: absolute;
        top: calc(2rem + 24px); // align with marker center
        right: 0;
        width: calc(100% - 48px - 1.75rem); // marker width + padding
        height: 1px;
        border-top: 1px dashed rgba(0, 0, 0, 0.12);
        pointer-events: none;

        @media (max-width: 768px) {
            display: none;
        }
    }

    .step-marker {
        width: 48px;
        height: 48px;
        border-radius: 50%;
        background: #1a1a1a;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-shrink: 0;
        transition: transform 0.4s ease, box-shadow 0.4s ease;
    }

    &:hover .step-marker {
        transform: scale(1.1);
        box-shadow: 0 8px 24px rgba(0, 0, 0, 0.15);
    }

    .step-num {
        font-size: 0.8rem;
        font-weight: 700;
        color: rgba(255, 255, 255, 0.8);
    }

    .step-text {
        display: flex;
        flex-direction: column;
        gap: 0.3rem;
    }

    .step-zh {
        font-size: 1.5rem;
        font-weight: bold;
        letter-spacing: -0.01em;
    }

    .step-en {
        font-size: 0.8rem;
        opacity: 0.3;
        letter-spacing: 0.03em;
    }

    .step-desc {
        font-size: 0.8rem;
        opacity: 0.35;
        line-height: 1.6;
        margin-top: 0.2rem;
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

.client-card {
    display: flex;
    align-items: center;
    gap: 1rem;
    padding: 1.5rem;
    border: 1px solid rgba(0, 0, 0, 0.05);
    border-radius: 20px;
    border-left: 3px solid transparent;
    transition:
        border-color 0.4s ease,
        border-left-color 0.4s ease,
        background 0.4s ease,
        transform 0.4s ease;

    &:hover {
        border-color: rgba(0, 0, 0, 0.12);
        border-left-color: #1a1a1a;
        background: rgba(0, 0, 0, 0.02);
        transform: translateX(4px);
    }

    .client-logo {
        width: 40px;
        height: 40px;
        border-radius: 10px;
        object-fit: cover;
        flex-shrink: 0;
    }

    .client-avatar {
        width: 40px;
        height: 40px;
        border-radius: 10px;
        background: rgba(0, 0, 0, 0.06);
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 1.1rem;
        font-weight: 700;
        color: rgba(0, 0, 0, 0.35);
        flex-shrink: 0;
    }

    .client-text {
        display: flex;
        flex-direction: column;
        gap: 0.15rem;
        min-width: 0;
    }

    .client-zh {
        font-size: 1.3rem;
        font-weight: bold;
        letter-spacing: -0.01em;
    }

    .client-en {
        font-size: 0.65rem;
        opacity: 0.28;
        letter-spacing: 0.08em;
        text-transform: uppercase;
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

.news-card {
    display: flex;
    flex-direction: column;
    gap: 0.75rem;
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
        background: rgba(0, 0, 0, 0.02);
        transform: translateY(-4px);
    }

    .news-thumb {
        width: 100%;
        aspect-ratio: 16 / 9;
        object-fit: cover;
        display: block;
    }

    .news-date {
        font-size: 0.75rem;
        opacity: 0.3;
        padding: 0 1.5rem;
    }

    .news-date:first-child {
        padding-top: 1.5rem;
    }

    .news-title {
        font-size: 1.1rem;
        line-height: 1.4;
        flex: 1;
        padding: 0 1.5rem;
    }

    .news-arrow {
        font-size: 0.9rem;
        opacity: 0;
        transition: opacity 0.3s ease, transform 0.3s ease;
        align-self: flex-end;
        padding: 0 1.5rem 1.5rem;
    }

    &:hover .news-arrow {
        opacity: 0.4;
        transform: translateX(4px);
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
    }
}

// ---------- Footer ----------
.footer {
    display: flex;
    justify-content: space-between;
    padding: 2rem;
    border-top: 1px solid rgba(0, 0, 0, 0.05);
    font-size: 0.8rem;
    opacity: 0.25;
}
</style>
