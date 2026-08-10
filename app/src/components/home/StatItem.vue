<template>
    <div class="stat-item">
        <span class="stat-num">{{ stat.num }}</span>
        <span class="stat-label">{{ stat.zh }}</span>
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
        color: #fff;
    }

    .stat-label {
        font-size: 0.85rem;
        opacity: 0.5;
        font-weight: 500;
        white-space: nowrap;
        color: #e5e5e5;
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
