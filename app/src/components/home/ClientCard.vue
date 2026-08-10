<template>
    <div class="client-card">
        <div class="client-logo-wrap">
            <img v-if="client.image" :src="client.image" :alt="client.zh" class="client-logo" />
            <div v-else class="client-avatar">
                <span class="avatar-text">{{ client.zh?.[0] || '?' }}</span>
            </div>
        </div>
        <div class="client-text">
            <span class="client-name">{{ client.zh }}</span>
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
    padding: 1rem 1.25rem;
    background: #141414;
    border: 1px solid rgba(255, 255, 255, 0.08);
    border-radius: 16px;
    transition: all 0.2s ease;

    &:hover {
        border-color: rgba(255, 255, 255, 0.2);
        background: #1a1a1a;
    }

    .client-logo-wrap {
        flex-shrink: 0;
    }

    .client-logo {
        width: 40px;
        height: 40px;
        object-fit: contain;
        filter: brightness(0) invert(1);
        opacity: 0.7;
        transition: opacity 0.2s ease;
    }

    &:hover .client-logo {
        opacity: 1;
    }

    .client-avatar {
        width: 40px;
        height: 40px;
        background: #fff;
        border-radius: 10px;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-shrink: 0;
    }

    .avatar-text {
        font-size: 1rem;
        font-weight: 600;
        color: #0a0a0a;
        font-family: 'JetBrainsMono', monospace;
    }

    .client-text {
        min-width: 0;
        flex: 1;
    }

    .client-name {
        font-size: 0.95rem;
        font-weight: 500;
        color: #e5e5e5;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }
}
</style>
