<template>
    <div class="process-step">
        <div class="step-marker">
            <span class="step-num">{{ padIndex }}</span>
        </div>
        <div class="step-content">
            <h4 class="step-zh">{{ step.zh }}</h4>
            <p class="step-en">{{ step.en }}</p>
            <p v-if="step.desc" class="step-desc">{{ step.desc }}</p>
        </div>
    </div>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
    step: { type: Object, required: true },
    index: { type: Number, required: true },
});

const padIndex = computed(() => String(props.index + 1).padStart(2, '0'));
</script>

<style lang="scss" scoped>
.process-step {
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
    gap: 1.25rem;
    padding: 1.5rem 1rem;
    position: relative;
    flex: 1;
    min-width: 0;

    .step-marker {
        width: 48px;
        height: 48px;
        border-radius: 50%;
        background: #fff;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-shrink: 0;
        position: relative;
        z-index: 1;
        transition: transform 0.2s ease;
    }

    &:hover .step-marker {
        transform: scale(1.08);
    }

    .step-num {
        font-size: 0.85rem;
        font-weight: 600;
        color: #0a0a0a;
        font-family: 'JetBrainsMono', monospace;
    }

    .step-content {
        display: flex;
        flex-direction: column;
        gap: 0.35rem;
    }

    .step-zh {
        font-size: 1.1rem;
        font-weight: 600;
        margin: 0;
        letter-spacing: -0.01em;
        color: #fff;
    }

    .step-en {
        font-size: 0.7rem;
        opacity: 0.5;
        margin: 0;
        text-transform: uppercase;
        letter-spacing: 0.08em;
        font-weight: 500;
        color: #e5e5e5;
    }

    .step-desc {
        font-size: 0.8rem;
        opacity: 0.6;
        margin: 0.25rem 0 0;
        line-height: 1.5;
        max-width: 180px;
        color: #e5e5e5;
    }
}
</style>
