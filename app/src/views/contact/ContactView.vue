<template>
    <div class="contact-page">
        <LoadingSpinner v-if="!loaded" />
        <template v-else-if="contact">
        <section class="contact-hero">
            <div class="contact-hero-text font-error">
                <h1>联系我们</h1>
                <p class="en">Get in Touch</p>
            </div>
        </section>

        <section class="contact-body">
            <div class="contact-info">
                <div class="info-item">
                    <span class="info-label">邮箱 / Email</span>
                    <a :href="'mailto:' + contact.email" class="info-value">{{ contact.email }}</a>
                </div>
                <div class="info-item">
                    <span class="info-label">电话 / Phone</span>
                    <span class="info-value">{{ contact.phone }}</span>
                </div>
                <div class="info-item">
                    <span class="info-label">地址 / Address</span>
                    <span class="info-value zh">{{ contact.address }}</span>
                    <span class="info-value en">{{ contact.en.address }}</span>
                </div>
            </div>

            <div class="contact-extra">
                <div class="extra-card">
                    <h3>商务合作</h3>
                    <p>有项目需求或合作意向，欢迎邮件联系，我们会在 24 小时内回复。</p>
                </div>
                <div class="extra-card">
                    <h3>加入我们</h3>
                    <p>我们持续寻找优秀工程师与设计师，简历投递至 hello@miwa-co.com。</p>
                </div>
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

const contact = ref(null);
const footer = ref(null);
const loaded = ref(false);

onMounted(async () => {
    const [data, home] = await Promise.all([getPageData('contact'), getHomeData()]);
    contact.value = data;
    footer.value = home.footer;
    loaded.value = true;
});
</script>

<style lang="scss" scoped>
.contact-page {
    color: #1a1a1a;
    background: #f8f8f8;
    min-height: 100vh;
}

.contact-hero {
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

    .contact-hero-text {
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

.contact-body {
    max-width: 700px;
    margin: 0 auto;
    padding: 5rem 2rem;
}

.contact-info {
    display: flex;
    flex-direction: column;
    gap: 2.5rem;
    margin-bottom: 4rem;
}

.info-item {
    display: flex;
    flex-direction: column;
    gap: 0.4rem;
}

.info-label {
    font-size: 0.75rem;
    opacity: 0.3;
    letter-spacing: 0.05em;
    text-transform: uppercase;
}

.info-value {
    font-size: 1.6rem;
    font-weight: bold;
    letter-spacing: -0.01em;
    color: #1a1a1a;
    text-decoration: none;

    &.en {
        font-size: 0.95rem;
        font-weight: normal;
        opacity: 0.35;
        margin-top: 0.15rem;
    }
}

a.info-value:hover {
    opacity: 0.6;
}

@media (max-width: 640px) {
    .contact-body {
        padding: 3rem 1.25rem;
    }

    .info-value {
        font-size: 1.2rem;
    }

    .info-item {
        gap: 0.25rem;
    }
}

.contact-extra {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 1.5rem;

    @media (max-width: 640px) {
        grid-template-columns: 1fr;
    }
}

.extra-card {
    border: 1px solid rgba(0, 0, 0, 0.06);
    border-radius: 20px;
    padding: 2rem;

    h3 {
        font-size: 1.2rem;
        font-weight: bold;
        margin: 0 0 0.75rem;
        letter-spacing: -0.01em;
    }

    p {
        font-size: 0.9rem;
        opacity: 0.4;
        line-height: 1.6;
        margin: 0;
    }
}
</style>
