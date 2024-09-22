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
  <div>
  <main class="w-full h-auto flex-grow shrink flex flex-col justify-start items-center">
      <RouterView/>
  </main>
  </div>

</template>
<script setup lang="ts">
</script>
