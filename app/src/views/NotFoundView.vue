<template>
    <div class="not-found">
        <div class="not-found-content font-error">
            <span class="not-found-num">404</span>
            <h1>页面不存在</h1>
            <p class="en">Page Not Found</p>
            <router-link to="/" class="back-home">返回首页 →</router-link>
        </div>
    </div>
</template>

<style lang="scss" scoped>
.not-found {
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;
    padding: 2rem;
    color: #1a1a1a;
    background: #f8f8f8;
}

.not-found-content {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 0.75rem;
}

.not-found-num {
    font-size: 8rem;
    font-weight: bold;
    opacity: 0.06;
    line-height: 1;
}

h1 {
    font-size: 2rem;
    font-weight: bold;
    margin: 0;
    letter-spacing: -0.02em;
}

.en {
    font-size: 0.9rem;
    opacity: 0.3;
    margin: 0 0 1.5rem;
}

.back-home {
    font-size: 0.95rem;
    color: #1a1a1a;
    text-decoration: none;
    opacity: 0.4;
    transition: opacity 0.3s ease;

    &:hover {
        opacity: 0.7;
    }
}
</style>
