<template>
    <router-link :to="to" class="news-card" :class="`news-card--${variant}`">
        <div class="news-card-image">
            <img v-if="item.image" :src="item.image" :alt="item.title" />
            <div v-else class="news-card-placeholder" />
        </div>
        <div class="news-card-body">
            <span class="news-card-date">{{ item.date }}</span>
            <h3 class="news-card-title">{{ item.title }}</h3>
            <p v-if="variant === 'full' && item.excerpt" class="news-card-excerpt">{{ item.excerpt }}</p>
            <span class="news-card-arrow">→</span>
        </div>
    </router-link>
</template>

<script setup>
defineProps({
    item: { type: Object, required: true },
    variant: { type: String, default: 'compact' },
    to: { type: String, default: '/news' },
});
</script>

<style lang="scss" scoped>
.news-card {
    display: flex;
    flex-direction: column;
    border: 1px solid rgba(255, 255, 255, 0.08);
    border-radius: 20px;
    color: #e5e5e5;
    text-decoration: none;
    overflow: hidden;
    transition:
        border-color 0.3s ease,
        background 0.3s ease,
        transform 0.3s ease;
    background: #141414;

    &:hover {
        border-color: rgba(255, 255, 255, 0.2);
        background: #1a1a1a;
        transform: translateY(-4px);

        .news-card-arrow {
            opacity: 0.6;
            transform: translateX(4px);
        }
    }
}

.news-card-image {
    img {
        width: 100%;
        aspect-ratio: 16 / 9;
        object-fit: cover;
        display: block;
    }

    .news-card-placeholder {
        width: 100%;
        aspect-ratio: 16 / 9;
        background: #1a1a1a;
    }
}

.news-card-body {
    display: flex;
    flex-direction: column;
    gap: 0.5rem;
    padding: 1.25rem 1.5rem;
    flex: 1;
}

.news-card-date {
    font-size: 0.75rem;
    opacity: 0.4;
    white-space: nowrap;
    color: #e5e5e5;
    font-family: 'JetBrainsMono', monospace;
}

.news-card-title {
    font-size: 1.1rem;
    font-weight: 600;
    margin: 0;
    letter-spacing: -0.01em;
    line-height: 1.35;
    color: #fff;
}

.news-card-excerpt {
    font-size: 0.85rem;
    opacity: 0.5;
    line-height: 1.6;
    margin: 0;
    flex: 1;
    color: #e5e5e5;
}

.news-card-arrow {
    font-size: 0.9rem;
    opacity: 0;
    align-self: flex-end;
    transition: opacity 0.3s ease, transform 0.3s ease;
    margin-top: auto;
    color: #fff;
}

// ========== compact variant (home page) ==========
.news-card--compact {
    .news-card-body {
        gap: 0.75rem;
    }

    .news-card-body:has(.news-card-date:first-child) {
        padding-top: 1.5rem;
    }
}

// ========== full variant (news page) ==========
.news-card--full {
    border-radius: 24px;

    .news-card-image img,
    .news-card-image .news-card-placeholder {
        aspect-ratio: 16 / 10;
    }

    .news-card-body {
        padding: 1.5rem;
        gap: 0.5rem;
    }

    .news-card-title {
        font-size: 1.2rem;
    }
}
</style>
