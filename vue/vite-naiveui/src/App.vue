<template>
  <p>
    <strong>Current route path:</strong> {{ $route.fullPath }}
  </p>
  <n-button-group>
    <n-flex>
      <RouterLink to="/">Go to Home</RouterLink>
      <RouterLink to="/about">Go to About</RouterLink>
      <RouterLink to="/layout">Go to Layout</RouterLink>
      <RouterLink to="/css/flex">Go to Css Flex</RouterLink>
    </n-flex>
  </n-button-group>
  <main>
    <RouterView />
  </main>
</template>
<script setup lang="ts">
</script>
