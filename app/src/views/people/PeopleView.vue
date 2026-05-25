<template>
    <div class="people-page">
        <LoadingSpinner v-if="!loaded" />
        <template v-else-if="people.length">
        <section class="people-hero">
            <div class="people-hero-text font-error">
                <h1>团队成员</h1>
                <p class="en">People</p>
            </div>
        </section>

        <section class="people-grid-section">
            <div class="people-grid">
                <router-link v-for="p in people" :key="p.id" :to="`/people/${p.id}`" class="person-card">
                    <div class="person-avatar">
                        <img :src="p.image" :alt="p.name" />
                    </div>
                    <div class="person-info">
                        <h3>{{ p.name }}</h3>
                        <span class="person-en">{{ p.en }}</span>
                        <span class="person-role">{{ p.role }} · {{ p.roleEn }}</span>
                        <p class="person-bio">{{ p.bio }}</p>
                    </div>
                </router-link>
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

const people = ref([]);
const footer = ref(null);
const loaded = ref(false);

onMounted(async () => {
    const [data, home] = await Promise.all([getPageData('people'), getHomeData()]);
    people.value = data;
    footer.value = home.footer;
    loaded.value = true;
});
</script>

<style lang="scss" scoped>
.people-page {
    color: #1a1a1a;
    background: #f8f8f8;
}

.people-hero {
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

    .people-hero-text {
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

.people-grid-section {
    max-width: 880px;
    margin: 0 auto;
    padding: 4rem 2rem;

    @media (max-width: 640px) {
        padding: 2.5rem 1.25rem;
    }
}

.people-grid {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 1.25rem;

    @media (max-width: 640px) {
        grid-template-columns: 1fr;
    }
}

.person-card {
    display: flex;
    gap: 1.25rem;
    padding: 1.75rem;
    border: 1px solid rgba(0, 0, 0, 0.06);
    border-radius: 20px;
    color: inherit;
    text-decoration: none;
    transition: border-color 0.4s ease, transform 0.4s ease, box-shadow 0.4s ease;

    &:hover {
        border-color: rgba(0, 0, 0, 0.15);
        transform: translateY(-4px);
        box-shadow: 0 12px 40px rgba(0, 0, 0, 0.06);
    }
}

.person-avatar {
    flex-shrink: 0;

    img {
        width: 72px;
        height: 72px;
        border-radius: 50%;
        object-fit: cover;
        display: block;
    }
}

.person-info {
    display: flex;
    flex-direction: column;
    gap: 0.25rem;
    min-width: 0;

    h3 {
        font-size: 1.2rem;
        font-weight: bold;
        margin: 0;
        letter-spacing: -0.01em;
    }

    .person-en {
        font-size: 0.7rem;
        opacity: 0.25;
        letter-spacing: 0.04em;
    }

    .person-role {
        font-size: 0.75rem;
        opacity: 0.35;
        margin-bottom: 0.25rem;
    }

    .person-bio {
        font-size: 0.8rem;
        opacity: 0.4;
        line-height: 1.55;
        margin: 0;
    }
}

@media (max-width: 640px) {
    .person-card {
        padding: 1.25rem;
        gap: 1rem;
    }

    .person-avatar img {
        width: 56px;
        height: 56px;
    }

    .person-info h3 {
        font-size: 1.1rem;
    }
}
</style>
