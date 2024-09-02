<template>
  <h1>Hello App!</h1>
  <p>
    <strong>Current route path:</strong> {{ $route.fullPath }}
  </p>
  <n-button-group>
    <n-flex>
      <RouterLink to="/">Go to Home</RouterLink>
      <RouterLink to="/about">Go to About</RouterLink>
      <RouterLink to="/layout">Go to Layout</RouterLink>
    </n-flex>
  </n-button-group>
  <main>
    <RouterView />
  </main>
</template>
<script setup lang="ts">
</script>
