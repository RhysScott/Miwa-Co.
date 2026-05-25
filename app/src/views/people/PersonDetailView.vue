<template>
    <div class="person-detail">
        <el-skeleton :loading="!loaded" animated>
            <template #template>
                <section class="person-hero">
                    <div class="person-hero-text font-error" style="display:flex;flex-direction:column;align-items:center">
                        <el-skeleton-item variant="image" style="width:100px;height:100px;border-radius:50%" />
                        <el-skeleton-item variant="h1" style="width:120px;margin-top:1.5rem" />
                        <el-skeleton-item variant="text" style="width:80px;margin-top:0.5rem" />
                        <el-skeleton-item variant="text" style="width:160px;margin-top:0.5rem" />
                    </div>
                </section>
                <section class="person-body">
                    <el-skeleton-item variant="text" style="width:100%;height:1.5rem" />
                    <el-skeleton-item variant="text" style="width:100%;margin-top:1rem" />
                    <el-skeleton-item variant="text" style="width:80%;margin-top:0.5rem" />
                    <el-skeleton-item variant="text" style="width:90%;margin-top:0.5rem" />
                    <el-skeleton-item variant="text" style="width:70%;margin-top:0.5rem" />
                </section>
            </template>
            <template #default>
                <template v-if="person">
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
                        <div class="detail-content" v-html="htmlContent" />
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
            </template>
        </el-skeleton>
        <div v-if="!loaded && !person" class="person-detail not-found">
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
import { marked } from 'marked';
import { markedHighlight } from 'marked-highlight';
import hljs from 'highlight.js';
import { getPersonById, getPageData } from '@/api/pages';
import { getHomeData } from '@/api/home';
import SiteFooter from '@/components/home/Footer.vue';

marked.use(markedHighlight({
    langPrefix: 'hljs language-',
    highlight(code, lang) {
        if (lang && hljs.getLanguage(lang)) {
            return hljs.highlight(code, { language: lang }).value;
        }
        return hljs.highlightAuto(code).value;
    },
}));

const route = useRoute();
const allPeople = ref([]);
const person = ref(null);
const footer = ref(null);
const loaded = ref(false);

const htmlContent = computed(() => {
    if (!person.value) return '';
    return marked(person.value.content || person.value.detail || '');
});

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
    background: url('/images/4.jpg') center / cover no-repeat;
    position: relative;
    isolation: isolate;

    &::before {
        content: '';
        position: absolute;
        inset: 0;
        z-index: 0;
        background: linear-gradient(180deg, rgba(0,0,0,0.1) 0%, rgba(0,0,0,0.25) 40%, rgba(0,0,0,0.5) 100%);
    }

    &::after {
        content: '';
        position: absolute;
        inset: 0;
        z-index: 0;
        opacity: 0.12;
        background-image: radial-gradient(circle, rgba(255,255,255,0.4) 1px, transparent 1px);
        background-size: 28px 28px;
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

    @media (max-width: 640px) {
        .person-bio {
            font-size: 1.1rem;
            margin-bottom: 1.5rem;
        }
    }
}

// shared markdown content styles
.detail-content {
    :deep(img) { max-width: 100%; height: auto; border-radius: 12px; margin: 1.5rem 0; display: block; }
    :deep(h2) { font-size: 1.5rem; font-weight: bold; margin: 3rem 0 1rem; letter-spacing: -0.01em; }
    :deep(h3) { font-size: 1.2rem; font-weight: bold; margin: 2rem 0 0.75rem; }
    :deep(p) { font-size: 1.05rem; line-height: 1.85; opacity: 0.55; margin: 0 0 1.5rem; }
    :deep(a) { color: #1a1a1a; text-decoration: underline; text-decoration-color: rgba(0,0,0,0.2); text-underline-offset: 0.2em; }
    :deep(ul), :deep(ol) { padding-left: 1.5rem; margin: 0 0 1.5rem; }
    :deep(li) { font-size: 1rem; line-height: 1.75; opacity: 0.5; margin-bottom: 0.4rem; }
    :deep(strong) { color: #1a1a1a; opacity: 0.8; }
    :deep(code) { font-family: 'SF Mono', 'Cascadia Code', 'Fira Code', monospace; font-size: 0.85em; background: rgba(0,0,0,0.05); padding: 0.15em 0.4em; border-radius: 4px; color: #333; }
    :deep(pre) { background: #f6f8fa; border: 1px solid rgba(0,0,0,0.06); border-radius: 12px; padding: 1.25rem 1.5rem; margin: 1.5rem 0; overflow-x: auto; font-size: 0.85rem; line-height: 1.65; }
    :deep(pre code) { background: none; padding: 0; border-radius: 0; font-size: inherit; }
    :deep(table) { width: 100%; border-collapse: collapse; margin: 1.5rem 0; font-size: 0.9rem; }
    :deep(th) { text-align: left; font-weight: 600; opacity: 0.6; padding: 0.6rem 0.75rem; border-bottom: 2px solid rgba(0,0,0,0.08); font-size: 0.8rem; text-transform: uppercase; }
    :deep(td) { padding: 0.6rem 0.75rem; border-bottom: 1px solid rgba(0,0,0,0.04); opacity: 0.55; }
    :deep(hr) { border: none; border-top: 1px solid rgba(0,0,0,0.06); margin: 2.5rem 0; }
    :deep(blockquote) { border-left: 3px solid rgba(0,0,0,0.15); margin: 2rem 0; padding: 0.5rem 0 0.5rem 1.5rem; }
    :deep(blockquote p) { font-size: 1rem; opacity: 0.45; font-style: italic; margin-bottom: 0.5rem; }

    @media (max-width: 640px) {
        :deep(h2) { font-size: 1.25rem; margin: 2rem 0 0.75rem; }
        :deep(p) { font-size: 0.95rem; line-height: 1.75; margin-bottom: 1.25rem; }
        :deep(pre) { padding: 1rem; border-radius: 8px; font-size: 0.8rem; }
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
