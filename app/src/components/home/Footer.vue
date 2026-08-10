<template>
    <footer class="site-footer">
        <div class="footer-info">
            <span v-if="footer.email" class="footer-item">{{ footer.email }}</span>
            <span v-if="footer.phone" class="footer-item">{{ footer.phone }}</span>
        </div>
        <div class="footer-location">
            <span v-if="footer.address">{{ footer.address }}</span>
        </div>
    </footer>
</template>

<script setup>
defineProps({
    footer: { type: Object, required: true },
});
</script>

<style lang="scss" scoped>
.site-footer {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 2rem;
    border-top: 1px solid rgba(255, 255, 255, 0.08);
    font-size: 0.8rem;
    opacity: 0.6;

    .footer-info,
    .footer-location {
        display: flex;
        align-items: center;
        gap: 1.5rem;
    }

    .footer-item {
        font-family: 'JetBrainsMono', monospace;
        font-size: 0.75rem;
        color: #e5e5e5;
    }

    .footer-location {
        color: #e5e5e5;
    }

    @media (max-width: 480px) {
        flex-direction: column;
        align-items: flex-start;
        gap: 0.75rem;
        padding: 1.5rem;
    }
}
</style>
