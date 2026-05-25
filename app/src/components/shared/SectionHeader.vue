<template>
    <div class="section-header font-error">
        <span class="label-zh">{{ zh }}</span>
        <span class="label-en">{{ en }}</span>
    </div>
</template>

<script setup>
defineProps({
    zh: { type: String, required: true },
    en: { type: String, required: true },
});
</script>

<style lang="scss" scoped>
.section-header {
    margin-bottom: 2rem;

    .label-zh {
        font-size: 0.75rem;
        opacity: 0.3;
        display: block;
        text-transform: uppercase;
        letter-spacing: 0.15em;
        margin-bottom: 0.35rem;
    }

    .label-en {
        font-size: 2.75rem;
        font-weight: bold;
        letter-spacing: -0.02em;
        white-space: nowrap;
    }

    @media (max-width: 640px) {
        margin-bottom: 1.5rem;

        .label-en {
            font-size: 1.75rem;
        }
    }
}
</style>
