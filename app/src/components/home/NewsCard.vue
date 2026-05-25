<template>
    <router-link to="/news" class="news-card">
        <img v-if="item.image" :src="item.image" :alt="item.title" class="news-thumb" />
        <span class="news-date">{{ item.date }}</span>
        <span class="news-title">{{ item.title }}</span>
        <span class="news-arrow">→</span>
    </router-link>
</template>

<script setup>
defineProps({
    item: { type: Object, required: true },
});
</script>

<style lang="scss" scoped>
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
        white-space: nowrap;
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
</style>
