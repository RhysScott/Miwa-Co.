<template>
  <div class="app-layout">
    <div class="app-header">
      <Nav />
    </div>

    <div class="app-main">
      <RouterView />
    </div>
  </div>
</template>

<script setup>
import Nav from '@/components/Nav.vue';
</script>

<style lang="scss" scoped>
.app-layout {
  min-height: 100vh;
  background-color: #0a0a0a;
}

.app-main {
  width: 100vw;
}

</style>
