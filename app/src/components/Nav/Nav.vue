<template>
    <div class="nav-wrapper">
        <div class="nav-header">
            <div class="brand font-error">Miwa & Co.</div>
            <div class="menu-button" @click="toggleMenuState">
                <!-- 根据菜单状态切换图标 -->
                <Menu v-if="!isMenuActive" color="white" />
                <CircleX v-else />
            </div>
        </div>

        <!-- 菜单遮罩层：使用 SVG clip-path 实现圆弧展开/收起动画 -->
        <div class="menu-overlay" :class="{ active: isMenuActive }">
            <!-- 隐藏的 SVG，仅用于定义 clipPath，由 GSAP 动态修改 path 的 d 属性驱动动画 -->
            <svg class="clip-svg-def">
                <defs>
                    <clipPath id="menuClip">
                        <path id="clip-path-element" :d="closedPath" fill="transparent" />
                    </clipPath>
                </defs>
            </svg>

            <div class="menu-content font-error">
                <div class="info-section">
                    <h1>
                        <span class="zh">联系我们</span>
                        <span class="en">Get in touch</span>
                    </h1>
                    <p>hello@miwa-co.com</p>
                    <p>+88 123 456789</p>
                    <p>Shanghai, China</p>
                </div>
                <div class="links-section">
                    <router-link to="/" class="nav-item">
                        <span class="zh">首页</span>
                        <span class="en">Home</span>
                    </router-link>
                    <router-link to="/news" class="nav-item">
                        <span class="zh">动态</span>
                        <span class="en">News</span>
                    </router-link>
                    <router-link to="/contact" class="nav-item">
                        <span class="zh">联系</span>
                        <span class="en">Contact</span>
                    </router-link>
                    <router-link to="/explore" class="nav-item">
                        <span class="zh">探索</span>
                        <span class="en">Explore</span>
                    </router-link>
                    <router-link to="/about" class="nav-item">
                        <span class="zh">关于</span>
                        <span class="en">About</span>
                    </router-link>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
    import { CircleX, Menu } from '@lucide/vue';
