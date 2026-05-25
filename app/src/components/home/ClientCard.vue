<template>
    <div class="client-card">
        <img v-if="client.image" :src="client.image" :alt="client.name" class="client-logo" />
        <div v-else class="client-avatar">{{ client.name[0] }}</div>
        <div class="client-text">
            <span class="client-zh">{{ client.name }}</span>
            <span class="client-en">{{ client.en }}</span>
        </div>
    </div>
</template>

<script setup>
defineProps({
    client: { type: Object, required: true },
});
</script>

<style lang="scss" scoped>
.client-card {
    display: flex;
    align-items: center;
    gap: 1rem;
    padding: 1.5rem;
    border: 1px solid rgba(0, 0, 0, 0.05);
    border-radius: 20px;
    border-left: 3px solid transparent;
    transition:
        border-color 0.4s ease,
        border-left-color 0.4s ease,
        background 0.4s ease,
        transform 0.4s ease;

    &:hover {
        border-color: rgba(0, 0, 0, 0.12);
        border-left-color: #1a1a1a;
        background: rgba(0, 0, 0, 0.02);
        transform: translateX(4px);
    }

    .client-logo {
        width: 40px;
        height: 40px;
        border-radius: 10px;
        object-fit: cover;
        flex-shrink: 0;
    }

    .client-avatar {
        width: 40px;
        height: 40px;
        border-radius: 10px;
        background: rgba(0, 0, 0, 0.06);
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 1.1rem;
        font-weight: 700;
        color: rgba(0, 0, 0, 0.35);
        flex-shrink: 0;
    }

    .client-text {
        display: flex;
        flex-direction: column;
        gap: 0.15rem;
        min-width: 0;
    }

    .client-zh {
        font-size: 1.3rem;
        font-weight: bold;
        letter-spacing: -0.01em;
        white-space: nowrap;
    }

    .client-en {
        font-size: 0.65rem;
        opacity: 0.28;
        letter-spacing: 0.08em;
        text-transform: uppercase;
        white-space: nowrap;
    }

    @media (max-width: 640px) {
        padding: 1rem;
        gap: 0.75rem;

        .client-zh {
            font-size: 1.05rem;
        }

        .client-logo,
        .client-avatar {
            width: 32px;
            height: 32px;
            border-radius: 8px;
        }
    }
}
</style>
