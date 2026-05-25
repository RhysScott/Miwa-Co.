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
import Nav from '@/components/Nav/Nav.vue';
</script>

<style lang="scss" scoped>
.app-layout {
  min-height: 100vh;
  background-color: #0d0d0d;
}

.app-main {
  width: 100vw;
}

</style>