import { gsap } from 'gsap';
import { SplitText } from 'gsap/SplitText';
import { onMounted, reactive, ref } from 'vue';
import { useRouter } from 'vue-router';

    const router = useRouter();

    gsap.registerPlugin(SplitText);

    // 视口尺寸，用于动态计算 SVG clip-path 坐标
    const screenSize = reactive({
        width: document.documentElement.clientWidth,
        height: document.documentElement.clientHeight
    });

    // 菜单打开动画路径：从顶部直线 → 向下凸出弧线 → 铺满全屏
    // Q 为二次贝塞尔，控制点居中，圆弧由屏幕中间向外扩展
    const closedPath = ref(`M 0 0 Q ${screenSize.width / 2} 0 ${screenSize.width} 0  L ${screenSize.width} 0 L 0 0 Z`);
    const midOpenPath = ref(
        `M 0 ${screenSize.height * 0.5} Q ${screenSize.width / 2} ${screenSize.height * 0.75} ${screenSize.width} ${screenSize.height * 0.5}  L ${screenSize.width} 0 L 0 0 Z`
    );
    const fullOpenPath = ref(
        `M 0 ${screenSize.height} Q ${screenSize.width / 2} ${screenSize.height} ${screenSize.width} ${screenSize.height} L ${screenSize.width} 0 L 0 0 Z`
    );

    // 菜单关闭动画路径：从底部直线 → 向上凸出弧线 → 回到顶部直线（即 closedPath）
    const reverseStartPath = ref(
        `M 0 0 Q ${screenSize.width / 2} 0 ${screenSize.width} 0  L ${screenSize.width} ${screenSize.height} L 0 ${screenSize.height} Z`
    );
    const reverseMidPath = ref(
        `M 0 ${screenSize.height / 2} Q ${screenSize.width / 2} ${screenSize.height * 0.25} ${screenSize.width} ${screenSize.height / 2}  L ${screenSize.width} ${screenSize.height} L 0 ${screenSize.height} Z`
    );
    const reverseEndPath = ref(
        `M 0 ${screenSize.height} Q ${screenSize.width / 2}  ${screenSize.height} ${screenSize.width} ${screenSize.height}  L ${screenSize.width} ${screenSize.height} L 0 ${screenSize.height} Z`
    );

    const isMenuActive = ref(false);
    // 存储所有 SplitText 实例，用于关闭时 revert 还原文字状态
    let splitInstances = [];

    // 打开/关闭菜单：通过 GSAP 在两段式 clip-path 动画中间切换
    const toggleMenuState = () => {
        isMenuActive.value = !isMenuActive.value;
        const clipPathElement = '#clip-path-element';

        // 每次切换前还原上一次的文字拆分，避免重复 split 造成文字变形
        splitInstances.forEach(s => s.revert());
        splitInstances = [];

        if (isMenuActive.value) {
            // 打开：clip-path 分两步走 —— 先弧线展开，再铺满全屏
            gsap.timeline()
                .fromTo(
                    clipPathElement,
                    { attr: { d: closedPath.value } },
                    {
                        attr: { d: midOpenPath.value },
                        duration: 0.3,
                        ease: 'power4.in',
                        // 动画开始时把品牌色切为菜单态颜色
                        onStart: () => gsap.to('.brand', { color: 'var(--logo-color-menu)', duration: 0.3 }),
                        onComplete: () => {
                            // links 中文大字从右侧弹入
                            const linksSplit = SplitText.create('.links-section .nav-item .zh', { type: 'chars,words' });
                            splitInstances.push(linksSplit);
                            gsap.fromTo(
                                linksSplit.chars,
                                { x: 100, opacity: 0 },
                                { stagger: 0.01, x: 0, opacity: 1, duration: 1, ease: 'elastic.out(1, 0.5)' }
                            );
                            // links 英文副标题淡入
                            gsap.fromTo('.links-section .nav-item .en', { opacity: 0 }, { opacity: 1, duration: 0.6, stagger: 0.05 });
                            // info-section 中文从左侧弹入
                            const infoSplit = SplitText.create('.info-section h1 .zh, .info-section p', { type: 'chars,words' });
                            splitInstances.push(infoSplit);
                            gsap.fromTo(
                                infoSplit.chars,
                                { x: -100, opacity: 0 },
                                { stagger: 0.01, x: 0, opacity: 1, duration: 1, ease: 'elastic.out(1, 0.5)' }
                            );
                            // info 英文副标题淡入
                            gsap.fromTo('.info-section h1 .en', { opacity: 0 }, { opacity: 1, duration: 0.6 });
                        }
                    }
                )
                .to(clipPathElement, { attr: { d: fullOpenPath.value }, duration: 0.3 });
        } else {
            // 关闭：先让文字侧滑出去，再收起 clip-path
            // links 中文向右侧滑出，英文淡出
            const linksSplit = SplitText.create('.links-section .nav-item .zh', { type: 'chars,words' });
            splitInstances.push(linksSplit);
            gsap.to(linksSplit.chars, {
                x: 100,
                opacity: 0,
                stagger: 0.01,
                duration: 0.4,
                ease: 'power2.in'
            });
            gsap.to('.links-section .nav-item .en', { opacity: 0, duration: 0.3 });
            // info-section 中文向左侧滑出，英文淡出
            const infoSplit = SplitText.create('.info-section h1 .zh, .info-section p', { type: 'chars,words' });
            splitInstances.push(infoSplit);
            gsap.to(infoSplit.chars, {
                x: -100,
                opacity: 0,
                stagger: 0.01,
                duration: 0.4,
                ease: 'power2.in'
            });
            gsap.to('.info-section h1 .en', { opacity: 0, duration: 0.3 });

            gsap.timeline()
                .fromTo(
                    clipPathElement,
                    { attr: { d: reverseStartPath.value } },
                    {
                        attr: { d: reverseMidPath.value },
                        duration: 0.3,
                        ease: 'power4.in',
                        // 动画开始时恢复品牌色
                        onStart: () => gsap.to('.brand', { color: 'var(--logo-color)', duration: 0.3 })
                    }
                )
                .to(clipPathElement, {
                    attr: { d: reverseEndPath.value },
                    duration: 0.3
                });
        }
    };

    onMounted(() => {
        // 品牌文字入场：从左侧滑入
        gsap.from('.brand', {
            x: '-100%',
            duration: 1,
            ease: 'power4.out'
        });

        // 初始化 clip-path 为闭合状态
        gsap.set('#clip-path-element', { attr: { d: closedPath.value } });

        // 路由切换时自动收起菜单
        router.afterEach(() => {
            if (isMenuActive.value) {
                toggleMenuState();
            }
        });

        // 窗口大小变化时重新计算所有路径（路径依赖 screenSize 宽高）
        window.addEventListener('resize', () => {
            screenSize.width = document.documentElement.clientWidth;
            screenSize.height = document.documentElement.clientHeight;
            closedPath.value = `M 0 0 Q ${screenSize.width / 2} 0 ${screenSize.width} 0  L ${screenSize.width} 0 L 0 0 Z`;
            midOpenPath.value = `M 0 ${screenSize.height * 0.5} Q ${screenSize.width / 2} ${screenSize.height * 0.75} ${screenSize.width} ${screenSize.height * 0.5}  L ${screenSize.width} 0 L 0 0 Z`;
            fullOpenPath.value = `M 0 ${screenSize.height} Q ${screenSize.width / 2} ${screenSize.height} ${screenSize.width} ${screenSize.height} L ${screenSize.width} 0 L 0 0 Z`;
            reverseStartPath.value = `M 0 0 Q ${screenSize.width / 2} 0 ${screenSize.width} 0  L ${screenSize.width} ${screenSize.height} L 0 ${screenSize.height} Z`;
            reverseMidPath.value = `M 0 ${screenSize.height / 2} Q ${screenSize.width / 2} ${screenSize.height * 0.25} ${screenSize.width} ${screenSize.height / 2}  L ${screenSize.width} ${screenSize.height} L 0 ${screenSize.height} Z`;
            reverseEndPath.value = `M 0 ${screenSize.height} Q ${screenSize.width / 2}  ${screenSize.height} ${screenSize.width} ${screenSize.height}  L ${screenSize.width} ${screenSize.height} L 0 ${screenSize.height} Z`;
        });
    });
