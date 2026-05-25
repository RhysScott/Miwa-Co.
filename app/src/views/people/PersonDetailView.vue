<template>
    <div class="person-detail">
        <LoadingSpinner v-if="!loaded" />
        <template v-else-if="person">
        <section class="person-hero">
            <div class="person-hero-text font-error">
                <div class="person-hero-avatar">
                    <img :src="person.image" :alt="person.name" />
                </div>
                <h1>{{ person.name }}</h1>
                <p class="hero-en">{{ person.en }}</p>
                <p class="hero-role">{{ person.role }} · {{ person.roleEn }}</p>
            </div>
        </section>

        <section class="person-body">
            <div class="person-content">
                <p class="person-bio">{{ person.bio }}</p>
                <div class="person-detail-text">{{ person.detail }}</div>
            </div>
        </section>

        <section class="person-others" v-if="otherPeople.length">
            <h3 class="others-title">更多成员</h3>
            <nav class="others-list">
                <router-link
                    v-for="p in otherPeople"
                    :key="p.id"
                    :to="`/people/${p.id}`"
                    class="others-item"
                    :class="{ active: p.id === person.id }"
                >
                    <img :src="p.image" :alt="p.name" class="others-avatar" />
                    <div class="others-text">
                        <span class="others-name">{{ p.name }}</span>
                        <span class="others-role">{{ p.role }}</span>
                    </div>
                </router-link>
            </nav>
        </section>

        <SiteFooter v-if="footer" :footer="footer" />
        </template>
        <div v-else class="person-detail not-found">
            <section class="person-hero">
                <div class="person-hero-text font-error">
                    <h1>成员不存在</h1>
                </div>
            </section>
        </div>
    </div>
</template>

<script setup>
import { computed, onMounted, ref, watch } from 'vue';
import { useRoute } from 'vue-router';
import { getPersonById, getPageData } from '@/api/pages';
import { getHomeData } from '@/api/home';
import LoadingSpinner from '@/components/shared/LoadingSpinner.vue';
import SiteFooter from '@/components/home/Footer.vue';

const route = useRoute();
const allPeople = ref([]);
const person = ref(null);
const footer = ref(null);
const loaded = ref(false);

const otherPeople = computed(() => {
    return allPeople.value.filter(p => p.id !== person.value?.id);
});

async function loadPerson(id) {
    loaded.value = false;
    person.value = await getPersonById(id);
    loaded.value = true;
    if (person.value) {
        document.title = `${person.value.name} - Miwa & Co.`;
    }
}

onMounted(async () => {
    const [peopleData, home] = await Promise.all([
        getPageData('people'),
        getHomeData(),
    ]);
    allPeople.value = peopleData;
    footer.value = home.footer;
    await loadPerson(route.params.id);
});

watch(() => route.params.id, (newId) => {
    loadPerson(newId);
});
</script>

<style lang="scss" scoped>
.person-detail {
    color: #1a1a1a;
    background: #f8f8f8;

    &.not-found {
        min-height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
    }
}

.person-hero {
    min-height: 40vh;
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

    .person-hero-text {
        position: relative;
        z-index: 1;
        color: rgba(255, 255, 255, 0.95);
        display: flex;
        flex-direction: column;
        align-items: center;
    }
}

.person-hero-avatar {
    margin-bottom: 1.5rem;

    img {
        width: 100px;
        height: 100px;
        border-radius: 50%;
        object-fit: cover;
        border: 3px solid rgba(255, 255, 255, 0.3);
    }
}

.person-hero-text {
    h1 {
        font-size: clamp(2rem, 5vw, 3rem);
        margin: 0;
        letter-spacing: -0.02em;
    }

    .hero-en {
        font-size: 1rem;
        opacity: 0.5;
        margin-top: 0.5rem;
    }

    .hero-role {
        font-size: 0.85rem;
        opacity: 0.4;
        margin-top: 0.5rem;
    }
}

.person-body {
    max-width: 660px;
    margin: 0 auto;
    padding: 4rem 3rem;

    @media (max-width: 640px) {
        padding: 2.5rem 1.25rem;
    }
}

.person-content {
    .person-bio {
        font-size: 1.35rem;
        font-weight: bold;
        line-height: 1.55;
        letter-spacing: -0.01em;
        margin: 0 0 2.5rem;
    }

    .person-detail-text {
        font-size: 1rem;
        line-height: 1.85;
        opacity: 0.55;
        white-space: pre-line;
    }

    @media (max-width: 640px) {
        .person-bio {
            font-size: 1.1rem;
            margin-bottom: 1.5rem;
        }

        .person-detail-text {
            font-size: 0.9rem;
            line-height: 1.7;
        }
    }
}

// ---------- others ----------
.person-others {
    max-width: 660px;
    margin: 0 auto;
    padding: 3rem 3rem 5rem;
    border-top: 1px solid rgba(0, 0, 0, 0.05);

    @media (max-width: 640px) {
        padding: 2.5rem 1.25rem;
    }
}

.others-title {
    font-size: 0.75rem;
    opacity: 0.3;
    letter-spacing: 0.06em;
    text-transform: uppercase;
    margin: 0 0 1.25rem;
}

.others-list {
    display: flex;
    flex-wrap: wrap;
    gap: 0.75rem;
}

.others-item {
    display: flex;
    align-items: center;
    gap: 0.75rem;
    padding: 0.75rem 1rem;
    border: 1px solid rgba(0, 0, 0, 0.06);
    border-radius: 14px;
    color: #1a1a1a;
    text-decoration: none;
    transition: border-color 0.3s ease, transform 0.3s ease;

    &:hover {
        border-color: rgba(0, 0, 0, 0.15);
        transform: translateY(-2px);
    }

    &.active {
        border-color: rgba(0, 0, 0, 0.2);
        background: rgba(0, 0, 0, 0.02);
    }
}

.others-avatar {
    width: 36px;
    height: 36px;
    border-radius: 50%;
    object-fit: cover;
}

.others-text {
    display: flex;
    flex-direction: column;
    gap: 0.1rem;

    .others-name {
        font-size: 0.9rem;
        font-weight: bold;
        letter-spacing: -0.01em;
    }

    .others-role {
        font-size: 0.7rem;
        opacity: 0.3;
    }
}
</style>
