<template>
    <footer class="site-footer">
        <span>{{ footer.copyright }}</span>
        <span>{{ footer.location }}</span>
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
    padding: 2rem;
    border-top: 1px solid rgba(0, 0, 0, 0.05);
    font-size: 0.8rem;
    opacity: 0.25;
}
</style>
