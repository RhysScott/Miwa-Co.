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

  background-repeat: no-repeat;
  background-size: cover;
  background-image: url('images/5.jpg');
}

.app-main {
  width: 100vw;
}

</style>
