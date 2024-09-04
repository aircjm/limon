<template>
  <div>
    <p>Css Flex Use</p>
  </div>
  <n-grid x-gap="12" :cols="4">
    <n-gi>
      <div class="light-green" />
    </n-gi>
    <n-gi>
      <div class="green" />
    </n-gi>
    <n-gi>
      <div class="light-green" />
    </n-gi>
    <n-gi>
      <div class="green" />
    </n-gi>
  </n-grid>

</template>

<style scoped>
.light-green {
  height: 108px;
  background-color: rgba(0, 128, 0, 0.12);
}
.green {
  height: 108px;
  background-color: rgba(0, 128, 0, 0.24);
}
</style>
