<template>
    <div class="loading-spinner">
        <span class="spinner" />
        <p v-if="text" class="spinner-text">{{ text }}</p>
    </div>
</template>

<script setup>
defineProps({
    text: { type: String, default: '' },
});
</script>

<style lang="scss" scoped>
.loading-spinner {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    gap: 1rem;
    padding: 6rem 2rem;
}

.spinner {
    width: 28px;
    height: 28px;
    border: 2px solid rgba(0, 0, 0, 0.08);
    border-top-color: rgba(0, 0, 0, 0.3);
    border-radius: 50%;
    animation: spin 0.7s linear infinite;
}

@keyframes spin {
    to { transform: rotate(360deg); }
}

.spinner-text {
    font-size: 0.8rem;
    opacity: 0.3;
    margin: 0;
}
</style>
