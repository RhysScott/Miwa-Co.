<template>
    <div class="stat-item">
        <span class="stat-num">{{ stat.num }}</span>
        <span class="stat-label">{{ stat.label }}</span>
    </div>
</template>

<script setup>
defineProps({
    stat: { type: Object, required: true },
});
</script>

<style lang="scss" scoped>
.stat-item {
    text-align: center;
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 0.5rem;

    .stat-num {
        font-size: 4.5rem;
        font-weight: 800;
        line-height: 1;
        letter-spacing: -0.03em;
        white-space: nowrap;
        background: linear-gradient(180deg, rgba(0,0,0,0.85) 0%, rgba(0,0,0,0.45) 100%);
        background-clip: text;
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
    }

    .stat-label {
        font-size: 0.85rem;
        opacity: 0.35;
        font-weight: 500;
        white-space: nowrap;
    }

    @media (max-width: 640px) {
        .stat-num {
            font-size: 2.75rem;
        }

        .stat-label {
            font-size: 0.75rem;
        }
    }
}
</style>