</script>

<style lang="scss" scoped>
    :root {
        --logo-color: white;
        --logo-color-menu: black;
    }

    .nav-wrapper {
        position: fixed;
        width: 100%;
        z-index: 10;
        // pointer-events: none 让点击穿透导航容器，仅品牌文字和菜单按钮可交互
        pointer-events: none;
    }

    .nav-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 1rem;
        position: relative;
        z-index: 2;
    }

    .brand {
        font-size: 3rem;
        font-weight: bold;
        color: var(--logo-color);
    }

    .menu-button {
        pointer-events: all;
        cursor: pointer;
        z-index: 3;
    }

    .menu-overlay {
        position: fixed;
        top: 0;
        left: 0;
        width: 100vw;
        height: 100vh;
        background-color: white;
        z-index: 1;
        // 通过 SVG clip-path 控制遮罩可见区域，GSAP 动画修改 path 的 d 属性实现展开效果
        clip-path: url(#menuClip);
        pointer-events: none;

        &.active {
            pointer-events: all;
        }

        .clip-svg-def {
            position: absolute;
            width: 0;
            height: 0;
            overflow: hidden;
        }

        .menu-content {
            position: relative;
            width: 100%;
            height: 100%;
        }

        .info-section {
            position: absolute;
            bottom: 2rem;
            left: 2rem;
            display: flex;
            flex-direction: column;
            gap: 0.75rem;

            h1 {
                margin: 0;
                color: #000;
                font-weight: bold;
                display: flex;
                flex-direction: column;
                gap: 0.25rem;

                .zh {
                    font-size: 2rem;
                }

                .en {
                    font-size: 1rem;
                    font-weight: normal;
                    color: #666;
                }
            }

            p {
                margin: 0;
                color: #555;
                font-size: 1rem;
                transition: color 0.3s ease, transform 0.3s ease;
                cursor: default;
            }

            // 悬停时整体微调
            p:hover {
                color: #000;
                transform: translateX(6px);
            }
        }

        .links-section {
            position: absolute;
            bottom: 2rem;
            right: 2rem;
            text-align: right;
            display: flex;
            flex-direction: column;
            gap: 1rem;

            .nav-item {
                color: black;
                text-decoration: none;
                display: flex;
                flex-direction: column;
                align-items: flex-end;
                gap: 0.15rem;
                line-height: 1;
                transition: color 0.3s ease, transform 0.3s ease;

                .zh {
                    font-size: 5rem;
                    font-weight: bold;
                }

                .en {
                    font-size: 1rem;
                    font-weight: normal;
                    color: #666;
                }
            }

            // 鼠标悬停任一链接时，所有链接变灰
            &:hover .nav-item {
                color: #999;
            }
            &:hover .nav-item .en {
                color: #aaa;
            }

            // 当前悬停的链接保持黑色并左移
            .nav-item:hover {
                color: #000;
                transform: translateX(-8px);
            }
            .nav-item:hover .en {
                color: #000;
            }
        }
    }
</style>
