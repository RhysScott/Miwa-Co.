<template>
    <div class="home">
        <!-- Hero 大字标语 -->
        <section class="hero">
            <div class="hero-text font-error">
                <h1 class="hero-zh">创造有意义的品牌</h1>
                <p class="hero-en">We craft brands that matter.</p>
            </div>
            <div class="scroll-hint" aria-hidden="true">↓</div>
        </section>

        <!-- 横向滚动服务词条 -->
        <section class="marquee">
            <div class="marquee-inner">
                <div class="marquee-track">
                    <span class="marquee-item">品牌设计</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">Branding</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">视觉识别</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">Visual Identity</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">数字体验</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">Digital</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">空间设计</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">Spatial</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">内容策划</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">Content</span>
                </div>
                <div class="marquee-track" aria-hidden="true">
                    <span class="marquee-item">品牌设计</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">Branding</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">视觉识别</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">Visual Identity</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">数字体验</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">Digital</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">空间设计</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">Spatial</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">内容策划</span>
                    <span class="marquee-divider">/</span>
                    <span class="marquee-item">Content</span>
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
                    <div class="service-icon">{{ s.icon }}</div>
                    <h3 class="service-zh">{{ s.zh }}</h3>
                    <p class="service-en">{{ s.en }}</p>
                    <p class="service-desc">{{ s.desc }}</p>
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

        <!-- 数据亮点 -->
        <section class="stats">
            <div class="stats-grid">
                <div class="stat-item" v-for="s in stats" :key="s.num">
                    <span class="stat-num">{{ s.num }}</span>
                    <span class="stat-label">{{ s.label }}</span>
                </div>
            </div>
        </section>

        <!-- 品牌理念 -->
        <section class="philosophy">
            <div class="philosophy-quote font-error">
                <p class="quote-zh">"好设计不喧哗，它只是恰到好处地存在"</p>
                <p class="quote-en">Good design is not loud. It simply exists, just right.</p>
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
                    <p>Miwa & Co. 是一家独立创意工作室，</p>
                    <p>专注于品牌策略、视觉设计与数字体验。</p>
                    <p>我们相信好设计来自于对细节的偏执热爱。</p>
                    <p class="about-en">An independent studio obsessed with brand strategy,</p>
                    <p class="about-en">visual identity, and digital craft.</p>
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
import { gsap } from 'gsap';
import { ScrollTrigger } from 'gsap/ScrollTrigger';
import { SplitText } from 'gsap/SplitText';
import { onMounted, reactive } from 'vue';

gsap.registerPlugin(SplitText, ScrollTrigger);

const services = reactive([
    { icon: '✦', zh: '品牌策略', en: 'Brand Strategy', desc: '从洞察到定位，构建品牌的核心叙事与价值体系' },
    { icon: '◉', zh: '视觉识别', en: 'Visual Identity', desc: '标志、字体、色彩系统 —— 让品牌一眼可辨' },
    { icon: '◎', zh: '数字体验', en: 'Digital Experience', desc: '网站、应用、交互，每个触点都精心打磨' },
    { icon: '◐', zh: '空间设计', en: 'Spatial Design', desc: '零售空间、展览、快闪 —— 品牌在物理世界的延伸' },
]);

const projects = reactive([
    { zh: '东方意境', en: 'Oriental Aesthetics', image: '/images/1.jpg' },
    { zh: '现代极简', en: 'Modern Minimal', image: '/images/2.jpg' },
    { zh: '城市脉搏', en: 'Urban Pulse', image: '/images/3.jpg' },
    { zh: '自然共生', en: 'Nature & Co', image: '/images/4.jpg' },
]);

const stats = reactive([
    { num: '50+', label: '项目作品' },
    { num: '12', label: '合作品牌' },
    { num: '8', label: '年经验' },
    { num: '100%', label: '热情投入' },
]);

onMounted(() => {
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
    background: rgba(255, 255, 255, 0.04);
    border: 1px solid rgba(255, 255, 255, 0.06);
    border-radius: 20px;
    padding: 2rem;
    transition: background 0.4s ease, transform 0.4s ease, border-color 0.4s ease;

    &:hover {
        background: rgba(255, 255, 255, 0.07);
        border-color: rgba(255, 255, 255, 0.15);
        transform: translateY(-4px);
    }

    .service-icon {
        font-size: 1.5rem;
        margin-bottom: 1.5rem;
        opacity: 0.6;
    }

    .service-zh {
        margin: 0 0 0.25rem;
        font-size: 1.25rem;
    }

    .service-en {
        margin: 0 0 0.75rem;
        font-size: 0.85rem;
        opacity: 0.35;
    }

    .service-desc {
        margin: 0;
        font-size: 0.9rem;
        opacity: 0.5;
        line-height: 1.6;
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
