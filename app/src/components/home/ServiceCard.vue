<template>
    <div class="service-card">
        <div class="card-top">
            <div class="card-icon">
                <img v-if="service.image" :src="service.image" :alt="service.zh" class="service-img" />
                <component v-else :is="resolvedIcon" :size="28" />
            </div>
            <span class="card-arrow">→</span>
        </div>
        <div class="card-body">
            <h3 class="service-zh">{{ service.zh }}</h3>
            <p class="service-en">{{ service.en }}</p>
        </div>
        <div class="card-divider"></div>
        <p v-if="service.desc" class="service-desc">{{ service.desc }}</p>
    </div>
</template>

<script setup>
import { computed } from 'vue';
import { Globe } from '@lucide/vue';

const props = defineProps({
    service: { type: Object, required: true },
    icon: { type: [Object, null], default: null },
});

const resolvedIcon = computed(() => props.icon ?? Globe);
</script>

<style lang="scss" scoped>
.service-card {
    background: #141414;
    border-radius: 20px;
    padding: 2.5rem;
    border: 1px solid rgba(255, 255, 255, 0.08);
    transition: all 0.3s ease;
    display: flex;
    flex-direction: column;
    min-height: 280px;

    &:hover {
        border-color: rgba(255, 255, 255, 0.2);
        background: #1a1a1a;
        transform: translateY(-4px);

        .card-arrow {
            opacity: 1;
            transform: translateX(4px);
        }
    }

    .card-top {
        display: flex;
        justify-content: space-between;
        align-items: flex-start;
        margin-bottom: 2rem;
    }

    .card-icon {
        width: 56px;
        height: 56px;
        background: #fff;
        border-radius: 14px;
        display: flex;
        align-items: center;
        justify-content: center;
        color: #0a0a0a;
        overflow: hidden;
        flex-shrink: 0;
    }

    .service-img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .card-arrow {
        font-size: 1.2rem;
        opacity: 0;
        transition: opacity 0.3s ease, transform 0.3s ease;
        color: #fff;
    }

    .card-body {
        display: flex;
        flex-direction: column;
    }

    .service-zh {
        margin: 0 0 0.35rem;
        font-size: 1.5rem;
        font-weight: 700;
        letter-spacing: -0.02em;
        line-height: 1.3;
        color: #fff;
    }

    .service-en {
        margin: 0;
        font-size: 0.7rem;
        opacity: 0.45;
        text-transform: uppercase;
        letter-spacing: 0.1em;
        font-weight: 500;
        color: #e5e5e5;
    }

    .card-divider {
        height: 1px;
        background: rgba(255, 255, 255, 0.08);
        margin: 1.5rem 0;
    }

    .service-desc {
        margin: 0;
        font-size: 0.9rem;
        opacity: 0.6;
        line-height: 1.7;
        color: #e5e5e5;
    }
}
</style>
