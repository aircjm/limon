<script setup lang="ts">
import { ref } from 'vue'
import {NDatePicker} from 'naive-ui';

defineProps<{ msg: string }>()

const count = ref(0)

function changeDate(value: number, formatValue: string) {
  console.log("value is "+ value);
  console.log("formatValue is "+ formatValue);
}

</script>

<template>
  <h1>{{ msg }}</h1>

  <div class="card">
    <button type="button" @click="count++">count is {{ count }}</button>
    <n-button type="primary">Test</n-button>
    <NDatePicker type="datetime" :on-update:value="changeDate" />
    <p>
      Edit
      <code>components/HelloWorld.vue</code> to test HMR
    </p>
  </div>

  <p>
    Check out
    <a href="https://vuejs.org/guide/quick-start.html#local" target="_blank"
      >create-vue</a
    >, the official Vue + Vite starter
  </p>
  <p>
    Learn more about IDE Support for Vue in the
    <a
      href="https://vuejs.org/guide/scaling-up/tooling.html#ide-support"
      target="_blank"
      >Vue Docs Scaling up Guide</a
    >.
  </p>
  <p class="read-the-docs">Click on the Vite and Vue logos to learn more</p>
</template>

<style scoped>
.read-the-docs {
  color: #888;
}
</style>
