<template>
    <div class="home">
        <!-- Hero 大字标语 -->
        <section class="hero">
            <div class="hero-text font-error">
                <h1 class="hero-zh">创造有意义的品牌</h1>
                <p class="hero-en">We craft brands that matter.</p>
            </div>
        </section>

        <!-- 横向滚动服务词条 -->
        <section class="marquee">
            <div class="marquee-track">
                <span v-for="n in 3" :key="n" class="marquee-text">
                    品牌设计&nbsp;&nbsp;/&nbsp;&nbsp;Branding&nbsp;&nbsp;/&nbsp;&nbsp;
                    视觉识别&nbsp;&nbsp;/&nbsp;&nbsp;Visual Identity&nbsp;&nbsp;/&nbsp;&nbsp;
                    数字体验&nbsp;&nbsp;/&nbsp;&nbsp;Digital&nbsp;&nbsp;/&nbsp;&nbsp;
                    空间设计&nbsp;&nbsp;/&nbsp;&nbsp;Spatial&nbsp;&nbsp;/&nbsp;&nbsp;
                    内容策划&nbsp;&nbsp;/&nbsp;&nbsp;Content&nbsp;&nbsp;/&nbsp;&nbsp;
                </span>
            </div>
        </section>

        <!-- 精选项目 -->
        <section class="projects">
            <div class="section-label font-error">
                <span class="zh">精选项目</span>
                <span class="en">Selected Works</span>
            </div>
            <div class="project-grid">
                <div class="project-card" v-for="(p, i) in projects" :key="i" :style="{ backgroundImage: `url(${p.image})` }" @mouseenter="onCardEnter(i)" @mouseleave="onCardLeave(i)">
                    <div class="card-overlay">
                        <span class="card-num">0{{ i + 1 }}</span>
                        <div class="card-info">
                            <span class="card-zh">{{ p.zh }}</span>
                            <span class="card-en">{{ p.en }}</span>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- 品牌简介 -->
        <section class="about">
            <div class="about-grid">
                <div class="about-label font-error">
                    <span class="zh">关于</span>
                    <span class="en">About</span>
                </div>
                <div class="about-text font-error">
                    <p>Miwa & Co. 是一家独立创意工作室，</p>
                    <p>专注于品牌策略、视觉设计与数字体验。</p>
                    <p>我们相信好设计来自于对细节的偏执热爱。</p>
                    <p class="about-en">An independent studio obsessed with brand strategy,</p>
                    <p class="about-en">visual identity, and digital craft.</p>
                </div>
            </div>
        </section>
    </div>
</template>

<script setup>
import { gsap } from 'gsap';
import { SplitText } from 'gsap/SplitText';
import { ScrollTrigger } from 'gsap/ScrollTrigger';
import { onMounted, reactive } from 'vue';

gsap.registerPlugin(SplitText, ScrollTrigger);

const projects = reactive([
    { zh: '东方意境', en: 'Oriental Aesthetics', image: '/images/1.jpg' },
    { zh: '现代极简', en: 'Modern Minimal', image: '/images/2.jpg' },
    { zh: '城市脉搏', en: 'Urban Pulse', image: '/images/3.jpg' },
    { zh: '自然共生', en: 'Nature & Co', image: '/images/4.jpg' },
]);

// 卡片 hover：非悬停卡片降低透明度
const onCardEnter = (i) => {
    gsap.to('.project-card', { opacity: 0.4, duration: 0.3 });
    gsap.to(`.project-card:nth-child(${i + 1})`, { opacity: 1, scale: 1.02, duration: 0.4 });
};
const onCardLeave = () => {
    gsap.to('.project-card', { opacity: 1, scale: 1, duration: 0.4 });
};

onMounted(() => {
    // Hero 逐字弹跳入场
    const heroSplit = SplitText.create('.hero-zh, .hero-en', { type: 'chars,words' });
    gsap.fromTo(heroSplit.chars,
        { y: 80, opacity: 0 },
        { y: 0, opacity: 1, stagger: 0.02, duration: 0.8, ease: 'elastic.out(1, 0.5)' }
    );

    // 项目卡片弹跳滚动入场
    gsap.fromTo('.project-card',
        { y: 80, opacity: 0 },
        {
            y: 0, opacity: 1, stagger: 0.15, duration: 0.8, ease: 'elastic.out(1, 0.5)',
            scrollTrigger: { trigger: '.projects', start: 'top 80%' }
        }
    );

    // About 文本弹跳滚动入场
    const aboutSplit = SplitText.create('.about-text p', { type: 'lines' });
    gsap.fromTo(aboutSplit.lines,
        { y: 40, opacity: 0 },
        {
            y: 0, opacity: 1, stagger: 0.1, duration: 0.6, ease: 'elastic.out(1, 0.5)',
            scrollTrigger: { trigger: '.about', start: 'top 85%' }
        }
    );
});
</script>

<style lang="scss" scoped>
.home {
    color: white;
}

// Hero
.hero {
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;
    padding: 2rem;

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
}

// Marquee
.marquee {
    width: 100%;
    overflow: hidden;
    border-top: 1px solid rgba(255, 255, 255, 0.15);
    border-bottom: 1px solid rgba(255, 255, 255, 0.15);
    padding: 1.5rem 0;
    white-space: nowrap;

    .marquee-track {
        display: inline-flex;
        animation: marquee 30s linear infinite;
    }

    .marquee-text {
        font-size: 1.5rem;
        letter-spacing: 0.05em;
        opacity: 0.5;
    }
}

@keyframes marquee {
    from { transform: translateX(0); }
    to { transform: translateX(-33.333%); }
}

// Projects
.projects {
    padding: 8rem 2rem;
}

.section-label {
    margin-bottom: 3rem;

    .zh {
        font-size: 1rem;
        opacity: 0.4;
        display: block;
    }

    .en {
        font-size: 2rem;
    }
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
    position: relative;
    cursor: pointer;
    overflow: hidden;

    .card-overlay {
        position: absolute;
        inset: 0;
        background: rgba(0, 0, 0, 0.25);
        padding: 1.5rem;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    .card-num {
        font-size: 0.875rem;
        opacity: 0.5;
    }

    .card-info {
        display: flex;
        flex-direction: column;
        gap: 0.25rem;

        .card-zh {
            font-size: 1.5rem;
            font-weight: bold;
        }

        .card-en {
            font-size: 0.875rem;
            opacity: 0.5;
        }
    }
}

// About
.about {
    padding: 8rem 2rem 6rem;
    border-top: 1px solid rgba(255, 255, 255, 0.1);
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

.about-label {
    .zh {
        font-size: 1rem;
        opacity: 0.4;
        display: block;
    }

    .en {
        font-size: 2rem;
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
        opacity: 0.4;
        margin-bottom: 0.5rem;
    }
}
</style>
