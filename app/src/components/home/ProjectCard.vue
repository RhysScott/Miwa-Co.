<template>
    <div
        class="project-card"
        :style="{ backgroundImage: `url(${project.image})` }"
    >
        <div class="card-gradient">
            <span class="card-num">{{ padIndex }}</span>
            <div class="card-info">
                <span class="card-zh">{{ project.zh }}</span>
                <span class="card-en">{{ project.en }}</span>
                <span class="card-desc">{{ project.desc }}</span>
            </div>
        </div>
    </div>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
    project: { type: Object, required: true },
    index: { type: Number, required: true },
});

const padIndex = computed(() => `0${props.index + 1}`);
</script>

<style lang="scss" scoped>
.project-card {
    aspect-ratio: 3 / 2;
    background-size: cover;
    background-position: center;
    border-radius: 24px;
    position: relative;
    cursor: pointer;
    overflow: hidden;
    transition:
        transform 0.6s cubic-bezier(0.25, 0.1, 0.25, 1),
        box-shadow 0.6s ease,
        opacity 0.5s ease;

    .card-gradient {
        position: absolute;
        inset: 0;
        background: linear-gradient(to top, rgba(0,0,0,0.7) 0%, rgba(0,0,0,0.05) 55%, transparent 100%);
        padding: 1.5rem;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    .card-num {
        font-size: 0.75rem;
        font-weight: 600;
        letter-spacing: 0.06em;
        color: rgba(255, 255, 255, 0.55);
    }

    .card-info {
        display: flex;
        flex-direction: column;
        gap: 0.2rem;
        color: #fff;

        .card-zh {
            font-size: 1.35rem;
            font-weight: bold;
            letter-spacing: -0.01em;
            white-space: nowrap;
        }

        .card-en {
            font-size: 0.75rem;
            opacity: 0.5;
            white-space: nowrap;
        }

        .card-desc {
            font-size: 0.75rem;
            opacity: 0.35;
            margin-top: 0.35rem;
            line-height: 1.5;
        }
    }
}
</style>
