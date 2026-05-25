<template>
    <div class="service-card">
        <div class="card-icon-ring">
            <img v-if="service.image" :src="service.image" :alt="service.zh" class="service-img" />
            <component v-else :is="icon" :size="32" />
        </div>
        <h3 class="service-zh">{{ service.zh }}</h3>
        <p class="service-en">{{ service.en }}</p>
        <p class="service-desc">{{ service.desc }}</p>
    </div>
</template>

<script setup>
defineProps({
    service: { type: Object, required: true },
    icon: { type: Object, required: true },
});
</script>

<style lang="scss" scoped>
.service-card {
    background: #fff;
    border: 1px solid rgba(0, 0, 0, 0.06);
    border-radius: 32px;
    padding: 2.5rem 2rem;
    transition:
        transform 0.5s cubic-bezier(0.25, 0.1, 0.25, 1),
        box-shadow 0.5s ease,
        border-color 0.5s ease;

    &:hover {
        transform: translateY(-8px);
        box-shadow: 0 20px 60px rgba(0, 0, 0, 0.08);
        border-color: rgba(0, 0, 0, 0.12);
    }

    .card-icon-ring {
        width: 56px;
        height: 56px;
        border-radius: 16px;
        background: rgba(0, 0, 0, 0.04);
        display: flex;
        align-items: center;
        justify-content: center;
        margin-bottom: 2rem;
        color: rgba(0, 0, 0, 0.55);
        overflow: hidden;
        transition: background 0.5s ease, color 0.5s ease, transform 0.5s ease;
    }

    .service-img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    &:hover .card-icon-ring {
        background: rgba(0, 0, 0, 0.08);
        color: rgba(0, 0, 0, 0.85);
        transform: scale(1.05);
    }

    .service-zh {
        margin: 0 0 0.25rem;
        font-size: 1.5rem;
        font-weight: bold;
        letter-spacing: -0.01em;
        white-space: nowrap;
    }

    .service-en {
        margin: 0 0 1.25rem;
        font-size: 0.75rem;
        opacity: 0.3;
        text-transform: uppercase;
        letter-spacing: 0.08em;
        white-space: nowrap;
    }

    .service-desc {
        margin: 0;
        font-size: 0.9rem;
        opacity: 0.45;
        line-height: 1.75;
    }
}
</style>
