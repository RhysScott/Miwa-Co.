<template>
    <router-link :to="`/projects/${project.id}`" class="project-card">
        <div class="card-bg" :style="bgStyle">
            <div v-if="!project.image" class="card-bg-pattern" />
        </div>
        <div class="card-content">
            <div class="card-header">
                <span class="card-num">{{ padIndex }}</span>
            </div>
            <div class="card-info">
                <h3 class="card-zh">{{ project.zh }}</h3>
                <p class="card-en">{{ project.en }}</p>
                <p v-if="project.desc" class="card-desc">{{ project.desc }}</p>
            </div>
        </div>
    </router-link>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
    project: { type: Object, required: true },
    index: { type: Number, required: true },
});

const padIndex = computed(() => String(props.index + 1).padStart(2, '0'));
const bgStyle = computed(() =>
    props.project.image
        ? { backgroundImage: `url(${props.project.image})` }
        : {}
);
</script>

<style lang="scss" scoped>
.project-card {
    aspect-ratio: 3 / 2;
    border-radius: 20px;
    position: relative;
    overflow: hidden;
    text-decoration: none;
    color: inherit;
    border: 1px solid rgba(255, 255, 255, 0.1);
    transition: border-color 0.3s ease;

    &:hover {
        border-color: rgba(255, 255, 255, 0.25);

        .card-bg {
            transform: scale(1.03);
        }

        .card-arrow {
            opacity: 1;
            transform: translate(2px, -2px);
        }
    }
}

.card-bg {
    position: absolute;
    inset: 0;
    background-size: cover;
    background-position: center;
    transition: transform 0.5s ease;
}

.card-bg-pattern {
    position: absolute;
    inset: 0;
    background-color: #1a1a1a;
    background-image:
        linear-gradient(45deg, #252525 25%, transparent 25%),
        linear-gradient(-45deg, #252525 25%, transparent 25%),
        linear-gradient(45deg, transparent 75%, #252525 75%),
        linear-gradient(-45deg, transparent 75%, #252525 75%);
    background-size: 20px 20px;
    background-position: 0 0, 0 10px, 10px -10px, -10px 0px;
}

.card-content {
    position: absolute;
    inset: 0;
    padding: 2rem;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    background: linear-gradient(to top, rgba(0,0,0,0.9) 0%, rgba(0,0,0,0.4) 40%, transparent 100%);
    color: #fff;
}

.card-header {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
}

.card-num {
    font-size: 0.75rem;
    font-weight: 500;
    letter-spacing: 0.1em;
    opacity: 0.8;
    font-family: 'JetBrainsMono', monospace;
}

.card-arrow {
    font-size: 1.1rem;
    opacity: 0;
    transition: opacity 0.3s ease, transform 0.3s ease;
}

.card-info {
    transition: transform 0.3s ease;
}

.project-card:hover .card-info {
    transform: translateY(-2px);
}

.card-zh {
    font-size: 1.4rem;
    font-weight: 600;
    margin: 0 0 0.25rem;
    letter-spacing: -0.02em;
    line-height: 1.3;
}

.card-en {
    font-size: 0.75rem;
    opacity: 0.6;
    margin: 0 0 0.75rem;
    text-transform: uppercase;
    letter-spacing: 0.08em;
    font-weight: 400;
}

.card-desc {
    font-size: 0.85rem;
    opacity: 0.75;
    margin: 0;
    line-height: 1.5;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
}
</style>
