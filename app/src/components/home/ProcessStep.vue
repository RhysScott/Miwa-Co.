<template>
    <div class="process-step">
        <div class="step-marker">
            <span class="step-num">{{ padIndex }}</span>
        </div>
        <div class="step-text">
            <span class="step-zh">{{ step.zh }}</span>
            <span class="step-en">{{ step.en }}</span>
            <span class="step-desc">{{ step.desc }}</span>
        </div>
    </div>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
    step: { type: Object, required: true },
    index: { type: Number, required: true },
});

const padIndex = computed(() => `0${props.index + 1}`);
</script>

<style lang="scss" scoped>
.process-step {
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
    gap: 1rem;
    padding: 1.5rem 1rem;
    position: relative;
    flex: 1;
    min-width: 0;
    transition: transform 0.4s ease;

    &:hover {
        transform: translateY(-6px);
    }

    .step-marker {
        width: 48px;
        height: 48px;
        border-radius: 50%;
        background: #1a1a1a;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-shrink: 0;
        position: relative;
        z-index: 1;
        transition: transform 0.4s ease, box-shadow 0.4s ease;
    }

    &:hover .step-marker {
        transform: scale(1.15);
        box-shadow: 0 8px 24px rgba(0, 0, 0, 0.2);
    }

    .step-num {
        font-size: 0.8rem;
        font-weight: 700;
        color: rgba(255, 255, 255, 0.8);
    }

    .step-text {
        display: flex;
        flex-direction: column;
        gap: 0.3rem;
        align-items: center;
    }

    .step-zh {
        font-size: 1.3rem;
        font-weight: bold;
        letter-spacing: -0.01em;
        white-space: nowrap;
    }

    .step-en {
        font-size: 0.75rem;
        opacity: 0.3;
        letter-spacing: 0.03em;
        white-space: nowrap;
    }

    .step-desc {
        font-size: 0.8rem;
        opacity: 0.35;
        line-height: 1.6;
        margin-top: 0.25rem;
        max-width: 220px;
    }
}
</style>
